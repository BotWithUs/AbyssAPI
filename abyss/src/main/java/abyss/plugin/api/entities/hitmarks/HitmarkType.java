package abyss.plugin.api.entities.hitmarks;


public enum HitmarkType {
    REGULAR(0),
    CRITICAL(1),
    BLOCK(2),
    ABSORB(3),
    MISS(4);

    private final int id;

    HitmarkType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static HitmarkType forId(int id) {
        for (HitmarkType value : values()) {
            if(value.id == id) {
                return value;
            }
        }
        return null;
    }
}
