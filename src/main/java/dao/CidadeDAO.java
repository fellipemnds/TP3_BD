package dao;

import ferramentas.OracleConnector;

import java.sql.*;

import modelo.Cidade;

import java.util.List;
import java.util.ArrayList;

public class CidadeDAO {

    private final Connection connection;
    private PreparedStatement dados;
    private ResultSet dadosEncontrados;
    private String sql;

    public CidadeDAO() {
        connection = OracleConnector.getConexao();
        dados = null;
        dadosEncontrados = null;
        sql = null;
    }

    public List<Cidade> listarCidades() throws SQLException {
        List<Cidade> cidades = new ArrayList<>();
        Integer contador = 0;

        sql = "SELECT * FROM Cidade";

        dados = connection.prepareStatement(sql);
        dados.execute();

        dadosEncontrados = dados.executeQuery();

        while(dadosEncontrados.next()) {
            Cidade cidade = new Cidade();

            cidade.setNome(dadosEncontrados.getString("nome"));
            cidade.setDataConstrucao(dadosEncontrados.getTimestamp("data_construçao"));
            cidade.setHistoria(dadosEncontrados.getString("historia"));
            cidade.setNomeReino(dadosEncontrados.getString("nome_reino"));

            cidades.add(cidade);
        }

        dados.close();
        dadosEncontrados.close();

        return cidades;
    }
}
