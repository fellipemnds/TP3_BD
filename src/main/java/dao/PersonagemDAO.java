package dao;

import ferramentas.OracleConnector;

import java.sql.*;

import modelo.Personagem;

import java.util.List;
import java.util.ArrayList;

public class PersonagemDAO {

    //Atributos
    private final Connection connection;
    private PreparedStatement dados;
    private ResultSet dadosEncontrados;
    private String sql;

    public PersonagemDAO(){
        connection = OracleConnector.getConexao();
        dados = null;
        dadosEncontrados = null;
        sql = null;
    }

    public void adicionarPersonagem(Personagem personagem) throws SQLException {
        sql = "INSERT INTO Personagem(id, nome, data_nascimento, cidade_natal, id_genitor_1, id_genitor_2, nome_casa)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";

        dados = connection.prepareStatement(sql);

        dados.setInt(1, personagem.getId());
        dados.setString(2, personagem.getNome());
        dados.setTimestamp(3, personagem.getDataNascimento());
        dados.setString(4, personagem.getCidadeNatal());
        dados.setInt(5, personagem.getIdGenitor1());
        dados.setInt(6, personagem.getIdGenitor2());
        dados.setString(7, personagem.getNomeCasa());

        dados.execute();
        dados.close();
    }

    public void buscarPersonagemId(Personagem personagem) throws SQLException {
        sql = "SELECT * FROM Personagem WHERE id = ?";

        dados = connection.prepareStatement(sql);
        dados.setInt(1, personagem.getId());
        dados.execute();

        dadosEncontrados = dados.executeQuery();

        while(dadosEncontrados.next()) {
            personagem.setNome(dadosEncontrados.getString("nome"));
            personagem.setDataNascimento(dadosEncontrados.getTimestamp("data_nascimento"));
            personagem.setCidadeNatal(dadosEncontrados.getString("cidade_natal"));
            personagem.setIdGenitor1(dadosEncontrados.getInt("id_genitor_1"));
            personagem.setIdGenitor2(dadosEncontrados.getInt("id_genitor_2"));
            personagem.setNomeCasa(dadosEncontrados.getString("nome_casa"));
        }

        dados.close();
        dadosEncontrados.close();
    }

    public void excluirPersonagem(Integer id) throws SQLException {
        sql = "DELETE FROM Personagem WHERE id = ?";

        dados = connection.prepareStatement(sql);

        dados.setInt(1, id);
        dados.execute();

        dadosEncontrados = dados.executeQuery();

        dados.close();
        dadosEncontrados.close();
    }

    public void editarCliente(Personagem personagem) throws SQLException {
        sql = "UPDATE Personagem set id = ?, nome = ?, data_nascimento = ?, cidade_natal = ?, id_genitor_1 = ?, " +
                "id_genitor_2 = ?, nome_casa = ?";

        dados = connection.prepareStatement(sql);

        dados.setInt(1, personagem.getId());
        dados.setString(2, personagem.getNome());
        dados.setTimestamp(3, personagem.getDataNascimento());
        dados.setInt(4, personagem.getIdGenitor1());
        dados.setInt(5, personagem.getIdGenitor2());
        dados.setString(6, personagem.getNomeCasa());

        dados.execute();
        dados.close();
    }

    public List<Personagem> listarPersonagem() throws SQLException {
        List<Personagem> personagens = new ArrayList<>();
        Integer contador = 0;

        sql = "SELECT * FROM Personagem";

        dados = connection.prepareStatement(sql);
        dados.execute();

        dadosEncontrados = dados.executeQuery();

        while (dadosEncontrados.next()) {
            Personagem personagem = new Personagem();

            personagem.setId(dadosEncontrados.getInt("id"));
            personagem.setNome(dadosEncontrados.getString("nome"));
            personagem.setDataNascimento(dadosEncontrados.getTimestamp("data_nascimento"));
            personagem.setCidadeNatal(dadosEncontrados.getString("cidade_natal"));
            personagem.setIdGenitor1(dadosEncontrados.getInt("id_genitor_1"));
            personagem.setIdGenitor2(dadosEncontrados.getInt("id_genitor_2"));
            personagem.setNomeCasa(dadosEncontrados.getString("nome_casa"));

            personagens.add(personagem);
        }

        dados.close();
        dadosEncontrados.close();

        return personagens;
    }
}