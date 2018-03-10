/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.markConsult.dao;

import br.com.markConsult.entidades.Sessao;
import br.com.markConsult.entidades.CondPagto;
import br.com.markConsult.entidades.Consulta;
import br.com.markConsult.entidades.ConsultaProcedimento;
import br.com.markConsult.entidades.Convenio;
import br.com.markConsult.entidades.Empresa;
import br.com.markConsult.entidades.EmpresaProcedimento;
import br.com.markConsult.entidades.Funcao;
import br.com.markConsult.entidades.Paciente;
import br.com.markConsult.entidades.Procedimento;
import br.com.markConsult.entidades.Tipo;
import br.com.markConsult.entidades.Usuario;
import br.com.markConsult.relatorios.ReportUtils;
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
public class CadConsultasDAO extends AbstractConecxaoDAO implements ICadConsultasDAO{
    /**
	 * @uml.property  name="idInserido"
	 */
	Integer idInserido = null;
	/**
	 * @uml.property  name="consulta"
	 * @uml.associationEnd  
	 */
	Consulta consulta = null;
        boolean idAlterado;
	/**
	 * @uml.property  name="consultas"
	 */
	

    public CadConsultasDAO() {
        this.idAlterado = false;
    }
    private final ArrayList<Consulta> consultas = new ArrayList<>();
    @Override
    public Integer inseConsult(Consulta consulta) {
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// GERAR O ID UNICO
			
                        String selectMaxSEQ = "SELECT MAX(sequencia) AS maxSEQ FROM consultas WHERE data_consulta = '"+consulta.getDataConsulta()+"' ";
                        int maxSEQ = 0;
                        
                        pstm = connection.prepareStatement(selectMaxSEQ);
			rs = pstm.executeQuery();
			while (rs.next()) {
				maxSEQ = rs.getInt(1);
			}
                        
                        String  sql = "INSERT INTO consultas (id_convenio, id_paciente, data_consulta, status, sequencia, "
                                 + "obs, valor, id_cond_pagto, id_medico, cod_tipo, prioritario, cod_autorizacao, id_empresa, id_funcao)"
                                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                         pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                         
                            int index = 0;
                            pstm.setInt(++index,consulta.getConvenio().getId());
                            pstm.setInt(++index, consulta.getPaciente().getId());
                            pstm.setDate(++index, consulta.getDataConsulta());
                            pstm.setInt(++index, consulta.getStatus());
                            pstm.setInt(++index, ++maxSEQ);
                            pstm.setString(++index, consulta.getObs());
                            pstm.setDouble(++index, consulta.getValor());
                            pstm.setInt(++index, consulta.getCondPagt().getId());
                            pstm.setInt(++index, consulta.getUsuario().getId());
                            pstm.setInt(++index, consulta.getTipo().getId());
                            pstm.setBoolean(++index, consulta.isPrioritario());
                            pstm.setString(++index, consulta.getCodAutorizacao());
                            
                            if(consulta.getPaciente().getEmpresa().getId() == null){
                              pstm.setNull(++index, java.sql.Types.INTEGER );
                            }else{
                            pstm.setInt(++index,consulta.getPaciente().getEmpresa().getId());
                            }                
                            if (consulta.getPaciente().getFuncao().getId() == null) {
                            pstm.setNull(++index, java.sql.Types.INTEGER);
                            } else {
                               pstm.setInt(++index, consulta.getPaciente().getFuncao().getId());
                            }
			
			// executar
			pstm.executeUpdate();
                        rs = pstm.getGeneratedKeys();  
                        int id = 0;  
                        if(rs.next()){  
                         id = rs.getInt(1);  
                        }
			commitTransaction(connection);
			idInserido = id;
                      
			inserirProcedimentosConsulta(consulta.getConsultaProcedimento(), id);
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
    
     public Integer inserirProcedimentosConsulta( List<ConsultaProcedimento> consultaProceidmento, int idConsulta) {
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// GERAR O ID UNICO
                    for (ConsultaProcedimento consultaProcedimento : consultaProceidmento) {
                        if(consultaProcedimento.getId() == null){
                            String  sql = "INSERT INTO procedimento_consulta (id_consulta, id_procedimento_empresa, valor, status)"
                                     + "VALUES (?, ?, ?, ?)";
                                pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                                int index = 0;
                                pstm.setInt(++index,idConsulta);
                                pstm.setInt(++index, consultaProcedimento.getEmpresaProcedimento().getId());
                                pstm.setDouble(++index, consultaProcedimento.getValor());
                                pstm.setString(++index, consultaProcedimento.getStatus());
                            // executar
                            pstm.executeUpdate();   
                         }
                    }

			commitTransaction(connection);
			
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
  
     @Override
    public boolean altConsult(Consulta consulta) {
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
            // pegar a connection
                connection = getConnection();
                beginTransaction(connection);
              
                        // criar o sql
                String sql = "UPDATE consultas SET id_convenio = ? , id_paciente = ?, obs = ?, data_consulta = ?, "
                        + "valor = ?, id_cond_pagto = ? , id_medico = ?, status = ?, cod_tipo = ?, fila = ?, prioritario = ?, cod_autorizacao = ?, id_empresa = ?, id_funcao = ? WHERE id = ?"; 
                         pstm = connection.prepareStatement(sql);
                          // setar os params      
                        int index = 0;
                        pstm.setInt(++index, consulta.getConvenio().getId());
                        pstm.setInt(++index, consulta.getPaciente().getId());
                        pstm.setString(++index, consulta.getObs());
                        pstm.setDate(++index, consulta.getDataConsulta());
                        pstm.setDouble(++index, consulta.getValor());
                        pstm.setInt(++index, consulta.getCondPagt().getId());
                        pstm.setInt(++index, consulta.getUsuario().getId());
                        pstm.setInt(++index, consulta.getStatus());
                        pstm.setInt(++index, consulta.getTipo().getId());
                        pstm.setBoolean(++index, consulta.isFila());
                        pstm.setBoolean(++index, consulta.isPrioritario());
                        pstm.setString(++index, consulta.getCodAutorizacao());
                        if(consulta.getPaciente().getEmpresa().getId() == null){
                          pstm.setNull(++index, java.sql.Types.INTEGER );
                        }else{
                        pstm.setInt(++index,consulta.getPaciente().getEmpresa().getId());
                        }        
                        if (consulta.getPaciente().getFuncao().getId() == null) {
                        pstm.setNull(++index, java.sql.Types.INTEGER);
                        } else {
                           pstm.setInt(++index, consulta.getPaciente().getFuncao().getId());
                        }
                        pstm.setInt(++index, consulta.getId());
                    
            // executar
                pstm.execute();

                commitTransaction(connection);
                idAlterado = true;
                inserirProcedimentosConsulta(consulta.getConsultaProcedimento(), consulta.getId());

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
    
        public boolean removerConsultaProcedimento(List<ConsultaProcedimento> consultaProcedimentos) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            for (ConsultaProcedimento consultaProcedimento : consultaProcedimentos) {
                if (consultaProcedimento.getId() != null) {
                    String sql = "delete from procedimento_consulta where id = ?";

                    pstm = connection.prepareStatement(sql);
                    pstm.setInt(1, consultaProcedimento.getId());
                    pstm.execute();
                    commitTransaction(connection);
                }
            }
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
    
      public boolean removerConsultasProcedimentos(List<ConsultaProcedimento> consultaProcedimento) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            for (ConsultaProcedimento procedimentos : consultaProcedimento) {
                if (procedimentos.getId() != null) {
                    String sql = "delete from procedimento_consulta where id = ?";

                    pstm = connection.prepareStatement(sql);
                    pstm.setInt(1, procedimentos.getId());
                    pstm.execute();
                    commitTransaction(connection);
                }
            }
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
        @Override
    public boolean altStatConsult(List<Consulta> consulta) {
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
            // pegar a connection
                connection = getConnection();
                beginTransaction(connection);
                // criar o sql
                String sql = "UPDATE consultas SET status = ?  WHERE id = ?";
                
                
                // criar o statement
                pstm = connection.prepareStatement(sql);
                    for (Consulta consulta1 : consulta) {
                        // setar os params
                        pstm.setInt(1, consulta1.getStatus());
                        pstm.setInt(2, consulta1.getId());
                        
                        
                        // executar
                        pstm.execute();
                        
                        commitTransaction(connection);
                        idAlterado = true;
                    }
               
                
               

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
    public boolean altStatProcedimento(List<ConsultaProcedimento> consultaProcedimento) {
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
            // pegar a connection
                connection = getConnection();
                beginTransaction(connection);
                // criar o sql
                String sql = "UPDATE procedimento_consulta SET status = ?  WHERE id = ?";
                
                
                // criar o statement
                pstm = connection.prepareStatement(sql);
                    for (ConsultaProcedimento consulta1 : consultaProcedimento) {
                        // setar os params
                        pstm.setString(1, consulta1.getStatus());
                        pstm.setInt(2, consulta1.getId());
                        // executar
                        pstm.execute();
                        
                        commitTransaction(connection);
                        idAlterado = true;
                    }
               
                
               

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
    
        @Override
    public boolean altSeqConsult(List<Consulta> consulta) {
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
            // pegar a connection
                connection = getConnection();
                beginTransaction(connection);
                // criar o sql
                String sql = "UPDATE consultas SET fila = ?,status = ?, sequencia = ?  WHERE id = ?";
                
                
                // criar o statement
                pstm = connection.prepareStatement(sql);
                    for (Consulta consulta1 : consulta) {
                        // setar os params
                        pstm.setBoolean(1, consulta1.isFila());
                        pstm.setInt(2, consulta1.getStatus());
                        pstm.setInt(3, consulta1.getSequencia());
                        pstm.setInt(4, consulta1.getId());
                        
                        
                        // executar
                        pstm.execute();
                        
                        commitTransaction(connection);
                        idAlterado = true;
                    }
               
                
               

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
        @Override
         public boolean altStatConsult2(Consulta consulta) {
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
            // pegar a connection
                connection = getConnection();
                beginTransaction(connection);
                // criar o sql
                String sql = "UPDATE consultas SET status = ?  WHERE id = ?";
                
                
                // criar o statement
                pstm = connection.prepareStatement(sql);
               
                    // setar os params      
                    pstm.setInt(1, consulta.getStatus());
                    pstm.setInt(2, consulta.getId());
                    
                 
                 // executar
                pstm.execute();

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


    @Override
    public void rmConsult(int id) {
      
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			//tempItem = procuraPorIDItem(id);
			
			String sql = "DELETE FROM consultas WHERE id = ?";

			pstm = connection.prepareStatement(sql);	
			pstm.setInt(1, id);
			pstm.execute();
			commitTransaction(connection);

		} catch (Exception e) {
			try {
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, pstm, connection);
		}
    
    }

     public Consulta procuraPorID(Integer id) {
       
                Consulta con = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT  consultas.*,pacientes.nome,num_convenio, convenios.ds_convenio, cond_pagto.descricao, usuarios.usuario, tipo_consulta.*, empresa.id AS id_empresa_paciente,fantasia, fun.nome AS nome_funcao  "
                                + " FROM pacientes "
                                + "LEFT JOIN consultas ON consultas.id_paciente = pacientes.id "
                                + "LEFT JOIN convenios ON convenios.id = consultas.id_convenio "
                                + "LEFT JOIN cond_pagto ON cond_pagto.id = consultas.id_cond_pagto "
                                + "LEFT JOIN usuarios ON usuarios.id = consultas.id_medico "
                                + "LEFT JOIN tipo_consulta ON tipo_consulta.id = consultas.cod_tipo "
                                + "LEFT JOIN empresa ON empresa.id = consultas.id_empresa "
                                + "LEFT JOIN funcao fun ON fun.id = consultas.id_funcao "
                                + "WHERE consultas.id = ? ";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
	
			con = retornObj(rs);
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
    @Override
    public List<Consulta> buscaConsultas(String dado, char tipo, String status) {
        if (status.equals("7")) {
            status = "1,2,3,4,5,6";
        }
        Consulta con;
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
                    sql ="SELECT  consultas.*,pacientes.nome,num_convenio, convenios.ds_convenio, cond_pagto.descricao, usuarios.usuario, tipo_consulta.*, empresa.id AS id_empresa_paciente,fantasia, fun.nome AS nome_funcao  "
                                + " FROM pacientes "
                                + "LEFT JOIN consultas ON consultas.id_paciente = pacientes.id "
                                + "LEFT JOIN convenios ON convenios.id = consultas.id_convenio "
                                + "LEFT JOIN cond_pagto ON cond_pagto.id = consultas.id_cond_pagto "
                                + "LEFT JOIN usuarios ON usuarios.id = consultas.id_medico "
                                + "LEFT JOIN tipo_consulta ON tipo_consulta.id = consultas.cod_tipo "
                                + "LEFT JOIN empresa ON empresa.id = consultas.id_empresa "
                                + "LEFT JOIN funcao fun ON fun.id = consultas.id_funcao "
                                + "WHERE pacientes.nome LIKE '%"+dado+"%' AND status IN ("+status+") ORDER BY data_consulta ASC";
                        break;
                case 'i':
                        sql = "SELECT  consultas.*,pacientes.nome,num_convenio, convenios.ds_convenio, cond_pagto.descricao, usuarios.usuario, tipo_consulta.*, empresa.id AS id_empresa_paciente,fantasia, fun.nome AS nome_funcao  "
                                + " FROM pacientes "
                                + "LEFT JOIN consultas ON consultas.id_paciente = pacientes.id "
                                + "LEFT JOIN convenios ON convenios.id = consultas.id_convenio "
                                + "LEFT JOIN cond_pagto ON cond_pagto.id = consultas.id_cond_pagto "
                                + "LEFT JOIN usuarios ON usuarios.id = consultas.id_medico "
                                + "LEFT JOIN tipo_consulta ON tipo_consulta.id = consultas.cod_tipo "
                                + "LEFT JOIN empresa ON empresa.id = consultas.id_empresa "
                                + "LEFT JOIN funcao fun ON fun.id = consultas.id_funcao "
                                + "WHERE data_consulta = '"+dado+"' AND status IN ("+status+") ORDER BY data_consulta ASC";
                        break;
                case 't':
                    sql = "SELECT  consultas.*,pacientes.nome,num_convenio, convenios.ds_convenio, cond_pagto.descricao, usuarios.usuario, tipo_consulta.*, empresa.id AS id_empresa_paciente,fantasia, fun.nome AS nome_funcao  "
                                + " FROM pacientes "
                                + "LEFT JOIN consultas ON consultas.id_paciente = pacientes.id "
                                + "LEFT JOIN convenios ON convenios.id = consultas.id_convenio "
                                + "LEFT JOIN cond_pagto ON cond_pagto.id = consultas.id_cond_pagto "
                                + "LEFT JOIN usuarios ON usuarios.id = consultas.id_medico "
                                + "LEFT JOIN tipo_consulta ON tipo_consulta.id = consultas.cod_tipo "
                                + "LEFT JOIN empresa ON empresa.id = consultas.id_empresa "
                                + "LEFT JOIN funcao fun ON fun.id = consultas.id_funcao "
                                + "WHERE status IN ("+status+")  ORDER BY data_consulta ASC";
                       
                        break;
                case 'a':
                    sql = "SELECT  consultas.*,pacientes.nome,num_convenio, convenios.ds_convenio, cond_pagto.descricao, usuarios.usuario, tipo_consulta.*, empresa.id AS id_empresa_paciente,fantasia, fun.nome AS nome_funcao  "
                                + " FROM pacientes "
                                + "LEFT JOIN consultas ON consultas.id_paciente = pacientes.id "
                                + "LEFT JOIN convenios ON convenios.id = consultas.id_convenio "
                                + "LEFT JOIN cond_pagto ON cond_pagto.id = consultas.id_cond_pagto "
                                + "LEFT JOIN usuarios ON usuarios.id = consultas.id_medico "
                                + "LEFT JOIN tipo_consulta ON tipo_consulta.id = consultas.cod_tipo "
                                 + "LEFT JOIN empresa ON empresa.id = consultas.id_empresa "
                                + "LEFT JOIN funcao fun ON fun.id = consultas.id_funcao "
                                + "WHERE id_paciente = '"+dado+"' OR num_convenio = '"+dado+"' AND status IN ("+status+") ORDER BY data_consulta ASC";
                    break;
           

                default:
                        break;
                }

                // criar o statement
                stm = connection.createStatement();
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                 
                 con = retornObj(rs);
                 consultas.add(con);
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
        return consultas;
    }
    
    public List<Consulta> buscaConsultasPorIdPaciente(Paciente p) {
        Consulta con;
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
                // pegar a connection
                connection = getConnection();
                beginTransaction(connection);
                // CRIAR SQL
                String sql="SELECT  id,data_consulta FROM consultas WHERE id_paciente = '"+p.getId()+"' ORDER BY data_consulta ASC";
                // criar o statement
                stm = connection.createStatement();
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                 
                 con = retornObjDatasConsultas(rs);
                 consultas.add(con);
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
        return consultas;
    }  
    
    @Override
     public List<Consulta> buscaConPstat(int coluna ,String dados, String status) {
        Consulta con;
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
                // pegar a connection
                connection = getConnection();
                beginTransaction(connection);
                // CRIAR SQL
                String sql="";
                switch (coluna) {
                case 0:
                    sql = "SELECT  consultas.*,pacientes.nome,num_convenio, convenios.ds_convenio, cond_pagto.descricao, usuarios.usuario, tipo_consulta.*, empresa.id AS id_empresa_paciente,fantasia, fun.nome AS nome_funcao  "
                                + " FROM pacientes "
                                + "LEFT JOIN consultas ON consultas.id_paciente = pacientes.id "
                                + "LEFT JOIN convenios ON convenios.id = consultas.id_convenio "
                                + "LEFT JOIN cond_pagto ON cond_pagto.id = consultas.id_cond_pagto "
                                + "LEFT JOIN usuarios ON usuarios.id = consultas.id_medico "
                                + "LEFT JOIN tipo_consulta ON tipo_consulta.id = consultas.cod_tipo "
                                + "LEFT JOIN empresa ON empresa.id = consultas.id_empresa "
                                + "LEFT JOIN funcao fun ON fun.id = consultas.id_funcao "
                                + "WHERE pacientes.nome LIKE '%"+dados+"%' AND status IN ("+status+") ORDER BY data_consulta ASC";
                        break;
                case 1:
                    sql = "SELECT  consultas.*,pacientes.nome,num_convenio, convenios.ds_convenio, cond_pagto.descricao, usuarios.usuario, tipo_consulta.*, empresa.id AS id_empresa_paciente,fantasia, fun.nome AS nome_funcao "
                                + " FROM pacientes "
                                + "LEFT JOIN consultas ON consultas.id_paciente = pacientes.id "
                                + "LEFT JOIN convenios ON convenios.id = consultas.id_convenio "
                                + "LEFT JOIN cond_pagto ON cond_pagto.id = consultas.id_cond_pagto "
                                + "LEFT JOIN usuarios ON usuarios.id = consultas.id_medico "
                                + "LEFT JOIN tipo_consulta ON tipo_consulta.id = consultas.cod_tipo "
                                + "LEFT JOIN empresa ON empresa.id = consultas.id_empresa "
                                + "LEFT JOIN funcao fun ON fun.id = consultas.id_funcao "
                                + "WHERE data_consulta = '"+dados+"' AND status IN ("+status+") ORDER BY data_consulta ASC";
                        break;
               
           

                default:
                        break;
                }

                // criar o statement
                stm = connection.createStatement();
                rs = stm.executeQuery(sql);
                while (rs.next()) {         
                 con = retornObj(rs);
                 consultas.add(con);
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
        return consultas;
    }
    
    public List<Consulta> buscaConDataPstat(String dados, String status) {
        Consulta con;
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
                // pegar a connection
                connection = getConnection();
                beginTransaction(connection);
                // CRIAR SQL
               
            
              
                   String   sql = "SELECT  consultas.*,pacientes.nome,num_convenio, convenios.ds_convenio, cond_pagto.descricao, usuarios.usuario, tipo_consulta.*, empresa.id AS id_empresa_paciente,fantasia, fun.nome AS nome_funcao "
                                + " FROM pacientes "
                                + "LEFT JOIN consultas ON consultas.id_paciente = pacientes.id "
                                + "LEFT JOIN convenios ON convenios.id = consultas.id_convenio "
                                + "LEFT JOIN cond_pagto ON cond_pagto.id = consultas.id_cond_pagto "
                                + "LEFT JOIN usuarios ON usuarios.id = consultas.id_medico "
                                + "LEFT JOIN tipo_consulta ON tipo_consulta.id = consultas.cod_tipo "
                                + "LEFT JOIN empresa ON empresa.id = consultas.id_empresa "
                                + "LEFT JOIN funcao fun ON fun.id = consultas.id_funcao "
                                + "WHERE data_consulta = '"+dados+"' AND status IN ("+status+")  AND consultas.fila = true ORDER BY sequencia ASC";
                

                // criar o statement
                stm = connection.createStatement();
                rs = stm.executeQuery(sql);
                while (rs.next()) {         
                 con = retornObj(rs);
                 consultas.add(con);
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
        return consultas;
    }
    
        @Override
        public List<Consulta> buscConsPdata(Date data) {
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT  consultas.*,pacientes.nome,num_convenio, convenios.ds_convenio, cond_pagto.descricao, usuarios.usuario, tipo_consulta.*, empresa.id AS id_empresa_paciente,fantasia, fun.nome AS nome_funcao "
                                + " FROM pacientes "
                                + "LEFT JOIN consultas ON consultas.id_paciente = pacientes.id "
                                + "LEFT JOIN convenios ON convenios.id = consultas.id_convenio "
                                + "LEFT JOIN cond_pagto ON cond_pagto.id = consultas.id_cond_pagto "
                                + "LEFT JOIN usuarios ON usuarios.id = consultas.id_medico "
                                + "LEFT JOIN tipo_consulta ON tipo_consulta.id = consultas.cod_tipo "
                                + "LEFT JOIN empresa ON empresa.id = consultas.id_empresa "
                                + "LEFT JOIN funcao fun ON fun.id = consultas.id_funcao "
                                + "WHERE data_consulta = '"+data+"' ORDER BY sequencia ASC";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
	
			while (rs.next()) {
                        
                        Consulta c = retornObj(rs);
               
                                consultas.add(c);
								
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
		return consultas;    
        }
        public List<Consulta> buscConsPdataEnome(Date data , String nome) {
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT  consultas.*,pacientes.nome,num_convenio, convenios.ds_convenio, cond_pagto.descricao, usuarios.usuario, tipo_consulta.*, empresa.id AS id_empresa_paciente,fantasia, fun.nome AS nome_funcao "
                                + " FROM pacientes "
                                + "LEFT JOIN consultas ON consultas.id_paciente = pacientes.id "
                                + "LEFT JOIN convenios ON convenios.id = consultas.id_convenio "
                                + "LEFT JOIN cond_pagto ON cond_pagto.id = consultas.id_cond_pagto "
                                + "LEFT JOIN usuarios ON usuarios.id = consultas.id_medico "
                                + "LEFT JOIN tipo_consulta ON tipo_consulta.id = consultas.cod_tipo "
                                + "LEFT JOIN empresa ON empresa.id = consultas.id_empresa "
                                + "LEFT JOIN funcao fun ON fun.id = consultas.id_funcao "
                                + "WHERE data_consulta = '"+data+"' and pacientes.nome LIKE '%"+nome+"%' ORDER BY sequencia ASC ";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
	
			while (rs.next()) {
                        
                        Consulta c = retornObj(rs);
               
                                consultas.add(c);
								
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
		return consultas;    
        }
        public List<Consulta> buscConsPdataEnomeNaFila(Date data , String nome) {
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT  consultas.*,pacientes.nome,num_convenio, convenios.ds_convenio, cond_pagto.descricao, usuarios.usuario, tipo_consulta.*, empresa.id AS id_empresa_paciente,fantasia, fun.nome AS nome_funcao "
                                + " FROM pacientes "
                                + "LEFT JOIN consultas ON consultas.id_paciente = pacientes.id "
                                + "LEFT JOIN convenios ON convenios.id = consultas.id_convenio "
                                + "LEFT JOIN cond_pagto ON cond_pagto.id = consultas.id_cond_pagto "
                                + "LEFT JOIN usuarios ON usuarios.id = consultas.id_medico "
                                + "LEFT JOIN tipo_consulta ON tipo_consulta.id = consultas.cod_tipo "
                                + "LEFT JOIN empresa ON empresa.id = consultas.id_empresa "
                                + "LEFT JOIN funcao fun ON fun.id = consultas.id_funcao "
                                + "WHERE data_consulta = '"+data+"' and pacientes.nome LIKE '%"+nome+"%' and fila = true ORDER BY sequencia ASC";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
	
			while (rs.next()) {
                        
                        Consulta c = retornObj(rs);
               
                                consultas.add(c);
								
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
		return consultas;    
        }
        public List<Consulta> buscConsEProcedimentoPorData(Date data) {
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT  consultas.*,pacientes.nome,num_convenio, convenios.ds_convenio, cond_pagto.descricao, usuarios.usuario, tipo_consulta.*, empresa.id AS id_empresa_paciente, fantasia, fun.nome AS nome_funcao "
                                + " FROM pacientes "
                                + "LEFT JOIN consultas ON consultas.id_paciente = pacientes.id "
                                + "LEFT JOIN convenios ON convenios.id = consultas.id_convenio "
                                + "LEFT JOIN cond_pagto ON cond_pagto.id = consultas.id_cond_pagto "
                                + "LEFT JOIN usuarios ON usuarios.id = consultas.id_medico "
                                + "LEFT JOIN tipo_consulta ON tipo_consulta.id = consultas.cod_tipo "
                                + "LEFT JOIN empresa ON empresa.id = consultas.id_empresa "
                                + "LEFT JOIN funcao fun ON fun.id = consultas.id_funcao "
                                + "WHERE data_consulta = '"+data+"' ORDER BY sequencia ASC";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
	
			while (rs.next()) {
                        
                        Consulta c = retornObj(rs);
               
                                consultas.add(c);
								
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
		return consultas;    
        }

        public List<ConsultaProcedimento> BuscaProcedimetoEmpresa(String nome, char ds_procedimento,int id_consulta) {
		ArrayList<ConsultaProcedimento> consultaProcedimentos = new ArrayList<>();
                ConsultaProcedimento ep ;
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
				sql = "SELECT procedimento_consulta.*, procedimento.ds_procedimento, procedimento_empresa.id AS id_empresa_procedimen "
                                        + "FROM procedimento_consulta LEFT JOIN procedimento_empresa ON procedimento_consulta.id_procedimento_empresa = procedimento_empresa.id "
                                        + "LEFT JOIN procedimento ON procedimento.id = procedimento_empresa.id_procedimento "
                                        + " WHERE ds_procedimento like ('%"+nome+"%') and id_consulta = ('"+id_consulta+"') ORDER BY procedimento_consulta.id ASC ";
				break;
			case 'i':
				sql = "SELECT procedimento_consulta.*, procedimento.ds_procedimento, procedimento_empresa.id AS id_empresa_procedimen "
                                        + "FROM procedimento_consulta LEFT JOIN procedimento_empresa ON procedimento_consulta.id_procedimento_empresa = procedimento_empresa.id "
                                        + "LEFT JOIN procedimento ON procedimento.id = procedimento_empresa.id_procedimento "
                                        + "WHERE id_procedimento =('"+nome+"') and id_consulta = ('"+id_consulta+"') ORDER BY procedimento_consulta.id ASC ";
				break;
			
			default:
				break;
			}
			
			// criar o statement
			stm = connection.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
            
			ep = retornProcedimentoConsulta(rs);       
                        consultaProcedimentos.add(ep);
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
		return consultaProcedimentos;  
    }

    @Override
    public Consulta buscConsPCli(int idPaciente) {
        Consulta con = null;
        Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String selectMaxData = "SELECT MAX(data_consulta) AS maxDATA from consultas ";
                        Date maxData = null;
                        pstm = connection.prepareStatement(selectMaxData);
			rs = pstm.executeQuery();
			while (rs.next()) {
                            
				maxData = rs.getDate(1);
			}
                        
                        String sql = "SELECT * FROM consultas WHERE data_consulta = '"+maxData+"' ";
			// criar o statement
			pstm = connection.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {	
                          con = new Consulta(rs.getDate("data_consulta"), rs.getInt("status"), rs.getString("obs"));
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
    
         public Consulta retornObj(ResultSet rs) throws SQLException{
             Consulta c = null;
             if (rs != null) {
                       
                        CondPagto  condPagto = new CondPagto();
                        condPagto.setId(rs.getInt("id_cond_pagto"));
                        condPagto.setCondPagt(rs.getString("descricao"));
                        Convenio conv = new Convenio();
                        conv.setId(rs.getInt("id_convenio"));
                        conv.setDsConvenio(rs.getString("ds_convenio"));
                        Empresa empresa = new Empresa(rs.getInt("id_empresa_paciente"), rs.getString("fantasia"));
                        Funcao funcao = new Funcao(rs.getInt("id_funcao"), rs.getString("nome_funcao"));
                        Paciente  paciente = new Paciente(rs.getInt("id_paciente"),rs.getString("num_convenio"), rs.getString("nome"),empresa, funcao);
                        Usuario  usuario = new Usuario(rs.getInt("id_medico"),rs.getString("usuario"));
                        Tipo tipo = new Tipo(rs.getInt("cod_tipo"), rs.getString("tipo"));
                         c = new Consulta(rs.getInt("id"), conv,paciente, usuario,rs.getDate("data_consulta"), rs.getInt("status"),
                                rs.getInt("sequencia"), rs.getString("obs"), rs.getDouble("valor"),condPagto, 
                                 tipo,null,rs.getBoolean("fila"),rs.getBoolean("prioritario"),rs.getString("cod_autorizacao"));
                         
             }
            return c;
         }
//         public Consulta retornConsultasProcedimentos(ResultSet rs) throws SQLException{
//             Consulta c = null;
//             if (rs != null) {
//                        Convenio conv = new Convenio();
//                        CondPagto  condPagto = new CondPagto();
//                        condPagto.setId(rs.getInt("id_cond_pagto"));
//                        condPagto.setCondPagt(rs.getString("descricao"));
//                        conv.setId(rs.getInt("id_convenio"));
//                        conv.setDsConvenio(rs.getString("ds_convenio"));
//                        Empresa empresa = new Empresa(rs.getInt("id_empresa"), rs.getString("fantasia"));
//                        Paciente  paciente = new Paciente(rs.getInt("id_paciente"),rs.getString("num_convenio"), rs.getString("nome"),empresa);
//                        Usuario  usuario = new Usuario(rs.getInt("id_medico"),rs.getString("usuario"));
//                        Tipo tipo = new Tipo(rs.getInt("cod_tipo"), rs.getString("tipo"));
//                         c = new Consulta(rs.getInt("id"), conv,paciente, usuario,rs.getDate("data_consulta"), rs.getInt("status"),
//                                rs.getInt("sequencia"), rs.getString("obs"), rs.getDouble("valor"),condPagto, 
//                                 tipo,BuscaProcedimetoEmpresa("",'e',rs.getInt("id")),rs.getBoolean("fila"),rs.getBoolean("prioritario"),rs.getString("cod_autorizacao"));
//                         
//             }
//            return c;
//         }
         
          public Consulta retornObjDatasConsultas(ResultSet rs) throws SQLException{
             Consulta c = null;
             if (rs != null) {
                       
                         c = new Consulta();
                         c.setId(rs.getInt("id"));
                         c.setDataConsulta(rs.getDate("data_consulta"));
             }
            return c;
         }
    public ConsultaProcedimento retornProcedimentoConsulta(ResultSet rs) throws SQLException {
        ConsultaProcedimento conp = null;
        if (rs != null) {
            Empresa emp = new Empresa();
            Procedimento proce = new Procedimento(null, rs.getString("ds_procedimento"));
            EmpresaProcedimento empresaProcedimento = new EmpresaProcedimento(rs.getInt("id_procedimento_empresa"),emp, proce);
            ConsultaProcedimento cp = new ConsultaProcedimento(rs.getInt("id"), false ,empresaProcedimento, rs.getDouble("valor"),rs.getString("status"));
            conp = cp;
        }

        return conp;
    }
         public void ConectRelatorio(Date init ,Date fin, String clien, String status, String convenio, String condPagt,String usuario, String ordem, int id_minha_empresa, String id_empresa){
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			InputStream inputStream = getClass().getResourceAsStream("/consulta_procedimentos_2.jasper");
            
           
                // mapa de parâmetros do relatório
                Map parametros = new HashMap();
                parametros.put("clie", clien);
                parametros.put("stat", status);
                parametros.put("conv", convenio);
                parametros.put("dataIn", init);
                parametros.put("dataFin", fin);
                parametros.put("condPg", condPagt);
                parametros.put("med", usuario);
                parametros.put("ordem", ordem);
                parametros.put("id_empresa", id_empresa);
                parametros.put("id_minha_empresa", id_minha_empresa);
               
              
               
               
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
         public void ConectRelatorio2(Date init ,Date fin, String status, String condPagt,String usuario, String ordem, int id_empresa){
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			InputStream inputStream = getClass().getResourceAsStream("/rela_movimento.jasper");
            
           
                // mapa de parâmetros do relatório
                Map parametros = new HashMap();
                parametros.put("stat", status);
                parametros.put("dataIn", init);
                parametros.put("dataFin", fin);
                parametros.put("condPg", condPagt);
                parametros.put("med", usuario);
                parametros.put("ordem", ordem);
                parametros.put("id_empresa", id_empresa);
               
              
               
               
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
         public void imprimirContula(int idConsulta,Boolean comValor, Integer sequencia){
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
                        InputStream inputStream;
                         if (comValor) {
                          inputStream = getClass().getResourceAsStream("/consulta_procedimentos.jasper");
                         }else{
                          inputStream = getClass().getResourceAsStream("/consulta_procedimentos_3.jasper");
                         }
			
            
           
                // mapa de parâmetros do relatório
                Map parametros = new HashMap();
                int c = Sessao.getInstance().getClinica().getId();
                parametros.put("idconsulta", idConsulta);
                parametros.put("id_minha_empresa", c);
                parametros.put("sequencia", sequencia);
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
           public void ConectRelatorioEmpresas(Date init ,Date fin, int id_empresa){
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			InputStream inputStream = getClass().getResourceAsStream("/empresas_com_movimento.jasper");
            
           
                // mapa de parâmetros do relatório
                Map parametros = new HashMap();
                parametros.put("dataIn", init);
                parametros.put("dataFin", fin);
                parametros.put("id_empresa", id_empresa);
               
              
               
               
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
