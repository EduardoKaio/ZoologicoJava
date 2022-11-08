package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FabricaConexao {
    
   private static final String USER = "root";
    private static final String PASS = "2003kaio";
    private static final String URL = "jdbc:mysql://localhost:3306/banco_zoologico";
    
    
       public static Connection getConnection() throws ClassNotFoundException {
            try {
                return DriverManager.getConnection(URL, USER, PASS);
                
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
                return null;
            }
        }

        public static void fecharConexao(Connection conn) {
            try {
                if (conn != null)
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
            }	
        }

    public static void fecharConexao(Connection conn, Statement s) {
        fecharConexao(conn);
        try {
                if (s != null)
                        s.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    public static void fecharConexao(Connection conn, Statement s, ResultSet rs) {
        fecharConexao(conn, s);
        try {
                if (rs != null)
                        rs.close();
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

	public static void fecharConexao(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
