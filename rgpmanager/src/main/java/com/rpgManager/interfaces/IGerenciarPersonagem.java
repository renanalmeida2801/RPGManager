package com.rpgManager.interfaces;

import com.rpgManager.model.Personagem;

public interface IGerenciarPersonagem {
    public void cadastrarPersonagem(Personagem personagem);

    public void excluirPersonagem(int id);

    public void editarPersonagem(int id, Personagem personagem);
}
