package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.com.restaurantechinaecia.model.FechamentoDiaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class RelatorioLucroController implements Initializable{

	@FXML
	TableView<FechamentoDiaModel> tableView;
	@FXML
	TableColumn<FechamentoDiaModel, String> clnData, clnFundo,  clnSaida, clnDespesa,  clnTotal, 
		clnLucro, clnCredito, clnDebito, clnVoucher, clnVisaVale;
	
	@FXML
	LineChart grafico;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		XYChart.Series lucro = new XYChart.Series();
		lucro.setName("Lucro Mensal");
		lucro.getData().add(new XYChart.Data("Janeiro", ValorMes(01)));
		lucro.getData().add(new XYChart.Data("Fevereiro", ValorMes(02)));
		lucro.getData().add(new XYChart.Data("Março", ValorMes(03)));
		lucro.getData().add(new XYChart.Data("Abril", ValorMes(04)));
		lucro.getData().add(new XYChart.Data("Maio", ValorMes(05)));
		lucro.getData().add(new XYChart.Data("Junho", ValorMes(06)));
		lucro.getData().add(new XYChart.Data("Julho", ValorMes(07)));
		lucro.getData().add(new XYChart.Data("Agosto", ValorMes(8)));
		lucro.getData().add(new XYChart.Data("Setembro", ValorMes(9)));
		lucro.getData().add(new XYChart.Data("Outubro", ValorMes(10)));
		lucro.getData().add(new XYChart.Data("Novembro", ValorMes(11)));
		lucro.getData().add(new XYChart.Data("Dezembro", ValorMes(12)));
		
		grafico.getData().addAll(lucro);
		
		ColunasTableView();
	}
	public double ValorMes(int mes){
		
		String valorLetra = null;
		/*for(FechamentoDiaModel item : _repositorio.ObterFechaMes()){
			valorLetra=item.getData().substring(3, 5);
			if(Integer.parseInt(valorLetra)==mes){
				return item.getLucro();
			}
		}*/
		return 0;
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
	public void AtualizarColunasTableView(){
		/*ArrayList<FechamentoDiaModel> lstFecha = _repositorio.ObterFechaMes();
		
		ObservableList<FechamentoDiaModel> data = FXCollections.observableArrayList(lstFecha);
		tableView.setItems(data);*/
	}
}
