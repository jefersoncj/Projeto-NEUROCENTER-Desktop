/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Paciente;
import br.com.markConsult.entidades.Empresa;
import br.com.markConsult.entidades.Exame;
import br.com.markConsult.entidades.Funcao;
import br.com.markConsult.entidades.OrdemExames;
import br.com.markConsult.entidades.Periodicidade;
import br.com.markConsult.entidades.Risco;
import br.com.markConsult.entidades.Setor;
import br.com.markConsult.entidades.TipoRisco;
import br.com.markConsult.relatorios.ReportUtilsModal;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jeferson
 */
public class CadPCMSODAO extends AbstractConecxaoDAO {

    /**
     * @uml.property name="idInserido"
     */
    Integer idInserido = null;
    /**
     * @uml.property name="consulta"
     * @uml.associationEnd
     */
    Paciente paciente = null;
    boolean idAlterado;

    /**
     * @uml.property name="consultas"
     */
    public CadPCMSODAO() {
        this.idAlterado = false;
    }
   
    private final ArrayList<Risco> riscos = new ArrayList<>();
    private final ArrayList<Exame> exames = new ArrayList<>();
    private final ArrayList<Setor> setores = new ArrayList<>();
    private final ArrayList<Funcao> Funcoes = new ArrayList<>();
    private final ArrayList<OrdemExames> ordemExames = new ArrayList<>();
    
    public Integer inseriEmpresaSetor(int id_empresa, int id_setor){
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO
            

            // criar o sql
            String sql = "INSERT INTO empresa_setor (id_empresa, id_setor)VALUES (?, ?)";
                // criar o statement
                pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                
                int index = 0;
              
                pstm.setInt(++index, id_empresa);  
                pstm.setInt(++index, id_setor);
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
    
    public Integer inseriEmpresaSetorFuncao(int id_empresa, int id_setor, int id_funcao){
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO
            

            // criar o sql
            String sql = "INSERT INTO empresa_setor_funcao (id_empresa, id_setor, id_funcao)VALUES (?, ?, ?)";
                // criar o statement
                pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                
                int index = 0;
              
                pstm.setInt(++index, id_empresa);  
                pstm.setInt(++index, id_setor);
                pstm.setInt(++index, id_funcao);
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
    
    
    public Integer inseriEmpresaFuncaoRisco(int id_empresa, int id_funcao, int id_risco){
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO
            

            // criar o sql
            String sql = "INSERT INTO empresa_funcao_riscos (id_empresa, id_funcao, id_risco)VALUES (?, ?, ?)";
                // criar o statement
                pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                
                int index = 0;
              
                pstm.setInt(++index, id_empresa);  
                pstm.setInt(++index, id_funcao);
                pstm.setInt(++index, id_risco);
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
    
    
    public Integer inseriEmpresaFuncaoExame(Empresa empresa, Funcao  funcao, Exame exame, Periodicidade periodicidade){
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO
            

            // criar o sql
            String sql = "INSERT INTO empresa_funcao_exames (id_empresa, id_funcao, id_exame, id_periodicidade)VALUES (?, ?, ?, ?)";
                // criar o statement
                pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                
                int index = 0;
              
                pstm.setInt(++index, empresa.getId());  
                pstm.setInt(++index, funcao.getId());
                pstm.setInt(++index, exame.getId());
                pstm.setInt(++index, periodicidade.getId());
                
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
   
    
  
    
  
    public boolean rmSetorPorEmpresa(Empresa empresa , Setor setor) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            String sql = "DELETE FROM empresa_setor WHERE id_empresa = ? AND id_setor = ? ";

            pstm = connection.prepareStatement(sql);
            int index = 0;
            pstm.setInt(++index, empresa.getId());
            pstm.setInt(++index, setor.getId());
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
    
    
    public boolean rmFuncaoPorEmpresa(Empresa empresa ,Setor setor,  Funcao funcao) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            String sql = "DELETE FROM empresa_setor_funcao WHERE id_empresa = ? AND id_setor = ? AND id_funcao = ? ";

            pstm = connection.prepareStatement(sql);
            int index = 0;
            pstm.setInt(++index, empresa.getId());
            pstm.setInt(++index, setor.getId());
            pstm.setInt(++index, funcao.getId());
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
    
    public boolean rmRiscoPorEmpFuncao(Risco risco) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            String sql = "DELETE FROM empresa_funcao_riscos WHERE id = ? ";

            pstm = connection.prepareStatement(sql);
            int index = 0;
            pstm.setInt(++index, risco.getIdRg());
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
    
    public boolean rmExamePorEmpFuncao( Exame exame) {
        boolean excluido = false;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            String sql = "DELETE FROM empresa_funcao_exames WHERE id  = ? ";

            pstm = connection.prepareStatement(sql);
            int index = 0;
            pstm.setInt(++index, exame.getIdRg());
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
    public List<Setor> buscSetorPorEmpresa(Empresa emp) {
        Setor set ;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           

            // CRIAR SQL
            String sql = " SELECT empresa_setor.*, setores.desc_setor "
                    + "FROM empresa_setor "
                    + "LEFT JOIN setores ON setores.id = empresa_setor.id_setor "
                    + "WHERE id_empresa = ? ORDER BY desc_setor";

            // criar o statement
            pstm = connection.prepareStatement(sql);
             int index = 0;
            pstm.setInt(++index, emp.getId());


            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                set = retornObSetor(rs);
                setores.add(set);
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
        return setores;
    }
        public List<Funcao> buscFuncaoPorEmpresa(Empresa emp) {
        Funcao set ;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           

            // CRIAR SQL
            String sql = "SELECT empresa_setor_funcao.*, funcoes.desc_funcao "
                    + "FROM empresa_setor_funcao "
                    + "LEFT JOIN funcoes ON funcoes.id = empresa_setor_funcao.id_funcao "
                    + "WHERE id_empresa = ?  ORDER BY desc_funcao ";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            int index = 0;
            pstm.setInt(++index, emp.getId());
            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                set = retornObFuncao(rs);
                Funcoes.add(set);
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
        return Funcoes;
    }
    public List<Funcao> buscFuncaoPorSetorEmpresa(Empresa emp, Setor setor) {
        Funcao set ;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           

            // CRIAR SQL
            String sql = "SELECT empresa_setor_funcao.*, funcoes.desc_funcao "
                    + "FROM empresa_setor_funcao "
                    + "LEFT JOIN funcoes ON funcoes.id = empresa_setor_funcao.id_funcao "
                    + "WHERE id_empresa = ? AND id_setor = ? ORDER BY desc_funcao ";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            int index = 0;
            pstm.setInt(++index, emp.getId());
            pstm.setInt(++index, setor.getId());
            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                set = retornObFuncao(rs);
                Funcoes.add(set);
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
        return Funcoes;
    }
    
    public List<Risco> buscRiscoPorFuncaoEmpresa(Empresa emp, Funcao funcao) {
        Risco rc ;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           

            // CRIAR SQL
            String sql = "SELECT empresa_funcao_riscos.*,tp.desc_tipo_risco, riscos.id_tipo_risco,desc_risco "
                    + "FROM empresa_funcao_riscos  "
                    + "LEFT JOIN riscos ON riscos.id = empresa_funcao_riscos.id_risco "
                    + "LEFT JOIN tipo_risco AS tp ON tp.id = riscos.id_tipo_risco "
                    + "WHERE id_empresa = ? AND id_funcao = ? ORDER BY desc_risco";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            int index = 0;
            pstm.setInt(++index, emp.getId());
            pstm.setInt(++index, funcao.getId());
            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                rc = retornObRisco(rs);
                riscos.add(rc);
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
        return riscos;
    }
     public List<Exame> buscExamePorFuncaoPeriod(Empresa emp, Funcao funcao, Periodicidade p) {
        Exame exa ;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           

            // CRIAR SQL
            String sql = "SELECT empresa_funcao_exames.*, exames.*, periodicidade.* "
                    + "FROM empresa_funcao_exames   "
                    + "LEFT JOIN exames ON exames.id = id_exame "
                    + "LEFT JOIN periodicidade ON periodicidade.id = id_periodicidade "
                    + "WHERE id_empresa = ? AND id_funcao = ? AND id_periodicidade = ? ORDER BY desc_exame";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            int index = 0;
            pstm.setInt(++index, emp.getId());
            pstm.setInt(++index, funcao.getId());
            pstm.setInt(++index, p.getId());
            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                exa = retornObExames(rs);
                exames.add(exa);
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
        return exames;
    }
     
     
     public List<Exame> buscExamePorOrdem(OrdemExames or) {
        Exame exa ;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           

            // CRIAR SQL
            String sql = "SELECT * FROM itens_exames_ordens "
                       + "WHERE id_ordem = ? ORDER BY desc_exame";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            int index = 0;
            
            pstm.setInt(++index, or.getId());
            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                exa = retornObExamesResultado(rs);
                exames.add(exa);
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
        return exames;
    }
     
     public List<OrdemExames> buscaOrdemPaciente(Paciente p) {
        OrdemExames ord ;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           

            // CRIAR SQL
            String sql = "SELECT ordem_exames.*, emp.id, emp.fantasia, periodicidade.* "
                    + "FROM ordem_exames "
                    + "LEFT JOIN periodicidade ON periodicidade.id = id_periodicidade "
                    + "LEFT JOIN empresas AS emp ON emp.id = id_empresa "
                    + "WHERE id_paciente = ? ORDER BY ordem_exames.id";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            int index = 0;
            pstm.setInt(++index, p.getId());
            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                ord = retornObOrdem(rs);
                ordemExames.add(ord);
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
        return ordemExames;
    }
     
     public List<Exame> filtraExamePorFuncaoEmpresa(Empresa emp, Funcao funcao, Periodicidade p) {
        Exame exa ;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           

            // CRIAR SQL
            String sql = "SELECT empresa_funcao_exames.*, exames.*, periodicidade.* "
                    + "FROM empresa_funcao_exames   "
                    + "LEFT JOIN exames ON exames.id = id_exame "
                    + "LEFT JOIN periodicidade ON periodicidade.id = id_periodicidade "
                    + "WHERE id_empresa = ? AND id_funcao = ? AND id_periodicidade = ? ORDER BY desc_exame";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            int index = 0;
            pstm.setInt(++index, emp.getId());
            pstm.setInt(++index, funcao.getId());
            pstm.setInt(++index, p.getId());
            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                exa = retornObExames(rs);
                exames.add(exa);
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
        return exames;
    }

    
    
    
    
     public Setor retornObSetor(ResultSet rs) throws SQLException {
        Setor s = null;
        if (rs != null) {
         s = new Setor(rs.getInt("id_setor"),rs.getString("desc_setor"));
        
        }
        return s;
    }
     
     public Funcao retornObFuncao(ResultSet rs) throws SQLException {
        Funcao f = null;
        if (rs != null) {
         f = new Funcao(rs.getInt("id_funcao"),rs.getString("desc_funcao"));
        
        }
        return f;
    }
     
     public Risco retornObRisco(ResultSet rs) throws SQLException {
        Risco r = null;
        if (rs != null) {
         
         TipoRisco tr = new TipoRisco(rs.getInt("id_tipo_risco"), rs.getString("desc_tipo_risco"));
         r = new Risco(rs.getInt("id_risco"),tr,rs.getString("desc_risco"),rs.getInt("id"));
        
        }
        return r;
    }
     
     
     public Exame retornObExames(ResultSet rs) throws SQLException {
        Exame e = null;
        if (rs != null) {
            
  
         e = new Exame(rs.getInt("id_exame"), rs.getString("desc_exame"), rs.getDouble("valor"), rs.getInt("id"));
        
        }
        return e;
    }
     public Exame retornObExamesResultado(ResultSet rs) throws SQLException {
        Exame e = null;
        if (rs != null) {
            
          
         e = new Exame(rs.getInt("id_exame"), rs.getString("desc_exame"), rs.getDouble("valor"),rs.getString("resultado"), rs.getInt("id"));
        
        }
        return e;
    }

      public OrdemExames retornObOrdem(ResultSet rs) throws SQLException {
        OrdemExames or = null;
        if (rs != null) {
         Periodicidade p = new Periodicidade(rs.getInt("id_periodicidade"), rs.getString("desc_periodicidade"));
         Empresa emp = new Empresa(rs.getInt("id_empresa"), rs.getString("fantasia"));
         or = new OrdemExames(rs.getInt("id"), rs.getDate("data_ordem"),emp,p);
        
        }
        return or;
    }
    
     public void ConectRelatorio(int ordem, int id_minha_empresa){
		Connection connection = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			// pegar a connection
			connection = getConnection();
			beginTransaction(connection);
			InputStream inputStream = getClass().getResourceAsStream("/ordemExames.jasper");
            
           
                // mapa de parâmetros do relatório
                Map parametros = new HashMap();
                parametros.put("ordem", ordem);
                parametros.put("id_minha_empresa", id_minha_empresa);
                
               
              
               
               
                    // abre o relatório
                    ReportUtilsModal.openReport("Relatório de consultas", inputStream, parametros, connection);                    
                    

		} catch (Exception e) {
			try {
                            System.out.println(e.getMessage());
				rollbackTransaction(connection);
			} catch (SQLException e1) {
				throw new IllegalStateException();
			}
		} finally {
			cleanup(rs, stm, connection);
		}  
     }
}
