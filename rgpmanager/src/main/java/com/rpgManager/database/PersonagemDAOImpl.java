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

    private final String url = dotenv.get("URL");
    private final String user = dotenv.get("USER");
    private final String password = dotenv.get("PASSWORD");

    Personagem personagem;

    // Método para obter a conexão com o banco de dados
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public boolean encontrarId(int id) {
        String query = "SELECT * FROM Personagem WHERE id = ?";
        try (Connection conexao = getConnection();
                PreparedStatement statement = conexao.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
            // TODO: handle exception
        }
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

        if (falso.getHabilidades() == null || !falso.getHabilidades().isEmpty()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Personagem> listarTodosPersonagens() {
        List<Personagem> personagens = new ArrayList<>();
        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM Personagem  order by id;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                personagem = new Personagem();
                personagem.setId(rs.getInt("id"));
                personagem.setNome(rs.getString("nome"));
                personagem.setRaca(rs.getString("raca"));
                personagem.setClasse(rs.getString("classe"));
                personagem.setSexo(rs.getString("sexo"));
                personagem.setNivel(rs.getInt("nivel"));
                String habildiadesString = rs.getString("habilidades");
                List<String> habilidades = Arrays.asList(habildiadesString.split(","));
                personagem.setHabilidade(habilidades);

                personagens.add(personagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personagens;
    }
}
