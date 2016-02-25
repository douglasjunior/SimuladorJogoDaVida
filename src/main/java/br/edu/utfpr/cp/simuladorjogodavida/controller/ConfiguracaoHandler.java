package br.edu.utfpr.cp.simuladorjogodavida.controller;

import br.edu.utfpr.cp.simuladorjogodavida.model.Configuracao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Douglas
 */
public class ConfiguracaoHandler {

    public Configuracao lerArquivo(String caminho) throws FileNotFoundException, IOException {
        File file = new File(caminho);
        if (!file.exists()) {
            throw new FileNotFoundException("Arquivo '" + file.getAbsolutePath() + "' não encontrado.");
        }
        Configuracao config = new Configuracao();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            try {
                line = reader.readLine();
                config.linhasColunas = Integer.parseInt(line);
            } catch (NumberFormatException | NullPointerException ex) {
                throw new NumberFormatException("Não foi possível ler a quantidade de linhas e colunas.");
            }
            try {
                line = reader.readLine();
                config.geracoes = Integer.parseInt(line);
            } catch (NumberFormatException | NullPointerException ex) {
                throw new NumberFormatException("Não foi possível ler a quantidade de gerações.");
            }
            config.matriz = new int[config.linhasColunas][config.linhasColunas];
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (line.length() != config.linhasColunas) {
                    throw new IllegalArgumentException("Quantidade de colunas da matriz deve ser igual a quantidade configurada.");
                }
                if (i >= config.linhasColunas) {
                    throw new IllegalArgumentException("Quantidade de linhas da matriz deve ser igual a quantidade configurada.");
                }
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);
                    config.matriz[i][j] = Character.getNumericValue(c);
                }
                i++;
            }
            reader.close();
        } finally {
            try {
                fis.close();
            } catch (Exception ex) {
            }
        }
        return config;
    }
}
