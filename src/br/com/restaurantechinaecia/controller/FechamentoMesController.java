package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.restaurantechinaecia.banco_de_dados.*;
import br.com.restaurantechinaecia.model.FechamentoDiaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class FechamentoMesController implements Initializable{

	@FXML
	TableColumn<FechamentoDiaModel, String> clnData, clnFundo,  clnSaida, clnDespesa,  clnTotal, 
		clnLucro, clnCredito, clnDebito, clnVoucher, clnVisaVale;
	
	@FXML
	TableColumn<FechamentoDiaModel, String> clnData1, clnFundo1,  clnSaida1, clnDespesa1,  clnTotal1, 
		clnLucro1, clnCredito1, clnDebito1, clnVoucher1, clnVisaVale1;
	
	@FXML
	TableView<FechamentoDiaModel> tableView;
	
	@FXML
	TableView<FechamentoDiaModel> tblTudo;
	
	FechamentoDiaModel fecha;
	Date data ;
        BdControl db;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		fecha=new FechamentoDiaModel();
		data = new Date();
		db = new BdControl();
		ColunasTableView();
		ColunasTudo();
		
		int diaHoje;
		int esteMes;
		int mesPassado = 0;
		String pegarUltimoMes = null;
		
		diaHoje= Integer.parseInt(data.toLocaleString().substring(0, 2));
		esteMes= Integer.parseInt(data.toLocaleString().substring(3, 5));
		
		System.out.println("dia="+diaHoje+"\nMes="+esteMes);
		
		/*if(diaHoje==28){
			if(_repositorioFechaMes.ObterFechaMes().size()==0){
				GerarFechamentoMes();
			}else{
				for(FechamentoDiaModel item : _repositorioFechaMes.ObterFechaMes()){
					pegarUltimoMes = item.getData();
				}
				mesPassado = Integer.parseInt(pegarUltimoMes.substring(3, 5));
				
				if(diaHoje==28 && esteMes > mesPassado){
					GerarFechamentoMes();
				}else if(diaHoje==28 && esteMes ==1 && mesPassado==12){
					GerarFechamentoMes();
				}else{
					JOptionPane.showMessageDialog(null, "O fechamento mensal é feito todo dia 28");
				}
			}
		}else{
			JOptionPane.showMessageDialog(null, "O fechamento mensal é feito todo dia 28");
		}*/	
	}
	public void GerarFechamentoMes(){
		/*for(FechamentoDiaModel item: _repositorioFechaDia.ObterFechaDia()){
			fecha.setCredito(fecha.getCredito() +item.getCredito());
			fecha.setDebito(fecha.getDebito()+item.getDebito());
			fecha.setVisaVale(fecha.getVisaVale()+ item.getVisaVale());
			fecha.setVoucher(fecha.getVoucher()+item.getVoucher());
			fecha.setFundo(fecha.getFundo()+item.getFundo());
			fecha.setSaida(fecha.getSaida()+item.getSaida());
			fecha.setDespesa(fecha.getDespesa()+item.getDespesa());
			fecha.setLucro(fecha.getLucro()+item.getLucro());
			fecha.setTotal(fecha.getTotal()+item.getTotal());
		}
		fecha.setData(data.toLocaleString());
		_repositorioFechaMes.Adicionar(fecha);
		_repositorioFechaDia.Remover();
		
		ColunasTudo();*/
		
	}
	public void ColunasTableView(){
		clnData.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("data"));
		clnCredito.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("credito"));
		clnDebito.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("debito"));
		clnVoucher.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("voucher"));
		clnVisaVale.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("visaVale"));
		
		clnFundo.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("fundo"));
		clnSaida.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("saida"));
		clnDespesa.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("despesa"));
		clnTotal.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("total"));
		clnLucro.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("lucro"));
		
		AtualizarColunasTableView();
	}
	public void ColunasTudo(){
		clnData1.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("data"));
		clnCredito1.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("credito"));
		clnDebito1.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("debito"));
		clnVoucher1.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("voucher"));
		clnVisaVale1.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("visaVale"));
		
		clnFundo1.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("fundo"));
		clnSaida1.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("saida"));
		clnDespesa1.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("despesa"));
		clnTotal1.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("total"));
		clnLucro1.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("lucro"));
		
		AtualizarColunasTudo();
	}
	public void AtualizarColunasTableView(){
		/*ArrayList<FechamentoDiaModel> lstFecha = _repositorioFechaDia.ObterFechaDia();
		ObservableList<FechamentoDiaModel> data = FXCollections.observableArrayList(lstFecha);
		tableView.setItems(data);*/
	}
	public void AtualizarColunasTudo(){
		/*ArrayList<FechamentoDiaModel> lstFecha = _repositorioFechaMes.ObterFechaMes();		
		ObservableList<FechamentoDiaModel> data = FXCollections.observableArrayList(lstFecha);
		tblTudo.setItems(data);*/
	}
}
