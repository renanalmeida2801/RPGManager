package com.rpgManager.app;

import java.nio.file.Paths;
import java.util.List;

import javax.management.relation.Relation;

import com.rpgManager.database.PersonagemDAOImpl;
import com.rpgManager.model.Personagem;
import com.rpgManager.model.Relatorio;
import com.rpgManager.view.PersonagemView;

public class Main {
    public static void main(String[] args) {
        PersonagemView menu = new PersonagemView();
        menu.mostrarMenu();

    }
}