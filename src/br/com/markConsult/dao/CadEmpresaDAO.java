/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Cep;
import br.com.markConsult.entidades.Empresa;
import java.io.IOException;
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
            String sql = "INSERT INTO empresas (fantasia, razao_social, email, cep, municipio, uf, logradouro, numero, bairro, cnpj,fone_fixo, celular1, celular2)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            pstm.setString(++index, empresa.getFoneFixo());
            pstm.setString(++index, empresa.getCelular1());
            pstm.setString(++index, empresa.getCelular2());
           

            // executar
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

    public boolean altEmpresa(Empresa empresa) {
        
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
         
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // criar o sql
            String sql = "UPDATE empresas SET  fantasia = ?, razao_social = ?, "
                    + "email = ?, cep = ?, municipio = ?, uf = ?, logradouro = ?, numero = ?, bairro = ?, "
                    + "cnpj = ?, fone_fixo = ?, celular1 = ?, celular2 = ? WHERE id = ?";

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
            pstm.setString(++index, empresa.getFoneFixo());
            pstm.setString(++index, empresa.getCelular1());
            pstm.setString(++index, empresa.getCelular2());
            pstm.setInt(++index, empresa.getId());
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

    public boolean rmEmpresa(int id) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            String sql = "delete from empresas where id = ?";

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

                    sql = " SELECT * FROM empresas WHERE fantasia LIKE '%" + dado + "%' ORDER BY id ";
                    break;

                case 'i':

                    sql = " SELECT * FROM empresas WHERE razao_social LIKE '%" + dado + "%' ORDER BY id ";
                    break;

                case 't':
                    sql = " SELECT * FROM empresas WHERE cnpj = '" + dado + "' ORDER BY id ";
                    break;

                case 'a':
                    sql = " SELECT * FROM empresas  WHERE logradouro LIKE '%" + dado + "%' ORDER BY id ";
                    break;
                case 'p':
                    sql = " SELECT * FROM empresas WHERE id = '" + dado + "' ORDER BY id ";
                    break;
                default:
                    break;
            }

            // criar o statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {

                emp = RetornObjetoImagem(rs);

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

    public Empresa buscEmpPid(int id) {
        Empresa empre = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM empresas  WHERE empresas.id = ? ";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                empre = RetornObjetoImagem(rs);
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
            String sql = "SELECT * FROM empresas  WHERE empresas.id = (SELECT MAX(id) AS maxID FROM empresas) ";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                emp = RetornObjetoImagem(rs);

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
            String sql = " SELECT * FROM empresas WHERE empresas.id = (SELECT MIN(id) AS minID FROM empresas) ";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                empr = RetornObjetoImagem(rs);

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
            String sql = "SELECT * FROM empresas WHERE empresas.id > '" + id + "' ORDER BY empresas.id limit 1";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                emp = RetornObjetoImagem(rs);
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
            String sql = " SELECT * FROM empresas  WHERE empresas.id < '" + id + "' ORDER BY empresas.id desc limit 1";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                emp = RetornObjetoImagem(rs);

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
                    rs.getString("fone_fixo"), rs.getString("celular1"), rs.getString("celular2"), cep);

        }
        return emp;
    }

    
    
    public Empresa RetornObjetoImagem(ResultSet rs) throws SQLException, IOException {
       
        Empresa emp = null;
        if (rs != null) {
            
            Cep cep = new Cep(null, rs.getString("cep"), rs.getString("logradouro"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("uf"));
          
            emp = new Empresa(rs.getInt("id"), rs.getString("fantasia"), rs.getString("razao_social"), rs.getString("email"), rs.getString("numero"), rs.getString("cnpj"),
                    rs.getString("fone_fixo"), rs.getString("celular1"), rs.getString("celular2"), cep);

        }
        return emp;
    }
}
