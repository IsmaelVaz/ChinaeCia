package br.com.restaurantechinaecia.helper;

import java.sql.Date;

public class Data {
	public static String RetornaDataHora(){
		java.util.Date dd = new java.util.Date();
		Date data = new Date(dd.getTime());
		return data+" "+String.format("%s:%s:%s", dd.getHours(), dd.getMinutes(), dd.getMinutes());
	}
	public static String RetornaData(){
		java.util.Date dd = new java.util.Date();
		Date data = new Date(dd.getTime());
		return data+"";
	}

}
