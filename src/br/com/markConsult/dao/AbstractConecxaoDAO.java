package br.com.markConsult.dao;



import br.com.markConsult.gui.FalhaComunicBD;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

abstract public class AbstractConecxaoDAO {
    private final Properties confBanco = new Properties();
       private static Connection connection = null;

	protected void cleanup(ResultSet rs, PreparedStatement pstm,
			Connection connection) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new IllegalStateException(e.getMessage());
			}
		}

		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				throw new IllegalStateException(e.getMessage());
			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new IllegalStateException(e.getMessage());
			}
		}
	}
	
	protected void cleanup(ResultSet rs, Statement stm,
			Connection connection) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new IllegalStateException(e.getMessage());
			}
		}

		if (stm != null) {
			try {
				stm.close();
			} catch (SQLException e) {
				throw new IllegalStateException(e.getMessage());
			}
		}

		if (connection != null) {
			try {
				connection.close();
                                connection = null;
			} catch (SQLException e) {
				throw new IllegalStateException(e.getMessage());
			}
		}
	}
        
        protected void cleanup(ResultSet rs, Statement stm) {
		cleanup(rs, stm, null);
	}

	protected Connection getConnection() throws Exception {
            confBanco.load(new FileInputStream("/markconsultas/banco.ini"));
            
            Class.forName(confBanco.getProperty("driver"));
            String url=confBanco.getProperty("url")+
            (!confBanco.getProperty("porta").equals("")?
            ":" + confBanco.getProperty
            ("porta") : confBanco.getProperty("porta"))
            +confBanco.getProperty("banco");
            connection = DriverManager.getConnection(url,confBanco.getProperty
            ("usuario"),confBanco.getProperty("senha"));
            
//            if (connection == null){
//		String username = "postgres";
//		String password = "bg7mkib";
//		String url = "jdbc:postgresql://localhost:5432/rep?charSet=LATIN1";
//
//		String driverName = "org.postgresql.Driver";
//
//		Class.forName(driverName);
//
//		connection = DriverManager.getConnection(url, username,
//				password);
//            }
		return connection;
	}

	protected void rollbackTransaction(Connection connection)
			throws SQLException {
		if (connection != null) {
			connection.rollback();
                        
		} else {
                        FalhaComunicBD f = new FalhaComunicBD(null,true);
                        f.setVisible(true);
			//throw new IllegalStateException();
                        
		}
	}

	protected void commitTransaction(Connection connection) throws SQLException {
		connection.commit();
	}

	protected void beginTransaction(Connection connection) throws SQLException {
              if (connection.getAutoCommit()){
                connection.setAutoCommit(false);
              }
	}
}