package abyss.plugin.api;

public class Headbar {
    private int id;
    //TODO should be currentValue and we should have a maxValue field.
    private int value;

    private Headbar() {
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }
}
