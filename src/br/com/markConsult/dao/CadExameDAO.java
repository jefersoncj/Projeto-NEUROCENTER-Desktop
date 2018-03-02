/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Exame;
import br.com.markConsult.entidades.Periodicidade;
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
public class CadExameDAO extends AbstractConecxaoDAO {

    /**
     * @uml.property name="idInserido"
     */
    Integer idInserido = null;
    /**
     * @uml.property name="consulta"
     * @uml.associationEnd
     */
    Exame exame = null;
    boolean idAlterado;

    /**
     * @uml.property name="consultas"
     */
    public CadExameDAO() {
        this.idAlterado = false;
    }
    private final ArrayList<Exame> exames = new ArrayList<>();

    public Integer inseExame(Exame exame) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            
             
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // criar o sql
            String sql = "INSERT INTO exames (desc_exame,valor)VALUES (?, ?)";

            // criar o statement
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // setar os params
            int index = 0;
            pstm.setString(++index, exame.getDescExame());
            pstm.setDouble(++index, exame.getValor());
          
           

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

    public boolean altExame(Exame exame) {
        
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
         
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // criar o sql
            String sql = "UPDATE exames SET  desc_exame = ?, valor = ?  WHERE id = ?";

            // criar o statement
            pstm = connection.prepareStatement(sql);

            // setar os params
            int index = 0;
            pstm.setString(++index, exame.getDescExame());
            pstm.setDouble(++index, exame.getValor());
            pstm.setInt(++index, exame.getId());
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

    public boolean rmExame(int id) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            String sql = "delete from exames where id = ?";

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

    public List<Exame> buscaEpresa(String dado, char tipo) {
        Exame emp;
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

                    sql = " SELECT * FROM exames "
                            + "WHERE desc_exame LIKE '%" + dado + "%' ORDER BY exames.id ";
                    break;
                case 'i':
                    sql = " SELECT * FROM exames "
                            + "WHERE exames.id = '" + dado + "' ORDER BY exames.id ";
                    break;
                default:
                    break;
            }

            // criar o statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {

                emp = RetornObjeto(rs);

                exames.add(emp);

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
        return exames;
    }

    public Exame buscEmpPid(int id) {
        Exame empre = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM exames "
                            + "WHERE exames.id = ? ";

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

    public Exame mostrarUltimo() {
        Connection connection = null;
        PreparedStatement pstm = null;
        Exame emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM exames "
                            + "WHERE exames.id = (SELECT MAX(id) AS maxID FROM exames) ";

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

    public Exame mostrarPrimeiro() {
        Connection connection = null;
        PreparedStatement pstm = null;
        Exame empr = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM exames "
                            + "WHERE exames.id = (SELECT MIN(id) AS minID FROM exames) ";

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

    public Exame mostrarProximo(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        Exame emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // CRIAR SQL
            String sql = " SELECT * FROM exames "
                            + "WHERE exames.id > '" + id + "' ORDER BY exames.id limit 1";

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

    public Exame mostrarAnterior(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        Exame emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM exames "
                            + "WHERE exames.id < '" + id + "' ORDER BY exames.id desc limit 1";

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

    public Exame RetornObjeto(ResultSet rs) throws SQLException {
        Exame emp = null;
        if (rs != null) {
            emp = new Exame(rs.getInt("id"),rs.getString("desc_exame"), rs.getDouble("valor"));

        }
        return emp;
    }
}
