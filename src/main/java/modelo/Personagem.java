package modelo;

import java.sql.Timestamp;

public class Personagem {

    // Atributos
    private int id;
    private String nome;
    private Timestamp dataNascimento;
    private String cidadeNatal;
    private int idGenitor1;
    private int idGenitor2;
    private String nomeCasa;

    // <editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Timestamp getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Timestamp dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidadeNatal() {
        return cidadeNatal;
    }

    public void setCidadeNatal(String cidadeNatal) {
        this.cidadeNatal = cidadeNatal;
    }

    public int getIdGenitor1() {
        return idGenitor1;
    }

    public void setIdGenitor1(int idGenitor1) {
        this.idGenitor1 = idGenitor1;
    }

    public int getIdGenitor2() {
        return idGenitor2;
    }

    public void setIdGenitor2(int idGenitor2) {
        this.idGenitor2 = idGenitor2;
    }

    public String getNomeCasa() {
        return nomeCasa;
    }

    public void setNomeCasa(String nomeCasa) {
        this.nomeCasa = nomeCasa;
    }
    // </editor-fold>
}
