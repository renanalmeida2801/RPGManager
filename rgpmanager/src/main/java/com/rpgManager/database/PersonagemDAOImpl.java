package com.rpgManager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import com.rpgManager.interfaces.IPersonagemDAO;
import com.rpgManager.model.Personagem;
import io.github.cdimascio.dotenv.Dotenv;
import java.nio.file.Paths;

public class PersonagemDAOImpl implements IPersonagemDAO {

    String caminho = Paths.get("").toAbsolutePath().toString();
    Dotenv dotenv = Dotenv.configure()
            .directory(caminho += "\\rgpmanager\\src")
            .filename(".env")
            .load();
    // configura o dotenv

    public void funciona() {
        String myVar = dotenv.get("MY_ENV_VAR");
        if (myVar != null) {
            System.out.println("Arquivo .env carregado com sucesso!");
        } else {
            System.out.println("Erro ao carregar o arquivo .env.");
        }
    }

    private final String url = dotenv.get("URL");
    private final String user = dotenv.get("USER");
    private final String password = dotenv.get("PASSWORD");

    // Método para obter a conexão com o banco de dados
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Método para converter a lista de habilidades em uma string
    private String joinAllHabilities(List<String> habilidades) {
        StringJoiner join = new StringJoiner(",");

        for (String habilidade : habilidades) {
            join.add(habilidade);
        }
        return join.toString();
    }

    @Override
    public void salvarPersonagem(Personagem persona) {
        String inserted = "INSERT INTO Personagem(nome, raca, classe, sexo, nivel, habilidades) VALUES (?,?,?,?,?,?)";

        try (Connection conn = getConnection();
                PreparedStatement statement = conn.prepareStatement(inserted)) {

            statement.setString(1, persona.getNome());
            statement.setString(2, persona.getRaca());
            statement.setString(3, persona.getClasse());
            statement.setString(4, String.valueOf(persona.getSexo()));
            statement.setInt(5, persona.getNivel());

            String habilidadesString = joinAllHabilities(persona.getHabilidades());
            statement.setString(6, habilidadesString);

            statement.executeUpdate();
            System.out.println("personagem: " + persona.getNome() + " foi adicionado");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Personagem buscarPersonagem(int id) {
        Personagem procurado = null;
        String query = "SELECT * FROM Personagem WHERE id = ?";

        try (Connection conexao = getConnection();
                PreparedStatement statement = conexao.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet response = statement.executeQuery();

            if (response.next()) {
                String nome = response.getString("nome");
                String raca = response.getString("raca");
                String classe = response.getString("classe");
                String sexo = response.getString("sexo");
                int nivel = response.getInt("nivel");
                String habilidadesString = response.getString("habilidades");
                List<String> habilidades = List.of(habilidadesString.split(","));
                procurado = new Personagem(nome, raca, classe, sexo, nivel, habilidades);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(procurado.getNome() + " " + procurado.getRaca() + " " + procurado.getClasse() + " "
                + procurado.getSexo() + " " + procurado.getNivel() + " " + procurado.getHabilidades());
        return procurado;
    }

    @Override
    public void atualizarPersonagem(int id, Personagem falso) {

        if (!falso.getNome().equals("")) {
            String query = "UPDATE Personagem SET nome = ? WHERE id = ?";
            try (Connection conexao = getConnection();
                    PreparedStatement statement = conexao.prepareStatement(query)) {
                statement.setString(1, falso.getNome());
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (!falso.getRaca().equals("")) {
            String query = "UPDATE Personagem SET raca = ? WHERE id = ?";
            try (Connection conexao = getConnection();
                    PreparedStatement statement = conexao.prepareStatement(query)) {
                statement.setString(1, falso.getRaca());
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (!falso.getClasse().equals("")) {
            String query = "UPDATE Personagem SET classe = ? WHERE id = ?";
            try (Connection conexao = getConnection();
                    PreparedStatement statement = conexao.prepareStatement(query)) {
                statement.setString(1, falso.getClasse());
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (!falso.getSexo().equals("")) {
            String query = "UPDATE Personagem SET sexo = ? WHERE id = ?";
            try (Connection conexao = getConnection();
                    PreparedStatement statement = conexao.prepareStatement(query)) {
                statement.setString(1, falso.getSexo());
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (falso.getNivel() >= 0) {
            String query = "UPDATE Personagem SET nivel = ? WHERE id = ?";
            try (Connection conexao = getConnection();
                    PreparedStatement statement = conexao.prepareStatement(query)) {
                statement.setInt(1, falso.getNivel());
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (falso.getHabilidades() != null || !falso.getHabilidades().isEmpty()) {
            String query = "UPDATE Personagem SET habilidades = ? WHERE id = ?";
            try (Connection conexao = getConnection();
                    PreparedStatement statement = conexao.prepareStatement(query)) {
                String habilidadeString = joinAllHabilities(falso.getHabilidades());
                statement.setString(1, habilidadeString);
                statement.setInt(2, id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deletarPersonagem(int id) {
        String deleteThis = "DELETE FROM Personagem WHERE id = ?";
        String nome = "";
        String query = "SELECT nome FROM Personagem WHERE id = ?";
        try (Connection conexao = getConnection();
                PreparedStatement statement = conexao.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                nome = result.getString("nome");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conexao = getConnection();
                PreparedStatement statement = conexao.prepareStatement(deleteThis)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("O personagem " + nome + " foi deletado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void listarTodosPersonagens() {
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM Personagem;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("Raça: " + rs.getString("raca"));
                System.out.println("Classe: " + rs.getString("classe"));
                System.out.println("Sexo: " + rs.getString("sexo"));
                System.out.println("Nível: " + rs.getInt("nivel"));
                System.out.println("Habilidades: " + rs.getString("habilidades"));
                System.out.println("----------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
