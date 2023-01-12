package net.ninjaworks.microservices.network;

import net.ninjaworks.microservices.frontend.NOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.logging.Level;

public class NetPropertyGetter {
    public static String getPublicIpAddress() throws IOException {
        URL url = new URL("http://checkip.dyndns.org/");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String withHTML = br.readLine();
        String beforeIP = withHTML.replaceAll("<html><head><title>Current IP Check" +
                "</title></head><body>Current IP Address: ", "");
        return beforeIP.replaceAll("</body></html>", "");
    }

    public static long sendPingRequest(String ipAddress, int timeout) {
        long pingTime = -425;
        try {
            InetAddress inet = InetAddress.getByName(ipAddress);

            NOutput.log("Testing connection speed...");
            NOutput.log("Sending Ping Request to " + ipAddress);

            long finish = 0;
            long start = new GregorianCalendar().getTimeInMillis();

            if (inet.isReachable(timeout)){
                finish = new GregorianCalendar().getTimeInMillis();
                pingTime = finish - start;
                NOutput.info("Ping RTT: " + (pingTime + "ms"));
            } else {
                NOutput.log(ipAddress + " not reachable (timeout).");
            }
        } catch ( Exception e ) {
            NOutput.log("Could not ping " + ipAddress + " : Exception. Terminating!");
            NOutput.log("Process terminated. Exit code 6");
            Runtime.getRuntime().exit(6);
        }
        if(!(pingTime == -425)) {
            return pingTime;
        } else {
            NOutput.log("Unknown error : could not get correct ping time for " +
                    ipAddress + ". Terminating!");
            NOutput.log("Process terminated. Exit code 6");
            Runtime.getRuntime().exit(6);
            return -425;
        }
    }
}
