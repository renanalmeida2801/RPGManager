package com.rpgManager.app;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Relation;

import com.rpgManager.database.PersonagemDAOImpl;
import com.rpgManager.model.Personagem;
import com.rpgManager.model.Relatorio;
import com.rpgManager.view.PersonagemView;

public class Main {
    public static void main(String[] args) {

        // PersonagemView persona = new PersonagemView();
        // persona.mostrarMenu();

        Relatorio relatorio = new Relatorio();

        ArrayList<String> lista = new ArrayList<>();

        lista.add("ROUBAR");
        lista.add("FURTAR");
        lista.add("EMGABELAR");
        lista.add("SAFADEZAS");

        relatorio.gerarRelatorio(new Personagem("Kratos", "Humano", "Guerreiro", "sim", 20, lista));
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