package ferramentas;

import java.sql.*;
import javax.swing.JOptionPane;
import modelo.Personagem;

/**
 *
 * @author Lucio Dutra
 */
public class OracleConnector {

    private static Connection conexao;
    private static PreparedStatement dados;
    private static ResultSet dadosEncontrados;
    private static String sql;
    private static String usuario = "m20320";
    private static String senha = "yyxyODD13";
    public static final String ORACLEDRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String ORACLEURL = "jdbc:oracle:thin:@200.131.242.43:1521/ifnmgpdb";

    public static void main(String[] args) throws SQLException {
        Connection conexao = getConexao();
        sql = "select * from Personagem";
        dados = conexao.prepareStatement(sql);
        dados.execute();
        dadosEncontrados = dados.executeQuery();

        Personagem p = new Personagem();
        while (dadosEncontrados.next()) {
            p.setId(dadosEncontrados.getInt("id"));
            p.setNome(dadosEncontrados.getString("nome"));
        }

        System.out.println(p.getId());
        System.out.println(p.getNome());

        dados.close();
        dadosEncontrados.close();
    }

    public OracleConnector() {
        super();
    }

    public static String getUrl() {
        return ORACLEURL;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String attUsuario) {
        usuario = attUsuario;
    }

    public static String getSenha() {
        return senha;
    }

    public static void setSenha(String attSenha) {
        senha = attSenha;
    }

    public static void ajustaParametros(String usuario, String senha) {
        OracleConnector.setUsuario(usuario);
        OracleConnector.setSenha(senha);

    }


    public static Connection getConexao() {
        if (conexao == null) {

            try {
                // a partir do Java 6 não é preciso carregar qual drive deve ser usado no DriverManager
                Class.forName(ORACLEDRIVER);
                System.out.println("Configuracao do Driver Oracle bem-sucedida");
                conexao = DriverManager.getConnection(getUrl(), getUsuario(), getSenha());
                System.out.println("Conexao bem-sucedida");

            } catch (ClassNotFoundException cnfex) {
                System.out.println("Class not found");
            } catch (SQLException sqlex) {
                //System.out.println("SQL Exception " + sqlex);
                JOptionPane.showMessageDialog(null, "SQL Exception " + sqlex, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        return conexao;
    }

    protected static void fechaConexao() {
        usuario = null;
        senha = null;

        try {
            if (conexao != null) {
                conexao.close();
                conexao = null;
            }
        } catch (Exception ex) {
            System.out.println("Deu Pau em ConectaBanco.fechaConexao().");
        }
    }

    @Override
    protected void finalize() {
        System.out.println("Se o Garbage Collector for usado");

        try {
            super.finalize();
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        fechaConexao();
    }


}
