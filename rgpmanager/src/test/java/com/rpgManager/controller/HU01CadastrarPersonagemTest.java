package com.rpgManager.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rpgManager.database.PersonagemDAOImpl;
import com.rpgManager.model.Personagem;

public class HU01CadastrarPersonagemTest {
    @InjectMocks
    private PersonagemController controlador;

    @Mock
    private PersonagemDAOImpl personagemDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testaCadastrarPersonagemComDadosCorretos() {
        // configuração
        Personagem personagem = new Personagem("Josuke", "Humano", "Lutador", "M", 12,
                Collections.singletonList("Ultra Punch"));

        doNothing().when(personagemDAO).salvarPersonagem(any(Personagem.class));

        // execução
        controlador.criarPersonagem(personagem.getNome(), personagem.getRaca(), personagem.getClasse(),
                personagem.getSexo(), personagem.getNivel(), personagem.getHabilidades());

        // verificação
        verify(personagemDAO, times(1)).salvarPersonagem(any(Personagem.class));
    }

    @Test
    public void testaCadastrarPersonagemComDadosInvalidos() {
        // configuração
        Personagem personagemInvalido = new Personagem("Ragnar", "Gigante", "Slime", "M", -1,
                Collections.singletonList("Aumentar/Reduzir tamanho"));

        // execução
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controlador.criarPersonagem(personagemInvalido.getNome(), personagemInvalido.getRaca(),
                    personagemInvalido.getClasse(), personagemInvalido.getSexo(), personagemInvalido.getNivel(),
                    personagemInvalido.getHabilidades());
        });

        // verificação
        String mensagemEsperada = "Nível não pode ser negativo";
        String mensagemAtual = exception.getMessage();
        assertTrue(mensagemAtual.contains(mensagemEsperada));

        verify(personagemDAO, never()).salvarPersonagem(any(Personagem.class));

    }

    @Test
    public void testaCadastrarPersonagemComDadosEmBranco() {
        // configuração
        Personagem personagemEmBranco = new Personagem("", "", "", "", 0, Collections.emptyList());

        // execução
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controlador.criarPersonagem(personagemEmBranco.getNome(), personagemEmBranco.getRaca(),
                    personagemEmBranco.getClasse(), personagemEmBranco.getSexo(), personagemEmBranco.getNivel(),
                    personagemEmBranco.getHabilidades());
        });

        // verificação
        String mensagemEsperada = "Os campos não podem estar em branco";
        String mensagemAtual = exception.getMessage();
        assertTrue(mensagemAtual.contains(mensagemEsperada));

        verify(personagemDAO, never()).salvarPersonagem(any(Personagem.class));
    }
}
