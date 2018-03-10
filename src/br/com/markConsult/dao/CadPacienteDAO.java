/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Cep;
import br.com.markConsult.entidades.Consulta;
import br.com.markConsult.entidades.Convenio;
import br.com.markConsult.entidades.Empresa;
import br.com.markConsult.entidades.Funcao;
import br.com.markConsult.entidades.Paciente;
import java.sql.Connection;
import java.sql.Date;
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
public class CadPacienteDAO extends AbstractConecxaoDAO implements ICadPacienteDAO {

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
    public CadPacienteDAO() {
        this.idAlterado = false;
    }
    private final ArrayList<Paciente> pacientes = new ArrayList<>();;
    @Override
    public Integer insePaciente(Paciente paciente){
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO
            

            // criar o sql
            String sql;
            Integer idConv = paciente.getConvenio().getId();
            if (idConv == null) {
                sql = "INSERT INTO pacientes (num_convenio, nome, email, cep, municipio,"
                        + "uf, logradouro, numero, bairro, complemento, cpf,"
                        + "rg, data_nasc, fone_fixo, celular1, celular2, celular3,"
                        + "obs,tipo_sang, desabilitado,data_cadastro, nome_mae, nome_pai, id_empresa, id_funcao)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                // criar o statement
                pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                
                int index = 0;
                pstm.setString(++index, paciente.getNumConvenio());
                pstm.setString(++index, paciente.getNome());
                pstm.setString(++index, paciente.getEmail());
                pstm.setString(++index, paciente.getCep().getCodCep());
                pstm.setString(++index, paciente.getCep().getCidade());
                pstm.setString(++index, paciente.getCep().getUf());
                pstm.setString(++index, paciente.getCep().getLogradouro());
                pstm.setString(++index, paciente.getNumero());
                pstm.setString(++index, paciente.getCep().getBairro());
                pstm.setString(++index, paciente.getComplemento());
                pstm.setString(++index, paciente.getCpf());
                pstm.setString(++index, paciente.getRg());
                pstm.setDate(++index, paciente.getDataNasc());
                pstm.setString(++index, paciente.getFoneFixo());
                pstm.setString(++index, paciente.getCelular1());
                pstm.setString(++index, paciente.getCelular2());
                pstm.setString(++index, paciente.getCelular3());
                pstm.setString(++index, paciente.getObs());
                pstm.setString(++index, paciente.getTipoSangue());
                pstm.setBoolean(++index, paciente.isDesabilitado());
                pstm.setDate(++index, paciente.getDataCadastro());
                pstm.setString(++index, paciente.getNomeMae());
                pstm.setString(++index, paciente.getNomePai());
                
                if(paciente.getEmpresa().getId() == null){
                    pstm.setNull(++index, java.sql.Types.INTEGER );
                }else{
                pstm.setInt(++index, paciente.getEmpresa().getId());
                }
                if (paciente.getFuncao().getId() == null) {
                    pstm.setNull(++index, java.sql.Types.INTEGER);
                } else {
                    pstm.setInt(++index, paciente.getFuncao().getId());
                }

            } else {

                sql = "INSERT INTO pacientes (num_convenio, id_convenio, nome, email, cep, municipio,"
                        + "uf, logradouro, numero, bairro, complemento, cpf,"
                        + "rg, data_nasc, fone_fixo, celular1, celular2, celular3,"
                        + "obs, tipo_sang, desabilitado, data_cadastro, nome_mae, nome_pai, id_empresa, id_funcao)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

                // criar o statement
                pstm = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

                // setar os params
                int index = 0;
                pstm.setString(++index, paciente.getNumConvenio());
                pstm.setInt(++index, idConv);
                pstm.setString(++index, paciente.getNome());
                pstm.setString(++index, paciente.getEmail());
                pstm.setString(++index, paciente.getCep().getCodCep());
                pstm.setString(++index, paciente.getCep().getCidade());
                pstm.setString(++index, paciente.getCep().getUf());
                pstm.setString(++index, paciente.getCep().getLogradouro());
                pstm.setString(++index, paciente.getNumero());
                pstm.setString(++index, paciente.getCep().getBairro());
                pstm.setString(++index, paciente.getComplemento());
                pstm.setString(++index, paciente.getCpf());
                pstm.setString(++index, paciente.getRg());
                pstm.setDate(++index, paciente.getDataNasc());
                pstm.setString(++index, paciente.getFoneFixo());
                pstm.setString(++index, paciente.getCelular1());
                pstm.setString(++index, paciente.getCelular2());
                pstm.setString(++index, paciente.getCelular3());
                pstm.setString(++index, paciente.getObs());
                pstm.setString(++index, paciente.getTipoSangue());
                pstm.setBoolean(++index, paciente.isDesabilitado());
                pstm.setDate(++index, paciente.getDataCadastro());
                pstm.setString(++index, paciente.getNomeMae());
                pstm.setString(++index, paciente.getNomePai());
                if(paciente.getEmpresa().getId() == null){
                    pstm.setNull(++index, java.sql.Types.INTEGER );
                }else{ 
                pstm.setInt(++index, paciente.getEmpresa().getId());
                }
                if (paciente.getFuncao().getId() == null) {
                    pstm.setNull(++index, java.sql.Types.INTEGER);
                } else {
                    pstm.setInt(++index, paciente.getFuncao().getId());
                }
              
            }

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

    @Override
    public boolean altPaciente(Paciente paciente) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // criar o sql
            String sql = "UPDATE pacientes SET num_convenio = ?, id_convenio = ? , nome = ?, "
                    + "email = ?, cep = ?, municipio = ?, uf = ?, logradouro = ?, numero = ?, bairro = ?, "
                    + "complemento = ?, cpf = ?, rg = ?, data_nasc = ?, fone_fixo = ?, celular1 = ?,"
                    + "celular2 = ?, celular3 = ?, obs = ?, tipo_sang = ?, desabilitado = ?, nome_mae = ?, "
                    + "nome_pai = ?, id_empresa = ?, id_funcao = ? WHERE id = ?";

            // criar o statement

            pstm = connection.prepareStatement(sql);

            // setar os params
            int index = 0;
            pstm.setString(++index, paciente.getNumConvenio());
            pstm.setInt(++index, paciente.getConvenio().getId());
            pstm.setString(++index, paciente.getNome());
            pstm.setString(++index, paciente.getEmail());
            pstm.setString(++index, paciente.getCep().getCodCep());
            pstm.setString(++index, paciente.getCep().getCidade());
            pstm.setString(++index, paciente.getCep().getUf());
            pstm.setString(++index, paciente.getCep().getLogradouro());
            pstm.setString(++index, paciente.getNumero());
            pstm.setString(++index, paciente.getCep().getBairro());
            pstm.setString(++index, paciente.getComplemento());
            pstm.setString(++index, paciente.getCpf());
            pstm.setString(++index, paciente.getRg());
            pstm.setDate(++index, paciente.getDataNasc());
            pstm.setString(++index, paciente.getFoneFixo());
            pstm.setString(++index, paciente.getCelular1());
            pstm.setString(++index, paciente.getCelular2());
            pstm.setString(++index, paciente.getCelular3());
            pstm.setString(++index, paciente.getObs());
            pstm.setString(++index, paciente.getTipoSangue());
            pstm.setBoolean(++index, paciente.isDesabilitado());
            pstm.setString(++index, paciente.getNomeMae());
            pstm.setString(++index, paciente.getNomePai());
            
            if(paciente.getEmpresa().getId() == null){
                pstm.setNull(++index, java.sql.Types.INTEGER );
            }else{
            pstm.setInt(++index, paciente.getEmpresa().getId());
            }
            if (paciente.getFuncao().getId() == null) {
                pstm.setNull(++index, java.sql.Types.INTEGER);
            } else {
                pstm.setInt(++index, paciente.getFuncao().getId());
            }
            pstm.setInt(++index, paciente.getId());
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
    public boolean rmPaciente(int id) {
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

    @Override
    public List<Paciente> buscaPaciente(String dado, char tipo) {
        Paciente cli;
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

                    sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                            + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                            + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                            + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                            + " WHERE pacientes.nome LIKE '%" + dado + "%' ORDER BY id ";
                    break;
                case 'i':
                    sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                            + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                            + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                            + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                            + " WHERE num_convenio = '" + dado + "' ORDER BY id ";
                    break;

                case 't':
                    sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                            + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                            + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                            + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                            + " WHERE cpf = '"+dado+"' ORDER BY id ";
                    break;

                case 'a':
                    sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                            + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                            + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                            + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                            + " WHERE logradouro LIKE '%" + dado + "%' ORDER BY id ";
                    break;
                case 'o':
                    sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                            + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                            + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                            + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                            + " WHERE id = '" + dado + "' ORDER BY id ";
                    break;
                 case 'p':
                    sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                            + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                            + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                            + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                            + " WHERE rg = '"+dado+"' ORDER BY id ";
                    break;
                default:
                    break;
            }

            // criar o statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                
                cli = retornObjPaciente(rs);
                pacientes.add(cli);
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
        return pacientes;
    }
    
   

    @Override
    public Paciente buscaPacientePorConsulta(String id) {
        Paciente pacient = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
               String sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                            + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                            + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                       + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                    + " WHERE pacientes.num_convenio = ? ORDER BY pacientes.id";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);


            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                pacient = retornObjPaciente(rs);
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
        return pacient;
    }

    @Override
    public Paciente buscaPacientePorId(int id) {
        Paciente pacient = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           

            // CRIAR SQL
               String sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                            + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                       + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                            + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                    + " WHERE pacientes.id = ? ";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);


            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                pacient = retornObjPaciente(rs);
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
        return pacient;
    }
public Paciente buscPacientePrecadastro(String dado, char tipo) {
        Paciente pacient = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           

            // CRIAR SQL
            String sql = "";
            switch (tipo) {
                case 'e':
                    sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                            + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                            + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                            + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                    + " WHERE pacientes.nome = ? ";
                    break;
                    
                case 'i':
                    
                    sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                            + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                            + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                            + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                    + " WHERE rg = ? ";
                    
                    break;
                default:
                    throw new AssertionError();
            }
            

            // criar o statement
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, dado);


            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                pacient = retornObjPaciente(rs);
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
        return pacient;
    }

    @Override
    public Cep buscCep(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public Consulta buscConsPCli(int idPaciente) {
        Consulta con = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String selectMaxData = "SELECT MAX(data_consulta) AS maxDATA from consultas WHERE id_paciente = '" + idPaciente + "' ";
            Date maxData = null;
            pstm = connection.prepareStatement(selectMaxData);
            rs = pstm.executeQuery();
            while (rs.next()) {

                maxData = rs.getDate(1);
            }
            if (maxData != null) {


                String sql = "SELECT * FROM consultas WHERE data_consulta = '" + maxData + "' ";
                // criar o statement
                pstm = connection.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    con = new Consulta(rs.getDate("data_consulta"), rs.getInt("status"), rs.getString("obs"));
                }
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

    @Override
    public Paciente mostrarUltimo() {
        Connection connection = null;
        PreparedStatement pstm = null;
        Paciente c = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           
            // CRIAR SQL
            String  sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                    + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                    + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                    + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                    + " WHERE pacientes.id = (SELECT MAX(id) AS maxID FROM pacientes)";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                c = retornObjPaciente(rs);

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
        return c;

    }

    @Override
    public Paciente mostrarPrimeiro() {
        Connection connection = null;
        PreparedStatement pstm = null;
        Paciente c = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
           
            // CRIAR SQL
            String  sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                    + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                    + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                    + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                    + " WHERE pacientes.id = (SELECT MIN(id) AS minID FROM pacientes)";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()) {

                c = retornObjPaciente(rs);
               
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
        return c;
    }

    @Override
    public Paciente mostrarProximo(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        Paciente c = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // CRIAR SQL
            String  sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                    + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                    + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                    + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                    + " WHERE pacientes.id > '" + id + "' ORDER BY pacientes.id limit 1";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                c = retornObjPaciente(rs);
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
        return c;
    }

    @Override
    public Paciente mostrarAnterior(int id) {
        Connection connection = null;
        PreparedStatement pstm = null;
        Paciente c = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String  sql = " SELECT pacientes.*,empresa.fantasia, convenios.ds_convenio,valor, funcao.nome AS nome_funcao FROM pacientes "
                    + "LEFT JOIN convenios ON convenios.id = pacientes.id_convenio "
                    + "LEFT JOIN empresa ON empresa.id = pacientes.id_empresa "
                    + "LEFT JOIN funcao ON funcao.id = pacientes.id_funcao "
                    + " WHERE pacientes.id < '" + id + "' ORDER BY pacientes.id desc limit 1";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                c = retornObjPaciente(rs);

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
        return c;
    }

    public Paciente retornObjPaciente(ResultSet rs) throws SQLException {
        Paciente c = null;
        if (rs != null) {

        
        Convenio conv = new Convenio(rs.getInt("id_convenio"), rs.getString("ds_convenio"),rs.getDouble("valor"));
        Cep cep = new Cep(null, rs.getString("cep"), rs.getString("logradouro"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("uf"));
        Empresa empresa = new Empresa(rs.getInt("id_empresa"), rs.getString("fantasia"));
        Funcao funcao = new Funcao(rs.getInt("id_funcao"), rs.getString("nome_funcao"));
         c = new Paciente(rs.getInt("id"), rs.getString("num_convenio"), conv, rs.getString("nome"),
                rs.getString("email"), rs.getString("numero"), rs.getString("complemento"), rs.getString("cpf"),
                rs.getString("rg"), rs.getDate("data_nasc"), rs.getString("tipo_sang"), rs.getString("fone_fixo"),
                rs.getString("celular1"), rs.getString("celular2"), rs.getString("celular3"), rs.getString("obs"),
                cep,rs.getBoolean("desabilitado"), null, rs.getString("nome_mae"), rs.getString("nome_pai"),empresa,funcao);
        }
        return c;
    }
   

    
    
}
