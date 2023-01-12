package net.ninjaworks.microservices.jobs;

@SuppressWarnings("all")
public class JobProperties {

    public final int pyramidLevel;
    public final long timeNeeded;
    public final long requiredPower;
    public final long requiredNetStrength;

    public JobProperties(int pyramidLevel, long timeNeeded, long requiredPower,
                         long requiredNetStrength) {
        this.pyramidLevel = pyramidLevel;
        this.timeNeeded = timeNeeded;
        this.requiredPower = requiredPower;
        this.requiredNetStrength = requiredNetStrength;
    }
}
