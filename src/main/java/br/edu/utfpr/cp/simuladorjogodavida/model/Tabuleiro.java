package br.edu.utfpr.cp.simuladorjogodavida.model;

/**
 *
 * @author Douglas
 */
public class Tabuleiro {

    private final Celula[][] celulas;

    public Tabuleiro(boolean[][] config) {
        if (config == null || config.length == 0) {
            throw new IllegalArgumentException("boolean[][] config não pode ser nulo ou vazio.");
        }
        if (config.length != config[0].length) {
            throw new IllegalArgumentException("boolean[][] config deve possuir o mesmo número de linhas e colunas.");
        }
        celulas = new Celula[config.length][config.length];
        for (int i = 0; i < celulas.length; i++) {
            Celula[] linha = celulas[i];
            for (int j = 0; j < linha.length; j++) {
                linha[j] = new Celula(config[i][j]);
            }
        }
    }

    public Celula getCelula(int x, int y) {
        return celulas[x][y];
    }

    public int getTamanho() {
        return celulas.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < celulas.length; i++) {
            Celula[] linha = celulas[i];
            for (int j = 0; j < linha.length; j++) {
                Celula celula = linha[j];
                sb.append(celula.isViva() ? "[X]" : "[ ]");
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }

    public String toStringDebug() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < celulas.length; i++) {
            Celula[] linha = celulas[i];
            for (int j = 0; j < linha.length; j++) {
                Celula celula = linha[j];
                sb.append("[" + celula.getVisinhosVivos() + "]");
            }
            sb.append("\r\n");
        }
        return sb.toString();
    }

}
