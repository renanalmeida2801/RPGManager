package com.rpgManager.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rpgManager.database.PersonagemDAOImpl;
import com.rpgManager.model.Personagem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.*;;

public class HU02EditarPersonagemTest {
    @InjectMocks
    private PersonagemController controlador;

    @Mock
    private PersonagemDAOImpl personagemDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testarEditarPersonagemComInformacoesCorretas() {
        // configuração
        int id = 1;
        Personagem personagemExistente = new Personagem("Naruto", "Humano", "Ninja", "M", 10,
                new ArrayList<>(List.of("Rasengan")));

        when(personagemDAO.buscarPersonagem(id)).thenReturn(personagemExistente);

        // exercitando
        List<String> novasHabilidades = new ArrayList<>(List.of("Chidori", "Rasengan"));
        controlador.editarPersonagem(id, "Naruto Uzumaki", "Humano", "Ninja", "M", 12, novasHabilidades);
        ArgumentCaptor<Personagem> captor = ArgumentCaptor.forClass(Personagem.class);

        // verificando
        verify(personagemDAO, times(1)).atualizarPersonagem(eq(id), captor.capture());
        Personagem personagemAtualizado = captor.getValue();
        assertEquals("Naruto Uzumaki", personagemAtualizado.getNome());
        assertEquals("Humano", personagemAtualizado.getRaca());
        assertEquals("Ninja", personagemAtualizado.getClasse());
        assertEquals("M", personagemAtualizado.getSexo());
        assertEquals(12, personagemAtualizado.getNivel());
        assertEquals(novasHabilidades, personagemAtualizado.getHabilidades());
    }

    @Test
    public void testarEditarPersonagemComInformacoesIncorretas() {
        // configurção
        int id = 1;
        Personagem personagemExistente = new Personagem("Sukuna", "Maldição", "Ameaça", "M", 30,
                new ArrayList<>(List.of("Desmantelar")));
        when(personagemDAO.buscarPersonagem(id)).thenReturn(personagemExistente);

        // exercitando e verificação
        assertThrows(IllegalArgumentException.class, () -> {
            List<String> novasHabilidades = new ArrayList<>(List.of("Mahoraga", "Santuário Malevolente"));
            when(personagemDAO.buscarPersonagem(id)).thenReturn(personagemExistente);
            controlador.editarPersonagem(id, "", null, null, "X", 12, novasHabilidades);
        });
    }

    @Test
    public void testarEditarPersonagemComInformacoesInvalidas() {
        // configuração
        int id = 1;
        Personagem personagemExistente = new Personagem("Satoro Gojo", "Humano", "Feiticeiro", "M", 10,
                new ArrayList<>(List.of("Vazio Roxo")));
        when(personagemDAO.buscarPersonagem(id)).thenReturn(personagemExistente);

        // exercitando e verificação
        assertThrows(IllegalArgumentException.class, () -> {
            List<String> novasHabilidades = new ArrayList<>(
                    List.of("Ilimitado", "Reversão de feitiço", "Amplificação de feitiço"));
            controlador.editarPersonagem(id, null, null, null, null, -5, novasHabilidades);
        });
    }
}
