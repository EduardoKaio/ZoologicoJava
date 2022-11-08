/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoologico;

import dao.AnimalDAO;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Animal;

public class VerAnimaisController implements Initializable {

    @FXML
    private TableView<Animal> tableAnimais;
    @FXML
    private TableColumn<?, ?> colunaID;
    @FXML
    private TableColumn<?, ?> colunaNome;
    @FXML
    private TableColumn<?, ?> ColunaClassificacao;
    @FXML
    private TableColumn<?, ?> colunaPeso;
    @FXML
    private TableColumn<?, ?> ColunaLocalizacao;
    @FXML
    private TableColumn<?, ?> ColunaCaracteristica;
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
        try {
            // TODO
            carregarDadosTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerAnimaisController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarDadosTabela() throws ClassNotFoundException {
        tableAnimais.getItems().clear();
        colunaID.setCellValueFactory(new PropertyValueFactory<>("id_animal"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        ColunaClassificacao.setCellValueFactory(new PropertyValueFactory<>("classificacao"));
        colunaPeso.setCellValueFactory(new PropertyValueFactory<>("Peso"));
        ColunaLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        ColunaCaracteristica.setCellValueFactory(new PropertyValueFactory<>("caracteristica"));

        AnimalDAO animalDao = new AnimalDAO();
        ArrayList<Animal> animais = animalDao.buscarTodos();
        System.out.println("˜˜carregando dados----" + animais.size());

        //comando para passar para javaFx
        ObservableList<Animal> itensAnimalFX = FXCollections.observableArrayList(animais);
        // Jogar na tabela.
        tableAnimais.setItems(itensAnimalFX);
    }
    
      public void remover(ActionEvent event) throws ClassNotFoundException{
        Animal animal = tableAnimais.getSelectionModel().getSelectedItem();
        AnimalDAO dao = new AnimalDAO();
        
        //apagar objeto
        
        if (animal == null) {
            Alert erroAlert = new Alert(Alert.AlertType.ERROR);
            erroAlert.setContentText("Selecione um animal para remover");
            erroAlert.showAndWait();
        } else {
          dao.delete(animal.getId_animal());
        carregarDadosTabela();
        }
    }
    
    
    public void AbrirModal(ActionEvent event) throws IOException {
        
        AnimalDAO dao = new AnimalDAO();
        Animal animal = tableAnimais.getSelectionModel().getSelectedItem();

        if (animal == null) {
            Alert erroAlert = new Alert(Alert.AlertType.ERROR);
            erroAlert.setContentText("Selecione um animal para editar");
            erroAlert.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/CadastrarAnimal.fxml"));
                
            loader.load();
            
            CadastrarAnimalController controller = loader.getController();
            
            controller.getTxtNome().setText(animal.getNome());
            controller.getTxtClassificacao().setText(animal.getClassificacao());
            controller.getTxtCaracteristica().setText(animal.getCaracteristica());
            controller.getTxtLocalizacao().setText(animal.getLocalizacao());
            controller.getTxtPeso().setText(animal.getPeso());
            controller.setUpdate(Boolean.TRUE);
            controller.setIdAnimal(animal.getId_animal());
            
            
            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        }

    }

}
