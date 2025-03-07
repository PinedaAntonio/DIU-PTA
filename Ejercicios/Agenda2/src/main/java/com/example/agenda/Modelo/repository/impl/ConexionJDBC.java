package com.example.agenda.Modelo.repository.impl;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {
    public ConexionJDBC() {
    }

    public Connection conectarBD() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/agenda?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            return conn;
        } catch (SQLException var2) {
            SQLException ex = var2;
            System.out.println("\n--- SQLException capturada ---\n");

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("El servidor no está activo");
            alert.setContentText("Activa el servidor para poder acceder a la base de datos");
            alert.showAndWait();

            while(ex != null) {
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }

            throw new SQLException();

        } catch (Exception var3) {
            throw new SQLException();

        }
    }

    public void desconectarBD(Connection conn) {
        try {
            conn.close();
        } catch (SQLException var3) {
            SQLException ex = var3;
            System.out.println("\n--- SQLException capturada ---\n");

            while(ex != null) {
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        }

    }
}

