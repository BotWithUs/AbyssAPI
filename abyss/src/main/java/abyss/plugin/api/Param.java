package abyss.plugin.api;

public class Param {
    private int id;
    private int type;
    private int intValue;
    private String stringValue;

    private Param() {

    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public int getIntValue() {
        return type == 1002 ? intValue : -1;
    }

    public String getStringValue() {
        return type == 1003 ? stringValue : "";
    }
}