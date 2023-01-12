package net.ninjaworks.microservices;

import net.ninjaworks.microservices.frontend.NLogger;
import net.ninjaworks.microservices.frontend.NOutput;
import net.ninjaworks.microservices.gui.AdminGUI;
import net.ninjaworks.microservices.handlers.Ticker;
import net.ninjaworks.microservices.handlers.WaitingRoom;
import net.ninjaworks.microservices.network.NetPropertyGetter;
import net.ninjaworks.microservices.pc.PC;
import net.ninjaworks.microservices.pc.PCProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Microservices {

    static WaitingRoom serverRoom = new WaitingRoom();


    public static void main(String[] args) {
        if(!(args.length == 0)) {
            NOutput.showInfo = Arrays.stream(args).noneMatch(
                    Predicate.isEqual("-noinfo"));
        }
        if(new File(NLogger.getLogDir() + "/latest.log").isFile()) {
            NLogger.handleOldLog();
        } else {
            try {
                Files.createDirectories(Paths.get(NLogger.getLogDir()));
                File file = new File(NLogger.getLogDir() + "/latest.log");
                boolean fileCreated = file.createNewFile();
                if(!fileCreated) {
                    System.out.println("Could not create log file!");
                }
            } catch (IOException e) {
                System.out.println("Could not create new log file : IOException");
            }
        }
        if(!(args.length == 0)) {
            if (Arrays.stream(args).anyMatch(Predicate.isEqual("-server"))) {
                runServerCode();
            } else if(Arrays.stream(args).anyMatch(Predicate.isEqual("-admin"))) {
                AdminGUI.load();
            } else {
                //TODO This is debug. Don't forget to remove.
               runServerCode();
            }
        }
        Ticker.tick();
    }

    public static long getPower() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        long maxMemory = Runtime.getRuntime().freeMemory();
        return availableProcessors * maxMemory;
    }

    public static void runServerCode() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        NOutput.log(dtf.format(now));
        String ip = "";
        try {
            ip = NetPropertyGetter.getPublicIpAddress();
            NOutput.info("Your IP address is : " + ip);
        } catch (IOException e) {
            NOutput.log("Could not get your IP address. Terminating!");
            NOutput.log("Process terminated. Exit code 5");
            Runtime.getRuntime().exit(5);
        }
        if (!ip.equals("")) {
            long netStrength = NetPropertyGetter.sendPingRequest(
                    "youtube.com", 9999);
            PCProperties properties = new PCProperties(getPower(), netStrength, ip);
            PC newServer = new PC(properties);
            newServer.waitInRoom(serverRoom);
        }
    }
}
