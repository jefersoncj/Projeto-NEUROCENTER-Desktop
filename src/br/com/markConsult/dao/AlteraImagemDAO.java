/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Jeferson1
 */
public class AlteraImagemDAO extends AbstractConecxaoDAO{
    
    boolean idAlterado;

    /**
     * @param caminho
     * @return 
     * @uml.property name="consultas"
     */
    
    public boolean altImagem(String caminho) {
        
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
           File arquivo = new File(caminho);  
            FileInputStream imagemStream = new FileInputStream(arquivo);  
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // criar o sql
            String sql = "UPDATE imagem_fundo SET  imagem = ?  WHERE id = ?";
            //String sql = "INSERT INTO imagem_fundo VALUES(?,?)";

            // criar o statement
            pstm = connection.prepareStatement(sql);

            // setar os params
            int index = 0;
       
            pstm.setBinaryStream(++index, imagemStream, (int)arquivo.length());
            pstm.setInt(++index,1);
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
    
    public byte[] buscImaPid() {
        byte[] logo = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = " SELECT * FROM imagem_fundo  WHERE id = ? ";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, 1);

            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                logo = rs.getBytes("imagem");
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
        return logo;
    }

}
