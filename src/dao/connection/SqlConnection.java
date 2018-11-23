package dao.connection;

import javax.swing.*;
import java.sql.*;

public class SqlConnection {

    private String url = "jdbc:mysql://localhost:3306/livraria?useSSL=false&allowPublicKeyRetrieval=true";

    // usar "jdbc:postgresql://" caso for o postgreSQL
    public Connection getConnect(String usuario, String senha) throws ClassNotFoundException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // usar "org.postgresql.Driver" caso for o postgreSQL
            conn = DriverManager.getConnection(url, usuario, senha);
            //JOptionPane.showMessageDialog(null,"Conectado com sucesso",
            //                         null,JOptionPane.PLAIN_MESSAGE);
            return conn;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados\n" +
                    e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            return conn;
        }
    }

    public static void closeConnect(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Fechada a conexão com o banco de dados");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível fechar a conexão com o banco de dados " + e.getMessage());
        }
    }

    public static void closeConnect(Connection conn, PreparedStatement stmt) {
        try {
            if (conn != null) {
                closeConnect(conn);
            }
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement fechado com sucesso");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível fechar o statement " + e.getMessage());
        }
    }

    public static void closeConnect(Connection conn, PreparedStatement stmt, ResultSet rs) {

        try {
            if (conn != null || stmt != null) {
                closeConnect(conn, stmt);
            }
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet fechado com sucesso");
            }


        } catch (Exception e) {
            System.out.println("Não foi possível fechar o ResultSet " + e.getMessage());
        }
    }

    public static void closeConnect(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
                System.out.println("Statement fechado com sucesso");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível fechar o statement " + e.getMessage());
        }
    }

    public static void closeConnect(PreparedStatement stmt, ResultSet rs) {
        try {
            if (stmt != null) {
                closeConnect(stmt);
            }
            if (rs != null) {
                rs.close();
                System.out.println("ResultSet fechado com sucesso");
            }
        } catch (Exception e) {
            System.out.println("Não foi possível fechar o ResultSet " + e.getMessage());
        }
    }
}