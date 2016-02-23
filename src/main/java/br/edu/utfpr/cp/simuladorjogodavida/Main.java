package br.edu.utfpr.cp.simuladorjogodavida;

import br.edu.utfpr.cp.simuladorjogodavida.model.Tabuleiro;

/**
 *
 * @author Douglas
 */
public class Main {

    private static Tabuleiro tabuleiro;

    private static boolean[][] config;

    public static void main(String[] args) {
        config = new boolean[][]{
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, true, true, true, false, false, false, false},
            {false, false, false, true, false, true, false, false, false, false},
            {false, false, false, true, true, true, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false}};

        tabuleiro = new Tabuleiro(config);
        System.out.println(tabuleiro);
    }
}
