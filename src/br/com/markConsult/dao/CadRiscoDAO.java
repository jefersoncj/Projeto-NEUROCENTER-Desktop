/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Risco;
import br.com.markConsult.entidades.TipoRisco;
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
public class CadRiscoDAO extends AbstractConecxaoDAO {

    /**
     * @uml.property name="idInserido"
     */
    Integer idInserido = null;
    /**
     * @uml.property name="consulta"
     * @uml.associationEnd
     */
    Risco risco = null;
    boolean idAlterado;

    /**
     * @uml.property name="consultas"
     */
    public CadRiscoDAO() {
        this.idAlterado = false;
    }
    private final ArrayList<Risco> riscos = new ArrayList<>();

    public Integer inseRisco(Risco risco) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            
             
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // criar o sql
            String sql = "INSERT INTO riscos (id_tipo_risco, desc_risco)VALUES (?, ?)";

            // criar o statement
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // setar os params
            int index = 0;
            pstm.setInt(++index, risco.getTipoRisco().getId());
            pstm.setString(++index, risco.getDescRisco());
          
           

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

    public boolean altRisco(Risco risco) {
        
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
         
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // criar o sql
            String sql = "UPDATE riscos SET id_tipo_risco = ?, desc_risco = ? WHERE id = ?";

            // criar o statement
            pstm = connection.prepareStatement(sql);

            // setar os params
            int index = 0;
            pstm.setInt(++index, risco.getTipoRisco().getId());
            pstm.setString(++index, risco.getDescRisco());
            pstm.setInt(++index, risco.getId());
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

    public boolean rmRisco(int id) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            String sql = "delete from riscos where id = ?";

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

    public List<Risco> buscaRisco(String dado, char tipo) {
        Risco emp;
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

                    sql = " SELECT riscos.*, tp.desc_tipo_risco "
                            + "FROM riscos "
                            + "LEFT JOIN tipo_risco AS tp ON tp.id = riscos.id_tipo_risco "
                            + "WHERE desc_risco LIKE '%" + dado + "%' ORDER BY id ";
                    break;
                case 'i':
                    sql = " SELECT riscos.*, tp.desc_tipo_risco "
                            + "FROM riscos "
                            + "LEFT JOIN tipo_risco AS tp ON tp.id = riscos.id_tipo_risco "
                            + "WHERE riscos.id = '" + dado + "' ORDER BY riscos.id ";
                    break;
                default:
                    break;
            }

            // criar o statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {

                emp = RetornObjeto(rs);

                riscos.add(emp);

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
        return riscos;
    }

    public Risco buscRiscPid(int id) {
        Risco empre = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT riscos.*, tp.desc_tipo_risco "
                         + "FROM riscos "
                         + "LEFT JOIN tipo_risco AS tp ON tp.id = riscos.id_tipo_risco "
                         + "WHERE riscos.id = ? ";

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

    public Risco mostrarUltimo() {
        Connection connection = null;
        PreparedStatement pstm = null;
        Risco emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT riscos.*, tp.desc_tipo_risco "
                        + "FROM riscos "
                        + "LEFT JOIN tipo_risco AS tp ON tp.id = riscos.id_tipo_risco "
                        + "WHERE riscos.id = (SELECT MAX(id) AS maxID FROM riscos) ";

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

    public Risco mostrarPrimeiro() {
        Connection connection = null;
        PreparedStatement pstm = null;
        Risco empr = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT riscos.*, tp.desc_tipo_risco "
                            + "FROM riscos "
                            + "LEFT JOIN tipo_risco AS tp ON tp.id = riscos.id_tipo_risco "
                            + "WHERE riscos.id = (SELECT MIN(id) AS minID FROM riscos) ";

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

    public Risco mostrarProximo(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        Risco emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // CRIAR SQL
            String sql = " SELECT riscos.*, tp.desc_tipo_risco "
                            + "FROM riscos "
                            + "LEFT JOIN tipo_risco AS tp ON tp.id = riscos.id_tipo_risco "
                            + "WHERE riscos.id > '" + id + "' ORDER BY riscos.id limit 1";

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

    public Risco mostrarAnterior(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        Risco emp = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT riscos.*, tp.desc_tipo_risco "
                            + "FROM riscos "
                            + "LEFT JOIN tipo_risco AS tp ON tp.id = riscos.id_tipo_risco "
                            + "WHERE riscos.id < '" + id + "' ORDER BY riscos.id desc limit 1";

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

    public Risco RetornObjeto(ResultSet rs) throws SQLException {
        Risco emp = null;
        if (rs != null) {
            
            TipoRisco tr = new TipoRisco(rs.getInt("id_tipo_risco"), rs.getString("desc_tipo_risco"));
            emp = new Risco(rs.getInt("id"), tr, rs.getString("desc_risco"));

        }
        return emp;
    }
}
