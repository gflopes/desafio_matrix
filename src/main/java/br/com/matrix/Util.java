package br.com.matrix;

import java.awt.Toolkit;
import java.util.Random;

public class Util {
    public static final int TAMANHO_FONTE = 16;
    private static final Random randomico = new Random();

    private Util() {
    }

    public static char[] getLetraRandomica() {
        int[] letrasExistentes = { 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113,
                114, 115, 116, 117, 118, 119, 120, 121, 122 };

        // Escolhe um indice do array aleatorio
        int indiceAleatorioLetra = getNumeroRandomico(letrasExistentes.length);

        // cria um array para o componente que desenha a letra
        char[] definirLetra = new char[1];

        // Pega a letra atraves do indice aleatorio escilho
        definirLetra[0] = (char) letrasExistentes[indiceAleatorioLetra];
        return definirLetra;
    }

    public static int getNumeroRandomico(int max) {
        return randomico.nextInt(max);
    }

    public static int getNumeroRandomico(int min, int max) {
        return randomico.nextInt(max - min + 1) + min;
    }

    public static int getNumeroRandomicoMultiplo(int maximo, int multiplo) {
        int valor = randomico.nextInt(maximo / multiplo) * multiplo;
        return valor == 0 ? 1 : valor;
    }

    public static int getAlturaFonte() {
        return TAMANHO_FONTE;
    }

    public static int getLarguraFonte() {
        return TAMANHO_FONTE;
    }

    public static int getAlturaTela() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }

    public static int getLarguraTela() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    public static int getVelocidadeRandomica() {
        int[] multiplos = { 1, 3, 6, 12 };
        int multiploEscolhido = randomico.nextInt(multiplos.length);
        return multiplos[multiploEscolhido];
    }
}
