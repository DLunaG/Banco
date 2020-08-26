package com.hiberus;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Cuenta {
    Conexion c = new Conexion();
    Scanner sc = new Scanner(System.in);

    private int id;
    private String account;
    private float saldo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void crearCuenta() throws SQLException, ClassNotFoundException {

        Statement st = c.conex();

        System.out.println("¿Con cuánto saldo dispondrá la cuenta?");
        this.saldo = sc.nextFloat();

        String sentenciaSql = "INSERT INTO Cuenta ( saldo) VALUES ('" + this.saldo + "');";
        st.executeUpdate(sentenciaSql);

        System.out.println("Se ha creado la cuenta.");

        System.out.println("¿Quiéres asociarlo a un usuario? 1.Sí 2.No");
        int respuesta = sc.nextInt();
        if (respuesta == 1) {

            System.out.println("¿A qué id de usuario desea asociarlo?");
            int opcion = sc.nextInt();
            System.out.println("Confirme el id de nuevo");
            int opciondos = sc.nextInt();
            if (opcion == opciondos) {
                if (comparaCliente(opcion)) {
                    asociarCuenta(opciondos);

                } else {
                    System.out.println("no existe el usuario. Desea crearlo? 1.Si 2.No");
                    int respuesta2 = sc.nextInt();
                    if (respuesta2 == 1) {
                        Usuario creacion = new Usuario();
                        creacion.crearUsuario();
                        System.out.println("usuario creado");
                        asociarCuenta(creacion.recuperarUltimoID());
                    }else {
                        System.out.println("Es obligatorio tener un usuario existente");
                        borrarUltimaCuenta();
                    }
                }

            } else {
                System.out.println("Error: el id no es el mismo");
            }
        }else{
            System.out.println("Es obligatorio tener un usuario asociado en la cuenta");
            borrarUltimaCuenta();
        }

    }

    public void asociarCuenta(int idUsuario) throws SQLException, ClassNotFoundException {
        Statement st = c.conex();
        String sentenciaSql = "SELECT * FROM cuenta;";
        ResultSet rs2 = st.executeQuery(sentenciaSql);
        while (!rs2.isLast()) {
            rs2.next();
        }
        int idCuenta = rs2.getInt("id");
        sentenciaSql = "INSERT INTO cuenta_usuario (id_usuario, id_cuenta) VALUES ('" + idUsuario + "', '" + idCuenta + "');";
        st.executeUpdate(sentenciaSql);
        System.out.println("La cuenta ha sido asociada ;D");

    }

    public boolean comparaCliente(int id) throws SQLException, ClassNotFoundException {
        Statement st = c.conex();
        String sql = "SELECT * FROM usuario WHERE id= '" + id + "';";
        ResultSet rs = st.executeQuery(sql);

        if (!rs.next()) {
            System.out.println("no ta.");
            return false;
        } else {
            int idVuelta = rs.getInt("id");
            System.out.println("ya ta " + idVuelta);
            return true;
        }
    }

    public void borrarCuenta() throws SQLException, ClassNotFoundException {

        Statement st = c.conex();

        System.out.println("¿Qué id de cuenta desea borrar?");
        int erase = sc.nextInt();

        String sentenciaSql = "DELETE FROM cuenta WHERE id = '" + erase + "';";
        int rs = st.executeUpdate(sentenciaSql);

        System.out.println("Se ha borrado correctamente la cuenta.");


    }

    public void borrarUltimaCuenta() throws SQLException, ClassNotFoundException {
        Statement st = c.conex();

        String sentenciaSQL = "SELECT id FROM cuenta;";

        ResultSet rs = st.executeQuery(sentenciaSQL);

        rs.afterLast();

        int id = rs.getInt("id");

        String sentenciaSql = "DELETE FROM cuenta WHERE id = '" + id + "';";
        st.executeUpdate(sentenciaSql);

        System.out.println("Se ha borrado la última cuenta.");

    }
}
