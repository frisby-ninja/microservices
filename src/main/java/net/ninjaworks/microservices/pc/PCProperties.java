package net.ninjaworks.microservices.pc;

import net.ninjaworks.api.ArrayManipulator;

public class PCProperties {

    public final long power;
    public final long netStrength;
    public final String UID;
    public final String IP;

    public PCProperties(long power, long netStrength, String IP) {
        this.power = power;
        this.netStrength = netStrength;
        this.IP = IP;
        String[] IPSeed = IP.split("\\.");
        this.UID = genUID(IPSeed, power + netStrength);
    }

    private static String genUID(String[] IPSeed, long rndMultiplier) {
        String[] UIDBuilder = new String[IPSeed.length];
        for(int i = 0; i < IPSeed.length - 1; i++) {
            UIDBuilder[i] = Long.toString(Integer.parseInt(IPSeed[i + 1]) * rndMultiplier);
        }
        return ArrayManipulator.arrayToString(UIDBuilder);
    }
}
