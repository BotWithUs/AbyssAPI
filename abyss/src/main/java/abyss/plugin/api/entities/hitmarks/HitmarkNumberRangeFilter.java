package abyss.plugin.api.entities.hitmarks;

import abyss.plugin.api.Hitmark;
import abyss.plugin.api.entities.Identifiable;
import abyss.plugin.api.entities.Locatable;

import java.util.List;
import java.util.function.Predicate;

public class HitmarkNumberRangeFilter<T extends Identifiable & Hitmarks & Locatable> implements Predicate<T> {
    private final int min;
    private final int max;

    private final HitmarkType type;

    public HitmarkNumberRangeFilter(HitmarkType type, int min, int max) {
        this.type = type;
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean test(T entity) {
        int number = HitmarkManager.MANAGER.getTotalHitFor(entity.getIdentifier(), type);
        return number >= this.min && number <= this.max;
    }
}
