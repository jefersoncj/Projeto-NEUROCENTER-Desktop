/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Cep;
import br.com.markConsult.entidades.MinhaEmpresa;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author jeferson
 */
public class CadMinhaEmpresaDAO extends AbstractConecxaoDAO {
private final  Properties confBanco = new Properties();
    /**
     * @uml.property name="idInserido"
     */
    Integer idInserido = null;
    /**
     * @uml.property name="consulta"
     * @uml.associationEnd
     */
    MinhaEmpresa minhaEmpresa = null;
    boolean idAlterado;

    /**
     * @uml.property name="consultas"
     */
    public CadMinhaEmpresaDAO() {
        this.idAlterado = false;
    }
    private final ArrayList<MinhaEmpresa> minhaEmpresas = new ArrayList<>();

    public Integer inseMinhaEmpresa(MinhaEmpresa minhaEmpresa) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // criar o sql
            String sql = "INSERT INTO minha_empresa (fantasia, razao_social, email, cep, municipio, uf, logradouro, numero, bairro, cnpj,fone_fixo, celular1, celular2)VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // criar o statement
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // setar os params
            int index = 0;
            pstm.setString(++index, minhaEmpresa.getFantasia());
            pstm.setString(++index, minhaEmpresa.getRazao());
            pstm.setString(++index, minhaEmpresa.getEmail());
            pstm.setString(++index, minhaEmpresa.getCep().getCodCep());
            pstm.setString(++index, minhaEmpresa.getCep().getCidade());
            pstm.setString(++index, minhaEmpresa.getCep().getUf());
            pstm.setString(++index, minhaEmpresa.getCep().getLogradouro());
            pstm.setString(++index, minhaEmpresa.getNumero());
            pstm.setString(++index, minhaEmpresa.getCep().getBairro());
            pstm.setString(++index, minhaEmpresa.getCnpj());
            pstm.setString(++index, minhaEmpresa.getFoneFixo());
            pstm.setString(++index, minhaEmpresa.getCelular1());
            pstm.setString(++index, minhaEmpresa.getCelular2());

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

    public boolean altMinhaEmpresa(MinhaEmpresa minhaEmpresa) {
        
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
          
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // criar o sql
            String sql = "UPDATE minha_empresa SET  fantasia = ?, razao_social = ?, "
                    + "email = ?, cep = ?, municipio = ?, uf = ?, logradouro = ?, numero = ?, bairro = ?, "
                    + "cnpj = ?, fone_fixo = ?, celular1 = ?, celular2 = ? WHERE id = ?";

            // criar o statement
            pstm = connection.prepareStatement(sql);

            // setar os params
            int index = 0;
            pstm.setString(++index, minhaEmpresa.getFantasia());
            pstm.setString(++index, minhaEmpresa.getRazao());
            pstm.setString(++index, minhaEmpresa.getEmail());
            pstm.setString(++index, minhaEmpresa.getCep().getCodCep());
            pstm.setString(++index, minhaEmpresa.getCep().getCidade());
            pstm.setString(++index, minhaEmpresa.getCep().getUf());
            pstm.setString(++index, minhaEmpresa.getCep().getLogradouro());
            pstm.setString(++index, minhaEmpresa.getNumero());
            pstm.setString(++index, minhaEmpresa.getCep().getBairro());
            pstm.setString(++index, minhaEmpresa.getCnpj());
            pstm.setString(++index, minhaEmpresa.getFoneFixo());
            pstm.setString(++index, minhaEmpresa.getCelular1());
            pstm.setString(++index, minhaEmpresa.getCelular2());
            pstm.setInt(++index, minhaEmpresa.getId());
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

    public boolean rmMinhaEmpresa(int id) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            String sql = "delete from minha_empresa where id = ?";

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

    public List<MinhaEmpresa> buscaEmpresa(String dado, char tipo) {
        MinhaEmpresa emp;
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

                    sql = " SELECT * FROM minha_empresa WHERE fantasia LIKE '%" + dado + "%' ORDER BY id ";
                    break;

                case 'i':

                    sql = " SELECT * FROM minha_empresa WHERE razao_social LIKE '%" + dado + "%' ORDER BY id ";
                    break;

                case 't':
                    sql = " SELECT * FROM minha_empresa WHERE cnpj = '" + dado + "' ORDER BY id ";
                    break;

                case 'a':
                    sql = " SELECT * FROM minha_empresa  WHERE logradouro LIKE '%" + dado + "%' ORDER BY id ";
                    break;
                case 'p':
                    sql = " SELECT * FROM minha_empresa WHERE id = '" + dado + "' ORDER BY id ";
                    break;
                default:
                    break;
            }

            // criar o statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {

                emp = RetornObjetoImagem(rs);

                minhaEmpresas.add(emp);

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
        return minhaEmpresas;
    }

    public MinhaEmpresa buscEmpPid(int id) {
        MinhaEmpresa empre = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM minha_empresa  WHERE minha_empresa.id = ? ";

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

    public MinhaEmpresa mostrarUltimo() {
        Connection connection = null;
        PreparedStatement pstm = null;
        MinhaEmpresa emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = "SELECT * FROM minha_empresa  WHERE minha_empresa.id = (SELECT MAX(id) AS maxID FROM minha_empresa) ";

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

    public MinhaEmpresa mostrarPrimeiro() {
        Connection connection = null;
        PreparedStatement pstm = null;
        MinhaEmpresa empr = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM minha_empresa WHERE minha_empresa.id = (SELECT MIN(id) AS minID FROM minha_empresa) ";

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

    public MinhaEmpresa mostrarProximo(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        MinhaEmpresa emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // CRIAR SQL
            String sql = "SELECT * FROM minha_empresa WHERE minha_empresa.id > '" + id + "' ORDER BY minha_empresa.id limit 1";

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

    public MinhaEmpresa mostrarAnterior(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        MinhaEmpresa emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM minha_empresa  WHERE minha_empresa.id < '" + id + "' ORDER BY minha_empresa.id desc limit 1";

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

    public MinhaEmpresa RetornObjeto(ResultSet rs) throws SQLException {
        MinhaEmpresa emp = null;
        if (rs != null) {
            Cep cep = new Cep(null, rs.getString("cep"), rs.getString("logradouro"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("uf"));

            emp = new MinhaEmpresa(rs.getInt("id"), rs.getString("fantasia"), rs.getString("razao_social"), rs.getString("email"), rs.getString("numero"), rs.getString("cnpj"),
                    rs.getString("fone_fixo"), rs.getString("celular1"), rs.getString("celular2"), cep);

        }
        return emp;
    }

    
    
    public MinhaEmpresa RetornObjetoImagem(ResultSet rs) throws SQLException, FileNotFoundException ,IOException {
       
        MinhaEmpresa emp = null;
        if (rs != null) {
            
            Cep cep = new Cep(null, rs.getString("cep"), rs.getString("logradouro"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("uf"));
            confBanco.load(new FileInputStream("/neurocenter/banco.ini"));
            File arquivo = new File(confBanco.getProperty("ip")+(rs.getInt("id"))+".jpg");
            emp = new MinhaEmpresa(rs.getInt("id"), rs.getString("fantasia"), rs.getString("razao_social"), rs.getString("email"), rs.getString("numero"), rs.getString("cnpj"),
                    rs.getString("fone_fixo"), rs.getString("celular1"), rs.getString("celular2"), cep, arquivo);

        }
        return emp;
    }
}
