/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abdelmoughit
 */
public class CreateDirectory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        System.out.println(path);
        //generateFiles("Abrab");
        //File file = new File("C:\\Directory1\\\\Sub2\\\\Sub-Sub2");
//        String workingDir = System.getProperty("user.dir");
//        File file = new File(workingDir + "/web/AbonnesFiles/test1");
//        if (!file.exists()) {
//            if (file.mkdir()) {
//                System.out.println("Directory is created!");
//            } else {
//                System.out.println("Failed to create directory!");
//            }
//        }
//
//        System.out.println("Current working directory : " + workingDir);

//        File files = new File("C:\\Directory2\\Sub2\\Sub-Sub2");
//        if (files.exists()) {
//            if (files.mkdirs()) {
//                System.out.println("Multiple directories are created!");
//            } else {
//                System.out.println("Failed to create multiple directories!");
//            }
//        }
    }

    public static void generateFiles(String indice) {
        List<File> files = new ArrayList<>();
        String workingDir = System.getProperty("user.dir");
        System.out.println(workingDir);
        File file = new File(workingDir + "/web/AbonnesFiles/" + indice);
        files.add(file);
        File file1 = new File(workingDir + "/web/AbonnesFiles/" + indice + "/PC");
        files.add(file1);
        File file2 = new File(workingDir + "/web/AbonnesFiles/" + indice + "/TV");
        files.add(file2);
        File file3 = new File(workingDir + "/web/AbonnesFiles/" + indice + "/Phones");
        files.add(file3);
        File file4 = new File(workingDir + "/web/AbonnesFiles/" + indice + "/Tablettes");
        files.add(file4);
        for (File loaded : files) {
            if (!loaded.exists()) {
                if (loaded.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
            System.out.println("Directory already existe!");
        }

    }
}
