package br.ifsp.pdm;

public class Treino {

    private String nome;
    private String desc;
    private String sexo;
    private String dia;
    private String peso;
    private String idade;


    public Treino(String nome, String desc, String sexo, String dia, String peso, String idade) {
        this.nome = nome;
        this.desc = desc;
        this.sexo = sexo;
        this.dia = dia;
        this.peso = peso;
        this.idade = idade;
    }

    public Treino() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }
}