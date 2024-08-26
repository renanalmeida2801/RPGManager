package com.rpgManager.interfaces;

import com.rpgManager.model.Personagem;

public interface IPesquisarPersonagem {
    public Personagem pesquisarPersonagemPorNome(String nome);

    public Personagem pesquisarPersonagemPorId(int id);

}
