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
import com.rpgManager.interfaces.IBancoDeDados;
import com.rpgManager.model.Personagem;

public class BancoDeDados implements IBancoDeDados {

    public static final String url = "jdbc:postgresql://localhost:5434/RPGSheet";
    public static final String user = "postgres";
    public static final String password = "9298";

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
                char sexo = response.getString("sexo").charAt(0);
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
    public void atualizarPersonagem(int id) {
        Scanner sc = new Scanner(System.in);
        boolean changing = true;
        while (changing) {
            System.out.println("deseja mudar qual informação do seu personagem?");
            String dataToBeChanged = sc.nextLine();
            switch (dataToBeChanged) {
                case "nome":
                    System.out.println("escreva o novo nome do personagem");
                    String info = sc.nextLine();
                    String query = "UPDATE Personagem SET nome = ? WHERE id = ?";
                    try (Connection conexao = getConnection();
                            PreparedStatement statement = conexao.prepareStatement(query)) {
                        statement.setString(1, info);
                        statement.setInt(2, id);
                        statement.executeUpdate();
                        System.out.println("atualização do nome feito com sucesso" + info);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case "raca":
                    System.out.println("escreva a nova raca do personagem");
                    info = sc.nextLine();
                    query = "UPDATE Personagem SET raca = ? WHERE id = ?";
                    try (Connection conexao = getConnection();
                            PreparedStatement statement = conexao.prepareStatement(query)) {
                        statement.setString(1, info);
                        statement.setInt(2, id);
                        statement.executeUpdate();
                        System.out.println("atualização da raca feita com sucesso");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case "classe":
                    System.out.println("escreva a nova classe do personagem");
                    info = sc.nextLine();
                    query = "UPDATE Personagem SET classe = ? WHERE id = ?";
                    try (Connection conexao = getConnection();
                            PreparedStatement statement = conexao.prepareStatement(query)) {
                        statement.setString(1, info);
                        statement.setInt(2, id);
                        statement.executeUpdate();
                        System.out.println("atualização da classe feita com sucesso");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case "sexo":
                    System.out.println("escreva o novo sexo do personagem");
                    info = sc.nextLine();
                    char sexInfo = info.charAt(0);
                    query = "UPDATE Personagem SET sexo = ? WHERE id = ?";
                    try (Connection conexao = getConnection();
                            PreparedStatement statement = conexao.prepareStatement(query)) {
                        statement.setString(1, String.valueOf(sexInfo));
                        statement.setInt(2, id);
                        statement.executeUpdate();
                        System.out.println("atualização do sexo do personagem feito com sucesso");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;

                case "nivel":
                    System.out.println("escreva o novo nivel do personagem");
                    info = sc.nextLine();
                    query = "UPDATE Personagem SET nivel = ? WHERE id = ?";
                    try (Connection conexao = getConnection();
                            PreparedStatement statement = conexao.prepareStatement(query)) {
                        statement.setInt(1, Integer.parseInt(info));
                        statement.setInt(2, id);
                        statement.executeUpdate();
                        System.out.println("atualização do nivel feita com sucesso");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "habilidades":
                    System.out.println("escreva as novas habilidades do personagem");
                    info = sc.nextLine();
                    String selectHabilidades = "SELECT habilidades FROM Personagem WHERE id = ?";
                    query = "UPDATE Personagem SET habilidades = ? WHERE id = ?";
                    String habilidadeString = "";
                    List<String> habilidadesList = null;
                    try (Connection conexao = getConnection();
                            PreparedStatement statement = conexao.prepareStatement(selectHabilidades)) {
                        statement.setInt(1, id);
                        ResultSet result = statement.executeQuery();
                        if (result.next()) {
                            habilidadeString = result.getString("habilidades");
                            habilidadesList = new ArrayList<>(Arrays.asList(habilidadeString.split(",")));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    try (Connection conexao = getConnection();
                            PreparedStatement statement = conexao.prepareStatement(query)) {
                        habilidadesList.add(info);
                        habilidadeString = joinAllHabilities(habilidadesList);
                        statement.setString(1, habilidadeString);
                        statement.setInt(2, id);
                        statement.executeUpdate();
                        System.out.println("atualização da classe feita com sucesso");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case "parar":
                    changing = false;
                    System.out.println("atualizações finalizadas");
                    break;
            }
        }
        sc.close();
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
