package br.com.matrix;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;

import javax.swing.JPanel;

public class Matrix extends JPanel {

    private Rastro[] rastros;

    private Font fonteMatrix;

    private final Integer larguraTela;

    private final Integer alturaTela;

    public Matrix() throws FontFormatException, IOException {
        // carrega em memória o fonte matrix.ttf
        fonteMatrix = Font.createFont(Font.TRUETYPE_FONT,
                this.getClass().getResourceAsStream("/fonts/matrix_code_nfi.ttf"));
        fonteMatrix = fonteMatrix.deriveFont(Font.PLAIN, Util.TAMANHO_FONTE);

        // define largura e altura da tela onde está sendo executado o programa
        larguraTela = (int) Util.getLarguraTela();
        alturaTela = (int) Util.getAlturaTela();

        executaMatrix();
    }

     private void executaMatrix() {
        Integer totalLetras = larguraTela / Util.getLarguraFonte();
        this.rastros = new Rastro[totalLetras];

        for (int i = 0; i < totalLetras; i++) {
            this.rastros[i] = new Rastro(i);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(new Color(13, 2, 8));
        g.fillRect(0, 0, larguraTela, alturaTela);

        // Gera grafico 2d para desenhar a letra
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(this.fonteMatrix);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // loop para cada letra de cada rastro
        for (Rastro rastro : this.rastros) {
            rastro.desenhar(g2);
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        repaint();
    }
}