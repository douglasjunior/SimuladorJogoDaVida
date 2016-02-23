/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.cp.simuladorjogodavida.model;

/**
 *
 * @author Douglas
 */
public class Celula {

    private boolean viva;
    private int visinhos;

    public Celula(boolean viva) {
        this.viva = viva;
    }

    public boolean isViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }

    public int getVisinhos() {
        return visinhos;
    }

    public void setVisinhos(int visinhos) {
        this.visinhos = visinhos;
    }

}
