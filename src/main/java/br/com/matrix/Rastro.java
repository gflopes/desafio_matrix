package br.com.matrix;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Rastro implements Serializable {
    private Letra[] letras; // Listra de letras do rastro
    private int velocidade; // Velocidade do Rastro
    private int posicaoAtualAnimacao; // Posicao da letra Branca
    private int tamanho; // Tamanho total do rastro
    private int posicaoLateral; // Posicao no Eixo X do rastro
    private boolean primeiraVez = true; // Variavel usada par bao comecar tudo na mesma posicao

    public Rastro(int posicaoLateral) {
        inicializar(posicaoLateral);
    }

    private void inicializar(int posicaoLateralEscolhida) {
        // Pega altura maxima da tela
        int alturaTela = (int) Util.getAlturaTela();

        // Com a altura maxima da pra saber quantas letras vao caber
        int totalLetras = alturaTela / Util.getAlturaFonte();

        // Limite do rastro, para o rastro nao ficar do tamanho da tela, ele pode ter o
        // tamanho total menos 2 posicoes
        int limiteLetras = 2;

        // tamanho aleatorio do rastro para cada rastro ficar diferente
        this.tamanho = Util.getNumeroRandomico(20, totalLetras - limiteLetras);

        // posicao do Eixo X escolhida no FOR da Chuva.java
        this.posicaoLateral = posicaoLateralEscolhida;

        // velocidade aleatorio para o rastro
        this.velocidade = Util.getVelocidadeRandomica();

        // Cria o rastro inteiro de letras
        this.letras = this.criaLetras();

        // Caso seja a primeira vez que o rastro sera desenhado
        // pegar uma posicao inicial aleatoria, para nao comecar todo mundo no mesmo lugar
        if (this.primeiraVez) {
            this.posicaoAtualAnimacao = Util.getNumeroRandomicoMultiplo(20 * Util.getAlturaFonte(), Util.getAlturaFonte());
            this.primeiraVez = false;
        } else {
            this.posicaoAtualAnimacao = 0;
        }
    }

    private Letra[] criaLetras() {
        // Descobre quantas letras cabem na vertical
        Integer alturaTela = (int) Util.getAlturaTela();
        Integer totalLetras = alturaTela / Util.getAlturaFonte();

        // Cria o tamanho maximo de letras
        Letra[] listaLetras = new Letra[totalLetras];

        // Cria randomicamente as letras
        for (int i = 0; i < totalLetras; i++) {

            // Inicia uma letra
            Letra letra = new Letra();
            letra.setY(i * Util.getAlturaFonte());
            letra.setX(posicaoLateral * Util.getAlturaFonte());
            letra.setLetra(Util.getLetraRandomica());
            letra.setCor(Color.BLACK);
            listaLetras[i] = letra;
        }

        return listaLetras;
    }

    public void desenhar(Graphics2D g2) {
        // Verifica todas as letras
        for (int i = 0; i < this.letras.length; i++) {
            // se a posicao for multiplo do tamanho da fonte, faz as verificacoes
            if (posicaoAtualAnimacao % Util.getAlturaFonte() == 0) {

                // Escolhe a cor da letra
                this.letras[i].setCor(selecionaCorLetra(i));

                // Verifica se o acaso vai mudar a letra
                // Em uma chance de 1 para 20
                if (Util.getNumeroRandomico(20) == 1) {
                    this.letras[i].setLetra(Util.getLetraRandomica());
                }
            }

            // Nao desenhar se a cor for preta
            if (!Color.BLACK.equals(this.letras[i].getCor())) {
                g2.setColor(this.letras[i].getCor());
                g2.drawChars(this.letras[i].getLetra(), 0, 1, this.letras[i].getX(), this.letras[i].getY());
            }
        }

        avancarPosicao();
    }

    private boolean isLetraNaPosicaoSolicitada(int indexLetra, int posicaoVerificar) {
        int verificaPosicao = posicaoVerificar * Util.getAlturaFonte();
        return (posicaoAtualAnimacao - this.letras[indexLetra].getY() == verificaPosicao);
    }

    private Color selecionaCorLetra(int indexLetra) {
        int tamanhoRastroCorreto = (tamanho * Util.getAlturaFonte());

        // Se a letra atual está dentro do tamanho do rastro
        boolean letraDentroLimiteRastro = posicaoAtualAnimacao > this.letras[indexLetra].getY()
                && this.letras[indexLetra].getY() >= posicaoAtualAnimacao - tamanhoRastroCorreto;

        // Verifica se a letra atual do index esta junto com a posicao atual da queda
        // 0 = posicao atual
        if (isLetraNaPosicaoSolicitada(indexLetra, 0)) {
            // Cor da primeira Letra
            return Color.WHITE;

        } else if (letraDentroLimiteRastro) {
            // Cor da segunda letra
            if (isLetraNaPosicaoSolicitada(indexLetra, 1)) {
                return new Color(146, 229, 161);
                // return Color.RED;

                // Cor da terceira letra
            } else if (isLetraNaPosicaoSolicitada(indexLetra, 2)) {
                return new Color(128, 206, 135);
                // return Color.YELLOW;

            } else if (isLetraNaPosicaoSolicitada(indexLetra, this.tamanho - 1)) {
                // return Color.YELLOW;
                return new Color(21, 130, 59);

            } else if (isLetraNaPosicaoSolicitada(indexLetra, this.tamanho)) {
                // return Color.RED;
                return new Color(13, 90, 40);

            } else {
                // Cor normal
                return new Color(34, 180, 85);
                // return new Color(0, 255, 65); //verde claro
            }

        } else {
            return Color.BLACK;
        }
    }

    private void avancarPosicao() {
        // Avanca posicao
        this.posicaoAtualAnimacao += this.velocidade;

        // Se ultrapassou o limite volta pro começo
        Integer alturaTela = (int) Util.getAlturaTela();
        int tamanhoTrilha = (tamanho * Util.getAlturaFonte());

        // Se sair da tela reinicia o rastro
        if (this.posicaoAtualAnimacao > alturaTela + tamanhoTrilha) {
            inicializar(this.posicaoLateral);
        }
    }
}
