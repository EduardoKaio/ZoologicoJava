/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoologico;

import dao.AnimalDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Animal;

public class CadastrarAnimalController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtClassificacao;
    @FXML
    private TextField txtCaracteristica;
    @FXML
    private TextField txtLocalizacao;
    @FXML
    private TextField txtPeso;
    @FXML
    private Button btCadastrar;

    private boolean update;

    private int idAnimal;

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public TextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(TextField txtNome) {
        this.txtNome = txtNome;
    }

    public TextField getTxtClassificacao() {
        return txtClassificacao;
    }

    public void setTxtClassificacao(TextField txtClassificacao) {
        this.txtClassificacao = txtClassificacao;
    }

    public TextField getTxtCaracteristica() {
        return txtCaracteristica;
    }

    public void setTxtCaracteristica(TextField txtCaracteristica) {
        this.txtCaracteristica = txtCaracteristica;
    }

    public TextField getTxtLocalizacao() {
        return txtLocalizacao;
    }

    public void setTxtLocalizacao(TextField txtLocalizacao) {
        this.txtLocalizacao = txtLocalizacao;
    }

    public TextField getTxtPeso() {
        return txtPeso;
    }

    public void setTxtPeso(TextField txtPeso) {
        this.txtPeso = txtPeso;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void AdicionarAnimal(ActionEvent event) throws ClassNotFoundException {

        String nome = txtNome.getText();
        String classificacao = txtClassificacao.getText();
        String caracteristica = txtCaracteristica.getText();
        String localizacao = txtLocalizacao.getText();
        String peso = txtPeso.getText();

        // criando um aluno novo e preenchendo 
        Animal animal = new Animal(nome, classificacao, caracteristica, localizacao, peso);

        //chamando o dao 
        AnimalDAO dao = new AnimalDAO();

        if (update) {
            animal.setId_animal(idAnimal);
            dao.update(animal);
            fecharModal();
        } else {
            dao.create(animal);
        }

        limparDadosFormulario();
    }

    public void fecharModal() {
        Stage stage = (Stage) btCadastrar.getScene().getWindow();
        stage.close();
    }

    private void limparDadosFormulario() {
        txtNome.setText("");
        txtClassificacao.setText("");
        txtCaracteristica.setText("");
        txtLocalizacao.setText("");
        txtPeso.setText("");
    }

    public Boolean getUpdate() {
        return update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

}
