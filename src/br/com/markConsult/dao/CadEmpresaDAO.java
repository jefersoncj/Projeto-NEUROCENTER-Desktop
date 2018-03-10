/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Cep;
import br.com.markConsult.entidades.Empresa;
import br.com.markConsult.entidades.EmpresaProcedimento;
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
public class CadEmpresaDAO extends AbstractConecxaoDAO {

    /**
     * @uml.property name="idInserido"
     */
    Integer idInserido = null;
    /**
     * @uml.property name="consulta"
     * @uml.associationEnd
     */
    Empresa empresa = null;
    boolean idAlterado;

    /**
     * @uml.property name="consultas"
     */
    public CadEmpresaDAO() {
        this.idAlterado = false;
    }
    private final ArrayList<Empresa> empresas = new ArrayList<>();

    public Integer inseEmpresa(Empresa empresa) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // criar o sql
            String sql = "INSERT INTO empresa (fantasia, razao_social, email, cep, municipio, uf, logradouro, numero, bairro, cnpj, inscricao_estadual, fone_fixo, celular2, contato)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // criar o statement
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // setar os params
            int index = 0;
            pstm.setString(++index, empresa.getFantasia());
            pstm.setString(++index, empresa.getRazao());
            pstm.setString(++index, empresa.getEmail());
            pstm.setString(++index, empresa.getCep().getCodCep());
            pstm.setString(++index, empresa.getCep().getCidade());
            pstm.setString(++index, empresa.getCep().getUf());
            pstm.setString(++index, empresa.getCep().getLogradouro());
            pstm.setString(++index, empresa.getNumero());
            pstm.setString(++index, empresa.getCep().getBairro());
            pstm.setString(++index, empresa.getCnpj());
            pstm.setString(++index, empresa.getInscricaoEstadual());
            pstm.setString(++index, empresa.getFoneFixo());
            pstm.setString(++index, empresa.getCelular2());
            pstm.setString(++index, empresa.getContato());

            // executar
            pstm.executeUpdate();
            rs = pstm.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            commitTransaction(connection);
            idInserido = id;
           inserirProcedimentosEmpresa(empresa.getEmpresaProcedimento(), id);
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
    
     public Integer inserirProcedimentosEmpresa( List<EmpresaProcedimento> empresaProcedimentos, int idEmpresa) {
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// GERAR O ID UNICO
                    for (EmpresaProcedimento empresaProcedimento : empresaProcedimentos) {
                        if(empresaProcedimento.getId() == null){
                            String sql = "INSERT INTO procedimento_empresa (id_empresa, id_procedimento, valor)VALUES (?, ?, ?)";
                            // criar o statement
                            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                            // setar os params
                            int index = 0;
                            pstm.setInt(++index, idEmpresa);
                            pstm.setInt(++index, empresaProcedimento.getProcedimento().getId());
                            pstm.setDouble(++index, empresaProcedimento.getValor());
                            // executar
                            pstm.executeUpdate();
                         }else{
                            alterarValor(empresaProcedimento);
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
     public boolean alterarValor(EmpresaProcedimento empresaProcedimento){
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
                    // pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "UPDATE procedimento_empresa SET valor = ? WHERE id = ?";

			// criar o statement
			pstm = connection.prepareStatement(sql);
                        
			// setar os params
			int index = 0;
			pstm.setDouble(++index, empresaProcedimento.getValor());
                        pstm.setInt(++index, empresaProcedimento.getId());

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
//    public Integer inseEmpresaProcedimento(EmpresaProcedimento empresaProcedimento) {
//        Connection connection = null;
//        PreparedStatement pstm = null;
//        ResultSet rs = null;
//        try {
//            // pegar a connection
//            connection = getConnection();
//            beginTransaction(connection);
//            // GERAR O ID UNICO
//
//            // criar o sql
//            String sql = "INSERT INTO procedimento_empresa (id_empresa, id_procedimento, valor)VALUES (?, ?, ?)";
//
//            // criar o statement
//            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//            // setar os params
//            int index = 0;
//            pstm.setInt(++index, empresaProcedimento.getEmpresa().getId());
//            pstm.setInt(++index, empresaProcedimento.getProcedimento().getId());
//            pstm.setDouble(++index, empresaProcedimento.getValor());
//
//            // executar
//            pstm.executeUpdate();
//            rs = pstm.getGeneratedKeys();
//            int id = 0;
//            if (rs.next()) {
//                id = rs.getInt(1);
//            }
//            commitTransaction(connection);
//            idInserido = id;
//
//        } catch (Exception e) {
//
//            try {
//                rollbackTransaction(connection);
//            } catch (SQLException e1) {
//                throw new IllegalStateException();
//            }
//        } finally {
//            cleanup(rs, pstm, connection);
//        }
//        return idInserido;
//    }

    public boolean altEmpresa(Empresa empresa) {
        
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // criar o sql
            String sql = "UPDATE empresa SET  fantasia = ?, razao_social = ?, "
                    + "email = ?, cep = ?, municipio = ?, uf = ?, logradouro = ?, numero = ?, bairro = ?, "
                    + "cnpj = ?,inscricao_estadual= ?, fone_fixo = ?, celular2 = ?, contato = ? WHERE id = ?";

            // criar o statement 
            pstm = connection.prepareStatement(sql);

            // setar os params
            int index = 0;
            pstm.setString(++index, empresa.getFantasia());
            pstm.setString(++index, empresa.getRazao());
            pstm.setString(++index, empresa.getEmail());
            pstm.setString(++index, empresa.getCep().getCodCep());
            pstm.setString(++index, empresa.getCep().getCidade());
            pstm.setString(++index, empresa.getCep().getUf());
            pstm.setString(++index, empresa.getCep().getLogradouro());
            pstm.setString(++index, empresa.getNumero());
            pstm.setString(++index, empresa.getCep().getBairro());
            pstm.setString(++index, empresa.getCnpj());
            pstm.setString(++index, empresa.getInscricaoEstadual());
            pstm.setString(++index, empresa.getFoneFixo());
            pstm.setString(++index, empresa.getCelular2());
            pstm.setString(++index, empresa.getContato());
            pstm.setInt(++index, empresa.getId());
            // executar
            pstm.execute();

            commitTransaction(connection);
            idAlterado = true;
            inserirProcedimentosEmpresa(empresa.getEmpresaProcedimento(), empresa.getId());
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

    public boolean rmEmpresa(int id) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            String sql = "delete from empresa where id = ?";

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

    public List<Empresa> buscaEmpresa(String dado, char tipo) {
        Empresa emp;
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = "";
            switch (tipo) {
                case 'e':

                    sql = " SELECT * FROM empresa WHERE fantasia LIKE '%" + dado + "%' ORDER BY id ";
                    break;

                case 'i':

                    sql = " SELECT * FROM empresa WHERE razao_social LIKE '%" + dado + "%' ORDER BY id ";
                    break;

                case 't':
                    sql = " SELECT * FROM empresa WHERE cnpj = '" + dado + "' ORDER BY id ";
                    break;

                case 'a':
                    sql = " SELECT * FROM empresa  WHERE logradouro LIKE '%" + dado + "%' ORDER BY id ";
                    break;
                case 'p':
                    sql = " SELECT * FROM empresa WHERE id = '" + dado + "' ORDER BY id ";
                    break;
                default:
                    break;
            }

            // criar o statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {

                emp = RetornObjeto(rs);

                empresas.add(emp);

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
        return empresas;
    }

    public Empresa buscaEmpresaPorId(int id) {
        Empresa empre = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM empresa  WHERE empresa.id = ? ";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                empre = RetornObjeto(rs);
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
        return empre;
    }

    public Empresa mostrarUltimo() {
        Connection connection = null;
        PreparedStatement pstm = null;
        Empresa emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = "SELECT * FROM empresa  WHERE empresa.id = (SELECT MAX(id) AS maxID FROM empresa) ";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                emp = RetornObjeto(rs);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            try {
                rollbackTransaction(connection);
            } catch (SQLException e1) {
                throw new IllegalStateException();
            }
        } finally {
            cleanup(rs, pstm, connection);
        }
        return emp;

    }

    public Empresa mostrarPrimeiro() {
        Connection connection = null;
        PreparedStatement pstm = null;
        Empresa empr = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM empresa WHERE empresa.id = (SELECT MIN(id) AS minID FROM empresa) ";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                empr = RetornObjeto(rs);

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
        return empr;
    }

    public Empresa mostrarProximo(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        Empresa emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // CRIAR SQL
            String sql = "SELECT * FROM empresa WHERE empresa.id > '" + id + "' ORDER BY empresa.id limit 1";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                emp = RetornObjeto(rs);
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
        return emp;
    }

    public Empresa mostrarAnterior(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        Empresa emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM empresa  WHERE empresa.id < '" + id + "' ORDER BY empresa.id desc limit 1";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                emp = RetornObjeto(rs);

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
        return emp;
    }

    public Empresa RetornObjeto(ResultSet rs) throws SQLException {
        Empresa emp = null;
        if (rs != null) {
            Cep cep = new Cep(null, rs.getString("cep"), rs.getString("logradouro"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("uf"));
            emp = new Empresa(rs.getInt("id"), rs.getString("fantasia"), rs.getString("razao_social"), rs.getString("email"), rs.getString("numero"), rs.getString("cnpj"),
                    rs.getString("inscricao_estadual"), rs.getString("fone_fixo"), rs.getString("celular2"), cep, rs.getString("contato"),null);

        }
        return emp;
    }

//    
//    
//    public Empresa RetornObjetoImagem(ResultSet rs) throws SQLException, IOException {
//       
//        Empresa emp = null;
//        if (rs != null) {
//            
//            Cep cep = new Cep(null, rs.getString("cep"), rs.getString("logradouro"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("uf"));
//            emp = new Empresa(rs.getInt("id"), rs.getString("fantasia"), rs.getString("razao_social"), rs.getString("email"), rs.getString("numero"), rs.getString("cnpj"),
//                    rs.getString("inscricao_estadual"), rs.getString("fone_fixo"), rs.getString("celular2"), cep,rs.getString("contato"));
//
//        }
//        return emp;
//    }
}
