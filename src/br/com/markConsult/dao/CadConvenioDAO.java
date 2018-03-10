/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Convenio;
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
public class CadConvenioDAO extends AbstractConecxaoDAO {
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
	 * @uml.property  name="Convenio"
	 * @uml.associationEnd  
	 */
	Convenio convenio = null;
	/**
	 * @uml.property  name="convenio"
	 */
	private final List<Convenio> convenios = new ArrayList<>();
        
              public Integer inserir(Convenio convenio) {
    
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "INSERT INTO convenios (ds_convenio, valor)VALUES (?, ?)";

			// criar o statement
			 pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// setar os params
			int index = 0;
			pstm.setString(++index, convenio.getDsConvenio());
			pstm.setDouble(++index, convenio.getValorConv());
                        
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

         public boolean alterar(Convenio convenio){
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
                    // pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "UPDATE convenios SET ds_convenio = ?, valor = ? WHERE id = ?";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			// setar os params
			int index = 0;
			pstm.setString(++index, convenio.getDsConvenio());
			pstm.setDouble(++index, convenio.getValorConv());
                        pstm.setInt(++index, convenio.getId());

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
			
			
			String sql = "delete from convenios where id = ?";

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

        public Convenio procuraPorID(Integer id) {
       
                Convenio con = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT * FROM convenios WHERE id = ?";

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

        public List<Convenio> BuscaConvenios(String nome, char tipo) {
		Convenio con ;
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// CRIAR SQL
			String sql="";
			switch (tipo) {
			case 'e':
				sql = "SELECT * FROM convenios WHERE ds_convenio like ('%"+nome+"%') ORDER BY id";
				break;
			case 'i':
				sql = "SELECT * FROM convenios WHERE id =('"+nome+"') ORDER BY id";
				break;
			
			default:
				break;
			}
			
			// criar o statement
			stm = connection.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
            
			con = retornObjeto(rs);       
                        convenios.add(con);
                     
                        
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
		return convenios;  
    }
    
            public Convenio mostrarUltimo(){
        
                Convenio conv = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
                       
			// CRIAR SQL
			String sql = "SELECT * FROM convenios WHERE id = (SELECT MAX(id) AS maxID FROM convenios)";

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
    
        
            public Convenio mostrarPrimeiro(){
            Convenio conv = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
                        
			// CRIAR SQL
			String sql = "SELECT * FROM convenios WHERE id = (SELECT MIN(id) AS minID FROM convenios)";

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
    
    
            public Convenio mostrarProximo(int id){
                Convenio conv = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT * FROM convenios WHERE convenios.id > '"+id+"' ORDER BY id limit 1";

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
        
            
                 public Convenio mostrarAnterior(int id){
                Convenio con = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT * FROM convenios WHERE convenios.id < '"+id+"' ORDER BY id desc limit 1";

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
                 
          public Convenio retornObjeto(ResultSet rs) throws SQLException{
              Convenio conv = null;
              if (rs != null) {
                  
                  Convenio con = new Convenio(rs.getInt("id"), rs.getString("ds_convenio"), rs.getDouble("valor"));
                  conv = con;
              }
              
            return conv;
          }
     
                
}
