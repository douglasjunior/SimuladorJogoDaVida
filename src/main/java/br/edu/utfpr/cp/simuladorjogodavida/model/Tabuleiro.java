package br.edu.utfpr.cp.simuladorjogodavida.model;

/**
 *
 * @author Douglas
 */
public class Tabuleiro {

    private final Celula[][] celulas;

    public Tabuleiro(int tamanho) {
        celulas = new Celula[tamanho][tamanho];
        for (int i = 0; i < celulas.length; i++) {
            Celula[] linha = celulas[i];
            for (int j = 0; j < linha.length; j++) {
                linha[j] = new Celula(false);
            }
        }
    }
    
    public Tabuleiro(boolean[][] config) {
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

}
