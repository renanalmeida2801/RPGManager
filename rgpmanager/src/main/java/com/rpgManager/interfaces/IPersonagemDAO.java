package com.rpgManager.interfaces;

import java.util.List;

import com.rpgManager.model.Personagem;
import java.util.*;

public interface IPersonagemDAO {

    public void salvarPersonagem(Personagem persona);

    public Personagem buscarPersonagem(int id);

    public void atualizarPersonagem(int id, Personagem falso);

    public void deletarPersonagem(int id);

    public List<Personagem> listarTodosPersonagens();

}
