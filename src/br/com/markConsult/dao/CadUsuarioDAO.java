/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Especialidade;
import br.com.markConsult.entidades.Usuario;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class CadUsuarioDAO extends AbstractConecxaoDAO implements ICadUsuarioDAO{
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
	 * @uml.property  name="Convenio"
	 * @uml.associationEnd  
	 */
	Usuario usuario = null;
	/**
	 * @uml.property  name="convenio"
	 */
	private List<Usuario> usuarios = new ArrayList<>();
	
    
        
              public Integer inserir(Usuario usuario) {
    
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			
                        
                        String sql;
                        
                        if (usuario.getEspecialidade().getId() != null) {
			// criar o sql
			 sql = "INSERT INTO usuarios (usuario, senha, crm, id_especialidade, "
                                 + "he_adm, he_medico, he_atendente, nome_medico, cod_tema) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			// criar o statement
			pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			// setar os params
			int index = 0;
			pstm.setString(++index, usuario.getNome());
			pstm.setString(++index, criptografia(usuario.getSenha()));
                        pstm.setString(++index, usuario.getCrm());
                        pstm.setInt(++index, usuario.getEspecialidade().getId());
			pstm.setBoolean(++index, usuario.isHeAdm());
                        pstm.setBoolean(++index, usuario.isHeMedico());
                        pstm.setBoolean(++index, usuario.isHeAtendente());
                        pstm.setString(++index, usuario.getNomeMedico());
                        pstm.setInt(++index, usuario.getCodTema());
                        
                        }else{
                          // criar o sql
			 sql = "INSERT INTO usuarios (usuario, senha, he_adm, he_medico, he_atendente,cod_tema) VALUES (?, ?, ?, ?, ?, ?)";

			// criar o statement
			pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			// setar os params
			int index = 0;
			pstm.setString(++index, usuario.getNome());
			pstm.setString(++index, criptografia(usuario.getSenha()));
			pstm.setBoolean(++index, usuario.isHeAdm());
                        pstm.setBoolean(++index, usuario.isHeMedico());
                        pstm.setBoolean(++index, usuario.isHeAtendente());
                        pstm.setInt(++index, usuario.getCodTema());
                        }
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
  
              
       public static String criptografia(String original){  
        String senha = null;  
        MessageDigest algoritmo;  
        byte messageDigest[];  
        StringBuilder hexString;  
        try {  
            //algoritmo =MessageDigest.getInstance("SHA-256");// 64 letras  
            algoritmo = MessageDigest.getInstance("MD5");  // 32 letras  
            messageDigest = algoritmo.digest(original.getBytes("UTF-8"));  
            hexString = new StringBuilder();  
            for (byte b : messageDigest) {  
                hexString.append(String.format("%02X", 0xFF & b));  
            }  
            senha = hexString.toString();  
        } catch (    NoSuchAlgorithmException | UnsupportedEncodingException e) {  
        }  
        //System.out.println("Senha normal: "+original+" - Senha criptografada: "+senha);  
        return senha;  
    }  
 
    
    

        @Override
         public boolean alterar(Usuario usuario){
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
                    // pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql; 
                        
                        if (usuario.getEspecialidade().getId() != null) {
                        
                    
                        if (usuario.isResetSenha()) {
                        sql = "UPDATE usuarios SET senha = ?, crm = ?, id_especialidade = ?,"
                                + "he_adm = ?, he_medico = ?, he_atendente = ?, nome_medico = ?  WHERE id = ? ";
                        // criar o statement
			pstm = connection.prepareStatement(sql);                 
			// setar os params
			int index = 0;
                        pstm.setString(++index, criptografia(usuario.getSenha()));
                        pstm.setString(++index, usuario.getCrm());
                        pstm.setInt(++index, usuario.getEspecialidade().getId());
			pstm.setBoolean(++index, usuario.isHeAdm());
			pstm.setBoolean(++index, usuario.isHeMedico());
			pstm.setBoolean(++index, usuario.isHeAtendente());
                        pstm.setString(++index, usuario.getNomeMedico());
                        pstm.setInt(++index, usuario.getId());
                    }else{
                          sql = "UPDATE usuarios SET crm = ?, id_especialidade = ?,"
                                + "he_adm = ?, he_medico = ?, he_atendente = ?, nome_medico = ?  WHERE id = ?";
                        // criar o statement
			pstm = connection.prepareStatement(sql);
	
			                 
			// setar os params
			int index = 0;
                        pstm.setString(++index, usuario.getCrm());
                        pstm.setInt(++index, usuario.getEspecialidade().getId());
			pstm.setBoolean(++index, usuario.isHeAdm());
			pstm.setBoolean(++index, usuario.isHeMedico());
			pstm.setBoolean(++index, usuario.isHeAtendente());
                        pstm.setString(++index, usuario.getNomeMedico());
                        pstm.setInt(++index, usuario.getId());
                        
                        }	
                        }else{
                            if (usuario.isResetSenha()) {
                        sql = "UPDATE usuarios SET senha = ?, crm = ?, id_especialidade = ?,"
                                + "he_adm = ?, he_medico = ?, he_atendente = ?, nome_medico = ?  WHERE id = ? ";
                        // criar o statement
			pstm = connection.prepareStatement(sql);                 
			// setar os params
			int index = 0;
                        pstm.setString(++index, criptografia(usuario.getSenha()));
                        pstm.setString(++index, usuario.getCrm());
                         if(usuario.getEspecialidade().getId() == null){
                         pstm.setNull(++index, java.sql.Types.INTEGER );
                        }else{
                        pstm.setInt(++index, usuario.getEspecialidade().getId());
                        }
			pstm.setBoolean(++index, usuario.isHeAdm());
			pstm.setBoolean(++index, usuario.isHeMedico());
			pstm.setBoolean(++index, usuario.isHeAtendente());
                        pstm.setString(++index, usuario.getNomeMedico());
                        pstm.setInt(++index, usuario.getId());
                    }else{
                          sql = "UPDATE usuarios SET crm = ?, id_especialidade = ?,"
                                + "he_adm = ?, he_medico = ?, he_atendente = ?, nome_medico = ?  WHERE id = ?";
                        // criar o statement
			pstm = connection.prepareStatement(sql);
	
			                 
			// setar os params
			int index = 0;
                        pstm.setString(++index, usuario.getCrm());
                        pstm.setInt(++index, 0);
			pstm.setBoolean(++index, usuario.isHeAdm());
			pstm.setBoolean(++index, usuario.isHeMedico());
			pstm.setBoolean(++index, usuario.isHeAtendente());
                        pstm.setString(++index, usuario.getNomeMedico());
                        pstm.setInt(++index, usuario.getId());
                        
                        }	
                        
                        }

                         // executar
			pstm.executeUpdate();

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
                
     public boolean alteraTema(Usuario usuario){
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
                    // pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql

                      
                        String sql = "UPDATE usuarios SET cod_tema = ?  WHERE id = ? ";
                        // criar o statement
			pstm = connection.prepareStatement(sql);                 
			// setar os params
			int index = 0;
                       
                        pstm.setInt(++index, usuario.getCodTema());
                        pstm.setInt(++index, usuario.getId());
                        

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
         public boolean escBlocBarra(Usuario usuario){
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
                    // pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "UPDATE usuarios SET  esconde_barra = ?  WHERE id = ? ";
                        // criar o statement
			pstm = connection.prepareStatement(sql);
			int id = usuario.getId();                  
			// setar os params
			int index = 0;
			pstm.setBoolean(++index, usuario.isEscBarra());
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
         
         public boolean trocarSenha(Usuario usuario){
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
                    // pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "UPDATE usuarios SET  senha = ? WHERE usuario = ?";
                        // criar o statement
			pstm = connection.prepareStatement(sql);
	
			String usu = usuario.getNome();                  
			// setar os params
			int index = 0;
                        pstm.setString(++index, criptografia(usuario.getSenha()));
                        pstm.setString(++index, usu);
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

        public Usuario remover(Integer id) {
                Usuario usu = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			usu = procuraPorID(id);
			
			String sql = "delete from usuarios where id = ?";

			pstm = connection.prepareStatement(sql);	
			pstm.setInt(1, id);
			pstm.execute();
			commitTransaction(connection);

		} catch (Exception e) {
			try {
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, pstm, connection);
		}
		return usu;  
         }

        public Usuario procuraPorID(Integer id) {
       
                Usuario con = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE usuarios.id = ?";
                        

			// criar o statement
			pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
			
			con = retornObUsuario(rs);
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
		return con;
    
    }
        public Usuario procuraPorSenha(String senha) {
       
                Usuario usu = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE senha = ?";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, criptografia(senha));
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
			
			usu = retornObUsuario(rs);
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
		return usu;
    
    }
     public Usuario procuraPorUsu(String usuario) {
       
                Usuario usu = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql ="SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE usuario = ?"; 

			// criar o statement
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, usuario);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
			
			usu = retornObUsuario(rs);
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
		return usu;
    
    }
        
        public Usuario login(String usuario, String senha){
       
                Usuario usu = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE usuario = ?  AND senha = ? ";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, usuario);
			pstm.setString(2, criptografia(senha));
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			//desencripta

			usu = retornObUsuario(rs);
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
		return usu;
    
    }

        public List<Usuario> BuscaUsuarios(String nome, char tipo) {
		Usuario usu ;
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
                String senha = criptografia(nome);
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// CRIAR SQL
			String sql="";
			switch (tipo) {
			case 'e':
				sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE  usuario like ('%"+nome+"%') ORDER BY id"; 
                               
				break;
			case 'i':
				sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE senha = '"+senha+"' ORDER BY id"; 
                               
				break;
                            
                        case 't':
				sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE usuarios.id = '"+nome+"' ORDER BY id"; 
                               
				break;
                            
                            case 'a':
				sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE crm like ('%"+nome+"%') ORDER BY id"; 
                               
				break;
			
			default:
				break;
			}
			
			// criar o statement
			stm = connection.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
                      
                              
				
                                
                                 
			usu = retornObUsuario(rs);
                        usuarios.add(usu);
                     
                        
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
		return usuarios;  
    }
        
        
        public List<Usuario> BuscaMedicos(String nome, char tipo) {
		Usuario usu ;
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
                String senha = criptografia(nome);
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// CRIAR SQL
			String sql="";
			switch (tipo) {
			case 'e':
				sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE  usuario like ('%"+nome+"%') AND he_medico = true ORDER BY id"; 
                               
				break;
			case 'i':
				sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE senha = '"+senha+"' AND he_medico = true ORDER BY id"; 
                               
				break;
                            
                        case 't':
				sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE usuarios.id = '"+nome+"' AND he_medico = true ORDER BY id"; 
                               
				break;
                            
                            case 'a':
				sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE crm like ('%"+nome+"%') AND he_medico = true ORDER BY id"; 
                               
				break;
			
			default:
				break;
			}
			
			// criar o statement
			stm = connection.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
                      
                              
				
                                
                                 
			usu = retornObUsuario(rs);
                        usuarios.add(usu);
                     
                        
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
		return usuarios;  
    }
    
            public Usuario mostrarUltimo(){
        
                Usuario conv = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// CRIAR SQL
			String sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE usuarios.id = (SELECT MAX(id) AS maxID FROM usuarios)";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
                            
			conv = retornObUsuario(rs);
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
		return conv;
        }
    
        
            public Usuario mostrarPrimeiro(){
            Usuario conv = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
                     
                        
			// CRIAR SQL
			String sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE usuarios.id = (SELECT MIN(id) AS minID FROM usuarios)";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
			
			conv = retornObUsuario(rs);
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
		return conv;
    }
    
    
            public Usuario mostrarProximo(int id){
                Usuario usu = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE usuarios.id > '"+id+"' ORDER BY id limit 1";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			//pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
			
			usu = retornObUsuario(rs);
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
		return usu;
    }
        
             public Usuario mostrarAnterior(int id){
             Usuario u = null;
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);

			// CRIAR SQL
			String sql = "SELECT usuarios.*, especialidades.ds_especialidade "
                            + "FROM usuarios "
                            + "LEFT JOIN especialidades ON especialidades.id = usuarios.id_especialidade "
                            + "WHERE usuarios.id < '"+id+"' ORDER BY id desc limit 1";

			// criar o statement
			pstm = connection.prepareStatement(sql);
			//pstm.setInt(1, id);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
			
			u = retornObUsuario(rs);
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
		return u;
                
                
              
         }
                 
                   public Usuario retornObUsuario(ResultSet rs) throws SQLException{
                   Especialidade esp = new Especialidade(rs.getInt("id_especialidade"), rs.getString("ds_especialidade"));
                    Usuario usu = new Usuario(rs.getInt("id"), rs.getString("usuario"), 
                            rs.getString("senha"),rs.getString("crm"),rs.getString("nome_medico"),esp, 
                            rs.getBoolean("he_adm"),rs.getBoolean("he_medico"),
                            rs.getBoolean("he_atendente"), false, rs.getBoolean("esconde_barra"),rs.getInt("cod_tema"));
                     return usu;               
                }
}
