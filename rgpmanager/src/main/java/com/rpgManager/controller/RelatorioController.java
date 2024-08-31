package com.rpgManager.controller;

import java.util.List;

import com.rpgManager.model.Personagem;
import com.rpgManager.model.Relatorio;
import com.rpgManager.view.RelatorioView;

public class RelatorioController {

    private PersonagemController personagemController;
    private Relatorio relatorio;

    public RelatorioController() {
        this.personagemController = new PersonagemController();
        this.relatorio = new Relatorio();
    }

    // Construtor personalizado para injeção de dependências
    public RelatorioController(PersonagemController personagemController, Relatorio relatorio) {
        this.personagemController = personagemController;
        this.relatorio = relatorio;
    }

    public void gerarRelatorio() {
        int id = solicitarId();
        if (id == 0) {
            List<Personagem> personagens = this.personagemController.listarPersonagens();
            this.relatorio.gerarRelatorio(personagens);
        } else {
            Personagem personagem = this.personagemController.buscarPersonagem(id);
            this.relatorio.gerarRelatorio(personagem);
        }
    }

    public int solicitarId() {
        RelatorioView relatorioView = new RelatorioView();
        int id = relatorioView.solicitarId();
        return id;
    }
}
