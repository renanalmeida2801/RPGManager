package com.rpgManager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rpgManager.database.PersonagemDAOImpl;
import com.rpgManager.model.Personagem;

public class PersonagemControllerTest {
    @InjectMocks
    private PersonagemController controller;

    @Mock
    private PersonagemDAOImpl personagemDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testarCriarPersonagem() {
        // configuração
        List<String> habilidades = new ArrayList<>();
        habilidades.add("habilidade1");

        // exercitando
        controller.criarPersonagem("Legolas", "Alien", "Cientista", "M", 5, habilidades);

        // verificando
        verify(personagemDao, times(1)).salvarPersonagem(any(Personagem.class));
    }

    @Test
    void testarEditarPersonagem() {
        // configuração
        Personagem personagem = new Personagem();
        personagem.setId(1);
        personagem.setNome("Millena");

        when(personagemDao.buscarPersonagem(1)).thenReturn(personagem);

        // exercitando
        controller.editarPersonagem(1, "Millena", "Humana", "Maga", "F", 30, new ArrayList<>());

        // verificando
        verify(personagemDao, times(1)).atualizarPersonagem(anyInt(), any(Personagem.class));
    }

    @Test
    void testarExcluirPersonagem() {
        // exercitando
        controller.excluirPersonagem(1);

        // verificando
        verify(personagemDao, times(1)).deletarPersonagem(1);

    }

    @Test
    void testarBuscarPersonagem() {
        // configurando
        Personagem personagem = new Personagem();
        personagem.setId(1);
        personagem.setNome("Shanks");

        when(personagemDao.buscarPersonagem(1)).thenReturn(personagem);

        // exercitando
        Personagem resultado = controller.buscarPersonagem(1);

        // verificando
        assertNotNull(resultado);
        assertEquals("Shanks", resultado.getNome());
        assertEquals(personagem, resultado);
        verify(personagemDao, times(1)).buscarPersonagem(1);
    }

    @Test
    void testarListarPersonagem() {
        // configurando
        List<Personagem> personagens = new ArrayList<>();
        personagens.add(new Personagem());
        when(personagemDao.listarTodosPersonagens()).thenReturn(personagens);

        // exercitando
        List<Personagem> resultado = personagemDao.listarTodosPersonagens();

        // verificando
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        verify(personagemDao, times(1)).listarTodosPersonagens();
    }

    @Test
    void gerarRelatorio() {
        // configuração
        Personagem personagem = new Personagem();
        personagem.setId(1);
        when(personagemDao.buscarPersonagem(1)).thenReturn(personagem);

        // exercitando
        // controller.gerarRelatorio(1);

        // verificando
        verify(personagemDao, times(1)).buscarPersonagem(1);
    }
}
