/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.util;

/**
 *
 * @author VashJHK
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

public class ArquivoUtil {
    
  
    public static File escrever(String name, byte[] contents) throws IOException {
        File file = new File(diretorioRaizParaArquivos(), name);

        OutputStream out = new FileOutputStream(file);
        out.write(contents);
        out.close();

        return file;
    }

    public static List<File> listar() {
        File dir = diretorioRaizParaArquivos();

        return Arrays.asList(dir.listFiles());
    }

    public static java.io.File diretorioRaizParaArquivos() {
        File dir = new File(diretorioRaiz(), "arquivos");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }
    
    public static File diretorioRaiz() {
      
        // File dir = new File(System.getProperty("user.home"), "algaworks")
        //System.out.println(System.getProperty("user.dir"));
        File dir = new File(System.getProperty("user.home"), "ControlChefWebUP");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
    }
}