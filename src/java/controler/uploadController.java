/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Khalid
 */
@ManagedBean
@RequestScoped
public class uploadController {

    private UploadedFile file;
    private String vmParam = "irisi.projet.upload.path";

    public void upload() throws IOException {
        System.out.println("in");
        if (file != null) {//cdt tjr verifier qlq soit la val du file , pour le resoudre il faut changer la cdt par file.getSize() > 0
            System.out.println("in1");
            String path = System.getProperty(vmParam);
            if (path == null) {
                FacesMessage message = new FacesMessage("Erreur", "option JVM manquante \"" + vmParam + "\"");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                File folder = new File(path);
                if (!folder.exists()) {
                    folder.mkdirs(); // Création de l'arborescense (dossier et sous dossier)
                }
                String outputPath = path + "/" + file.getFileName();
                File outputFile = new File(outputPath);
                Files.copy(file.getInputstream(), outputFile.toPath());

                FacesMessage message = new FacesMessage("Succée", file.getFileName() + " est bien reçcu.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
