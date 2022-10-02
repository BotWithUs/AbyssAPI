package abyss.plugin.api;

public class VarpRequest {

    private int varpId;
    private int value;

    public VarpRequest(int varpId, int value) {
        this.varpId = varpId;
        this.value = value;
    }

    public int getVarpId() {
        return varpId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
