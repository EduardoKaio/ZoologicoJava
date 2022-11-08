/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoologico;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class FXMLDocumentController implements Initializable {

    @FXML
    private StackPane pilhaPainel;

    @FXML
    private void CadastrarAnimal(ActionEvent event) {
        carregarPagina("CadastrarAnimal");
    }

    @FXML
    private void CadastrarFuncionario(ActionEvent event) {
        carregarPagina("CadastrarFuncionario");
    }

    @FXML
    private void VerAnimais(ActionEvent event) {
        carregarPagina("VerAnimais");
    }

    @FXML
    private void VerFuncionarios(ActionEvent event) {
        carregarPagina("VerFuncionarios");
    }

    private void carregarPagina(String name) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/" + name + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        pilhaPainel.getChildren().clear();
        pilhaPainel.getChildren().add(root);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
