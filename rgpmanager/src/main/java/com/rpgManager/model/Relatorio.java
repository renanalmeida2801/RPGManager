package com.rpgManager.model;

import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Relatorio {
    

    private void sla(String texto, PDPageContentStream conteudoPagina){

    }

    public boolean gerarRelatorio(Personagem p){
        String format = "";
        
        String dest = "Ficha-" + p.getNome()+".pdf";
            try (PDDocument documento = new PDDocument()) {
                
                PDPage pagina = new PDPage();
                documento.addPage(pagina);
                try(PDPageContentStream conteudoPagina = new PDPageContentStream(documento, pagina)){
                    conteudoPagina.beginText();
                    conteudoPagina.setFont(PDType1Font.TIMES_BOLD, 10);
                    conteudoPagina.setLeading(15.0f);
                    conteudoPagina.newLineAtOffset(100, 700);
                    
                    format = "Nome: " + p.getNome();
                    conteudoPagina.showText(format);
                    conteudoPagina.newLine();
                    format = "Nivel: " + String.valueOf(p.getNivel());
                    conteudoPagina.showText(format);
                    conteudoPagina.newLine();
                    format = "Classe: " + p.getClasse();
                    conteudoPagina.showText(format);
                    conteudoPagina.newLine();
                    format = "Raça: " + p.getRaca();
                    conteudoPagina.showText(format);
                    conteudoPagina.newLine();
                    
                    format = "HABILIDADES";
                    conteudoPagina.showText(format);
                    conteudoPagina.newLine();

                    for(String a : p.getHabilidades()){
                        format = p.getRaca();
                        conteudoPagina.showText(format);
                        conteudoPagina.newLine();
                    }

                    conteudoPagina.endText();
                }
                documento.save(dest);
            } catch (Exception e) {
                // TODO: handle exception
            }



        return false;
    }

    public boolean gerarRelatorio(ArrayList<Personagem> personagem){
        String format = "";
        for(Personagem p : personagem){
            String dest = "Ficha-" + p.getNome()+".pdf";
            try (PDDocument documento = new PDDocument()) {
                
                PDPage pagina = new PDPage();
                documento.addPage(pagina);
                try(PDPageContentStream conteudoPagina = new PDPageContentStream(documento, pagina)){
                    conteudoPagina.beginText();
                    conteudoPagina.setFont(PDType1Font.TIMES_BOLD, 10);
                    conteudoPagina.setLeading(15.0f);
                    conteudoPagina.newLineAtOffset(100, 700);
                    
                    format = "Nome: " + p.getNome();
                    conteudoPagina.showText(format);
                    conteudoPagina.newLine();
                    format = "Nivel: " + String.valueOf(p.getNivel());
                    conteudoPagina.showText(format);
                    conteudoPagina.newLine();
                    format = "Classe: " + p.getClasse();
                    conteudoPagina.showText(format);
                    conteudoPagina.newLine();
                    format = "Raça: " + p.getRaca();
                    conteudoPagina.showText(format);
                    conteudoPagina.newLine();

                    conteudoPagina.endText();
                }
                documento.save(dest);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        return false;
    }
}
