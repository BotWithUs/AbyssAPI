package abyss.plugin.api.entities.hitmarks;


public enum HitmarkTypes implements HitmarkType {
    MELEE(132),
    STUNNED(133),
    RANGE(135),
    MAGIC(138),
    BLOCK(141);

    private final int id;

    HitmarkTypes(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.name();
    }

    public static HitmarkTypes forId(int id) {
        for (HitmarkTypes value : values()) {
            if(value.id == id) {
                return value;
            }
        }
        return null;
    }
}
