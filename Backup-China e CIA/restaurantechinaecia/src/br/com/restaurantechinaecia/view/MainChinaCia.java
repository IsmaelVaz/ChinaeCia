package br.com.restaurantechinaecia.view;

import java.io.IOException;
import br.com.restaurantechinaecia.controller.*;
import br.com.restaurantechinaecia.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainChinaCia extends Application {

    Stage primaryStage;
    Stage secondStage;
    BorderPane border;
    public static FechamentoDiaModel fechaDia;
    FormasDePagamentoModel fp;
    static int usuario_logado;

    @Override
    public void start(Stage primaryStage) {
        // TODO Auto-generated method stub
        this.primaryStage = primaryStage;
        secondStage = new Stage();
        fp = new FormasDePagamentoModel();
        fechaDia = new FechamentoDiaModel();
        TelaLogin();
    }

    public void AtribuirFundo(double fundo) {
        /*XMLValorFechamento _valor = new  XMLValorFechamento();
		ArrayList<FechamentoDiaModel> lstValor= _valor.ObterValores();
		for(FechamentoDiaModel item : lstValor){
			item.setFundo(fundo);
			_valor.AtualizarXML(lstValor);
			break;
		}*/
    }

    public void TelaLogin() {

        //Image imagem = new Image("file:/C:/China e CIA/imagens/china.png");
        TelaLoginController controller = new TelaLoginController(this);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("TelaLogin.fxml"));

        AnchorPane telaLogin;
        try {
            telaLogin = (AnchorPane) loader.load();
            Scene scene = new Scene(telaLogin);
            telaLogin.setOnKeyPressed(k -> {
                final KeyCombination enter = new KeyCodeCombination(KeyCode.ENTER);
                if (enter.match(k)) {
                    controller.Entrar();
                }
            });
            primaryStage.setScene(scene);
            primaryStage.setTitle("China & CIA - Login");
            primaryStage.getIcons().add(new Image("file:/C:/China e CIA/imagens/china.png"));
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CarregarBorderFuncionario() {

        BorderFuncionarioController controller = new BorderFuncionarioController(this);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("BorderFuncionario.fxml"));

        try {
            border = (BorderPane) loader.load();
            Scene scene = new Scene(border);

            primaryStage.setScene(scene);
            primaryStage.setTitle("China & CIA - Funcionario");
            primaryStage.getIcons().add(new Image("file:/C:/China e CIA/imagens/china.png"));
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CarregarBorderADM() {

        BorderADMController controller = new BorderADMController(this);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("BorderIndex.fxml"));

        try {
            border = (BorderPane) loader.load();
            Scene scene = new Scene(border);

            primaryStage.setScene(scene);
            primaryStage.setTitle("China & CIA - Administrador");
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CarregarMesas() {
        MesaController controller = new MesaController(this);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("MesasIndex.fxml"));

        AnchorPane mesa;

        try {
            mesa = (AnchorPane) loader.load();
            /*mesa.setOnKeyPressed(k->{
				final KeyCombination f1 = new KeyCodeCombination(KeyCode.ENTER);
				final KeyCombination f3 = new KeyCodeCombination(KeyCode.F3);
				final KeyCombination f4 = new KeyCodeCombination(KeyCode.F4);
				if (f1.match(k)) {
					controller.AdicionarProduto();
				}else if(f3.match(k)){
					controller.RemoverProduto();
				}else if(f4.match(k)){
					controller.FecharMesa();
				}
			});*/

            primaryStage.setTitle("China & CIA - Mesas");
            border.setCenter(mesa);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CarregarProdutos() {
        ProdutosController controller = new ProdutosController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("ProdutosIndex.fxml"));

        AnchorPane produtos;
        try {
            produtos = (AnchorPane) loader.load();
            primaryStage.setTitle("China & CIA - Produtos");
            border.setCenter(produtos);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CarregarConfig() {
        ConfigController controller = new ConfigController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("Config.fxml"));

        AnchorPane config;
        try {
            config = (AnchorPane) loader.load();
            primaryStage.setTitle("China & CIA - Config");
            border.setCenter(config);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void CarregarDespesaDia() {
        DespesasDiaController controller = new DespesasDiaController(fp);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("DespesasDiaIndex.fxml"));

        AnchorPane despesa;
        try {
            despesa = (AnchorPane) loader.load();
            primaryStage.setTitle("China & CIA - Despesas do Dia");
            border.setCenter(despesa);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CarregarProdutosDia() {
        ProdutosVendidosDiaController controller = new ProdutosVendidosDiaController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("ProdutosVendidosDia.fxml"));

        AnchorPane produtosDia;

        try {
            produtosDia = (AnchorPane) loader.load();
            primaryStage.setTitle("China & CIA - Produtos vendidos no dia");
            border.setCenter(produtosDia);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void CarregarFechamentoDia() {
        FechamentoDiaController controller = new FechamentoDiaController(fp);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("FechamentoDia.fxml"));

        AnchorPane fechaDia;

        try {
            fechaDia = (AnchorPane) loader.load();
            primaryStage.setTitle("China & CIA - Fechamento do dia");
            border.setCenter(fechaDia);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void CarregarProdutosVendidosMes() {
        ProdutosVendidosMesController controller = new ProdutosVendidosMesController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("ProdutosVendidosMes.fxml"));

        AnchorPane pvMes;

        try {
            pvMes = (AnchorPane) loader.load();
            primaryStage.setTitle("China & CIA - Produtos vendidos no M�s");
            border.setCenter(pvMes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void CarregarDespesasMes() {
        DespesasMesController controller = new DespesasMesController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("DespesasMesIndex.fxml"));

        AnchorPane DespesasMes;

        try {
            DespesasMes = (AnchorPane) loader.load();
            primaryStage.setTitle("China & CIA - Despesas do M�s");
            border.setCenter(DespesasMes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CarregarUsuarios() {
        ContaUsuarioController controller = new ContaUsuarioController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("ContaUsuarios.fxml"));

        AnchorPane usuarios;

        try {
            usuarios = (AnchorPane) loader.load();
            primaryStage.setTitle("China & CIA - Conta de us�arios");
            border.setCenter(usuarios);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CarregarClientes() {
        ContaClienteController controller = new ContaClienteController(this);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("ContaCliente.fxml"));

        AnchorPane ContaCliente;

        try {
            ContaCliente = (AnchorPane) loader.load();
            ContaCliente.setOnKeyPressed(k -> {
                final KeyCombination f2 = new KeyCodeCombination(KeyCode.F1);
                final KeyCombination f3 = new KeyCodeCombination(KeyCode.F3);
                final KeyCombination f4 = new KeyCodeCombination(KeyCode.F4);
                final KeyCombination f5 = new KeyCodeCombination(KeyCode.F4);
                if (f2.match(k)) {
                    controller.Adicionar();
                } else if (f3.match(k)) {
                    controller.CadastrarNovoCliente();
                } else if (f4.match(k)) {
                    controller.MostrarCliente();
                } else if (f5.match(k)) {
                    controller.AtualizarTabela();
                }
            });
            primaryStage.setTitle("China & CIA - Conta dos clientes");
            border.setCenter(ContaCliente);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CarregarContaClienteAdicionar() {
        //Stage secondStage = new Stage();
        ContaClienteAdicionarController controller = new ContaClienteAdicionarController(secondStage);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("ContaClienteAdicionar.fxml"));

        AnchorPane ContaClienteAdicionar;
        try {
            ContaClienteAdicionar = (AnchorPane) loader.load();
            Scene scene = new Scene(ContaClienteAdicionar);
            secondStage.setScene(scene);
            secondStage.setTitle("China & CIA - Conta dos clientes");
            secondStage.getIcons().add(new Image("file:/C:/China e CIA/imagens/china.png"));
            secondStage.show();
            //border.setCenter(ContaClienteAdicionar);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CarregarContaClienteMostrar(int cliente) {

        ContaClienteMostrarController controller = new ContaClienteMostrarController(cliente, this);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("ContaClienteMostrar.fxml"));

        AnchorPane ContaClienteMostrar;
        //Stage secondStage = new Stage();
        try {
            ContaClienteMostrar = (AnchorPane) loader.load();
            Scene scene = new Scene(ContaClienteMostrar);
            secondStage.setScene(scene);
            secondStage.setTitle("China & CIA - Conta dos clientes");
            secondStage.getIcons().add(new Image("file:/C:/China e CIA/imagens/china.png"));
            secondStage.show();
            //border.setCenter(ContaClienteMostrar);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CarregarPagamento(double valorProduto) {
        Stage secondStage = new Stage();
        PagamentoController controller = new PagamentoController(valorProduto, secondStage);
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("Pagamento.fxml"));

        AnchorPane Pagamento;

        try {
            Pagamento = (AnchorPane) loader.load();
            Scene scene = new Scene(Pagamento);
            secondStage.setScene(scene);
            secondStage.setTitle("China & CIA - Pagamento");
            secondStage.getIcons().add(new Image("file:/C:/China e CIA/imagens/china.png"));
            secondStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void CarregarFechamentoMes() {
        FechamentoMesController controller = new FechamentoMesController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("FechamentoMes.fxml"));

        AnchorPane ContaCliente;

        try {
            ContaCliente = (AnchorPane) loader.load();
            primaryStage.setTitle("China & CIA - Fechamento do m�s");
            border.setCenter(ContaCliente);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void CarregarRelatorioLucro() {
        RelatorioLucroController controller = new RelatorioLucroController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("RelatorioLucro.fxml"));

        AnchorPane ContaCliente;

        try {
            ContaCliente = (AnchorPane) loader.load();
            primaryStage.setTitle("China & CIA - Relatorio de lucros");
            border.setCenter(ContaCliente);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
