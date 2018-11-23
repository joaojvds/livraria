package dao;

import dao.connection.SqlConnection;

import java.sql.Connection;

public class ConnectionFactory {

    private String user;
    private String password;

    public Connection getConnection() throws ClassNotFoundException {
        SqlConnection conn = new SqlConnection();
        try {
            return conn.getConnect(this.getUser(), this.getPassword());
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Erro ao conectar com a base de dados" + e.getMessage());
        }
    }

    private String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
