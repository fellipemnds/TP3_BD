package modelo;

import java.sql.Timestamp;

public class Cidade {

    // Atributos
    private String nome;
    private Timestamp dataConstrucao;
    private String historia;
    private String nomeReino;

    // <editor-fold defaultstate="collapsed" desc="Getters e Setters">
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Timestamp getDataConstrucao() {
        return dataConstrucao;
    }

    public void setDataConstrucao(Timestamp dataConstrucao) {
        this.dataConstrucao = dataConstrucao;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getNomeReino() {
        return nomeReino;
    }

    public void setNomeReino(String nomeReino) {
        this.nomeReino = nomeReino;
    }

    // </editor-fold>
}
