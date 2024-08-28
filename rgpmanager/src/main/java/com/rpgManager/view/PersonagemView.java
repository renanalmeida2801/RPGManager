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
                    "(1): Criar Personagem \n(2): Editar Personagem\n(3): Excluir Personagem\n(4): Listar Personagens\n(5): Gerar relatório\n(0): Sair");
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
                    listarPersonagens();
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
            if (!resposta.equals("0")) {
                habilidades.add(idHabilidade++, resposta);
            }
        } while (!resposta.equals("0"));
        controller.criarPersonagem(nome, raca, classe, sexo, nivel, habilidades);
        limparTela();
        System.out.println("Personagem Criado com Sucesso!");
    }

    public void editarPersonagem() {
        int idHabilidade = 0;
        String habilidade;
        String nome = "";
        String raca = "";
        String classe = "";
        String sexo = "";
        int nivel = -1;
        List<String> habilidades = new ArrayList<String>();
        boolean mudanca = true;

        listarPersonagens();
        System.out.print("Digite o ID do personagem que deseja editar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        do {
            System.out.println("O que você deseja Editar ? Digite o número correspondente!");
            System.out.println("------------------------------------------------------------");
            System.out.println(
                    "(1): Nome do Personagem \n(2): Raca do Personagem\n(3): Classe do Personagem\n(4): Sexo do Personagem\n(5): Nivel do Personagem\n(6): Habilidades do Personagem\n(0): Terminar edicao");
            System.out.println("------------------------------------------------------------");
            System.out.print("digite um número:");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o novo Nome de seu Personagem:\n");
                    nome = scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Digite a nova Raça de seu Personagem:\n");
                    raca = scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Digite a nova Classe de seu Personagem:\n");
                    classe = scanner.nextLine();
                    break;
                case 4:
                    System.out.print("Digite o novo Sexo de seu Personagem:\n");
                    sexo = scanner.nextLine();
                    break;
                case 5:
                    System.out.print("Digite o novo Nivel de seu Personagem:");
                    nivel = scanner.nextInt();
                    break;
                case 6:
                    System.out.print("Digite quantas Habilidades seu Personagem possui:\n");
                    idHabilidade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite as novas habilidades de seu Personagem:\n");
                    for (int i = 0; i < idHabilidade; i++) {
                        habilidade = scanner.nextLine();
                        System.out.println(habilidade + " " + i);
                        habilidades.add(habilidade);
                    }
                    break;
                case 0:
                    mudanca = false;
                    limparTela();
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");
                    break;
            }
        } while (mudanca);

        // System.out.println("Digite somente as caracteristicas que devem ser
        // mudadas");
        // System.out.print("Digite o novo Nome de seu Personagem:");
        // nome = scanner.nextLine();

        // System.out.print("Digite a nova Raça de seu Personagem:");
        // raca = scanner.nextLine();

        // System.out.print("Digite a nova Classe de seu Personagem:");
        // classe = scanner.nextLine();

        // System.out.print("Digite o novo Sexo de seu Personagem:");
        // sexo = scanner.nextLine();

        // System.out.print("Digite o novo Nivel de seu Personagem:");
        // nivel = scanner.nextInt();
        // scanner.nextLine();

        // System.out.print("Digite quantas Habilidades seu Personagem possui:");
        // idHabilidade = scanner.nextInt();
        // System.out.print("Digite as novas habilidades de seu Personagem:");
        // for (int i = 0; i < idHabilidade; i++) {
        // habilidade = scanner.nextLine();
        // habilidades.add(habilidade);
        // }
        controller.editarPersonagem(id, nome, raca, classe, sexo, nivel, habilidades);
    }

    public void excluirPersonagem() {
        listarPersonagens();
        System.out.println("Digite o ID do personagem que deseja excluir --- (digite 0 para Cancelar)");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (id == 0) {
            limparTela();
            System.out.println("Operação cancelada!");
            return;
        }
        System.out.println("Tem certeza que deseja deletar este personagem? S/N");
        String confirmacao = scanner.nextLine();
        if (confirmacao.equals("S")) {
            controller.excluirPersonagem(id);
            limparTela();
            System.out.println("Personagem Excluido com sucesso!");
        } else if (confirmacao.equals("N")) {
            limparTela();
            System.out.println("Operação cancelada!");
        } else {
            limparTela();
            System.out.println("ERRO na exclusão. Tente novamente");
        }
    }

    public void listarPersonagens() {
        List<Personagem> personagens = controller.listarPersonagens();

        for (Personagem p : personagens) {
            System.out.println("ID: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Raça: " + p.getRaca());
            System.out.println("Classe: " + p.getClasse());
            System.out.println("Sexo: " + p.getSexo());
            System.out.println("Nível: " + p.getNivel());
            System.out.println("Habilidades: " + p.getHabilidades());
            System.out.println("--------------------------------");

        }
    }

    public void gerarRelatorio() {
        System.out.print("Digite o ID do personagem que deseja emitir o relatório: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.gerarRelatorio(id);
    }

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
