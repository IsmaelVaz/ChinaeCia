package br.com.restaurantechinaecia.banco_de_dados;

import java.sql.Connection;
 
import java.sql.DriverManager;
import java.sql.Statement;

public class ConexaoBd {
	  
	Connection con;
	private Connection oConn;
	private Statement sStmt;
	  
	public ConexaoBd(){
	}
	 
	public Connection abrirBDConn(){
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dbchinaecia";
			con = DriverManager.getConnection(url,"root","bobbydoido");
			return con;
		}
		catch(Exception e){
			System.out.println("Erro ao abrir conexao com banco:");
			e.printStackTrace();
			return null;
		}
	}
	public void fecharBDConn(){
		try{
			con.close();
		}catch(Exception e){
			System.out.println("Erro ao fechar conexao com banco" + e.getMessage());
		}
	}
}

