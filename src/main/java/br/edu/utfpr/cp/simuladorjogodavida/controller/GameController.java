package br.edu.utfpr.cp.simuladorjogodavida.controller;

import br.edu.utfpr.cp.simuladorjogodavida.model.Celula;
import br.edu.utfpr.cp.simuladorjogodavida.model.Configuracao;
import br.edu.utfpr.cp.simuladorjogodavida.model.Tabuleiro;

/**
 * Classe responsável por manipular o tabuleiro.
 *
 * @author Douglas
 */
public class GameController {

    private final Tabuleiro tabuleiro;

    private final int geracoes;

    public GameController(Configuracao config) {
        if (config == null) {
            throw new IllegalArgumentException("Configuracao config não pode ser nulo.");
        }
        if (config.matriz == null || config.matriz.length == 0) {
            throw new IllegalArgumentException("int[][] matriz não pode ser nulo ou vazio.");
        }
        if (config.matriz.length != config.matriz[0].length) {
            throw new IllegalArgumentException("int[][] matriz config deve possuir o mesmo número de linhas e colunas.");
        }
        if (config.linhasColunas != config.matriz.length) {
            throw new IllegalArgumentException("int linhaColunas não pode ser diferente do tamanho da int[][] matriz.");
        }
        this.geracoes = config.geracoes;
        Celula[][] celulas = new Celula[config.linhasColunas][config.linhasColunas];
        for (int i = 0; i < celulas.length; i++) {
            Celula[] linha = celulas[i];
            for (int j = 0; j < linha.length; j++) {
                linha[j] = new Celula(config.matriz[i][j] == 1);
            }
        }
        tabuleiro = new Tabuleiro(celulas);
    }

    public String imprimirTabuleiro() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tabuleiro.getTamanho(); i++) {
            Celula[] linha = tabuleiro.getLinha(i);
            for (int j = 0; j < linha.length; j++) {
                Celula celula = linha[j];
                sb.append(celula.isViva() ? "[X]" : "[ ]");
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }

    public String imprimirTabuleiroDebug() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tabuleiro.getTamanho(); i++) {
            Celula[] linha = tabuleiro.getLinha(i);
            for (int j = 0; j < linha.length; j++) {
                Celula celula = linha[j];
                sb.append("[" + celula.getVisinhosVivos() + "]");
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }

    /**
     * Percorre o tabuleiro atualizando a quantidade de vizinhos vivos das
     * células, em seguida percorre atualizando suas vidas.
     */
    public void proximaGeracao() {
        atualizaVizinhos();
        atualizaVidas();
    }

    /**
     * Percorre todo o tabuleiro e atualiza a quantidade de vizinhos
     */
    private void atualizaVizinhos() {
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
    private void atualizaVizinho(Celula celula, Celula vizinho) {
        if (vizinho.isViva()) {
            celula.incVisinhosVivos();
        }
    }

    /**
     * Percorre todas as celulas e atualiza suas vidas de acordo com quantidade
     * de vizinhos.
     */
    private void atualizaVidas() {
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
    private void atualizaVida(Celula celula) {
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

    public void executarInteracoes() {
        for (int i = 0; i < geracoes; i++) {
            System.out.println("Geração: " + i);
            System.out.println(imprimirTabuleiro());
//            System.out.println("Debug:");
//            System.out.println(imprimirTabuleiroDebug());
            proximaGeracao();
            System.out.println(" ");
        }
    }

}
