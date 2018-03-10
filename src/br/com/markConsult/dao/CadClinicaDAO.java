/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Cep;
import br.com.markConsult.entidades.Clinica;
import br.com.markConsult.entidades.LogoClinica;
import java.io.File;
import java.io.FileInputStream;
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
public class CadClinicaDAO extends AbstractConecxaoDAO {

    /**
     * @uml.property name="idInserido"
     */
    Integer idInserido = null;
    /**
     * @uml.property name="consulta"
     * @uml.associationEnd
     */
    Clinica clinica = null;
    boolean idAlterado;

    /**
     * @uml.property name="consultas"
     */
    public CadClinicaDAO() {
        this.idAlterado = false;
    }
    private final ArrayList<Clinica> clinicas = new ArrayList<>();

    public Integer inseClinica(Clinica clinica) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            
            File arquivo = new File(clinica.getLogoClinica().getNome());  
            FileInputStream imagemStream = new FileInputStream(arquivo);  
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // criar o sql
            String sql = "INSERT INTO clinicas (fantasia, razao_social, email, cep, municipio, uf, logradouro, numero, bairro, cnpj,fone_fixo, celular1, celular2, nome, figura)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

            // criar o statement
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // setar os params
            int index = 0;
            pstm.setString(++index, clinica.getFantasia());
            pstm.setString(++index, clinica.getRazao());
            pstm.setString(++index, clinica.getEmail());
            pstm.setString(++index, clinica.getCep().getCodCep());
            pstm.setString(++index, clinica.getCep().getCidade());
            pstm.setString(++index, clinica.getCep().getUf());
            pstm.setString(++index, clinica.getCep().getLogradouro());
            pstm.setString(++index, clinica.getNumero());
            pstm.setString(++index, clinica.getCep().getBairro());
            pstm.setString(++index, clinica.getCnpj());
            pstm.setString(++index, clinica.getFoneFixo());
            pstm.setString(++index, clinica.getCelular1());
            pstm.setString(++index, clinica.getCelular2());
            pstm.setString(++index, clinica.getLogoClinica().getNome());
            pstm.setBinaryStream(++index, imagemStream, (int)arquivo.length());
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

    public boolean altClinica(Clinica clinica) {
        
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
           File arquivo = new File(clinica.getLogoClinica().getNome());  
            FileInputStream imagemStream = new FileInputStream(arquivo);  
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // criar o sql
            String sql = "UPDATE clinicas SET  fantasia = ?, razao_social = ?, "
                    + "email = ?, cep = ?, municipio = ?, uf = ?, logradouro = ?, numero = ?, bairro = ?, "
                    + "cnpj = ?, fone_fixo = ?, celular1 = ?, celular2 = ?, nome = ?, figura = ? WHERE id = ?";

            // criar o statement
            pstm = connection.prepareStatement(sql);

            // setar os params
            int index = 0;
            pstm.setString(++index, clinica.getFantasia());
            pstm.setString(++index, clinica.getRazao());
            pstm.setString(++index, clinica.getEmail());
            pstm.setString(++index, clinica.getCep().getCodCep());
            pstm.setString(++index, clinica.getCep().getCidade());
            pstm.setString(++index, clinica.getCep().getUf());
            pstm.setString(++index, clinica.getCep().getLogradouro());
            pstm.setString(++index, clinica.getNumero());
            pstm.setString(++index, clinica.getCep().getBairro());
            pstm.setString(++index, clinica.getCnpj());
            pstm.setString(++index, clinica.getFoneFixo());
            pstm.setString(++index, clinica.getCelular1());
            pstm.setString(++index, clinica.getCelular2());
            pstm.setString(++index, clinica.getLogoClinica().getNome());
            pstm.setBinaryStream(++index, imagemStream, (int)arquivo.length());
            pstm.setInt(++index, clinica.getId());
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

    public boolean rmClinica(int id) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            String sql = "delete from clinicas where id = ?";

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

    public List<Clinica> buscaEpresa(String dado, char tipo) {
        Clinica emp;
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

                    sql = " SELECT * FROM clinicas WHERE fantasia LIKE '%" + dado + "%' ORDER BY id ";
                    break;

                case 'i':

                    sql = " SELECT * FROM clinicas WHERE razao_social LIKE '%" + dado + "%' ORDER BY id ";
                    break;

                case 't':
                    sql = " SELECT * FROM clinicas WHERE cnpj = '" + dado + "' ORDER BY id ";
                    break;

                case 'a':
                    sql = " SELECT * FROM clinicas  WHERE logradouro LIKE '%" + dado + "%' ORDER BY id ";
                    break;
                case 'p':
                    sql = " SELECT * FROM clinicas WHERE id = '" + dado + "' ORDER BY id ";
                    break;
                default:
                    break;
            }

            // criar o statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {

                emp = RetornObjetoImagem(rs);

                clinicas.add(emp);

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
        return clinicas;
    }

    public Clinica buscEmpPid(int id) {
        Clinica empre = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM clinicas  WHERE clinicas.id = ? ";

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

    public Clinica mostrarUltimo() {
        Connection connection = null;
        PreparedStatement pstm = null;
        Clinica emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = "SELECT * FROM clinicas  WHERE clinicas.id = (SELECT MAX(id) AS maxID FROM clinicas) ";

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

    public Clinica mostrarPrimeiro() {
        Connection connection = null;
        PreparedStatement pstm = null;
        Clinica empr = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM clinicas WHERE clinicas.id = (SELECT MIN(id) AS minID FROM clinicas) ";

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

    public Clinica mostrarProximo(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        Clinica emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // CRIAR SQL
            String sql = "SELECT * FROM clinicas WHERE clinicas.id > '" + id + "' ORDER BY clinicas.id limit 1";

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

    public Clinica mostrarAnterior(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        Clinica emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM clinicas  WHERE clinicas.id < '" + id + "' ORDER BY clinicas.id desc limit 1";

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

    public Clinica RetornObjeto(ResultSet rs) throws SQLException {
        Clinica emp = null;
        if (rs != null) {
            Cep cep = new Cep(null, rs.getString("cep"), rs.getString("logradouro"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("uf"));

            emp = new Clinica(rs.getInt("id"), rs.getString("fantasia"), rs.getString("razao_social"), rs.getString("email"), rs.getString("numero"), rs.getString("cnpj"),
                    rs.getString("fone_fixo"), rs.getString("celular1"), rs.getString("celular2"), cep);

        }
        return emp;
    }

    
    
    public Clinica RetornObjetoImagem(ResultSet rs) throws SQLException, IOException {
       
        Clinica emp = null;
        if (rs != null) {
            
            Cep cep = new Cep(null, rs.getString("cep"), rs.getString("logradouro"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("uf"));
            LogoClinica logoClinica = new LogoClinica(rs.getString("nome"),rs.getBytes("figura"));
            emp = new Clinica(rs.getInt("id"), rs.getString("fantasia"), rs.getString("razao_social"), rs.getString("email"), rs.getString("numero"), rs.getString("cnpj"),
                    rs.getString("fone_fixo"), rs.getString("celular1"), rs.getString("celular2"), cep, logoClinica);

        }
        return emp;
    }
}
