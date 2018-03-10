/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.AcuidadeVisual;
import br.com.markConsult.entidades.Convenio;
import br.com.markConsult.entidades.Empresa;
import br.com.markConsult.entidades.Funcao;
import br.com.markConsult.entidades.Paciente;
import br.com.markConsult.entidades.Sessao;
import br.com.markConsult.relatorios.ReportUtils;
import java.io.InputStream;
import java.sql.Connection;
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
public class CadAcuidadeVisualDAO extends AbstractConecxaoDAO {
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
	 * @uml.property  name="AcuidadeVisual"
	 * @uml.associationEnd  
	 */
	AcuidadeVisual acuidadeVisual = null;
	/**
	 * @uml.property  name="acuidadeVisual"
	 */
	private final List<AcuidadeVisual> acuidadesVisuais = new ArrayList<>();
        
              public Integer inserir(AcuidadeVisual acuidadeVisual) {
    
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "INSERT INTO acuidade_visual (id_paciente, tipo_exame, olho_direito_sem_correcao, olho_esquerdo_sem_correcao, olho_direito_com_correcao, olho_esquerdo_com_correcao,"
                                + "   vermelho, amarelo, verde, conclusao, observacao, data_acuidade, id_empresa, id_convenio, id_funcao )VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

			// criar o statement
			 pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// setar os params
			int index = 0;
			pstm.setInt(++index, acuidadeVisual.getPaciente().getId());
			pstm.setString(++index, acuidadeVisual.getTipoExame());
                        
                        if(acuidadeVisual.getOlhoDireitoSemCorrecao() == null){
                        pstm.setNull(++index, java.sql.Types.INTEGER );
                        }else{
                        pstm.setInt(++index, acuidadeVisual.getOlhoDireitoSemCorrecao());
                        }
                        if (acuidadeVisual.getOlhoEsquerdoSemCorrecao() == null) {
                            pstm.setNull(++index, java.sql.Types.INTEGER);
                        } else {
                           pstm.setInt(++index, acuidadeVisual.getOlhoEsquerdoSemCorrecao());
                        }

                        if(acuidadeVisual.getOlhoDireitoComCorrecao() == null){
                        pstm.setNull(++index, java.sql.Types.INTEGER );
                        }else{
                        pstm.setInt(++index, acuidadeVisual.getOlhoDireitoComCorrecao());
                        }
                        if (acuidadeVisual.getOlhoEsquerdoComCorrecao() == null) {
                            pstm.setNull(++index, java.sql.Types.INTEGER);
                        } else {
                           pstm.setInt(++index, acuidadeVisual.getOlhoEsquerdoComCorrecao());
                        }
                        pstm.setBoolean(++index, acuidadeVisual.isVermelho());
                        pstm.setBoolean(++index, acuidadeVisual.isAmarelo());
                        pstm.setBoolean(++index, acuidadeVisual.isVerde());
                        pstm.setString(++index, acuidadeVisual.getConclusao());
                        pstm.setString(++index, acuidadeVisual.getObservacao());
                        pstm.setDate(++index, acuidadeVisual.getData());
                        if (acuidadeVisual.getPaciente().getEmpresa().getId() == null) {
                            pstm.setNull(++index, java.sql.Types.INTEGER);
                        } else {
                           pstm.setInt(++index, acuidadeVisual.getPaciente().getEmpresa().getId());
                        }
                        if (acuidadeVisual.getPaciente().getConvenio().getId() == null) {
                            pstm.setNull(++index, java.sql.Types.INTEGER);
                        } else {
                           pstm.setInt(++index, acuidadeVisual.getPaciente().getConvenio().getId());
                        }
                        if (acuidadeVisual.getPaciente().getFuncao().getId() == null) {
                            pstm.setNull(++index, java.sql.Types.INTEGER);
                        } else {
                           pstm.setInt(++index, acuidadeVisual.getPaciente().getFuncao().getId());
                        }
                        
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

         public boolean alterar(AcuidadeVisual acuidadeVisual){
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
                    // pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "UPDATE acuidade_visual SET tipo_exame = ?, olho_direito_sem_correcao = ?, olho_esquerdo_sem_correcao = ?, olho_direito_com_correcao = ?, olho_esquerdo_com_correcao = ?, "
                                + "vermelho = ?, amarelo = ?, verde = ?, conclusao = ?, observacao = ?, id_empresa = ?, id_convenio = ?, id_funcao = ? WHERE id = ?";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			// setar os params
			int index = 0;
			pstm.setString(++index, acuidadeVisual.getTipoExame());
                        
                        if(acuidadeVisual.getOlhoDireitoSemCorrecao() == null){
                        pstm.setNull(++index, java.sql.Types.INTEGER );
                        }else{
                        pstm.setInt(++index, acuidadeVisual.getOlhoDireitoSemCorrecao());
                        }
                        if (acuidadeVisual.getOlhoEsquerdoSemCorrecao() == null) {
                            pstm.setNull(++index, java.sql.Types.INTEGER);
                        } else {
                           pstm.setInt(++index, acuidadeVisual.getOlhoEsquerdoSemCorrecao());
                        }

                        if(acuidadeVisual.getOlhoDireitoComCorrecao() == null){
                        pstm.setNull(++index, java.sql.Types.INTEGER );
                        }else{
                        pstm.setInt(++index, acuidadeVisual.getOlhoDireitoComCorrecao());
                        }
                        if (acuidadeVisual.getOlhoEsquerdoComCorrecao() == null) {
                            pstm.setNull(++index, java.sql.Types.INTEGER);
                        } else {
                           pstm.setInt(++index, acuidadeVisual.getOlhoEsquerdoComCorrecao());
                        }
                        
                        pstm.setBoolean(++index, acuidadeVisual.isVermelho());
                        pstm.setBoolean(++index, acuidadeVisual.isAmarelo());
                        pstm.setBoolean(++index, acuidadeVisual.isVerde());
                        pstm.setString(++index, acuidadeVisual.getConclusao());
                        pstm.setString(++index, acuidadeVisual.getObservacao());
                        
                        if (acuidadeVisual.getPaciente().getEmpresa().getId() == null) {
                            pstm.setNull(++index, java.sql.Types.INTEGER);
                        } else {
                           pstm.setInt(++index, acuidadeVisual.getPaciente().getEmpresa().getId());
                        }
                        if (acuidadeVisual.getPaciente().getConvenio().getId() == null) {
                            pstm.setNull(++index, java.sql.Types.INTEGER);
                        } else {
                           pstm.setInt(++index, acuidadeVisual.getPaciente().getConvenio().getId());
                        }
                        if (acuidadeVisual.getPaciente().getFuncao().getId() == null) {
                            pstm.setNull(++index, java.sql.Types.INTEGER);
                        } else {
                           pstm.setInt(++index, acuidadeVisual.getPaciente().getFuncao().getId());
                        }
                        pstm.setInt(++index, acuidadeVisual.getId());

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
			
			
			String sql = "delete from acuidade_visual where id = ?";

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

        public AcuidadeVisual procuraPorID(Integer id) {
                AcuidadeVisual con = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT av.*,p.nome, emp.fantasia, convenios.ds_convenio, fun.nome AS nome_funcao "
                                + "FROM acuidade_visual av "
                                + "LEFT JOIN pacientes AS p ON p.id = av.id_paciente "
                                + "LEFT JOIN empresa emp ON emp.id = av.id_empresa "
                                + "LEFT JOIN convenios ON convenios.id = av.id_convenio "
                                + "LEFT JOIN funcao fun ON fun.id = av.id_funcao "
                                + "WHERE av.id = ?";

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

        public List<AcuidadeVisual> BuscaAcuidadeVisuals(String nome, char tipo) {
		AcuidadeVisual con ;
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
				sql = "SELECT av.*,p.nome, emp.fantasia, convenios.ds_convenio, fun.nome AS nome_funcao "
                                + "FROM acuidade_visual av "
                                + "LEFT JOIN pacientes AS p ON p.id = av.id_paciente "
                                + "LEFT JOIN empresa emp ON emp.id = av.id_empresa "
                                + "LEFT JOIN convenios ON convenios.id = av.id_convenio "
                                + "LEFT JOIN funcao fun ON fun.id = av.id_funcao "
                                + "WHERE p.nome like ('%"+nome+"%') ORDER BY id";
				break;
			case 'i':
				sql = "SELECT av.*,p.nome, emp.fantasia, convenios.ds_convenio, fun.nome AS nome_funcao "
                                + "FROM acuidade_visual av "
                                + "LEFT JOIN pacientes AS p ON p.id = av.id_paciente "
                                + "LEFT JOIN empresa emp ON emp.id = av.id_empresa "
                                + "LEFT JOIN convenios ON convenios.id = av.id_convenio "
                                + "LEFT JOIN funcao fun ON fun.id = av.id_funcao "
                                + "WHERE emp.fantasia like ('%"+nome+"%') ORDER BY id";
				break;
			case 't':
				sql = "SELECT av.*,p.nome, emp.fantasia, convenios.ds_convenio, fun.nome AS nome_funcao "
                                + "FROM acuidade_visual av "
                                + "LEFT JOIN pacientes AS p ON p.id = av.id_paciente "
                                + "LEFT JOIN empresa emp ON emp.id = av.id_empresa "
                                + "LEFT JOIN convenios ON convenios.id = av.id_convenio "
                                + "LEFT JOIN funcao fun ON fun.id = av.id_funcao "
                                + "WHERE av.id =('"+nome+"') ORDER BY id";
				break;
			
			default:
				break;
			}
			
			// criar o statement
			stm = connection.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
            
			con = retornObjeto(rs);       
                        acuidadesVisuais.add(con);
                     
                        
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
		return acuidadesVisuais;  
    }
    
            public AcuidadeVisual mostrarUltimo(){
        
                AcuidadeVisual conv = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
                       
			// CRIAR SQL
			String sql = "SELECT av.*,p.nome, emp.fantasia, convenios.ds_convenio, fun.nome AS nome_funcao "
                                + "FROM acuidade_visual av "
                                + "LEFT JOIN pacientes AS p ON p.id = av.id_paciente "
                                + "LEFT JOIN empresa emp ON emp.id = av.id_empresa "
                                + "LEFT JOIN convenios ON convenios.id = av.id_convenio "
                                + "LEFT JOIN funcao fun ON fun.id = av.id_funcao "
                                + "WHERE av.id = (SELECT MAX(id) AS maxID FROM acuidade_visual)";// (SELECT MAX(id) AS maxID FROM acuidade_visual)";

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
    
        
            public AcuidadeVisual mostrarPrimeiro(){
            AcuidadeVisual conv = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
                        
			// CRIAR SQL
			String sql = "SELECT av.*,p.nome, emp.fantasia, convenios.ds_convenio, fun.nome AS nome_funcao "
                                + "FROM acuidade_visual av "
                                + "LEFT JOIN pacientes AS p ON p.id = av.id_paciente "
                                + "LEFT JOIN empresa emp ON emp.id = av.id_empresa "
                                + "LEFT JOIN convenios ON convenios.id = av.id_convenio "
                                + "LEFT JOIN funcao fun ON fun.id = av.id_funcao "
                                + "WHERE av.id = (SELECT MIN(id) AS minID FROM acuidade_visual)";

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
    
    
            public AcuidadeVisual mostrarProximo(int id){
                AcuidadeVisual conv = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT av.*,p.nome, emp.fantasia, convenios.ds_convenio, convenios.ds_convenio, fun.nome AS nome_funcao "
                                + "FROM acuidade_visual av "
                                + "LEFT JOIN pacientes AS p ON p.id = av.id_paciente "
                                + "LEFT JOIN empresa emp ON emp.id = av.id_empresa "
                                + "LEFT JOIN convenios ON convenios.id = av.id_convenio "
                                + "LEFT JOIN funcao fun ON fun.id = av.id_funcao "
                                + "WHERE av.id > '"+id+"' ORDER BY id limit 1";

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
        
            
                 public AcuidadeVisual mostrarAnterior(int id){
                AcuidadeVisual con = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT av.*,p.nome, emp.fantasia, convenios.ds_convenio, convenios.ds_convenio, fun.nome AS nome_funcao "
                                + "FROM acuidade_visual av "
                                + "LEFT JOIN pacientes AS p ON p.id = av.id_paciente "
                                + "LEFT JOIN empresa emp ON emp.id = av.id_empresa "
                                + "LEFT JOIN convenios ON convenios.id = av.id_convenio "
                                + "LEFT JOIN funcao fun ON fun.id = av.id_funcao "
                                + "WHERE av.id < '"+id+"' ORDER BY id desc limit 1";

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
                 
          public AcuidadeVisual retornObjeto(ResultSet rs) throws SQLException{
              AcuidadeVisual acuidadeV = null;
              if (rs != null) {
                  Empresa empresa = new Empresa(rs.getInt("id_empresa"), rs.getString("fantasia"));
                  Convenio conv = new Convenio();
                  conv.setId(rs.getInt("id_convenio"));
                  conv.setDsConvenio(rs.getString("ds_convenio"));
                  Funcao funcao = new Funcao(rs.getInt("id_funcao"), rs.getString("nome_funcao"));
                  Paciente paciente = new Paciente(rs.getInt("id_paciente"), rs.getString("nome"), empresa, funcao, conv);
                  AcuidadeVisual av = new AcuidadeVisual(rs.getInt("id"),paciente, rs.getString("tipo_exame"), 
                          rs.getInt("olho_direito_sem_correcao"), rs.getInt("olho_esquerdo_sem_correcao"), 
                          rs.getInt("olho_direito_com_correcao"), rs.getInt("olho_esquerdo_com_correcao"), 
                          rs.getBoolean("vermelho"), rs.getBoolean("amarelo"), rs.getBoolean("verde"), rs.getString("conclusao"), rs.getString("observacao"),rs.getDate("data_acuidade"));
                  acuidadeV = av;
              }
              
            return acuidadeV;
          }
     
    public void imprimirAcuidade(int idAcuidade){
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
                        InputStream 
                          inputStream = getClass().getResourceAsStream("/acuidade.jasper");
                         
			
            
           
                // mapa de parâmetros do relatório
                Map parametros = new HashMap();
                int c = Sessao.getInstance().getClinica().getId();
                parametros.put("idAcuidade", idAcuidade);
                parametros.put("id_empresa", c);
                    // abre o relatório

                    ReportUtils.openReport("Relatório de consultas", inputStream, parametros, connection);                    
                    

		} catch (Exception e) {
			try {
                            System.out.println(e.getMessage());
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, stm, connection);
		}  
     }
                
}
