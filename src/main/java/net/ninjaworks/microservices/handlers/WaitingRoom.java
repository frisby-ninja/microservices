package net.ninjaworks.microservices.handlers;

import net.ninjaworks.api.ArrayManipulator;
import net.ninjaworks.microservices.frontend.NOutput;
import net.ninjaworks.microservices.jobs.JobGiver;
import net.ninjaworks.microservices.pc.PC;

public class WaitingRoom {

    PC[] waitingPCs;
    int maxPCs;

    public WaitingRoom(int maxPCs) {
        this.maxPCs = maxPCs;
    }

    public WaitingRoom() {
        this.maxPCs = -1;
    }

    public void join(PC pc) {
        if(this.waitingPCs.length <= maxPCs) {
            this.waitingPCs = ArrayManipulator.addElement(waitingPCs, pc);
            JobGiver.addConnectedPC(pc);
            JobGiver.registerPC(pc);
        } else {
            NOutput.log("Waiting room currently full. Trying again soon.");
            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {
                NOutput.log("Queue time to waiting interrupted.");
            }
            join(pc);
        }
    }
}
