package net.ninjaworks.microservices.jobs;

import net.ninjaworks.api.ArrayManipulator;
import net.ninjaworks.microservices.pc.PC;

import java.util.*;

public class JobGiver {
    private static Map<Job, Integer> pendingJobs = new HashMap<Job, Integer>();
    private static Map<PC, Job[]> PCJobs = new HashMap<PC, Job[]>();
    private static PC[] availablePCs;
    private static PC[] allConnectedPCs;

    public void tick() {
        if(!pendingJobs.isEmpty()) {
            List<Job> indexes = new ArrayList<Job>(pendingJobs.keySet());

        }
    }

    public static PC[] getAllConnectedPCs() {
        if(allConnectedPCs == null) {
            return new PC[0];
        } else {
            return allConnectedPCs;
        }
    }

    public static void addConnectedPC(PC pc) {
        allConnectedPCs = ArrayManipulator.addElement(allConnectedPCs, pc);
    }

    public static void addJob(Job job, int neededPCS) {
        if(pendingJobs.containsKey(job)) {
            pendingJobs.replace(job, neededPCS);
        } else {
            pendingJobs.put(job, neededPCS);
        }
    }

    public static void registerPC(PC pc) {
        availablePCs = ArrayManipulator.addElement(availablePCs, pc);
    }

    public static Job[] getPCJobs(PC pc) {
        return PCJobs.get(pc);
    }

    public static Map<Job, Integer> getPendingJobs() {
        return pendingJobs;
    }
}
