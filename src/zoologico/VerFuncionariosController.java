/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoologico;

import dao.FuncionarioDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Funcionario;

public class VerFuncionariosController implements Initializable {

    @FXML
    private TableView<Funcionario> tableFuncionarios;
    @FXML
    private TableColumn<?, ?> colunaID;
    @FXML
    private TableColumn<?, ?> colunaNome;
    @FXML
    private TableColumn<?, ?> ColunaCpf;
    @FXML
    private TableColumn<?, ?> colunaRg;
    @FXML
    private TableColumn<?, ?> ColunaTelefone;
    @FXML
    private TableColumn<?, ?> ColunaEmail;
    @FXML
    private TableColumn<?, ?> ColunaEndereco;
    @FXML
    private TableColumn<?, ?> ColunaData;
    @FXML
    private Button btExcluir;
    @FXML
    private Button btEditar;
    @FXML
    private Button btAtualizar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO
            carregarDadosTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerAnimaisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarDadosTabela() throws ClassNotFoundException {
        tableFuncionarios.getItems().clear();
        colunaID.setCellValueFactory(new PropertyValueFactory<>("id_funcionario"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        ColunaCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colunaRg.setCellValueFactory(new PropertyValueFactory<>("rg"));
        ColunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        ColunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColunaEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        ColunaData.setCellValueFactory(new PropertyValueFactory<>("data_nascimento"));

        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        ArrayList<Funcionario> funcionarios = funcionarioDao.buscarTodos();
        System.out.println("˜˜carregando dados----" + funcionarios.size());

        //comando para passar para javaFx
        ObservableList<Funcionario> itensFuncionarioFX = FXCollections.observableArrayList(funcionarios);
        // Jogar na tabela.
        tableFuncionarios.setItems(itensFuncionarioFX);
    }

    public void remover(ActionEvent event) throws ClassNotFoundException {
        Funcionario funcionario = tableFuncionarios.getSelectionModel().getSelectedItem();
        FuncionarioDAO dao = new FuncionarioDAO();

        //apagar objeto
        if (funcionario == null) {
            Alert erroAlert = new Alert(Alert.AlertType.ERROR);
            erroAlert.setContentText("Selecione um animal para remover");
            erroAlert.showAndWait();
        } else {
            dao.delete(funcionario.getId_funcionario());
            carregarDadosTabela();
        }
    }
    
    public void AbrirModal(ActionEvent event) throws IOException {
        
        FuncionarioDAO dao = new FuncionarioDAO();
        Funcionario funcionario = tableFuncionarios.getSelectionModel().getSelectedItem();

        if (funcionario == null) {
            Alert erroAlert = new Alert(Alert.AlertType.ERROR);
            erroAlert.setContentText("Selecione um funcionario para editar");
            erroAlert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/CadastrarFuncionario.fxml"));
                
            loader.load();
            
            CadastrarFuncionarioController controller = loader.getController();
            
            controller.getTxtNome().setText(funcionario.getNome());
            controller.getTxtCpf().setText(funcionario.getCpf());
            controller.getTxtRg().setText(funcionario.getRg());
            controller.getTxtTelefone().setText(funcionario.getTelefone());
            controller.getTxtEmail().setText(funcionario.getEmail());
            controller.getTxtEndereco().setText(funcionario.getEndereco());
            controller.getTxtData_nascimento().setText(funcionario.getData_nascimento());
            
            
            controller.setUpdate(Boolean.TRUE);
            controller.setIdFuncionario(funcionario.getId_funcionario());
            
            
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }

    }

}
