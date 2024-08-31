package com.rpgManager.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rpgManager.model.Personagem;
import com.rpgManager.model.Relatorio;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Paths;

public class HU05GerarRelatorioTest {
    @Mock
    private PersonagemController personagemController;

    @Mock
    private Relatorio relatorio;

    @InjectMocks
    private RelatorioController controladorRelatorio;

    @BeforeEach
    public void setUp() {
        String caminho = Paths.get("").toAbsolutePath().toString();
        // Dotenv dotenv = Dotenv.configure()
        // .directory(caminho += "\\src")
        // .filename(".env")
        // .load();
        // MockitoAnnotations.openMocks(this);
        personagemController = new PersonagemController();
        relatorio = new Relatorio();
        controladorRelatorio = new RelatorioController(this.personagemController,
                relatorio);
    }

    @Test
    public void testarGerarRelatorioUnicoPersonagem() {
        int id = 1;
        Personagem personagem = new Personagem("Naruto", "Humano", "Ninja", "M", 10,
                new ArrayList<>(List.of("Rasengan")));
        when(personagemController.buscarPersonagem(id)).thenReturn(personagem);

        // Exercício
        controladorRelatorio.gerarRelatorio();

        // Verificação
        verify(personagemController, times(1)).buscarPersonagem(id);
        verify(relatorio, times(1)).gerarRelatorio(personagem);
    }

    @Test
    public void testarGerarRelatorioVariosPersonagens() {
        // Configuração
        List<Personagem> personagens = new ArrayList<>();
        personagens.add(new Personagem("Naruto", "Humano", "Ninja", "M", 10, new ArrayList<>(List.of("Rasengan"))));
        personagens.add(new Personagem("Sasuke", "Humano", "Ninja", "M", 12, new ArrayList<>(List.of("Chidori"))));
        when(personagemController.listarPersonagens()).thenReturn(personagens);

        // Exercício
        controladorRelatorio.gerarRelatorio(); // 0 indica que queremos gerar relatório para todos os personagens

        // Verificação
        verify(personagemController, times(1)).listarPersonagens();
        verify(relatorio, times(1)).gerarRelatorio(personagens);
    }
}
