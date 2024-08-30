package com.rpgManager.model;

import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class Relatorio {

    public void gerarRelatorio(Personagem p){
        String format = "";
        
        String dest = "Ficha-" + p.getNome()+".pdf";
            try (PDDocument documento = new PDDocument()) {
                
                PDPage pagina = new PDPage();
                documento.addPage(pagina);
                try(PDPageContentStream conteudoPagina = new PDPageContentStream(documento, pagina)){
                     
                    conteudoPagina.addRect(320, 690, 130, 20);
                    conteudoPagina.addRect(470, 690, 33, 20);
                    conteudoPagina.addRect(100, 650, 130, 20);
                    conteudoPagina.addRect(100, 690, 200, 20);
                    conteudoPagina.addRect(100, 50, 403, 580);
                    conteudoPagina.stroke();

                    conteudoPagina.beginText();
                    conteudoPagina.setFont(PDType1Font.TIMES_BOLD, 10);
                    conteudoPagina.setLeading(15.0f);
                    conteudoPagina.newLineAtOffset(100, 712);
                    conteudoPagina.showText("NOME");
                    conteudoPagina.setFont(PDType1Font.TIMES_ROMAN, 8);
                    conteudoPagina.newLine();
                    conteudoPagina.showText(" " + p.getNome());
                    conteudoPagina.endText();

                    conteudoPagina.beginText();
                    conteudoPagina.setFont(PDType1Font.TIMES_BOLD, 10);
                    conteudoPagina.setLeading(15.0f);
                    conteudoPagina.newLineAtOffset(320, 712);
                    conteudoPagina.showText("CLASSE");
                    conteudoPagina.setFont(PDType1Font.TIMES_ROMAN, 8);
                    conteudoPagina.newLine();
                    conteudoPagina.showText(" " + p.getClasse());
                    conteudoPagina.endText();

                    conteudoPagina.beginText();
                    conteudoPagina.setFont(PDType1Font.TIMES_BOLD, 10);
                    conteudoPagina.setLeading(15.0f);
                    conteudoPagina.newLineAtOffset(470, 712);
                    conteudoPagina.showText("NÍVEL");
                    conteudoPagina.setFont(PDType1Font.TIMES_ROMAN, 8);
                    conteudoPagina.newLine();
                    conteudoPagina.showText(" " + String.valueOf(p.getNivel()));
                    conteudoPagina.endText();

                    conteudoPagina.beginText();
                    conteudoPagina.setFont(PDType1Font.TIMES_BOLD, 10);
                    conteudoPagina.setLeading(15.0f);
                    conteudoPagina.newLineAtOffset(100, 672);
                    conteudoPagina.showText("RAÇA");
                    conteudoPagina.setFont(PDType1Font.TIMES_ROMAN, 8);
                    conteudoPagina.newLine();
                    conteudoPagina.showText(" " + p.getRaca());
                    conteudoPagina.endText();

                    conteudoPagina.beginText();
                    conteudoPagina.setFont(PDType1Font.TIMES_BOLD, 10);
                    conteudoPagina.setLeading(15.0f);
                    conteudoPagina.newLineAtOffset(100, 632);
                    conteudoPagina.showText("HABILIDADES");
                    conteudoPagina.endText();


                    conteudoPagina.beginText();

                    conteudoPagina.setFont(PDType1Font.TIMES_ROMAN, 8);
                    conteudoPagina.setLeading(15.0f);
                    conteudoPagina.newLineAtOffset(100, 632);
                    conteudoPagina.newLine();

                    for(String a : p.getHabilidades()){
                        format = a;
                        conteudoPagina.showText(" " + format);
                        conteudoPagina.newLine();
                    }

                    conteudoPagina.endText();
                }
                documento.save(dest);
            } catch (Exception e) {
                System.out.println(e.getMessage());;
            }
    }

    public void gerarRelatorio(List<Personagem> personagem){
        for (Personagem p : personagem) gerarRelatorio(p);
    }
}
