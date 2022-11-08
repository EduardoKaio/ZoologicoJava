package model;

public class Animal {
    private Integer id_animal;
    private String nome;
    private String classificacao;
    private String caracteristica;
    private String localizacao;
    private String peso;
    
    public Animal(){
        
    }
    
    public Animal(String nome, String classificacao, String caracteristica, String localizacao, String peso){
        this.nome = nome;
        this.classificacao = classificacao;
        this.caracteristica = caracteristica;
        this.localizacao = localizacao;
        this.peso = peso;         
                
    }
    
    public Integer getId_animal() {
        return id_animal;
    }

    public void setId_animal(Integer id_animal) {
        this.id_animal = id_animal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
    
    
}
