package com.rpgManager.view;

import java.util.Scanner;

public class RelatorioView {

    public int solicitarId(){

        System.out.println("Digite o ID do personagem que deseja emitir o relatório: (0 - PARA IMPRIMIR TODOS)");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

}