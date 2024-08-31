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

    public PersonagemController(IPersonagemDAO person) {
        this.personagemDAO = person;
    }

    public void criarPersonagem(String nome, String raca, String classe, String sexo, int nivel,
            List<String> habilidades) {

        if (nome == null || nome.isEmpty() || raca == null || raca.isEmpty() || classe == null || classe.isEmpty()
                || sexo == null || sexo.isEmpty()) {

            throw new IllegalArgumentException("Os campos não podem estar em branco");
        }

        if (nivel < 0) {
            throw new IllegalArgumentException("Nível não pode ser negativo");
        }

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
        Personagem personagem = personagemDAO.buscarPersonagem(id);
        if (personagem == null) {
            throw new IllegalArgumentException("Personagem não encontrado.");
        }
        personagemDAO.deletarPersonagem(id);
    }

    public Personagem buscarPersonagem(int id) {
        if (id == 0) {
            throw new IllegalArgumentException("Id não pode ser vazio");
        }

        Personagem esperado = personagemDAO.buscarPersonagem(id);
        return esperado;
    }

    public List<Personagem> listarPersonagens() {
        return personagemDAO.listarTodosPersonagens();
    }

    public void editarPersonagem(int id, String nome, String raca, String classe, String sexo, int nivel,
            List<String> habilidades) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        if (raca == null || raca.trim().isEmpty()) {
            throw new IllegalArgumentException("Raça inválida");
        }

        if (classe == null || classe.trim().isEmpty()) {
            throw new IllegalArgumentException("Classe inválida");
        }

        if (sexo == null || raca.trim().isEmpty()) {
            throw new IllegalArgumentException("Sexo inválido");
        }
        if (habilidades == null || habilidades.isEmpty()) {
            throw new IllegalArgumentException("Habilidades inválidas");
        }

        Personagem personagemExistente = personagemDAO.buscarPersonagem(id);
        if (personagemExistente == null) {
            throw new IllegalArgumentException("Personagem não encontrado");
        }

        Personagem falso = new Personagem(nome, raca, classe, sexo, nivel, habilidades);
        personagemDAO.atualizarPersonagem(id, falso);
    }

    public void gerarRelatorio(int id) {
        personagemDAO.buscarPersonagem(id);
    }

    public boolean encontrarId(int id) {
        return personagemDAO.encontrarId(id);
    }
}
