package com.rpgManager.database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rpgManager.model.Personagem;

import io.github.cdimascio.dotenv.Dotenv;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PersonagemDaoImplTest {

    private PersonagemDAOImpl personagemDao;

    @Mock
    private Connection conexao;

    @Mock
    private PreparedStatement preparada;

    @Mock
    private ResultSet resultado;

    @BeforeAll
    public static void setUp() {
        System.setProperty("DB_HOST", "jdbc:postgresql://localhost:5432/RPGSheet");
        System.setProperty("DB_USER", "postgres");
        System.setProperty("DB_PASS", "1234");
        String caminho = Paths.get("").toAbsolutePath().toString();
        Dotenv dotenv = Dotenv.configure()
                .directory(caminho += "\\src")
                .filename(".env")
                .load();

    }

    // @Test
    // void testarSalvarPersonagem() throws SQLException {
    // // configuração
    // Personagem personagem = new Personagem();
    // personagem.setNome("Josuke");

    // // exercitando
    // personagemDao.salvarPersonagem(personagem);

    // // verificando
    // verify(preparada, times(1)).executeUpdate();
    // }

    @Test
    void testBuscarPersonagem() throws SQLException {
        // Arrange
        when(preparada.executeQuery()).thenReturn(resultado);
        when(resultado.next()).thenReturn(true);
        when(resultado.getInt("id")).thenReturn(1);
        when(resultado.getString("nome")).thenReturn("Aragorn");

        // Act
        Personagem personagem = personagemDao.buscarPersonagem(1);

        // Assert
        assertNotNull(personagem);
        assertEquals("Aragorn", personagem.getNome());
        verify(preparada, times(1)).executeQuery();
    }

    @Test
    void testDotenvLoading() {
        Dotenv dotenv = Dotenv.configure()
                .directory(
                        "C:\\Users\\felip\\OneDrive\\Documentos\\5 semestre\\Verificação e Validação\\RPGManager\\rgpmanager\\src\\.env")
                .load();

        String dbUser = dotenv.get("USER");
        assertNotNull(dbUser, "DB_USER should not be null");
    }

}
