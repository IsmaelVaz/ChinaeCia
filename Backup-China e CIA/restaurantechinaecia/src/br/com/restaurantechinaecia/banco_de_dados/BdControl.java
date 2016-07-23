package br.com.restaurantechinaecia.banco_de_dados;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
 
public class BdControl {
  
	public void DadosSemRetorno(String sql){
		ConexaoBd banco = new ConexaoBd();
		String retorno = "erro";
		java.util.Date dataUtil = new java.util.Date();
		java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
		try {
			Connection ExConn = (Connection) banco.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			boolean res = stmt.execute(sql);
			System.out.println("DADOS INSERIDOS");
			stmt.close();
			banco.fecharBDConn();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Os dados não puderam ser inseridos!!!");
			System.out.println(sql);
		}
	} 
	public ResultSet DadosComRetorno(String sql) {
	 	ConexaoBd banco = new ConexaoBd();
	 	ResultSet rs = null;
	 	
		try {
			Connection ExConn = (Connection) banco.abrirBDConn();
			Statement stmt = (Statement) ExConn.createStatement();
			String sSQL = sql;
			rs = stmt.executeQuery(sSQL);
			System.out.println("DADOS ACESSADOS");
			stmt.close();
			banco.fecharBDConn();
			return rs;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"Os dados não puderam ser encontrado!!!");
			System.out.println(sql);
		}
		return rs; 
	}
}
