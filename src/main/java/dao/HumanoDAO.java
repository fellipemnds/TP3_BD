package dao;

import ferramentas.OracleConnector;

import java.sql.*;

import modelo.Humano;

import java.util.List;
import java.util.ArrayList;

public class HumanoDAO {

    private final Connection connection;
    private PreparedStatement dados;
    private ResultSet dadosEncontrados;
    private String sql;

    public HumanoDAO() {
        connection = OracleConnector.getConexao();
        dados = null;
        dadosEncontrados = null;
        sql = null;
    }

    public void AdicionarHumano(Humano humano) throws SQLException {
        sql = "INSERT INTO Humano(id_personagem, nome_raça, sobrenome, genero, cabelo, olhos) VALUES (?, ?, ?, ?, ?, ?)";

        dados = connection.prepareStatement(sql);

        dados.setInt(1, humano.getIdPersonagem());
        dados.setString(2, humano.getNomeRaca());
        dados.setString(3, humano.getSobrenome());
        dados.setString(4, humano.getGenero());
        dados.setString(5, humano.getCabelo());
        dados.setString(6, humano.getOlhos());

        dados.execute();
        dados.close();
    }

    public void BuscarHumanoID(Humano humano) throws SQLException {
        sql = "SELECT * FROM Humano WHERE id_personagem = ?";

        dados = connection.prepareStatement(sql);
        dados.setInt(1, humano.getIdPersonagem());
        dados.execute();

        dadosEncontrados = dados.executeQuery();
        while (dadosEncontrados.next()) {
            humano.setNomeRaca(dadosEncontrados.getString("nome_raça"));
            humano.setSobrenome(dadosEncontrados.getString("sobrenome"));
            humano.setGenero(dadosEncontrados.getString("genero"));
            humano.setCabelo(dadosEncontrados.getString("cabelo"));
            humano.setOlhos(dadosEncontrados.getString("olhos"));
        }

        dados.close();
        dadosEncontrados.close();
    }

    public void excluirHumano(Integer id_personagem) throws SQLException {
        sql = "DELETE FROM Humano WHERE id_personagem = ?";

        dados = connection.prepareStatement(sql);

        dados.setInt(1, id_personagem);

        dados.execute();

        dadosEncontrados = dados.executeQuery();

        dados.close();
        dadosEncontrados.close();
    }

    public void editarHumano(Humano humano) throws SQLException {
        sql = "UPDATE Humano SET nome_raça = ?, sobrenome = ?, genero = ?, cabelo = ?, olhos = ? WHERE id_personagem = ?";

        dados = connection.prepareStatement(sql);

        dados.setString(1, humano.getNomeRaca());
        dados.setString(2, humano.getSobrenome());
        dados.setString(3, humano.getGenero());
        dados.setString(4, humano.getCabelo());
        dados.setString(5, humano.getOlhos());
        dados.setInt(6, humano.getIdPersonagem());

        dados.execute();
        dados.close();
    }

    public List<Humano> listarHumano() throws SQLException {
        List<Humano> humanos = new ArrayList<>();
        Integer contador = 0;

        sql = "SELECT * FROM Humano";

        dados = connection.prepareStatement(sql);
        dados.execute();

        dadosEncontrados = dados.executeQuery();

        while(dadosEncontrados.next()) {
            Humano humano = new Humano();

            humano.setIdPersonagem(dadosEncontrados.getInt("id_personagem"));
            humano.setNomeRaca(dadosEncontrados.getString("nome_raça"));
            humano.setSobrenome(dadosEncontrados.getString("sobrenome"));
            humano.setGenero(dadosEncontrados.getString("genero"));
            humano.setCabelo(dadosEncontrados.getString("cabelo"));
            humano.setOlhos(dadosEncontrados.getString("olhos"));

            humanos.add(humano);
        }

        dados.close();
        dadosEncontrados.close();

        return humanos;
    }
}
