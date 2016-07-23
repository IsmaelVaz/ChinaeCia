package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import br.com.restaurantechinaecia.banco_de_dados.*;
import br.com.restaurantechinaecia.helper.Usuario;
import br.com.restaurantechinaecia.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class FechamentoDiaController implements Initializable{
	
	@FXML
	TableView<ProdutoModel> tblProdutos ;
	
	@FXML
	TableView<DespesasDiaModel> tblDespesas;
	
	@FXML
	TableView<FechamentoDiaModel> tblTudo;
	
	@FXML
	TableColumn<ProdutoModel, String> clnCodigo, clnProduto, clnValor;
	
	@FXML
	TableColumn<DespesasDiaModel, String> clnEmpresa, clnValorEmpresa, clnSaidaEmpresa;
	
	@FXML
	TableColumn<FechamentoDiaModel, String> clnFundo,  clnSaida, clnDespesa,  clnTotal, clnLucro;
	
	@FXML
	TableColumn<FechamentoDiaModel, String> clnCredito, clnDebito, clnVoucher, clnVisaVale;
		
	@FXML
	Button btnGerarFechamento;

	FechamentoDiaModel fechaDia;
	FormasDePagamentoModel fp;
        ArrayList<FechamentoDiaModel> lstFechaDia;
        ArrayList<DespesasDiaModel> lstDespesa;
        ArrayList<ProdutoModel> lstProduto;
	Date data;
        BdControl db;
        
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		data = new Date();
		fechaDia = new  FechamentoDiaModel();
                db = new BdControl();
		
		btnGerarFechamento.setOnAction(l-> GerarFechamento());
		
                
                
		clnProduto.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("nome"));
		clnCodigo.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("id"));
		clnValor.setCellValueFactory(new PropertyValueFactory<ProdutoModel, String>("valor"));
		
		AtualizarColunasProdutos();
		
		clnEmpresa.setCellValueFactory(new PropertyValueFactory<DespesasDiaModel, String>("nome"));
		clnValorEmpresa.setCellValueFactory(new PropertyValueFactory<DespesasDiaModel, String>("valor"));
		clnSaidaEmpresa.setCellValueFactory(new PropertyValueFactory<DespesasDiaModel, String>("saida"));

		AtualizarColunasDespesas();
	}
	public FechamentoDiaController(FormasDePagamentoModel fp) {
		// TODO Auto-generated constructor stub
		this.fp=fp;
	}
	public void GerarFechamento(){
                fechaDia.setDespesa(0.00);
                fechaDia.setSaida(0.00);
                fechaDia.setDebito(0.00);
                fechaDia.setCredito(0.00);
                fechaDia.setVoucher(0.00);
                fechaDia.setVisaVale(0.00);
                String sql_3 ="select * from fechamento order by cod_fechamento desc limit 1;";
                ResultSet rs_3 = db.DadosComRetorno(sql_3);
                int cod = 0;
                boolean jaTem = false;
                try {
                    while(rs_3.next()){
                        if(rs_3.getDouble("total_saida") == 0 && rs_3.getDouble("total_despesa") == 0){
                            jaTem = false;
                            cod= rs_3.getInt("cod_fechamento");
                        }else{
                            jaTem = true;
                            String sql ="insert into fechamento (fundo, verif, data) values ("+Usuario.FUNDO+", 1, now())";
                            db.DadosSemRetorno(sql);
                            cod= rs_3.getInt("cod_fechamento")+1;
                        }
                        
                        
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FechamentoDiaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                if(lstProduto.isEmpty()){
                    JOptionPane.showMessageDialog(null, "NÃO HÁ PRODUTOS PARA SEREM FECHADOS !!");
                }else{
                    lstFechaDia = new ArrayList<>();
                    String sql ="select cod_tipopagamento, sum(valor) as total "
                            + "from pagamento where fechado is null "
                            + "group by cod_tipopagamento;";

                    ResultSet rs = db.DadosComRetorno(sql);
                    try {
                        while(rs.next()){
                            switch(rs.getInt("cod_tipopagamento")){
                                case 2:
                                    fechaDia.setSaida(rs.getDouble("total"));
                                    break;
                                case 3:
                                    fechaDia.setDebito(rs.getDouble("total"));
                                    break;
                                case 4:
                                    fechaDia.setCredito(rs.getDouble("total"));
                                    break;
                                case 5:
                                    fechaDia.setVoucher(rs.getDouble("total"));
                                    break;
                                case 6:
                                    fechaDia.setVisaVale(rs.getDouble("total"));
                                    break;
                            }
                        }
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }


                    String sql_2 ="select d.*, dt.* from despesa as d "
                            + "left join despesa_tipo as dt "
                            + "on d.cod_despesa = dt.cod_despesa "
                            + "where dt.vlr_saida is not null "
                            + "and d.fechado is null;";

                    ResultSet rs_2 = db.DadosComRetorno(sql_2);
                    try {
                        while(rs_2.next()){
                            fechaDia.setSaida(fechaDia.getSaida()+rs_2.getDouble("vlr_saida"));
                        }
                    } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                    }

                    for(DespesasDiaModel item : lstDespesa){
                        fechaDia.setDespesa(fechaDia.getDespesa()+item.getValor());
                    }
                    fechaDia.setTotal(fechaDia.getCredito()+fechaDia.getDebito()+fechaDia.getVoucher()+fechaDia.getVisaVale()
                                    +fechaDia.getSaida());
                    fechaDia.setLucro(fechaDia.getTotal()-fechaDia.getDespesa());

                    fechaDia.setFundo(Usuario.FUNDO);
                    lstFechaDia.add(fechaDia);

                    String sql_fecha ="update fechamento set "
                            + "total_saida = "+fechaDia.getTotal()+", "
                            + "total_despesa = "+fechaDia.getDespesa()+", "
                            + "usr = "+Usuario.COD_USER+" "
                            + "where cod_fechamento = "+cod+";";
                    db.DadosSemRetorno(sql_fecha);

                    String sql_proc ="update produto_vendido set fechado = "+cod+" where fechado = null or fechado is null; ";

                    db.DadosSemRetorno(sql_proc);
                    sql_proc ="update despesa set fechado = "+cod+" where fechado = null or fechado is null;";
                    db.DadosSemRetorno(sql_proc);
                    sql_proc ="update pagamento set fechado = "+cod+" where fechado = null or fechado is null;";
                    db.DadosSemRetorno(sql_proc);


                    ColunasTudo();
                    AtualizarColunasProdutos();

                    AtualizarColunasDespesas();
                }
                
	}

	public void ColunasTudo(){
		clnCredito.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("credito"));
		clnDebito.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("debito"));
		clnVoucher.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("voucher"));
		clnVisaVale.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("visaVale"));
		
		clnFundo.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("fundo"));
		clnSaida.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("saida"));
		clnDespesa.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("despesa"));
		clnTotal.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("total"));
		clnLucro.setCellValueFactory(new PropertyValueFactory<FechamentoDiaModel, String>("lucro"));
		
		AtualizarColunasTudo();
	}
	public void AtualizarColunasProdutos(){
                
                lstProduto = new ArrayList<>();
		
		String sql = "select pv.*, p.* from produto_vendido as pv"
                        + " inner join produto as p "
                        + "on pv.cod_produto = p.cod_produto "
                        + "where pv.fechado is null;";
                
                
		ResultSet rs = db.DadosComRetorno(sql);
		try {
			while(rs.next()){
				ProdutoModel produto = new ProdutoModel();
				produto.setId(rs.getInt("cod_produto"));
				produto.setNome(rs.getString("nome"));
				produto.setValor(rs.getDouble("valor"));
				lstProduto.add(produto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<ProdutoModel> data = FXCollections.observableList(lstProduto);
		tblProdutos.setItems(data);
	}
	public void AtualizarColunasDespesas(){
		
            lstDespesa = new ArrayList<>();
            String sql ="select d.cod_despesa, e.nome as nomeEmpresa, d.valor as valorTotal, dt.vlr_metade, dt.vlr_saida, d.data "
            +"from despesa as d "
            +"left join despesa_tipo as dt "
            +"on (dt.cod_despesa = d.cod_despesa) "
            + "inner join empresa as e "
            + "on (d.cod_empresa = e.cod_empresa) "
            + "where d.fechado is null;";

            ResultSet rs = db.DadosComRetorno(sql);
            try {
                    while(rs.next()){
                            DespesasDiaModel despesaModel = new DespesasDiaModel();
                            despesaModel.setNome(rs.getString("nomeEmpresa"));
                            despesaModel.setValor(rs.getDouble("valorTotal"));
                            despesaModel.setCod_despesa(rs.getInt("cod_despesa"));
                            if(rs.getDouble("vlr_saida") != 0){
                                    if(rs.getDouble("vlr_metade")!=0){
                                            despesaModel.setSaida("METADE/"+rs.getDouble("vlr_saida"));
                                    }else {
                                            despesaModel.setSaida("SAIDA");
                                    }
                            }else{
                                    despesaModel.setSaida("NORMAL");
                            }
                            lstDespesa.add(despesaModel);
                    }
                    ObservableList<DespesasDiaModel> data = FXCollections.observableList(lstDespesa);
                    tblDespesas.setItems(data);
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
	}
	public void AtualizarColunasTudo(){
		
		ObservableList<FechamentoDiaModel> data = FXCollections.observableArrayList(lstFechaDia);
		tblTudo.setItems(data);
	}
}
