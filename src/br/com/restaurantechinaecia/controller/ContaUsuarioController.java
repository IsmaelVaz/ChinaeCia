package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import br.com.restaurantechinaecia.banco_de_dados.BdControl;
import br.com.restaurantechinaecia.helper.Helper;
import br.com.restaurantechinaecia.helper.Mascaras;
import br.com.restaurantechinaecia.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ContaUsuarioController implements Initializable{

	@FXML
	TableView<ContaUsuarioModel> tableView;
	
	@FXML
	TableColumn<ContaUsuarioModel, String> clnCpf, clnNome, clnNascimento, clnCargo, clnTipo, clnLogin, clnNomeUser;
	
	@FXML
	TextField txtNome, txtCargo, txtNascimento, txtCPF , txtNomeUser;
	
	@FXML
	PasswordField txtSenha;
	
	@FXML
	RadioButton radioAdm, radioFunSimples;
	
	@FXML
	Button btnAdicionar, btnRemover, btnAtualizar;
	
	@FXML
	Pane pane;

	ToggleGroup group;
	BdControl db;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		db = new BdControl();
		
		btnAdicionar.setOnAction(l-> AdicionarUsuario());
		btnRemover.setOnAction(l-> RemoverUsuario());
		btnAtualizar.setOnAction(l-> AtualizarUsuario());
		
		group = new ToggleGroup();
		radioAdm.setToggleGroup(group);
		radioFunSimples.setToggleGroup(group);
		
		pane.setVisible(true);
		Mascaras.mascaraCPF(txtCPF);
		Mascaras.mascaraData(txtNascimento);

		clnNome.setCellValueFactory(new PropertyValueFactory<ContaUsuarioModel, String>("nome"));
		clnNomeUser.setCellValueFactory(new PropertyValueFactory<ContaUsuarioModel, String>("nomeUser"));
		clnCargo.setCellValueFactory(new PropertyValueFactory<ContaUsuarioModel, String>("cargo"));
		clnNascimento.setCellValueFactory(new PropertyValueFactory<ContaUsuarioModel, String>("dataNascimento"));
		clnTipo.setCellValueFactory(new PropertyValueFactory<ContaUsuarioModel, String>("tipoUsuario"));
		clnLogin.setCellValueFactory(new PropertyValueFactory<ContaUsuarioModel, String>("ultimoLogin"));
		clnCpf.setCellValueFactory(new PropertyValueFactory<ContaUsuarioModel, String>("cpf"));
		AtualizarTabela();
	}
    public void AdicionarUsuario(){
        if(!txtNome.getText().isEmpty() && !txtCargo.getText().isEmpty()){
            if(!txtNomeUser.getText().isEmpty() && !txtSenha.getText().isEmpty()){
                if(txtNascimento.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "PREECHA A DATA DE ANASCIMENTO!!");
                }else{
                    String dia= txtNascimento.getText().substring(0, 2);
                    String mes =txtNascimento.getText().substring(3, 5);
                    String ano =txtNascimento.getText().substring(6, 10);
                    String dataAtualizada = ano+"-"+mes+"-"+dia;
                    System.out.print(dataAtualizada);
                    String nome = txtNome.getText().toUpperCase();
                    String cpf = txtCPF.getText().replace(".", "").replace("-", "");
                    String subcargo = txtCargo.getText().toUpperCase();
                    int cod_cargo = 0;
                    if(radioAdm.isSelected()){cod_cargo = 1; }else if (radioFunSimples.isSelected()){cod_cargo = 2;}
                    
                    String senha= txtSenha.getText();
                    String nome_user=txtNomeUser.getText();
                    if(Helper.ValidarCpf(cpf)){
                        
                    String sql="insert into usuario(nome, cpf, subcargo, dt_nasc, cod_cargo, senha, nome_user) values"
                            + "('"+nome+"', '"+cpf+"', '"+subcargo+"', '"+dataAtualizada+"', "
                            + ""+cod_cargo+", SHA1('"+senha+"'), '"+nome_user+"')";
                    
                    db.DadosSemRetorno(sql);
                    LimparCampos();
                    AtualizarTabela();
                    }else{
                        JOptionPane.showMessageDialog(null, "CPF INVALIDO!!");
                    }

                }
            }else{
                JOptionPane.showMessageDialog(null, "PREENCHA O TIPO DE USUARIO!!");
            }
        }else{
            JOptionPane.showMessageDialog(null, "PREENCHA O CAMPO NOME E CARGO!!");

        }

    }
	public void LimparCampos(){
		txtCargo.setText("");
		txtCPF.setText("");
		txtNascimento.setText("");
		txtNome.setText("");
		txtNomeUser.setText("");
		txtSenha.setText("");
		radioAdm.setSelected(false);
		radioFunSimples.setSelected(false);
	}
	public void RemoverUsuario(){
		ContaUsuarioModel conta = tableView.getSelectionModel().getSelectedItem();
		if(conta!=null){
			int cod_user = conta.getCod_usuario();
			
			String sql ="delete from usuario where cod_usuario="+cod_user;
			db.DadosSemRetorno(sql);
			AtualizarTabela();
		}else{
			JOptionPane.showMessageDialog(null, "SELECIONE UM USUARIO PARA EXCLUI-LO!!");
		}
	}
	public void AtualizarUsuario(){
		/*ContaUsuarioModel usuario = tableView.getSelectionModel().getSelectedItem();
		ArrayList<ContaUsuarioModel> lstUsuarios = _usuariosRepositorio.ObterUsuarios();
		if(!txtCargo.getText().isEmpty() && !txtNascimento.getText().isEmpty()){
			for(ContaUsuarioModel item: lstUsuarios ){
				if(usuario.getNome().equals(txtNome.getText()) && usuario.getSobrenome().equals(txtSobrenome.getText())){
					item.setCargo(txtCargo.getText());
					item.setDataNascimento(txtNascimento.getText());
					if(radioAdm.isSelected()){
						item.setTipoUsuario(radioAdm.getText());
					}else if(radioFunSimples.isSelected()){
						item.setTipoUsuario(radioFunSimples.getText());
					}
					_usuariosRepositorio.AtualizarXML(lstUsuarios);
					AtualizarTabela();
					break;
				}
			}
		}*/
	}
	public void AtualizarTabela(){
		
		ArrayList<ContaUsuarioModel> lstUsuarios = new ArrayList<>();
		String sql ="SELECT u.*, c.nome as nomeTipo from usuario as u inner join cargo as c on (u.cod_cargo = c.cod_cargo);";
		ResultSet rs = db.DadosComRetorno(sql);
		
		try {
			while (rs.next()){
				ContaUsuarioModel conta = new ContaUsuarioModel();
				conta.setCargo(rs.getString("subcargo"));
				conta.setDataNascimento(rs.getString("dt_nasc"));
				conta.setNome(rs.getString("nome"));
				conta.setNomeUser(rs.getString("nome_user"));
				conta.setSenha(rs.getString("senha"));
				conta.setTipoUsuario(rs.getString("nomeTipo"));
				conta.setUltimoLogin(rs.getString("ultimo_login"));
				conta.setCod_usuario(rs.getInt("cod_usuario"));
				conta.setCpf(rs.getString("cpf"));
				lstUsuarios.add(conta);
			}
			ObservableList<ContaUsuarioModel> data = FXCollections.observableArrayList(lstUsuarios);
			tableView.setItems(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
