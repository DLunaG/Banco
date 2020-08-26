package com.hiberus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    Connection cn = null;

    public Statement conex() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://192.168.56.2:5432/banco";

        String usuario = "banquero";

        String pass = "abc";

        cn = DriverManager.getConnection(url, usuario, pass);

        Statement st = cn.createStatement();

        return st;
    }

}
