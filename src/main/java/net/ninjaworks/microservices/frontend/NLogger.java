package net.ninjaworks.microservices.frontend;

import net.ninjaworks.api.ArrayManipulator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class NLogger {

    public static void log(String line) {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
                getLogDir() + "/latest.log", true)))) {
            out.println(line);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static String getLogDir() {
        String newUserDir = System.getProperty("user.dir").replace("\\",
                "/");
        newUserDir = newUserDir.substring(0, newUserDir.lastIndexOf("/microservices"))
                + "/logs";
        return newUserDir;
    }

    public static void handleOldLog() {
        String[] oldLogLines = new String[1];
        File file = new File(getLogDir() + "/latest.log");
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if(!(oldLogLines[0] == null)) {
                    oldLogLines = ArrayManipulator.addElement(oldLogLines, line);
                } else {
                    oldLogLines[0] = line;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //Since the file system doesn't like special characters, we'll convert them here.
        boolean renameSuccessful;
        try {
            renameSuccessful = file.renameTo(
                    new File(getLogDir() + "/" + oldLogLines[0].replace("/",
                            ".").replace(":", "-").replace(" ",
                            "_") + ".log"));
        } catch (Exception e) {
            renameSuccessful = false;
        }
        if(!renameSuccessful) {
            System.out.println("Could not rename old log file. Terminating!");
            Runtime.getRuntime().exit(7);
        }
    }
}
