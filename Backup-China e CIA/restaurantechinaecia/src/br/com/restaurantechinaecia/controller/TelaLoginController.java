package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import br.com.restaurantechinaecia.banco_de_dados.BdControl;
import br.com.restaurantechinaecia.helper.Usuario;
import br.com.restaurantechinaecia.view.MainChinaCia;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class TelaLoginController implements Initializable{

	@FXML
	TextField txtNome, txtFundo;
	
	@FXML
	PasswordField txtSenha;
	
	@FXML
	Button btnEntrar, btnOk;
	
	@FXML
	ImageView imgChina;
	
	@FXML
	Label lblOk;
	
	@FXML
	Pane pane;
	
	public static boolean x=false;
	MainChinaCia main;
	Image imagem;
	BdControl db;
	int aux = 0;
        int tipo_user = 0;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		db = new BdControl();
		lblOk.setVisible(false);
		imgChina.setImage(imagem);
		btnEntrar.setOnAction(l-> Entrar());
		btnOk.setOnAction(l-> Ok());
                
		AtribuirBotoes();
	}
	public void AtribuirBotoes(){
		txtSenha.setOnKeyPressed(k->Key(k));
		pane.setOnKeyPressed(k->Key(k));
		btnEntrar.setOnKeyPressed(k->Key(k));
		btnOk.setOnKeyPressed(k->Key(k));
		txtNome.setOnKeyPressed(k->Key(k));
		txtFundo.setOnKeyPressed(k->Key(k));
		
	}
	public void Key(KeyEvent k){
		final KeyCombination enter = new KeyCodeCombination(KeyCode.ENTER);
		if (enter.match(k)) {
			if(pane.isVisible()){
                            Ok();
			}else{
				Entrar();
			}
		}
	}
	public TelaLoginController(MainChinaCia main) {
		// TODO Auto-generated constructor stub
		this.main=main;
	}
	public void Entrar(){
		if(!txtNome.getText().isEmpty() && !txtSenha.getText().isEmpty()){
			String sql ="select * from usuario where nome_user='"+txtNome.getText()+"' and senha = SHA1('"+txtSenha.getText()+"');";
			ResultSet rs = db.DadosComRetorno(sql);
			try {
				
                                int cod_user;
				while(rs.next()){
					tipo_user = rs.getInt("cod_cargo");
                                        cod_user = rs.getInt("cod_usuario");
                                        Usuario.COD_USER = cod_user;
					String sql_2 ="update usuario set ultimo_login = now() where cod_usuario ="+cod_user;
					db.DadosSemRetorno(sql_2);
                                        String sql_3 ="insert into login_acessado(cod_usuario, data) values("+cod_user+",now());";
					db.DadosSemRetorno(sql_3);
                                        
                                        String sql_4 ="select * from fechamento "
                                                + "where data between concat(current_date, ' ', '00:00:00') "
                                                + "and concat(current_date, ' ', '23:59:59');";
                                        ResultSet rs_4 = db.DadosComRetorno(sql_4);
                                        
                                        if(tipo_user == 1 || tipo_user == 2){
                                            lblOk.setVisible(true);
                                            if(!rs_4.next()){
                                                aux =1 ;
                                                pane.setVisible(true);
                                            }else{
                                                Usuario.FUNDO=rs_4.getDouble("fundo");
                                                if(tipo_user == 1){

                                                    main.CarregarBorderADM();
                                                     
                                                }else if(tipo_user == 2){

                                                    main.CarregarBorderFuncionario();
                                                }
                                            }
                                        }else{
                                               JOptionPane.showMessageDialog(null, "USUARIO NãO RECONHECIDO PELO SISTEMA. CONTATAR O ADMINISTRADOR !!");
                                        }
                                        
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(null, "PREECHA OS CAMPOS DE LOGIN !!");
		}
	}
        
    public void Ok(){
        if(!txtFundo.getText().isEmpty()){
            try{
                double vlr_fundo = Double.parseDouble(txtFundo.getText().replace(",", "."));
                
                String sql ="insert into fechamento (fundo, verif, data) values ("+vlr_fundo+", 1, now())";
                db.DadosSemRetorno(sql);
                Usuario.FUNDO=vlr_fundo;
                if(tipo_user == 1){

                    main.CarregarBorderADM();

                }else if(tipo_user == 2){

                    main.CarregarBorderFuncionario();
                }
                
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "DIGITE APENAS VALORES !!");
            }
            
        }
        
    }
}
