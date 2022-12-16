package abyss.plugin.api;

import abyss.plugin.api.game.GameEngine;

public class Hitmark {
    private int id;
    private int damage;
    private int startCycle;

    private Hitmark() {
    }

    public int getId() {
        return id;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isVisible() {
        //TODO locate the endCycle memory location and verify the current engine cycle is also below the end cycle.
        long cycle = GameEngine.getEngineTime();
        return cycle >= startCycle;
    }
}
