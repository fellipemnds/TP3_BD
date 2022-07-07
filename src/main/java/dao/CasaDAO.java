package dao;

import ferramentas.OracleConnector;

import java.sql.*;

import modelo.Casa;

import java.util.List;
import java.util.ArrayList;

public class CasaDAO {

    private final Connection connection;
    private PreparedStatement dados;
    private ResultSet dadosEncontrados;
    private String sql;

    public CasaDAO() {
        connection = OracleConnector.getConexao();
        dados = null;
        dadosEncontrados = null;
        sql = null;
    }

    public List<Casa> listarCasa() throws SQLException {
        List<Casa> casas = new ArrayList<>();
        Integer contador = 0;

        sql = "SELECT * FROM Casa";

        dados = connection.prepareStatement(sql);
        dados.execute();

        dadosEncontrados = dados.executeQuery();

        while(dadosEncontrados.next()) {
            Casa casa = new Casa();

            casa.setNome(dadosEncontrados.getString("nome"));
            casa.setBandeira(dadosEncontrados.getString("bandeira"));
            casa.setLema(dadosEncontrados.getString("lema"));
            casa.setNomeCidade(dadosEncontrados.getString("nome_cidade"));

            casas.add(casa);
        }

        dados.close();
        dadosEncontrados.close();

        return casas;
    }
}
