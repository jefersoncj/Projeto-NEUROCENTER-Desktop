/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.CondPagto;
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
public class CadCondPagtoDAO extends AbstractConecxaoDAO implements ICadCondPagtoDAO{
    /**
	 * @uml.property  name="idInserido"
	 */
	Integer idInserido = null;
        Integer idInserido2 = null;
	
	/**
	 * @uml.property  name="idInserido"
	 */
	boolean idAlterado = false;
	
	
	/**
	 * @uml.property  name="CadCondPagto"
	 * @uml.associationEnd  
	 */
	CondPagto condPagto = null;
	/**
	 * @uml.property  name="CondPagto"
	 */
	private final List<CondPagto> condPagtos = new ArrayList<>();
        @Override
      public Integer inserir(CondPagto condPagto) {
    
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "INSERT INTO cond_pagto (descricao) VALUES (?)";
			// criar o statement
			 pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                         
			// setar os params
			int index = 0;
			pstm.setString(++index, condPagto.getCondPagt());
                        
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

    @Override
     public boolean alterar(CondPagto condPagto){
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
                    // pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "UPDATE cond_pagto SET descricao = ? WHERE id = ?";

			// criar o statement
			pstm = connection.prepareStatement(sql);
	
			int id = condPagto.getId();
			String tipo_mate = condPagto.getCondPagt();
                        
			// setar os params
			int index = 0;
			pstm.setString(++index, tipo_mate);
                        pstm.setInt(++index, id);

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

    

    @Override
    public boolean remover(Integer id) {
            boolean excluido = false;

		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			
			
			String sql = "delete from cond_pagto where id = ?";

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

    @Override
    public CondPagto procuraPorID(Integer id) {
       
                CondPagto condPagtos1 = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT * FROM cond_pagto WHERE id = ?";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			condPagtos1 = retornObjt(rs);
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
		return condPagtos1;
    
    }

    @Override
    public List<CondPagto> BuscaCondPagto(String nome, char tipo) {
            
		CondPagto condPagtos1;
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// CRIAR SQL
			String sql="";
			switch (tipo) {
			case 'e':
				sql = "SELECT * FROM cond_pagto WHERE descricao like ('%"+nome+"%') ORDER BY id";
				break;
			case 'i':
				sql = "SELECT * FROM cond_pagto WHERE id =('"+nome+"') ORDER BY id";
				break;
			
			default:
				break;
			}
			
			// criar o statement
			stm = connection.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
                      
                  
			condPagtos1 = retornObjt(rs);   
                        condPagtos.add(condPagtos1);
                     
                        
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
		return condPagtos;  
    }
    
        @Override
    public CondPagto mostrarUltimo(){
        
                CondPagto condPagtos1 = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
                        
			// CRIAR SQL
			String sql = "SELECT * FROM cond_pagto WHERE id = (SELECT MAX(id) AS maxID FROM cond_pagto)";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			condPagtos1 = retornObjt(rs);
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
		return condPagtos1;
        }
    
        
        @Override
    public CondPagto mostrarPrimeiro(){
        CondPagto condPagtos1 = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
                     
                        
			// CRIAR SQL
			String sql = "SELECT * FROM cond_pagto WHERE id = (SELECT MIN(id) AS minID FROM cond_pagto)";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
			condPagtos1 = retornObjt(rs);
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
		return condPagtos1;
    }
    
    
        @Override
    public CondPagto mostrarProximo(int id){
                CondPagto condPagtos1 = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT * FROM cond_pagto WHERE cond_pagto.id > '"+id+"' ORDER BY id limit 1";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			//pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
			condPagtos1 = retornObjt(rs);
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
		return condPagtos1;
    }
        
        @Override
         public CondPagto mostrarAnterior(int id){
             CondPagto condPagtos1 = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT * FROM cond_pagto WHERE cond_pagto.id < '"+id+"' ORDER BY id desc limit 1";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			//pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			condPagtos1 = retornObjt(rs);
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
		return condPagtos1;
         }
         
         public CondPagto retornObjt(ResultSet rs) throws SQLException {

        CondPagto esp = new CondPagto(rs.getInt("id"), rs.getString("descricao"));

        return esp;


    }
}
