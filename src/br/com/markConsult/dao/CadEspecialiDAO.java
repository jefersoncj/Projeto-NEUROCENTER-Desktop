/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Especialidade;
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
public class CadEspecialiDAO extends AbstractConecxaoDAO {

    /**
     * @uml.property name="idInserido"
     */
    Integer idInserido = null;
    Integer idInserido2 = null;
    /**
     * @uml.property name="idInserido"
     */
    boolean idAlterado = false;
    Especialidade especialidade = null;
    private final List<Especialidade> especialidades = new ArrayList<>();

    public Integer inserir(Especialidade esp) {

        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // criar o sql
            String sql = "INSERT INTO especialidades (ds_especialidade) VALUES (?)";

            // criar o statement
           pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // setar os params
            int index = 0;  
            pstm.setString(++index, esp.getEspecialidade());
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

    public boolean alterar(Especialidade esp) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // criar o sql
            String sql = "UPDATE especialidades SET ds_especialidade = ?  WHERE id = ?";

            // criar o statement
            pstm = connection.prepareStatement(sql);

            // setar os params
            int index = 0;
            pstm.setString(++index, esp.getEspecialidade());
            pstm.setInt(++index, esp.getId());



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

    public boolean remover(Integer id) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           

            String sql = "delete from especialidades where id = ?";

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

    public Especialidade procuraPorID(Integer id) {

        Especialidade esp = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = "SELECT * FROM especialidades WHERE id = ?";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {


                esp = retornObjt(rs);
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
        return esp;

    }

    public List<Especialidade> BuscaEsp(String nome, char tipo) {
        Especialidade esp;
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
                    sql = "SELECT * FROM especialidades WHERE ds_especialidade like ('%"+nome+"%') ORDER BY id";
                    break;
                case 'i':
                    sql = "SELECT * FROM especialidades WHERE id =('"+nome+"') ORDER BY id";
                    break;

                default:
                    break;
            }

            // criar o statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                esp = retornObjt(rs);
                especialidades.add(esp);

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
        return especialidades;
    }

    public Especialidade mostrarUltimo() {

        Especialidade esp = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           

            // CRIAR SQL
            String sql = "SELECT * FROM especialidades WHERE id = (SELECT MAX(id) AS maxID FROM especialidades)";

            // criar o statement
            pstm = connection.prepareStatement(sql);

            rs = pstm.executeQuery();

            while (rs.next()) {

                esp = retornObjt(rs);
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
        return esp;
    }

    public Especialidade mostrarPrimeiro() {
        Especialidade esp = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = "SELECT * FROM especialidades WHERE id = (SELECT MIN(id) AS minID FROM especialidades)";

            // criar o statement
            pstm = connection.prepareStatement(sql);

            rs = pstm.executeQuery();

            while (rs.next()) {
                esp = retornObjt(rs);
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
        return esp;
    }

    public Especialidade mostrarProximo(int id) {
        Especialidade esp = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = "SELECT * FROM especialidades WHERE especialidades.id > '"+id+"' ORDER BY id limit 1";

            // criar o statement
            pstm = connection.prepareStatement(sql);

            rs = pstm.executeQuery();

            while (rs.next()) {


                esp = retornObjt(rs);
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
        return esp;
    }

    public Especialidade mostrarAnterior(int id) {
        Especialidade es = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = "SELECT * FROM especialidades WHERE especialidades.id < '"+id+"' ORDER BY id desc limit 1";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            //pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                es = retornObjt(rs);
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
        return es;
    }

    public Especialidade retornObjt(ResultSet rs) throws SQLException {

        Especialidade esp = new Especialidade(rs.getInt("id"), rs.getString("ds_especialidade"));



        return esp;


    }
}
