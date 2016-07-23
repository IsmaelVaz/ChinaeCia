package br.com.restaurantechinaecia.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import br.com.restaurantechinaecia.banco_de_dados.*;
import br.com.restaurantechinaecia.helper.Usuario;
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

public class DespesasDiaController implements Initializable{

    @FXML
    TableView<DespesasDiaModel> tableView;

    @FXML
    TableColumn<DespesasDiaModel, String> clnNEmpresa, clnVEmpresa, clnVSaida;

    @FXML
    TextField txtValor, txtPrecoMetade;

    @FXML
    Button btnAdicionar, btnAtualizar, btnRemover;

    @FXML
    RadioButton radioSaida, radioMetade, radioNenhum;

    @FXML
    ComboBox<Empresa> cboEmpresa;

    @FXML
    Pane paneMetade;

    Date data;
    BdControl db;
    String hora;
    java.util.Date dd;
    FormasDePagamentoModel fp;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
            // TODO Auto-generated method stub
            db = new BdControl();

            dd = new java.util.Date();
            data = new Date(dd.getTime());

            ToggleGroup grupo= new ToggleGroup();
            radioSaida.setToggleGroup(grupo);
            radioMetade.setToggleGroup(grupo);
            radioNenhum.setToggleGroup(grupo);
            grupo.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                    public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                            if(radioMetade.isSelected()){
                                    paneMetade.setVisible(true);
                            }else if(radioSaida.isSelected()){
                                    paneMetade.setVisible(false);
                            }else if(radioNenhum.isSelected()){
                                    paneMetade.setVisible(false);
                            }
                    }
            });

            String sql="SELECT * FROM empresa";

            ResultSet rs = db.DadosComRetorno(sql);
            ArrayList<Empresa> lstEmpresa = new ArrayList<>();
            try {
                    while(rs.next()){
                            Empresa e = new Empresa();
                            e.setCnpj(rs.getString("cnpj"));
                            e.setCod_empresa(rs.getInt("cod_empresa"));
                            e.setNome(rs.getString("nome"));
                            lstEmpresa.add(e);
                    }
                    cboEmpresa.getItems().addAll(lstEmpresa);
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            clnNEmpresa.setCellValueFactory(new PropertyValueFactory<DespesasDiaModel, String>("nome"));
            clnVEmpresa.setCellValueFactory(new PropertyValueFactory<DespesasDiaModel, String>("valor"));
            clnVSaida.setCellValueFactory(new PropertyValueFactory<DespesasDiaModel, String>("saida"));

            btnAdicionar.setOnAction(l-> Adicionar());
            btnAtualizar.setOnAction(l-> AtualizarDespesa());
            btnRemover.setOnAction(l-> RemoverDespesa());
            AtualizarTabela();
    }
    public DespesasDiaController(FormasDePagamentoModel fp) {
            // TODO Auto-generated constructor stub
            this.fp=fp;
    }
    private void Adicionar(){
            hora = String.format("%s:%s:%s", dd.getHours(), dd.getMinutes(), dd.getMinutes());

            if(!txtValor.getText().isEmpty()){
                    try{
                        String sql_tes="SELECT cod_usuario FROM login_acessado order by data desc limit 1";
                        ResultSet rs2 = db.DadosComRetorno(sql_tes);
                        int user_acess=0;
                        try {
                                while(rs2.next()){
                                        user_acess = rs2.getInt("cod_usuario");
                                }
                        } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                            double valor =Double.parseDouble(txtValor.getText().replace(",", "."));
                            int cod_empresa = cboEmpresa.getSelectionModel().getSelectedItem().getCod_empresa();

                            if(radioSaida.isSelected()){
                                    String sql= "INSERT INTO despesa (cod_empresa, valor, data, cod_usuario) "
                                                    + "values ("+cod_empresa+", "+valor+", now(), "+user_acess+")";
                                    db.DadosSemRetorno(sql);

                                    sql="SELECT MAX(cod_despesa) AS cod_despesa FROM despesa";
                                    ResultSet rs = db.DadosComRetorno(sql);
                                    int cod_despesa=0;
                                    try {
                                            while(rs.next()){
                                                    cod_despesa = rs.getInt("cod_despesa");
                                            }
                                    } catch (SQLException e) {
                                            // TODO Auto-generated catch block
                                            e.printStackTrace();
                                    }
                                    sql= "INSERT INTO despesa_tipo (cod_despesa, vlr_saida) "
                                                    + "values ("+cod_despesa+", "+valor+")";
                                    db.DadosSemRetorno(sql);
                                    AtualizarTabela();
                            }else if(radioMetade.isSelected()){
                                    if(!txtPrecoMetade.getText().isEmpty()){
                                            double vlr_saida = Double.parseDouble(txtPrecoMetade.getText().replace(",", "."));

                                            String sql= "INSERT INTO despesa (cod_empresa, valor, data, cod_usuario) "
                                                    + "values ("+cod_empresa+", "+valor+", now(), "+user_acess+")";
                                            db.DadosSemRetorno(sql);

                                            sql="SELECT MAX(cod_despesa) AS cod_despesa FROM despesa";
                                            ResultSet rs = db.DadosComRetorno(sql);
                                            int cod_despesa=0;
                                            try {
                                                    while(rs.next()){
                                                            cod_despesa = rs.getInt("cod_despesa");
                                                    }
                                            } catch (SQLException e) {
                                                    // TODO Auto-generated catch block
                                                    e.printStackTrace();
                                            }
                                            sql= "INSERT INTO despesa_tipo (cod_despesa, vlr_metade, vlr_saida) "
                                                            + "values ("+cod_despesa+", "+(valor-vlr_saida)+", "+vlr_saida+")";
                                            db.DadosSemRetorno(sql);
                                            AtualizarTabela();
                                    }else{
                                            JOptionPane.showMessageDialog(null, "DIGITE O PREãO DA METADE!!");
                                    }
                            }else{
                                    String sql= "INSERT INTO despesa (cod_empresa, valor, data, cod_usuario) "
                                                    + "values ("+cod_empresa+", "+valor+", now(), "+user_acess+")";
                                    db.DadosSemRetorno(sql);
                                    AtualizarTabela();
                            }
                    }catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "DIGITE APENAS VALORES VALIDOS - EX. 420.50");
                    }
            }else{
                    JOptionPane.showMessageDialog(null, "PREECHA O CAMPO VALOR!!");
            }

    }
    public void AtualizarTabela(){
            ArrayList<DespesasDiaModel> lstDespesa = new ArrayList<>();
            String sql ="select d.cod_despesa, e.nome as nomeEmpresa, d.valor as valorTotal, dt.vlr_metade, dt.vlr_saida, d.data "
            +"from despesa as d "
            +"left join despesa_tipo as dt "
            +"on (dt.cod_despesa = d.cod_despesa) "
            + "inner join empresa as e "
            + "on (d.cod_empresa = e.cod_empresa) "
            + "where fechado is null;";

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
                    tableView.setItems(data);
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }
    public void AtualizarDespesa(){
            DespesasDiaModel despesaSelect = tableView.getSelectionModel().getSelectedItem();
            if(despesaSelect!=null){
                    int cod_despesa = despesaSelect.getCod_despesa();
                    if(!txtValor.getText().isEmpty()){
                            try{
                                    double valor =Double.parseDouble(txtValor.getText().replace(",", "."));
                                    int cod_empresa = cboEmpresa.getSelectionModel().getSelectedItem().getCod_empresa();
                                    hora = String.format("%s:%s:%s", dd.getHours(), dd.getMinutes(), dd.getMinutes());
                                    if(radioSaida.isSelected()){
                                            String sql="UPDATE despesa set cod_empresa="+cod_empresa+", valor ="+valor+", data='"+data+" "+hora+"' "
                                                            + "where cod_despesa = "+cod_despesa+";";
                                            db.DadosSemRetorno(sql);

                                            sql="UPDATE despesa_tipo set vlr_saida="+valor+", vlr_metade=0 where cod_despesa = "+cod_despesa+";";
                                            db.DadosSemRetorno(sql);

                                            AtualizarTabela();
                                    }else if(radioMetade.isSelected()){
                                            if(!txtPrecoMetade.getText().isEmpty()){
                                                    double vlr_saida = Double.parseDouble(txtPrecoMetade.getText().replace(",", "."));

                                                    String sql="UPDATE despesa set cod_empresa="+cod_empresa+", valor ="+valor+", data='"+data+" "+hora+"' "
                                                                    + "where cod_despesa = "+cod_despesa+";";
                                                    db.DadosSemRetorno(sql);

                                                    sql="UPDATE despesa_tipo set vlr_metade="+(valor-vlr_saida)+", vlr_saida="+vlr_saida+" where cod_despesa = "+cod_despesa+";";
                                                    db.DadosSemRetorno(sql);

                                                    AtualizarTabela();
                                            }else{
                                                    JOptionPane.showMessageDialog(null, "DIGITE O PREãO DA METADE!!");
                                            }
                                    }else{
                                            String sql="UPDATE despesa set cod_empresa="+cod_empresa+", valor ="+valor+", data='"+data+" "+hora+"' "
                                                            + "where cod_despesa = "+cod_despesa+";";
                                            db.DadosSemRetorno(sql);
                                    }
                            }catch(NumberFormatException e){
                                    JOptionPane.showMessageDialog(null, "DIGITE APENAS VALORES VALIDOS - EX. 420.50");
                            }
                    }else{
                            JOptionPane.showMessageDialog(null, "PREENCHA O CAMPO VALOR!!");
                    }
            }else{
                    JOptionPane.showMessageDialog(null, "SELECIONE UMA DESPESA!!");
            }
    }
    public void RemoverDespesa(){
            DespesasDiaModel despesaSelect = tableView.getSelectionModel().getSelectedItem();
            if(despesaSelect!=null){
                    int result = JOptionPane.showConfirmDialog(null,"Deseja Excluir ? ","Excluir",JOptionPane.YES_NO_CANCEL_OPTION);   

                    if(result ==JOptionPane.YES_OPTION){
                            int cod_despesa= despesaSelect.getCod_despesa();
                            String sql = "DELETE FROM despesa_tipo where cod_despesa ="+cod_despesa;
                            db.DadosSemRetorno(sql);
                            sql = "DELETE FROM despesa where cod_despesa ="+cod_despesa;
                            db.DadosSemRetorno(sql);
                            AtualizarTabela();
                    } 
            }else{
                    JOptionPane.showMessageDialog(null, "SELECIONE UMA DESPESA!!");
            }
    }
}
