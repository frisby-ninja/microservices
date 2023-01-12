package net.ninjaworks.microservices.pc;

import net.ninjaworks.microservices.handlers.WaitingRoom;
import net.ninjaworks.microservices.jobs.Job;

public class PC {

    PCProperties properties;

    public PC(PCProperties properties) {
        this.properties = properties;
    }

    public PCProperties getProperties() {
        return this.properties;
    }

    public boolean canHandleJob(Job job) {
        if(this.getProperties().power >= job.getProperties().requiredPower) {
            return this.getProperties().netStrength >= job.getProperties().requiredNetStrength;
        } else {
            return false;
        }
    }

    public void waitInRoom(WaitingRoom room) {
        room.join(this);
    }
}
