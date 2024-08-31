package com.rpgManager.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rpgManager.database.PersonagemDAOImpl;
import com.rpgManager.model.Personagem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

public class HU04BuscarPersonagemTest {
    @Mock
    private PersonagemDAOImpl personagemDAOImpl;

    @InjectMocks
    private PersonagemController controlador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testarBuscarPorIdEfetuadaComSucesso() {
        // configuração
        int id = 1;
        Personagem personagemEsperado = new Personagem("Uraraka", "Humano", "Super Herói", "F", 10,
                new ArrayList<>(List.of("Levitar")));

        personagemEsperado.setId(id);
        when(personagemDAOImpl.buscarPersonagem(id)).thenReturn(personagemEsperado);

        // exercitando
        Personagem personagemRetornado = controlador.buscarPersonagem(id);

        // verificando
        assertEquals(personagemEsperado, personagemRetornado);
        verify(personagemDAOImpl, times(1)).buscarPersonagem(id);
    }

    @Test
    public void testarBuscarPorIdInvalido() {
        // configuração
        int idInvalido = 999;
        when(personagemDAOImpl.buscarPersonagem(idInvalido)).thenReturn(null);

        // exercitando
        Personagem personagemRetornado = controlador.buscarPersonagem(idInvalido);

        // verificação
        assertNull(personagemRetornado);
        verify(personagemDAOImpl, times(1)).buscarPersonagem(idInvalido);
    }

    @Test
    public void testarBuscarComCampoVazio() {
        // configuração
        int idVazio = 0;

        // exercitando e verificando
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.buscarPersonagem(idVazio);
        });

        verify(personagemDAOImpl, never()).buscarPersonagem(idVazio);
    }

    @Test
    public void testarListarPersonagensComSucesso() {
        // configuração
        List<Personagem> personagensEsperados = new ArrayList<>();
        personagensEsperados
                .add(new Personagem("Naruto", "Humano", "Ninja", "M", 10, new ArrayList<>(List.of("Rasengan"))));
        personagensEsperados
                .add(new Personagem("Hinata", "Humano", "Ninja", "F", 15, new ArrayList<>(List.of("Byakugan"))));

        when(personagemDAOImpl.listarTodosPersonagens()).thenReturn(personagensEsperados);

        // exercitando
        List<Personagem> personagensRetornados = controlador.listarPersonagens();

        // verificação
        assertEquals(personagensEsperados.size(), personagensRetornados.size());
        verify(personagemDAOImpl, times(1)).listarTodosPersonagens();
    }

}
