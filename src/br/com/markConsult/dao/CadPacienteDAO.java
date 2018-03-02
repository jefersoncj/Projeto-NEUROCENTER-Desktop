/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Cep;
import br.com.markConsult.entidades.Paciente;
import br.com.markConsult.entidades.Empresa;
import br.com.markConsult.entidades.Exame;
import br.com.markConsult.entidades.Funcao;
import br.com.markConsult.entidades.OrdemExames;
import br.com.markConsult.entidades.Risco;
import br.com.markConsult.entidades.Setor;
import br.com.markConsult.relatorios.ReportUtils;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
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

    private final ArrayList<Paciente> pacientes = new ArrayList<>();
    private final ArrayList<Risco> riscos = new ArrayList<>();
    private final ArrayList<Exame> exames = new ArrayList<>();

    @Override
    public Integer insePaciente(Paciente paciente) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // criar o sql
            String sql = "INSERT INTO pacientes ( nome, email, cep, municipio, "
                        + "uf, logradouro, numero, bairro, complemento, cpf, "
                        + "rg, data_nasc, fone_fixo, celular1, celular2, celular3, "
                        + "obs,desabilitado,data_cadastro, nome_mae, nome_pai, id_empresa, "
                        + "id_setor, id_funcao, data_admissao)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                // criar o statement
                pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                int index = 0;
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
                pstm.setBoolean(++index, paciente.isDesabilitado());
                pstm.setDate(++index, paciente.getDataCadastro());
                pstm.setString(++index, paciente.getNomeMae());
                pstm.setString(++index, paciente.getNomePai());
                pstm.setInt(++index, paciente.getEmpresa().getId());
                pstm.setInt(++index, paciente.getSetor().getId());
                pstm.setInt(++index, paciente.getFuncao().getId());
                pstm.setDate(++index, paciente.getDataAdmissao());

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

    public Integer inserirOdemPExame(OrdemExames gope) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // criar o sql
            String sql = "INSERT INTO ordem_exames (data_ordem, id_paciente, id_empresa, aso, id_periodicidade)VALUES (?, ?, ?, ?, ?)";
            // criar o statement
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int index = 0;
            pstm.setDate(++index, gope.getData());
            pstm.setInt(++index, gope.getPaciente().getId());
            pstm.setInt(++index, gope.getEmpresa().getId());
            pstm.setBoolean(++index, gope.isAso());
            pstm.setInt(++index, gope.getPeriodicidade().getId());
            // executar
            pstm.executeUpdate();
            rs = pstm.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            inserirExamesOrdem(gope.getExames(), id);
            inserirRiscosOrdem(gope.getRiscos(), id);

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

    public void inserirExamesOrdem(List<Exame> ListaExames, int id_ordem) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // criar o sql
            String sql = "INSERT INTO itens_exames_ordens ( id_ordem, id_exame, desc_exame, valor)VALUES ( ?, ?, ?, ?)";
            // criar o statement
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (Exame ListaExame : ListaExames) {

                int index = 0;
                pstm.setInt(++index, id_ordem);
                pstm.setInt(++index, ListaExame.getId());
                pstm.setString(++index, ListaExame.getDescExame());
                pstm.setDouble(++index, ListaExame.getValor());

                // executar
                pstm.executeUpdate();
                rs = pstm.getGeneratedKeys();
                int id = 0;
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                commitTransaction(connection);
                idInserido = id;
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

    }

    public void inserirRiscosOrdem(List<Risco> ListaRiscos, int id_ordem) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // GERAR O ID UNICO

            // criar o sql
            String sql = "INSERT INTO itens_riscos_ordens ( id_ordem, id_risco)VALUES (?, ?)";
            // criar o statement
            pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (Risco ListaRisco : ListaRiscos) {

                int index = 0;
                pstm.setInt(++index, id_ordem);
                pstm.setInt(++index, ListaRisco.getId());

                // executar
                pstm.executeUpdate();
                rs = pstm.getGeneratedKeys();
                int id = 0;
                if (rs.next()) {
                    id = rs.getInt(1);
                }
                commitTransaction(connection);
                idInserido = id;
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
            String sql = "UPDATE pacientes SET nome = ?, "
                    + "email = ?, cep = ?, municipio = ?, uf = ?, logradouro = ?, numero = ?, bairro = ?, "
                    + "complemento = ?, cpf = ?, rg = ?, data_nasc = ?, fone_fixo = ?, celular1 = ?, "
                    + "celular2 = ?, celular3 = ?, obs = ?, desabilitado = ?, nome_mae = ?, "
                    + "nome_pai = ?, id_empresa = ?, id_setor = ?, id_funcao = ?, data_admissao = ? "
                    + "WHERE id = ?";

            // criar o statement
            pstm = connection.prepareStatement(sql);

            // setar os params
            int index = 0;
            
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
            pstm.setBoolean(++index, paciente.isDesabilitado());
            pstm.setString(++index, paciente.getNomeMae());
            pstm.setString(++index, paciente.getNomePai());
            pstm.setInt(++index, paciente.getEmpresa().getId());
            pstm.setInt(++index, paciente.getSetor().getId());
            pstm.setInt(++index, paciente.getFuncao().getId());
            pstm.setDate(++index, paciente.getDataAdmissao());
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

    public boolean altResultadoExame(Exame exame) {
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {

            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            // criar o sql
            String sql = "UPDATE itens_exames_ordens SET resultado = ? WHERE id = ?";

            // criar o statement
            pstm = connection.prepareStatement(sql);

            // setar os params
            int index = 0;

            pstm.setString(++index, exame.getResultado());
            pstm.setInt(++index, exame.getIdRg());
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

                    sql = "SELECT p.*, e.fantasia, e.cnpj, s.desc_setor, f.desc_funcao "
                            + "FROM pacientes AS p LEFT JOIN empresas AS e ON e.id = p.id_empresa "
                            + "LEFT JOIN setores AS s ON s.id = p.id_setor "
                            + "LEFT JOIN funcoes AS f ON f.id = p.id_funcao "
                            + "WHERE p.nome LIKE '%" + dado + "%' ORDER BY id ";
                    break;
                case 'i':
                    sql = "SELECT p.*, e.fantasia, e.cnpj, s.desc_setor, f.desc_funcao "
                            + "FROM pacientes AS p LEFT JOIN empresas AS e ON e.id = p.id_empresa "
                            + "LEFT JOIN setores AS s ON s.id = p.id_setor "
                            + "LEFT JOIN funcoes AS f ON f.id = p.id_funcao "
                            + "WHERE p.id = '" + dado + "' ORDER BY riscos.id";
                    break;

                case 't':
                    sql = "SELECT p.*, e.fantasia, e.cnpj, s.desc_setor, f.desc_funcao "
                            + "FROM pacientes AS p LEFT JOIN empresas AS e ON e.id = p.id_empresa "
                            + "LEFT JOIN setores AS s ON s.id = p.id_setor "
                            + "LEFT JOIN funcoes AS f ON f.id = p.id_funcao "
                            + " WHERE cpf = '" + dado + "' ORDER BY id ";
                    break;

                case 'a':
                    sql = "SELECT p.*, e.fantasia, e.cnpj, s.desc_setor, f.desc_funcao "
                            + "FROM pacientes AS p LEFT JOIN empresas AS e ON e.id = p.id_empresa "
                            + "LEFT JOIN setores AS s ON s.id = p.id_setor "
                            + "LEFT JOIN funcoes AS f ON f.id = p.id_funcao "
                            + " WHERE rg LIKE '%" + dado + "%' ORDER BY id ";
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

    public List<Exame> buscaExames(int id_risco) {
        Exame exame;
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = "SELECT riscos_exames.*, exames.desc_exame,valor,id_periodicidade, periodicidade.desc_periodicidade "
                    + "FROM riscos_exames LEFT JOIN exames ON exames.id = riscos_exames.id_exame "
                    + "LEFT JOIN periodicidade ON exames.id_periodicidade = periodicidade.id "
                    + "WHERE riscos_exames.id_risco = '" + id_risco + "'";

            // criar o statement
            stm = connection.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {

                exame = retornObExames(rs);

                exames.add(exame);

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

    @Override
    public Paciente buscClientPCon(String id) {
        Paciente client = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = "SELECT p.*, e.fantasia, e.cnpj, s.desc_setor, f.desc_funcao "
                    + "FROM pacientes AS p LEFT JOIN empresas AS e ON e.id = p.id_empresa "
                    + "LEFT JOIN setores AS s ON s.id = p.id_setor "
                    + "LEFT JOIN funcoes AS f ON f.id = p.id_funcao "
                    + " WHERE pacientes.num_empresa = ? ORDER BY pacientes.id";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);

            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                client = retornObjPaciente(rs);
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
        return client;
    }

    @Override
    public Paciente busaPacientePId(int id) {
        Paciente client = null;
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);

            // CRIAR SQL
            String sql = "SELECT p.*, e.fantasia, e.cnpj, s.desc_setor, f.desc_funcao "
                    + "FROM pacientes AS p LEFT JOIN empresas AS e ON e.id = p.id_empresa "
                    + "LEFT JOIN setores AS s ON s.id = p.id_setor "
                    + "LEFT JOIN funcoes AS f ON f.id = p.id_funcao "
                    + " WHERE p.id = ? ";

            // criar o statement
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, id);

            rs = pstm.executeQuery();

            while (rs.next()) {
                //desencripta
                client = retornObjPaciente(rs);
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
        return client;
    }

    public Paciente buscPacientePrecadastro(String dado, char tipo) {
        Paciente client = null;
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
                    sql = "SELECT p.*, e.fantasia, e.cnpj, s.desc_setor, f.desc_funcao "
                            + "FROM pacientes AS p LEFT JOIN empresas AS e ON e.id = p.id_empresa "
                            + "LEFT JOIN setores AS s ON s.id = p.id_setor "
                            + "LEFT JOIN funcoes AS f ON f.id = p.id_funcao "
                            + " WHERE pacientes.nome = ? ";
                    break;

                case 'i':

                    sql = "SELECT p.*, e.fantasia, e.cnpj, s.desc_setor, f.desc_funcao "
                            + "FROM pacientes AS p LEFT JOIN empresas AS e ON e.id = p.id_empresa "
                            + "LEFT JOIN setores AS s ON s.id = p.id_setor "
                            + "LEFT JOIN funcoes AS f ON f.id = p.id_funcao "
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
                client = retornObjPaciente(rs);
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
        return client;
    }

    @Override
    public Cep buscCep(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            String sql = "SELECT p.*, e.fantasia, e.cnpj, s.desc_setor, f.desc_funcao "
                    + "FROM pacientes AS p LEFT JOIN empresas AS e ON e.id = p.id_empresa "
                    + "LEFT JOIN setores AS s ON s.id = p.id_setor "
                    + "LEFT JOIN funcoes AS f ON f.id = p.id_funcao "
                    + " WHERE p.id = (SELECT MAX(id) AS maxID FROM pacientes)";

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
            String sql = "SELECT p.*, e.fantasia, e.cnpj, s.desc_setor, f.desc_funcao "
                    + "FROM pacientes AS p LEFT JOIN empresas AS e ON e.id = p.id_empresa "
                    + "LEFT JOIN setores AS s ON s.id = p.id_setor "
                    + "LEFT JOIN funcoes AS f ON f.id = p.id_funcao "
                    + " WHERE p.id = (SELECT MIN(id) AS minID FROM pacientes)";

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
            String sql = "SELECT p.*, e.fantasia, e.cnpj, s.desc_setor, f.desc_funcao "
                    + "FROM pacientes AS p LEFT JOIN empresas AS e ON e.id = p.id_empresa "
                    + "LEFT JOIN setores AS s ON s.id = p.id_setor "
                    + "LEFT JOIN funcoes AS f ON f.id = p.id_funcao "
                    + " WHERE p.id > '" + id + "' ORDER BY p.id limit 1";

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
            String sql = "SELECT p.*, e.fantasia, e.cnpj, s.desc_setor, f.desc_funcao "
                    + "FROM pacientes AS p LEFT JOIN empresas AS e ON e.id = p.id_empresa "
                    + "LEFT JOIN setores AS s ON s.id = p.id_setor "
                    + "LEFT JOIN funcoes AS f ON f.id = p.id_funcao "
                    + " WHERE p.id < '" + id + "' ORDER BY p.id desc limit 1";

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

            Empresa empresa = new Empresa(rs.getInt("id_empresa"), rs.getString("fantasia"));
            Setor setor = new Setor(rs.getInt("id_setor"), rs.getString("desc_setor"));
            Funcao funcao = new Funcao(rs.getInt("id_funcao"), rs.getString("desc_funcao"));
            Cep cep = new Cep(null, rs.getString("cep"), rs.getString("logradouro"), rs.getString("bairro"), rs.getString("municipio"), rs.getString("uf"));

            c = new Paciente(rs.getInt("id"), empresa, rs.getString("nome"),
                    rs.getString("email"), rs.getString("numero"), rs.getString("complemento"), rs.getString("cpf"),
                    rs.getString("rg"), rs.getDate("data_nasc"), rs.getString("fone_fixo"),
                    rs.getString("celular1"), rs.getString("celular2"), rs.getString("celular3"), rs.getString("obs"),
                    cep, rs.getBoolean("desabilitado"), null, rs.getString("nome_mae"), rs.getString("nome_pai"), setor, funcao,rs.getDate("data_admissao"));

        }
        return c;
    }

    public Exame retornObExames(ResultSet rs) throws SQLException {
        Exame e = null;
        if (rs != null) {

          
            e = new Exame(rs.getInt("id_exame"), rs.getString("desc_exame"), rs.getDouble("valor"));

        }
        return e;
    }

    public void ConectRelatorio(int ordem, int id_minha_empresa) {
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
            ReportUtils.openReport("Ordem para exames", inputStream, parametros, connection);

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
    
    public void ConectRelatorio2(int minhaEmpresa, Date dataIncial,Date dataFinal, String empresa, String paciente) {
        Connection connection = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            // pegar a connection
            connection = getConnection();
            beginTransaction(connection);
            InputStream inputStream = getClass().getResourceAsStream("/ordemExames_1.jasper");

            // mapa de parâmetros do relatório
            Map parametros = new HashMap();
            parametros.put("id_minha_empresa", minhaEmpresa);
            parametros.put("dataInicial", dataIncial);
            parametros.put("dataFinal", dataFinal);
            parametros.put("empresa", empresa);
            parametros.put("paciente", paciente);
            

            // abre o relatório
            ReportUtils.openReport("Ordem para exames", inputStream, parametros, connection);

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
