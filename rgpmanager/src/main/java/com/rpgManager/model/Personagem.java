package com.rpgManager.model;

import java.util.List;

public class Personagem {
    private String nome;
    private String raca;
    private String classe;
    private char sexo;
    private int nivel;
    private List<String> habilidades;

    public Personagem(String nome, String raca, String classe, char sexo, int nivel, List<String> habilidades) {
        this.nome = nome;
        this.raca = raca;
        this.classe = classe;
        this.sexo = sexo;
        this.nivel = nivel;
        this.habilidades = habilidades;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setRaca(String novaRaca) {
        this.raca = novaRaca;
    }

    public String getRaca() {
        return this.raca;
    }

    public void setClasse(String novaClasse) {
        this.classe = novaClasse;
    }

    public String getClasse() {
        return this.classe;
    }

    public void setSexo(char novoSexo) {
        this.sexo = novoSexo;
    }

    public char getSexo() {
        return this.sexo;
    }

    public void setNivel(int novoNivel) {
        this.nivel = novoNivel;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setHabilidade(List<String> novaHabilidades) {
        this.habilidades = novaHabilidades;
    }

    public List<String> getHabilidades() {
        return this.habilidades;
    }

    public void editarPersonagem(String nome, String raca, String classe, char sexo, int nivel,
            List<String> habilidades) {
        setNome(nome);
        setRaca(raca);
        setClasse(classe);
        setSexo(sexo);
        setNivel(nivel);
        setHabilidade(habilidades);
    }

    public void gerarRelatorio() {

    }
}
