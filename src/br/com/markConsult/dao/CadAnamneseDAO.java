/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Anamnese;
import br.com.markConsult.entidades.Paciente;
import br.com.markConsult.relatorios.ReportUtilsModal;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jeferson
 */
public class CadAnamneseDAO extends AbstractConecxaoDAO {
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
	 * @uml.property  name="Anaminese"
	 * @uml.associationEnd  
	 */
	Anamnese anamnese = null;
	/**
	 * @uml.property  name="anamnese"
	 */
	private final List<Anamnese> anamneses = new ArrayList<>();
        
              public Integer inserir(Anamnese anamnese) {
    
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "INSERT INTO anamneses (id_paciente, data_cadastro, descricao) VALUES (?, ?, ?)";

			// criar o statement
			 pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// setar os params
			int index = 0;
			pstm.setInt(++index, anamnese.getPaciente().getId());
			pstm.setDate(++index, anamnese.getDataCadastro());
			pstm.setString(++index, anamnese.getDescricao());
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

         public boolean alterar(Anamnese anamnese){
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
                    // pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "UPDATE anamneses SET descricao = ? WHERE id = ?";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			// setar os params
			int index = 0;
			pstm.setString(++index, anamnese.getDescricao());
                        pstm.setInt(++index, anamnese.getId());

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
			
			
			String sql = "delete from anamneses where id = ?";

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

        public Anamnese procuraPorID(Integer id) {
       
                Anamnese con = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT * FROM anamneses WHERE id = ?";

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
        public Anamnese procuraPorData(Date data) {
       
                Anamnese con = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT * FROM anamneses WHERE data_cadastro = ?";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			pstm.setDate(1, data);
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
        
        public List<Anamnese> datasAnamneses(int idPaciente) {
                Anamnese con ;
                Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// CRIAR SQL
			String sql = "SELECT * FROM anamneses WHERE id_paciente = '"+idPaciente+"' ORDER BY  id ASC";
			
			// criar o statement
			stm = connection.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
            
			con = retornObjeto(rs);
                        anamneses.add(con);
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
		return anamneses;  
        }
                 
          public Anamnese retornObjeto(ResultSet rs) throws SQLException{
              Anamnese ana = null;    
                      if (rs != null) {
                  
                  Paciente cli = new Paciente(rs.getInt("id_paciente"),null,null);
                  Anamnese con = new Anamnese(rs.getInt("id"), cli, rs.getDate("data_cadastro"), rs.getString("descricao"));
                  ana = con;
              }
              
            return ana;
          }
           public void ConectRelatorio(int id, int id_usu, int id_empresa){
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			InputStream inputStream = getClass().getResourceAsStream("/fixa.jasper");

                // mapa de parâmetros do relatório
                Map parametros = new HashMap();
                parametros.put("id_usu", id_usu);
                parametros.put("id_fixa", id);
                parametros.put("id_empresa", id_empresa);
               
                    // abre o relatório
                    ReportUtilsModal.openReport("Fixa de Paciente ", inputStream, parametros, connection);                    
			

		} catch (Exception e) {
			try {
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, stm, connection);
		}  
        }
                
}
