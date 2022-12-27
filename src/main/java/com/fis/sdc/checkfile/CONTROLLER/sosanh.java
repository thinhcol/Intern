package com.fis.sdc.checkfile.CONTROLLER;

import org.springframework.web.multipart.MultipartFile;
import org.zeroturnaround.zip.commons.FileUtils;

import java.io.*;


public class sosanh {
    public static void main(String[] args) throws IOException {
        File srcDir = new File("C:\\FPT\\1\\1");
        File destDir = new File("C:\\FPT\\1\\3");
        if (!srcDir.exists()) {
            System.out.println("Directory does not exist.");
        } else {
            sosanh fileDemo = new sosanh();
            fileDemo.copydir(srcDir, destDir);
            System.out.println("Copied successfully.");
        }

    }

    public void copydir(File src, File dest) throws IOException {

        if (src.isDirectory()) {

            // if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
                System.out.println("Directory copied from " + src + "  to "
                        + dest);
            }

            // list all the directory contents
            String files[] = src.list();

            for (String fileName : files) {
                // construct the src and dest file structure
                File srcFile = new File(src, fileName);
                File destFile = new File(dest, fileName);
                // recursive copy
                copydir(srcFile, destFile);
            }

        } else {
            // If file, then copy it
            fileCopy(src, dest);
        }
    }

    private void fileCopy(File src, File dest)
            throws FileNotFoundException, IOException {

        InputStream in = null;
        OutputStream out = null;

        try {
            // If file, then copy it
            in = new FileInputStream(src);
            out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            // Copy the file content in bytes
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        System.out.println("File copied from " + src + " to " + dest);
    }
//    public static void main(String[] args) throws IOException {
////        File file = new File("src\\main\\resources\\static\\SaveFile\\css");
////        System.out.println(file.getAbsolutePath());
//        FileUtils.copyDirectory(new File("C:\\FPT\\1\\3"),new File("C:\\FPT\\1\\1"));
//    }

}


