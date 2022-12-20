package abyss.plugin.api.entities;

public class Headbar {

    private final int id;
    private final int value;

    public Headbar(int id, int value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }
}
