package abyss.plugin.api.params.impl;

import abyss.plugin.api.params.Struct;

public class Smelting extends Struct {

    private int level;
    private int primaryOre;
    private int secondaryOre;

    public Smelting(int id) {
        super(id);
    }

    @Override
    public void initialize() {
        this.level = readInt(2645);
        Struct primaryOre = readStruct(2675);
        if(primaryOre != null) {
            this.primaryOre = primaryOre.readInt(7763);
        }
        Struct secondaryOre = readStruct(2676);
        if(secondaryOre != null) {
            this.secondaryOre = secondaryOre.readInt(7763);
        }
    }

    public int getLevel() {
        return level;
    }

    public int getPrimaryOre() {
        return primaryOre;
    }

    public int getSecondaryOre() {
        return secondaryOre;
    }
}