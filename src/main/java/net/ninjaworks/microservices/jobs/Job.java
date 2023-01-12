package net.ninjaworks.microservices.jobs;

public class Job {

    JobProperties properties;

    public Job(int pyramidLevel, long timeNeeded, long requiredPower,
               long requiredNetStrength) {
        properties = new JobProperties(pyramidLevel, timeNeeded, requiredPower,
                requiredNetStrength);
    }

    public JobProperties getProperties() {
        return this.properties;
    }
}
