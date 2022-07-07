package dao;

import ferramentas.OracleConnector;

import java.sql.*;

import modelo.NaoHumano;

import java.util.List;
import java.util.ArrayList;

public class NaoHumanoDAO {

    private final Connection connection;
    private PreparedStatement dados;
    private ResultSet dadosEncontrados;
    private String sql;

    public NaoHumanoDAO() {
        connection = OracleConnector.getConexao();
        dados = null;
        dadosEncontrados = null;
        sql = null;
    }

    public void AdicionarNaoHumano(NaoHumano naoHumano) throws SQLException {
        sql = "INSERT INTO Nao_Humano(id_personagem, especie) VALUES (?, ?)";

        dados = connection.prepareStatement(sql);

        dados.setInt(1, naoHumano.getIdPersonagem());
        dados.setString(2, naoHumano.getEspecie());

        dados.execute();
        dados.close();
    }

    public void BuscarNaoHumanoID(NaoHumano naoHumano) throws SQLException {
        sql = "SELECT * FROM Nao_Humano WHERE id_personagem = ?";

        dados = connection.prepareStatement(sql);
        dados.setInt(1, naoHumano.getIdPersonagem());
        dados.execute();

        dadosEncontrados = dados.executeQuery();
        while (dadosEncontrados.next()) {
            naoHumano.setEspecie(dadosEncontrados.getString("especie"));
        }

        dados.close();
        dadosEncontrados.close();
    }

    public void excluirNaoHumano(Integer id_personagem) throws SQLException {
        sql = "DELETE FROM Nao_Humano WHERE id_personagem = ?";

        dados = connection.prepareStatement(sql);

        dados.setInt(1, id_personagem);

        dados.execute();

        dadosEncontrados = dados.executeQuery();

        dados.close();
        dadosEncontrados.close();
    }

    public void editarNaoHumano(NaoHumano naoHumano) throws SQLException {
        sql = "UPDATE Nao_Humano SET especie = ? WHERE id_personagem = ?";

        dados = connection.prepareStatement(sql);

        dados.setString(1, naoHumano.getEspecie());
        dados.setInt(2, naoHumano.getIdPersonagem());

        dados.execute();
        dados.close();
    }

    public List<NaoHumano> listarNaoHumano() throws SQLException {
        List<NaoHumano> naoHumanos = new ArrayList<>();
        Integer contador = 0;

        sql = "SELECT * FROM Nao_Humano";

        dados = connection.prepareStatement(sql);
        dados.execute();

        dadosEncontrados = dados.executeQuery();

        while(dadosEncontrados.next()) {
            NaoHumano naoHumano = new NaoHumano();

            naoHumano.setIdPersonagem(dadosEncontrados.getInt("id_personagem"));
            naoHumano.setEspecie(dadosEncontrados.getString("especie"));

            naoHumanos.add(naoHumano);
        }

        dados.close();
        dadosEncontrados.close();

        return naoHumanos;
    }
}
