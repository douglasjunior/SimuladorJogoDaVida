package br.edu.utfpr.cp.simuladorjogodavida;

import br.edu.utfpr.cp.simuladorjogodavida.model.Celula;
import br.edu.utfpr.cp.simuladorjogodavida.model.Tabuleiro;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
        atualizaVizinhos();
        int i = 1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                System.out.println("Geração " + i + ":");
                System.out.println(tabuleiro);
                System.out.println("\nPressione ENTER para rodar a próxima geração... (ou escreva sair)");

                String entrada = reader.readLine();

                if ("sair".equalsIgnoreCase(entrada)) {
                    break;
                }

                proximaGeracao();
                i++;
            } catch (Exception ex) {
                ex.printStackTrace();
                break;
            }
        }
    }

    /**
     * Percorre o tabuleiro atualizando a quantidade de vizinhos vivos das
     * células, em seguida percorre atualizando suas vidas.
     */
    private static void proximaGeracao() {
        atualizaVizinhos();
        atualizaVidas();
    }

    /**
     * Percorre todo o tabuleiro e atualiza a quantidade de vizinhos
     */
    private static void atualizaVizinhos() {
        for (int i = 0; i < tabuleiro.getTamanho(); i++) {
            for (int j = 0; j < tabuleiro.getTamanho(); j++) {
                Celula celula = tabuleiro.getCelula(i, j);
                celula.resetVisinhosVivos();
                // atualiza vizinho ao oeste
                if (i > 0) {
                    Celula norte = tabuleiro.getCelula(i - 1, j);
                    atualizaVizinho(celula, norte);
                }
                // atualiza vizinho ao leste
                if (i + 1 < tabuleiro.getTamanho()) {
                    Celula leste = tabuleiro.getCelula(i + 1, j);
                    atualizaVizinho(celula, leste);
                }

                // atualiza vizinho ao note
                if (j > 0) {
                    Celula oeste = tabuleiro.getCelula(i, j - 1);
                    atualizaVizinho(celula, oeste);
                    // atualiza vizinho ao noroeste
                    if (i > 0) {
                        Celula noroeste = tabuleiro.getCelula(i - 1, j - 1);
                        atualizaVizinho(celula, noroeste);
                    }
                    // atualiza vizinho ao nordeste
                    if (i + 1 < tabuleiro.getTamanho()) {
                        Celula nordeste = tabuleiro.getCelula(i + 1, j - 1);
                        atualizaVizinho(celula, nordeste);
                    }
                }

                // atualiza vizinho ao sul
                if (j + 1 < tabuleiro.getTamanho()) {
                    Celula nordeste = tabuleiro.getCelula(i, j + 1);
                    atualizaVizinho(celula, nordeste);
                    // atualiza vizinho ao sudoeste
                    if (i > 0) {
                        Celula sudoeste = tabuleiro.getCelula(i - 1, j + 1);
                        atualizaVizinho(celula, sudoeste);
                    }
                    // atualiza vizinho ao sudeste
                    if (i + 1 < tabuleiro.getTamanho()) {
                        Celula sudeste = tabuleiro.getCelula(i + 1, j + 1);
                        atualizaVizinho(celula, sudeste);
                    }
                }
            }
        }
    }

    /**
     * Incrementa vida da celula caso o vizinho esteja vivo
     *
     * @param celula
     * @param vizinho
     */
    private static void atualizaVizinho(Celula celula, Celula vizinho) {
        if (vizinho.isViva()) {
            celula.incVisinhosVivos();
        }
    }

    /**
     * Percorre todas as celulas e atualiza suas vidas de acordo com quantidade
     * de vizinhos.
     */
    private static void atualizaVidas() {
        for (int i = 0; i < tabuleiro.getTamanho(); i++) {
            for (int j = 0; j < tabuleiro.getTamanho(); j++) {
                Celula celula = tabuleiro.getCelula(i, j);
                atualizaVida(celula);
            }
        }
    }

    /**
     * Atualiza a vida da célula de acordo com as regras do jogo.<br/>
     *
     * Nascimento: uma célula com 3 vizinhos torna-se viva. <br/>
     * Sobrevivência:uma célula com 2 ou 3 vizinhos permanece viva. <br/>
     * Morte por solidão: uma célula morre se tiver menos que 2 vizinhos. <br/>
     * Morte por superpopulação: uma célula morre se tiver mais do que 3
     * vizinhos. <br/>
     *
     * @param celula
     */
    private static void atualizaVida(Celula celula) {
        if (celula.getVisinhosVivos() == 2) {
            return;
        }
        if (!celula.isViva() && celula.getVisinhosVivos() == 3) {
            celula.setViva(true);
            return;
        }
        if (celula.isViva() && (celula.getVisinhosVivos() > 3 || celula.getVisinhosVivos() < 2)) {
            celula.setViva(false);
            return;
        }
    }

}
