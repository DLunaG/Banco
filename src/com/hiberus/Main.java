package com.hiberus;



import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Conexion conex = new Conexion();


        System.out.println("Bienvenido.");

        /*Usuario pepa = new Usuario();
        try {
            pepa.borrarUsuario();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        Cuenta pipo = new Cuenta();
        pipo.crearCuenta();
    }
}
