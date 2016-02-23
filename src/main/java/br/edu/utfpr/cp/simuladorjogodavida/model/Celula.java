package br.edu.utfpr.cp.simuladorjogodavida.model;

/**
 *
 * @author Douglas
 */
public class Celula {

    private boolean viva;
    private int visinhosVivos;

    public Celula(boolean viva) {
        this.viva = viva;
    }

    public boolean isViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }

    public int getVisinhosVivos() {
        return visinhosVivos;
    }

    public void setVisinhosVivos(int visinhosVivos) {
        this.visinhosVivos = visinhosVivos;
    }

    public void incVisinhosVivos() {
        this.visinhosVivos++;
    }

    public void resetVisinhosVivos() {
        this.visinhosVivos = 0;
    }
}
