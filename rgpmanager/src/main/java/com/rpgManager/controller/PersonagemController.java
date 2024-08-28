package com.rpgManager.controller;

import java.util.List;
import java.util.Scanner;

import com.rpgManager.database.PersonagemDAOImpl;
import com.rpgManager.interfaces.IPersonagemDAO;
import com.rpgManager.model.Personagem;

public class PersonagemController {
    Scanner scanner = new Scanner(System.in);
    private IPersonagemDAO personagemDAO;

    public PersonagemController() {
        this.personagemDAO = new PersonagemDAOImpl();
    }

    public void criarPersonagem(String nome, String raca, String classe, String sexo, int nivel,
            List<String> habilidades) {
        Personagem personagem = new Personagem();
        personagem.setNome(nome);
        personagem.setRaca(raca);
        personagem.setClasse(classe);
        personagem.setSexo(sexo);
        personagem.setNivel(nivel);
        personagem.setHabilidade(habilidades);

        personagemDAO.salvarPersonagem(personagem);
    }

    public void excluirPersonagem(int id) {
        personagemDAO.deletarPersonagem(id);
    }

    public Personagem buscarPersonagem(int id) {
        Personagem esperado = personagemDAO.buscarPersonagem(id);
        return esperado;
    }

    public List<Personagem> listarPersonagens() {
        return personagemDAO.listarTodosPersonagens();
    }

    public void editarPersonagem(int id, String nome, String raca, String classe, String sexo, int nivel,
            List<String> habilidades) {

        Personagem falso = new Personagem(nome, raca, classe, sexo, nivel, habilidades);
        personagemDAO.atualizarPersonagem(id, falso);
    }

    public void gerarRelatorio(int id) {

    }
}
