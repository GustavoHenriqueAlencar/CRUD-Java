/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetobiblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection conectaDB() {

        Connection conn = null;

        try {
            
            String url = "jdbc:mysql://localhost:3306/cadastro?useSSL=false";
            String user = "root";
            String password = "";
            
            conn = DriverManager.getConnection(url,user,password);
            
        }catch(SQLException expt){
            System.out.println("Deu Ruim! " + expt.getMessage());
        }
        return conn;
    }
}
