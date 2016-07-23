/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.restaurantechinaecia.controller;

import br.com.restaurantechinaecia.banco_de_dados.BdControl;
import br.com.restaurantechinaecia.model.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ismael
 */
public class ConfigController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableColumn<Categoria, String> clnTPID, clnTPNome;
    
    @FXML
    TableView<Categoria> tblTipoProduto;
    
    @FXML
    TextField txtTPNome;
    
    @FXML
    Button btnTPAdicionar, btnTPAtualizar, btnTPRemover;
    
    BdControl db;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new BdControl();
        clnTPID.setCellValueFactory(new PropertyValueFactory<Categoria, String>("cod_categoria"));
        clnTPNome.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nome"));
        
        btnTPAdicionar.setOnAction(l-> AdicionarTP());
        btnTPAtualizar.setOnAction(l->AtualizarTP());
        btnTPRemover.setOnAction(l->RemoverTP());
        AtualizarTableTipoProduto();
    }

    public void AdicionarTP(){
        if(!txtTPNome.getText().isEmpty()){
            String sql ="insert into categoria(nome) values (ucase('"+txtTPNome.getText()+"'));";
            db.DadosSemRetorno(sql);
            AtualizarTableTipoProduto();
            
            txtTPNome.setText("");
        }else{
            JOptionPane.showMessageDialog(null, "DIGITE O NOME DO TIPO DE PRODUTO !!");
        }
    
    }
    public void AtualizarTP(){
        Categoria c = tblTipoProduto.getSelectionModel().getSelectedItem();
        if(c!= null){
            int cod_categoria = c.getCod_categoria();
            String sql ="update categoria set nome = ucase('"+txtTPNome.getText()+"') where cod_categoria = "+cod_categoria+";";
            db.DadosSemRetorno(sql);
            AtualizarTableTipoProduto();
            txtTPNome.setText("");
        }else{
            JOptionPane.showMessageDialog(null, "SELECIONE O TIPO DE PRODUTO !!");
        }
    }
    public void RemoverTP(){
        Categoria c = tblTipoProduto.getSelectionModel().getSelectedItem();
        if(c!= null){
            int cod_categoria = c.getCod_categoria();
            
            String sql ="update produto set cod_categoria = null where cod_categoria = "+cod_categoria+";";
            db.DadosSemRetorno(sql);
            sql ="delete from categoria where cod_categoria = "+cod_categoria+";";
            db.DadosSemRetorno(sql);
            AtualizarTableTipoProduto();
        }else{
            JOptionPane.showMessageDialog(null, "SELECIONE O TIPO DE PRODUTO !!");
        }
    }
    
    public void AtualizarTableTipoProduto(){
        String sql = "SELECT * FROM categoria";
        List<Categoria> lstCategoria = new ArrayList<>();
        ResultSet rs = db.DadosComRetorno(sql);
        try {
                while(rs.next()){
                        Categoria categoria = new Categoria();
                        categoria.setCod_categoria(rs.getInt("cod_categoria"));
                        categoria.setNome(rs.getString("nome"));
                        lstCategoria.add(categoria);
                }
                ObservableList<Categoria> data = FXCollections.observableList(lstCategoria);
                tblTipoProduto.setItems(data);
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
    }
}
