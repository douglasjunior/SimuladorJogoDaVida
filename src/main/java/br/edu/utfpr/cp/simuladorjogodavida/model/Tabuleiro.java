package br.edu.utfpr.cp.simuladorjogodavida.model;

/**
 *
 * @author Douglas
 */
public class Tabuleiro {

    private final Celula[][] celulas;

    public Tabuleiro(Celula[][] celulas) {
        this.celulas = celulas;
    }

    public Celula getCelula(int x, int y) {
        return celulas[x][y];
    }

    public int getTamanho() {
        return celulas.length;
    }

    public Celula[] getLinha(int i) {
        return celulas[i];
    }
}
