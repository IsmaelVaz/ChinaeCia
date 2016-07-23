package br.com.restaurantechinaecia.controller;

import br.com.restaurantechinaecia.banco_de_dados.BdControl;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.com.restaurantechinaecia.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sun.security.pkcs11.Secmod;

public class DespesasMesController implements Initializable{

	@FXML
	TableView<DespesasMesModel> tableView;
	
	@FXML
	TableColumn<DespesasMesModel, String> clnData, clnNome, clnValor;
	
	@FXML
	Label lblTotal;
	
	double valorTotal = 0;
        BdControl db;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		/*for(DespesasMesModel item : _despesaMesRepositorio.ObterDespesas()){
			valorTotal+=item.getValor();
		}*/
                db = new BdControl();
		
		
                String sql ="select sum(valor) as valorTotal "
                +"from despesa ";
                ResultSet rs = db.DadosComRetorno(sql);
                try {
                        while(rs.next()){
                            valorTotal= rs.getFloat("valorTotal");
                        }
                        
                } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                lblTotal.setText("R$ "+valorTotal);
		clnData.setCellValueFactory(new PropertyValueFactory<DespesasMesModel, String>("data"));
		clnNome.setCellValueFactory(new PropertyValueFactory<DespesasMesModel, String>("nome"));
		clnValor.setCellValueFactory(new PropertyValueFactory<DespesasMesModel, String>("valor"));
		
		AtualizarTabela();
	}
	
	public void AtualizarTabela(){
            ArrayList<DespesasMesModel> lstDespesa = new ArrayList<>();
            String sql ="select d.cod_despesa, d.data, e.nome as nomeEmpresa, d.valor as valorTotal "
            +"from despesa as d "
            +"left join despesa_tipo as dt "
            +"on (dt.cod_despesa = d.cod_despesa) "
            + "inner join empresa as e "
            + "on (d.cod_empresa = e.cod_empresa);";

            ResultSet rs = db.DadosComRetorno(sql);
            try {
                    while(rs.next()){
                            DespesasMesModel despesaModel = new DespesasMesModel();
                            despesaModel.setNome(rs.getString("nomeEmpresa"));
                            despesaModel.setValor(rs.getDouble("valorTotal"));
                            despesaModel.setData(rs.getString("data"));
                            lstDespesa.add(despesaModel);
                    }
                    ObservableList<DespesasMesModel> data = FXCollections.observableList(lstDespesa);
                    tableView.setItems(data);
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
        }
}
