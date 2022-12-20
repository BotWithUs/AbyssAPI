package abyss.plugin.api.entities.hitmarks;

import abyss.plugin.api.entities.Identifiable;
import abyss.plugin.api.entities.Locatable;

import java.util.Comparator;

public class HitmarkComparator<T extends Hitmarks & Identifiable & Locatable> implements Comparator<T> {

    private HitmarkType type;

    public HitmarkComparator(HitmarkType type) {
        this.type = type;
    }

    @Override
    public int compare(T o1, T o2) {
        int totalForEntityOne = HitmarkManager.MANAGER.getTotalHitFor(o1.getIdentifier(), type);
        int totalForEntityTwo = HitmarkManager.MANAGER.getTotalHitFor(o2.getIdentifier(), type);
        return Integer.compare(totalForEntityOne, totalForEntityTwo);
    }
}
