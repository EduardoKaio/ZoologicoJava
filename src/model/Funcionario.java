package model;

public class Funcionario {
    private Integer id_funcionario;
    private String nome;
    private String rg;
    private String cpf;
    private String telefone;
    private String endereco;
    private String data_nascimento;
    private String email;
    
    public Funcionario(){
    }
    
    public Funcionario(String nome, String rg, String cpf, String telefone, String endereco, String data_nascimento, String email){
    this.nome = nome;
    this.rg = rg;
    this.cpf = cpf;
    this.telefone = telefone;
    this.endereco = endereco;
    this.data_nascimento = data_nascimento;
    this.email = email;
    }

    public Integer getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Integer id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
