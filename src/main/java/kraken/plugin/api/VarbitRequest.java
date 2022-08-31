package kraken.plugin.api;

public class VarbitRequest {

    private final int varbitID;
    private final int conVarID;
    private int value;

    public VarbitRequest(int varbitID, int conVarID, int value) {
        this.varbitID = varbitID;
        this.conVarID = conVarID;
        this.value = value;
    }

    public int getVarbitID() {
        return varbitID;
    }

    public int getConVarID() {
        return conVarID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
