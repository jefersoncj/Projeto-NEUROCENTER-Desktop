/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Empresa;
import br.com.markConsult.entidades.EmpresaProcedimento;
import br.com.markConsult.entidades.Procedimento;
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
public class EmpresaProcedimentoDAO extends AbstractConecxaoDAO {
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
	 * @uml.property  name="EmpresaProcedimento"
	 * @uml.associationEnd  
	 */
	EmpresaProcedimento procedimento = null;
	/**
	 * @uml.property  name="procedimento"
	 */
        ArrayList<EmpresaProcedimento> procedimentoEmpresas = new ArrayList<>();

        public List<EmpresaProcedimento> BuscaProcedimetoEmpresa(String nome, char ds_procedimento,int idEmpresa) {
		
                EmpresaProcedimento ep ;
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// CRIAR SQL
			String sql="";
			switch (ds_procedimento) {
			case 'e':
				sql = "SELECT procedimento_empresa.*, procedimento.* "
                                        + " FROM procedimento_empresa "
                                        + " LEFT JOIN procedimento ON procedimento.id = procedimento_empresa.id_procedimento "
                                        + " WHERE ds_procedimento like ('%"+nome+"%') and id_empresa = ('"+idEmpresa+"') ORDER BY procedimento_empresa.id ";
				break;
			case 'i':
				sql = "SELECT procedimento_empresa.*, procedimento.* "
                                        + "FROM procedimento_empresa "
                                        + "LEFT JOIN procedimento ON procedimento.id = procedimento_empresa.id_procedimento"
                                        + "WHERE id_procedimento =('"+nome+"') and id_empresa = ('"+idEmpresa+"') procedimento_empresa.id";
				break;
			
			default:
				break;
			}
			
			// criar o statement
			stm = connection.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
            
			ep = retornProcedimentoEmpresa(rs);       
                        procedimentoEmpresas.add(ep);
                     
                        
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
		return procedimentoEmpresas;  
    }
         public boolean alterarValor(EmpresaProcedimento empresaProcedimento){
                Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
                    
                    // pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			// criar o sql
			String sql = "UPDATE procedimento_empresa SET valor = ? WHERE id = ?";

			// criar o statement
			pstm = connection.prepareStatement(sql);
                        
			// setar os params
			int index = 0;
			pstm.setDouble(++index, empresaProcedimento.getValor());
                        pstm.setInt(++index, empresaProcedimento.getId());

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
    public boolean removerEmpresasProcedimentos(List<EmpresaProcedimento> empresaProcedimentos) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            for (EmpresaProcedimento empresaProcedimento : empresaProcedimentos) {
                if (empresaProcedimento.getId() != null) {
                    String sql = "delete from procedimento_empresa where id = ?";

                    pstm = connection.prepareStatement(sql);
                    pstm.setInt(1, empresaProcedimento.getId());
                    pstm.execute();
                    commitTransaction(connection);
                }
            }
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

                 

          public EmpresaProcedimento retornProcedimentoEmpresa(ResultSet rs) throws SQLException{
              EmpresaProcedimento conv = null;
              if (rs != null) {
                         Empresa  emp =  new Empresa();
                         emp.setId(rs.getInt("id_empresa"));
                         Procedimento proce = new Procedimento(rs.getInt("id_procedimento"), rs.getString("ds_procedimento"));
                  EmpresaProcedimento ep = new EmpresaProcedimento(rs.getInt("id"),emp , proce, rs.getDouble("valor"));
                  conv = ep;
              }
              
            return conv;
          }
     
                
}
