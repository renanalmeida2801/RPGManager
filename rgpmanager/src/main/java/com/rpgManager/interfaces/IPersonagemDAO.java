package com.rpgManager.interfaces;

import com.rpgManager.model.Personagem;

public interface IPersonagemDAO {

    public void salvarPersonagem(Personagem persona);

    public Personagem buscarPersonagem(int id);

    public void atualizarPersonagem(int id);

    public void deletarPersonagem(int id);

    public void listarTodosPersonagens();

}
