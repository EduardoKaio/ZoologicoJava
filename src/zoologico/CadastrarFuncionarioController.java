/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoologico;

import dao.FuncionarioDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Funcionario;


public class CadastrarFuncionarioController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtRg;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtData_nascimento;
        @FXML
    private Button btCadastrar;

    private boolean update;

    private int idFuncionario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AdicionarFuncionario(ActionEvent event) throws ClassNotFoundException {

        String nome = txtNome.getText();
        String rg = txtRg.getText();
        String cpf = txtCpf.getText();;
        String endereco = txtEndereco.getText();
        String email = txtEmail.getText();
        String telefone = txtTelefone.getText();
        String data_nascimento = txtData_nascimento.getText();

        // criando um aluno novo e preenchendo 
        Funcionario funcionario = new Funcionario(nome, rg, cpf, telefone, endereco, data_nascimento, email);

        //chamando o dao 
        FuncionarioDAO dao = new FuncionarioDAO();
       if (update) {
            funcionario.setId_funcionario(idFuncionario);
            dao.update(funcionario);
            fecharModal();
        } else {
            dao.create(funcionario);
        }

        limparDadosFormulario();
    }

    public TextField getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(TextField txtNome) {
        this.txtNome = txtNome;
    }

    public TextField getTxtRg() {
        return txtRg;
    }

    public void setTxtRg(TextField txtRg) {
        this.txtRg = txtRg;
    }

    public TextField getTxtCpf() {
        return txtCpf;
    }

    public void setTxtCpf(TextField txtCpf) {
        this.txtCpf = txtCpf;
    }

    public TextField getTxtEndereco() {
        return txtEndereco;
    }

    public void setTxtEndereco(TextField txtEndereco) {
        this.txtEndereco = txtEndereco;
    }

    public TextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(TextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public TextField getTxtTelefone() {
        return txtTelefone;
    }

    public void setTxtTelefone(TextField txtTelefone) {
        this.txtTelefone = txtTelefone;
    }

    public TextField getTxtData_nascimento() {
        return txtData_nascimento;
    }

    public void setTxtData_nascimento(TextField txtData_nascimento) {
        this.txtData_nascimento = txtData_nascimento;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public void fecharModal() {
        Stage stage = (Stage) btCadastrar.getScene().getWindow();
        stage.close();
    }


    private void limparDadosFormulario() {
        txtNome.setText("");
        txtRg.setText("");
        txtCpf.setText("");
        txtEndereco.setText("");
        txtEmail.setText("");
        txtTelefone.setText("");
        txtData_nascimento.setText("");
    }
}
