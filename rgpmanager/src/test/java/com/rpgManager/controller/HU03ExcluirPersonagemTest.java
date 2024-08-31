package com.rpgManager.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rpgManager.database.PersonagemDAOImpl;
import com.rpgManager.model.Personagem;

public class HU03ExcluirPersonagemTest {
    @Mock
    private PersonagemDAOImpl personagemDAOImpl;

    @InjectMocks
    private PersonagemController controlador;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testarExcluirPersonagemCorretamente() {
        // configuração
        int id = 1;
        Personagem personagemExistente = new Personagem();

        when(personagemDAOImpl.buscarPersonagem(id)).thenReturn(personagemExistente);

        // exercitando
        controlador.excluirPersonagem(id);

        // verificando
        verify(personagemDAOImpl, times(1)).deletarPersonagem(id);
    }

    @Test
    public void testarExcluirPersonagemInexistente() {
        // configuração
        int id = 999;
        when(personagemDAOImpl.buscarPersonagem(id)).thenReturn(null);

        // exercitando e verificando
        assertThrows(IllegalArgumentException.class, () -> {
            controlador.excluirPersonagem(id);
        });

        verify(personagemDAOImpl, times(0)).deletarPersonagem(id);
    }
}
