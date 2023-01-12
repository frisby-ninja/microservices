package net.ninjaworks.microservices.handlers;

import net.ninjaworks.microservices.frontend.NOutput;
import net.ninjaworks.microservices.gui.AdminGUI;
import net.ninjaworks.microservices.gui.builders.MainScreenBuilder;

public class Ticker {

    public static void tick() {
        onTick();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            NOutput.log("Thread sleep for tick interrupted.");
        }
        tick();
    }

    public static void onTick() {
        AdminGUI.refreshMainScreen();
    }
}
