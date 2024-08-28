package com.rpgManager.app;

import java.nio.file.Paths;
import java.util.List;

import com.rpgManager.database.PersonagemDAOImpl;
import com.rpgManager.model.Personagem;
import com.rpgManager.view.PersonagemView;

public class Main {
    public static void main(String[] args) {

        System.out.println(Paths.get("").toAbsolutePath().toString());
        PersonagemView persona = new PersonagemView();

        persona.mostrarMenu();

        // PersonagemDAOImpl banco = new PersonagemDAOImpl();

        // String nome = "TCK The dirty";
        // String raca = "O SUJO";
        // String classe = "Duelista";
        // char sexo = 'Y';
        // int nivel = 1000;
        // List<String> habilidades = List.of(
        // "Espada Longa",
        // "Arco e Flecha",
        // "Cura Rápida",
        // "Camuflagem",
        // "Acrobacia",
        // "Escudo de Fogo");

        // Personagem persona = new Personagem(nome, raca, classe, sexo, nivel,
        // habilidades);

        // banco.listarTodosPersonagens();
        // // banco.salvarPersonagem(persona);
        // System.out.println(banco.buscarPersonagem(10).getNome());
        // banco.listarTodosPersonagens();
        // banco.atualizarPersonagem(10);

    }
}