/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Album;
import br.com.markConsult.entidades.ArquivosPaciente;
import br.com.markConsult.entidades.ArquivosProcedimento;
import br.com.markConsult.entidades.ConsultaProcedimento;
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
public class CadImagensPacienteDAO extends AbstractConecxaoDAO{

    /**
     * @uml.property name="idInserido"
     */
    Integer idInserido = null;
    /**
     * @uml.property name="consulta"
     * @uml.associationEnd
     */
    ArquivosPaciente imagensPaciente = null;
    boolean idAlterado;

    /**
     * @uml.property name="consultas"
     */
    public CadImagensPacienteDAO() {
        this.idAlterado = false;
    }
   
    private final ArrayList<ArquivosPaciente> imagensPacientes = new ArrayList<>();
    private final ArrayList<ArquivosProcedimento> arquivosProcedimentos = new ArrayList<>();
   
    public Integer inseImagensPaciente(ArquivosPaciente imp){
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO
            

            // criar o sql
            String sql = "INSERT INTO imagens_pacientes (id_paciente, data_cadastro, obs, imagem, id_album)VALUES (?, ?, ?, ?, ?)";
                // criar o statement
                pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                
                int index = 0;
                pstm.setInt(++index, imp.getPaciente().getId());
                pstm.setDate(++index, imp.getDataCadastro());
                pstm.setString(++index, imp.getObservacao());
                pstm.setString(++index, imp.getImagem());
                pstm.setInt(++index, imp.getIdAlbum().getId());
                
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
public Integer inserirArquivoProcedimento(ArquivosProcedimento ap){
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO
            

            // criar o sql
            String sql = "INSERT INTO procedimento_arquivo (id_procedimento, data_cadastro, obs, arquivo)VALUES (?, ?, ?, ?)";
                // criar o statement
                pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                
                int index = 0;
                pstm.setInt(++index, ap.getConsultaProcedimento().getId());
                pstm.setDate(++index, ap.getDataCadastro());
                pstm.setString(++index, ap.getObservacao());
                pstm.setString(++index, ap.getArquivo());
                
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

   
    public boolean rmImagensPaciente(int id) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            String sql = "delete from imagens_pacientes where id = ?";

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

    
    public List<ArquivosPaciente> buscaImagensPaciente(Album abAlbum) {
        ArquivosPaciente ip;
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;
      
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);


            // CRIAR SQL
            String sql = "SELECT * FROM imagens_pacientes WHERE id_album = '" +abAlbum.getId()+ "' ";

            // criar o statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
               
                ip = RetornObImagemPaciente(rs);

                imagensPacientes.add(ip);


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
        return imagensPacientes;
    }
  public List<ArquivosProcedimento> buscaArquivosProcedimento(ConsultaProcedimento cp) {
        ArquivosProcedimento ip;
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;
      
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);


            // CRIAR SQL
            String sql = "SELECT * FROM procedimento_arquivo WHERE id_procedimento = '" +cp.getId()+ "' ";

            // criar o statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
               
                ip = RetornObArquivoProcedimento(rs);

                arquivosProcedimentos.add(ip);


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
        return arquivosProcedimentos;
    }
  
      public boolean rmArquivoProcedimento(int id) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            String sql = "delete from procedimento_arquivo where id = ?";

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
    public ArquivosPaciente RetornObImagemPaciente(ResultSet rs) throws SQLException {
        ArquivosPaciente ip;
       
        Paciente p = new Paciente();
        p.setId(rs.getInt("id_paciente"));
        Album ab = new Album();
        ab.setId(rs.getInt("id_album"));
        ip = new  ArquivosPaciente(rs.getInt("id"), p, rs.getDate("data_cadastro"), rs.getString("obs"),rs.getString("imagem"),ab);
       

        
        return ip;
    }
    public ArquivosProcedimento RetornObArquivoProcedimento(ResultSet rs) throws SQLException {
        ArquivosProcedimento ip;
        ConsultaProcedimento ab = new ConsultaProcedimento();
        ab.setId(rs.getInt("id_procedimento"));
        ip = new  ArquivosProcedimento(rs.getInt("id"), ab, rs.getDate("data_cadastro"), rs.getString("obs"),rs.getString("arquivo"));
        
        return ip;
    }

    
}
