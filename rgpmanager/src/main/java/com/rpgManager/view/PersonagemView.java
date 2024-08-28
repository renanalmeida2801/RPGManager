package com.rpgManager.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rpgManager.controller.PersonagemController;
import com.rpgManager.model.Personagem;

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
                    listarPersonagem();
                    break;
                case 5:
                    gerarRelatorio();
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
        String sexo = "";
        List<String> habilidades = new ArrayList<String>() {

        };
        int idHabilidade = 0;

        System.out.println("CRIAÇÃO DE PERSONAGEM");
        System.out.print("Digite o nome do seu personagem:");
        String nome = scanner.nextLine();

        System.out.print("Digite a raça do seu personagem:");
        String raca = scanner.nextLine();

        System.out.print("Digite a classe do seu personagem:");
        String classe = scanner.nextLine();

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
            habilidades.add(idHabilidade++, resposta);
        } while (!resposta.equals("0"));
        controller.criarPersonagem(nome, raca, classe, sexo, nivel, habilidades);
        System.out.println("Personagem Criado com Sucesso!");
    }

    public void editarPersonagem() {
        int idHabilidade = 0;
        String nome;
        String raca;
        String classe;
        String sexo;
        int nivel;
        List<String> habilidades = new ArrayList<String>();

        System.out.print("Digite o ID do personagem que deseja editar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o novo Nome de seu Personagem:");
        nome = scanner.nextLine();

        System.out.print("Digite a nova Raça de seu Personagem:");
        raca = scanner.nextLine();

        System.out.print("Digite a nova Classe de seu Personagem:");
        classe = scanner.nextLine();

        System.out.print("Digite o novo Sexo de seu Personagem:");
        sexo = scanner.nextLine();

        System.out.print("Digite o novo Nivel de seu Personagem:");
        nivel = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite as novas habilidades de seu Personagem:");
        habilidades.add(idHabilidade++, scanner.nextLine());
        nome = scanner.nextLine();
        controller.editarPersonagem(id, nome, raca, classe, sexo, nivel, habilidades);
    }

    public void excluirPersonagem() {
        System.out.print("Digite o ID do personagem que deseja excluir:");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.buscarPersonagem(id);

        
        controller.excluirPersonagem(id);
    }

    public void listarPersonagem() {
        System.out.print("Digite o ID do personagem que deseja encontrar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        Personagem persona = controller.buscarPersonagem(id);
        System.out.println("Nome: " + persona.getNome());
        System.out.println("Raça: " + persona.getRaca());
        System.out.println("Classe: " + persona.getClasse());
        System.out.println("Sexo: " + persona.getSexo());
        System.out.println("Nivel: " + persona.getNivel());
        System.out.println("Habilidades: " + persona.getHabilidades());

    }

    public void gerarRelatorio() {
        System.out.print("Digite o ID do personagem que deseja emitir o relatório: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.gerarRelatorio(id);
    }
}
