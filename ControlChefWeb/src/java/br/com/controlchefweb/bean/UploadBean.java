/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

import br.com.controlchefweb.util.ArquivoUtil;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author VashJHK
 */
@ManagedBean(name = "uploadBean")
@ViewScoped
public class UploadBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<File> arquivos = new ArrayList<>();
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    public void postConstruct() {
        arquivos = new ArrayList<>(ArquivoUtil.listar());
    }

    public void upload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();

        try {
            URL location = getClass().getProtectionDomain().getCodeSource().getLocation();
            File arquivo = ArquivoUtil.escrever(uploadedFile.getFileName(), uploadedFile.getContents());
            
            //System.out.println("/  -> " + new File("/").getCanonicalPath());
            //System.out.println(".. -> " + new File("..").getCanonicalPath());
           // System.out.println(".  -> " + new File(".").getCanonicalPath());
            
            
            fileName = event.getFile().getFileName();
            arquivos.add(arquivo);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Upload completo", "O arquivo " + arquivo.getName() + " foi salvo!"));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro", e.getMessage()));
        }
    }

    public List<File> getArquivos() {
        return arquivos;
    }
}
