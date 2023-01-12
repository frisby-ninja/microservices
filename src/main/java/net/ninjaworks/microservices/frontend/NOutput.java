package net.ninjaworks.microservices.frontend;

public class NOutput {

    public static boolean showInfo;

    //I know that there is a built-in logger, but I don't like it. So I made my own.

    public static void log(Object line) {
        System.out.println(line.toString());
        NLogger.log(line.toString());
    }

    public static void info(Object line) {
        System.out.println("[INFO]->" + line.toString());
        NLogger.log("[INFO]->" + line.toString());
    }
}
