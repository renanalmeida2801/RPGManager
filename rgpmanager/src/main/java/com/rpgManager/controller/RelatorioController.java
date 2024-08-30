package com.rpgManager.controller;

import java.util.List;

import com.rpgManager.model.Personagem;
import com.rpgManager.model.Relatorio;
import com.rpgManager.view.RelatorioView;

public class RelatorioController {
    
    private PersonagemController personagemController;
    private Relatorio relatorio;

    public RelatorioController(){
        personagemController = new PersonagemController();
        relatorio = new Relatorio();
    }
    
    public void gerarRelatorio(){
        int id = solicitarId();
        if(id == 0){
            List<Personagem> personagens =  personagemController.listarPersonagens();
            relatorio.gerarRelatorio(personagens);
        }else{
            Personagem personagem = personagemController.buscarPersonagem(id);
            relatorio.gerarRelatorio(personagem);
        }
    }

    public int solicitarId(){
        RelatorioView relatorioView = new RelatorioView();
        int id = relatorioView.solicitarId();
        return id;
    }
}
