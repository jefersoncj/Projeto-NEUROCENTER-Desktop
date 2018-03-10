/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Procedimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeferson
 */
public class CadProcedimentoDAO extends AbstractConecxaoDAO {
    /**
	 * @uml.property  name="idInserido"
	 */
	Integer idInserido = null;
        Integer idInserido2 = null;
	
	/**
	 * @uml.property  name="idInserido"
	 */
	boolean idAlterado = false;
	


	/**
	 * @uml.property  name="Procedimento"
	 * @uml.associationEnd  
	 */
	Procedimento procedimento = null;
	/**
	 * @uml.property  name="procedimento"
	 */
	private final List<Procedimento> procedimentos = new ArrayList<>();
              public Integer inserir(Procedimento procedimento) {
    
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// GERAR O ID UNICO

			// criar o sql
			String sql = "INSERT INTO procedimento (ds_procedimento) VALUES ( ?)";

			// criar o statement
                        pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// setar os params
			int index = 0;
			pstm.setString(++index, procedimento.getDsProcedimento());
			pstm.executeUpdate();
                        rs = pstm.getGeneratedKeys();
                        int id = 0;
                        if (rs.next()) {
                            id = rs.getInt(1);
                        }
			commitTransaction(connection);
			idInserido = id;
			
		} catch (Exception e) {
			
			try {
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, pstm, connection);
		}
		return idInserido;
		   
    
    }

         public boolean alterar(Procedimento procedimento){
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
                    // pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "UPDATE procedimento SET ds_procedimento = ? WHERE id = ?";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			// setar os params
			int index = 0;
			pstm.setString(++index, procedimento.getDsProcedimento());
                        pstm.setInt(++index, procedimento.getId());

			// executar
			pstm.executeUpdate();

			commitTransaction(connection);
			idAlterado = true;
			
		} catch (Exception e) {
			idAlterado = false;
			try {
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, pstm, connection);
		}
		return idAlterado;
    
    
    }

        public boolean remover(Integer id) {
                boolean excluido = false;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			
			
			String sql = "delete from procedimento where id = ?";

			pstm = connection.prepareStatement(sql);	
			pstm.setInt(1, id);
			pstm.execute();
			commitTransaction(connection);
                        excluido = true;
		} catch (Exception e) {
			try {
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, pstm, connection);
		}
		return excluido;  
         }

        public Procedimento procuraPorID(Integer id) {
       
                Procedimento con = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT * FROM procedimento WHERE id = ?";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
	
			con = retornObjeto(rs);
			}
		} catch (Exception e) {
			try {
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, pstm, connection);
		}
		return con;
    
    }

        public List<Procedimento> BuscaProcedimentos(String nome, char ds_procedimento) {
		Procedimento con ;
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// CRIAR SQL
			String sql="";
			switch (ds_procedimento) {
			case 'e':
				sql = "SELECT * FROM procedimento WHERE ds_procedimento like ('%"+nome+"%') ORDER BY id";
				break;
			case 'i':
				sql = "SELECT * FROM procedimento WHERE id =('"+nome+"') ORDER BY id";
				break;
			
			default:
				break;
			}
			
			// criar o statement
			stm = connection.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
            
			con = retornObjeto(rs);       
                        procedimentos.add(con);
                     
                        
			}

		} catch (Exception e) {
			try {
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, stm, connection);
		}
		return procedimentos;  
    }
    
            public Procedimento mostrarUltimo(){
        
                Procedimento conv = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
                        
			// CRIAR SQL
			String sql = "SELECT * FROM procedimento WHERE id = (SELECT MAX(id) AS maxID FROM procedimento)";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
		
			conv = retornObjeto(rs);
			}
		} catch (Exception e) {
			try {
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, pstm, connection);
		}
		return conv;
        }
    
        
            public Procedimento mostrarPrimeiro(){
            Procedimento conv = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
                    
                        
			// CRIAR SQL
			String sql = "SELECT * FROM procedimento WHERE id = (SELECT MIN(id) AS minID FROM procedimento)";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
	
			conv = retornObjeto(rs);
			}
		} catch (Exception e) {
			try {
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, pstm, connection);
		}
		return conv;
    }
    
    
            public Procedimento mostrarProximo(int id){
                Procedimento conv = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT * FROM procedimento WHERE procedimento.id > '"+id+"' ORDER BY id limit 1";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			//pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			conv = retornObjeto(rs);
			}
		} catch (Exception e) {
			try {
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, pstm, connection);
		}
		return conv;
    }
        
            
                 public Procedimento mostrarAnterior(int id){
                Procedimento con = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT * FROM procedimento WHERE procedimento.id < '"+id+"' ORDER BY id desc limit 1";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			//pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			con = retornObjeto(rs);
			}
		} catch (Exception e) {
			try {
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, pstm, connection);
		}
		return con;
         }
                 
          public Procedimento retornObjeto(ResultSet rs) throws SQLException{
              Procedimento conv = null;
              if (rs != null) {
                  
                  Procedimento con = new Procedimento(rs.getInt("id"), rs.getString("ds_procedimento"));
                  conv = con;
              }
              
            return conv;
          }    
                
}
