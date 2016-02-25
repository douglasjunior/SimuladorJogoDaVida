package br.edu.utfpr.cp.simuladorjogodavida;

import br.edu.utfpr.cp.simuladorjogodavida.controller.ConfiguracaoHandler;
import br.edu.utfpr.cp.simuladorjogodavida.controller.GameController;
import br.edu.utfpr.cp.simuladorjogodavida.model.Configuracao;
import java.io.IOException;

/**
 *
 * @author Douglas
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //executar();
        executarArquivo();
    }

    private static void executarArquivo() throws IOException {
        ConfiguracaoHandler handler = new ConfiguracaoHandler();

        Configuracao config = handler.lerArquivo("entrada.txt");

        GameController game = new GameController(config);

        game.executarInteracoes();
    }

    private static void executar() {
        Configuracao config = new Configuracao();
        config.linhasColunas = 10;
        config.geracoes = 4;
        config.matriz = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        GameController game = new GameController(config);

        game.executarInteracoes();
    }

}
