package com.rpgManager.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rpgManager.controller.PersonagemController;

public class PersonagemView {

    private PersonagemController controller;
    Scanner scanner = new Scanner(System.in);

    public PersonagemView() {
        this.controller = new PersonagemController();
    }

    public void mostrarMenu() {
        int opcao = 0;

        do {
            System.out.println("Bem vindo ao RPG Manager!");
            System.out.println("O que você deseja realizar ? Digite o número correspondente!");
            System.out.println("------------------------------------------------------------");
            System.out.println(
                    "(1): Criar Personagem \n(2): Editar Personagem\n(3): Excluir Personagem\n(4): Listar Personagem\n(5): Gerar relatório\n(0): Sair");
            System.out.println("------------------------------------------------------------");
            System.out.print("digite um número:");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    criarPersonagem();
                    break;
                case 2:
                    editarPersonagem();
                    break;
                case 3:
                    excluirPersonagem();
                    break;
                case 4:
                    // listarPersonagem();
                    break;
                case 5:
                    // gerarRelatorio();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }

    public void criarPersonagem() {
        String resposta = "";
        List<String> habilidades = new ArrayList<String>() {

        };
        int id = 0;

        System.out.println("CRIAÇÃO DE PERSONAGEM");
        System.out.print("Digite o nome do seu personagem:");
        String nome = scanner.nextLine();

        System.out.print("Digite a raça do seu personagem:");
        String raca = scanner.nextLine();

        System.out.print("Digite a classe do seu personagem:");
        String classe = scanner.nextLine();

        String sexo = "";
        do {
            System.out.println("Digite o sexo do seu personagem:");
            System.out.println("M: Masculino\nF: Feminino ");
            sexo = scanner.nextLine();
            resposta = sexo;
            if (!(sexo.equals("M")) && !(sexo.equals("F"))) {
                System.out.println("Sexo inválido, tente novamente!");
            }
        } while (!resposta.equals("M") && !resposta.equals("F"));

        System.out.print("Digite o nivel do seu personagem:");
        int nivel = scanner.nextInt();
        scanner.nextLine();

        do {
            System.out.println("Digite as habilidades do seu personagem: (digite 0 para finalizar)");
            resposta = scanner.nextLine();
            habilidades.add(id++, resposta);
        } while (!resposta.equals("0"));
        controller.criarPersonagem(nome, raca, classe, sexo, nivel, habilidades);
        System.out.println("Personagem Criado com Sucesso!");
    }

    public void editarPersonagem() {

    }

    public void excluirPersonagem() {
        System.out.print("Digite o ID do personagem que deseja excluir:");
        int id = scanner.nextInt();
        scanner.nextLine();
        // controller.excluirPersonagem(id);
    }

    public void listarPersonagem() {

    }

    public void gerarRelatorio() {

    }
}
