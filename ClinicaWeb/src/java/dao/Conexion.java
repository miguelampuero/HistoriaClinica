package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/clinicaweb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root",
                "codelyoco1A"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método de prueba opcional
    public static void main(String[] args) {
        Connection conn = getConexion();
        if (conn != null) {
            System.out.println("✅ Conexión exitosa a la base de datos.");
        } else {
            System.out.println("❌ Error al conectar con la base de datos.");
        }
    }
}