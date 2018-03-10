/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Album;
import br.com.markConsult.entidades.Paciente;
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
public class CadAlbunsPacienteDAO extends AbstractConecxaoDAO{

    /**
     * @uml.property name="idInserido"
     */
    Integer idInserido = null;
    /**
     * @uml.property name="consulta"
     * @uml.associationEnd
     */
    Album album = null;
    boolean idAlterado;

    /**
     * @uml.property name="consultas"
     */
    public CadAlbunsPacienteDAO() {
        this.idAlterado = false;
    }
   
    private final ArrayList<Album> albums = new ArrayList<>();
   
    public Integer inseAlbum(Album alb){
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO
            

            // criar o sql
            String sql = "INSERT INTO albuns (id_paciente, desc_album)VALUES (?, ?)";
                // criar o statement
                pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                
                int index = 0;
                pstm.setInt(++index, alb.getPaciente().getId());
                pstm.setString(++index, alb.getDescAlbum());
              
                
            // executar
                pstm.executeUpdate();
               rs = pstm.getGeneratedKeys();  
                 int id = 0;  
                if(rs.next()){  
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


   
    public boolean rmAlbum(int id) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            String sql = "delete from pacientes where id = ?";

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

    
    public List<Album> buscaAlbum(Paciente p) {
        Album ip;
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;
      
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);


            // CRIAR SQL
            String sql = "SELECT * FROM albuns WHERE id_paciente = '" +p.getId()+ "' ";

            // criar o statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
               
                ip = RetornObImagemPaciente(rs);

                albums.add(ip);


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
        return albums;
    }

    public Album RetornObImagemPaciente(ResultSet rs) throws SQLException {
        Album ip;
      
        Paciente p = new Paciente();
        p.setId(rs.getInt("id_paciente"));
        ip = new  Album(rs.getInt("id"),p,rs.getString("desc_album"));
       

        
        return ip;
    }

    
}
