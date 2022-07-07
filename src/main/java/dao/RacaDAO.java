package dao;

import ferramentas.OracleConnector;

import java.sql.*;

import modelo.Raca;

import java.util.List;
import java.util.ArrayList;

public class RacaDAO {

    private final Connection connection;
    private PreparedStatement dados;
    private ResultSet dadosEncontrados;
    private String sql;

    public RacaDAO() {
        connection = OracleConnector.getConexao();
        dados = null;
        dadosEncontrados = null;
        sql = null;
    }

    public List<Raca> listarRaca() throws SQLException {
        List<Raca> racas = new ArrayList<>();
        Integer contador = 0;

        sql = "SELECT * FROM Raça";

        dados = connection.prepareStatement(sql);
        dados.execute();

        dadosEncontrados = dados.executeQuery();

        while(dadosEncontrados.next()) {
            Raca raca = new Raca();

            raca.setNome(dadosEncontrados.getString("nome"));
            raca.setCaracteristicas(dadosEncontrados.getString("caracteristica"));

            racas.add(raca);
        }

        dados.close();
        dadosEncontrados.close();

        return racas;
    }
}
