package abyss.plugin.api.query.results;

import abyss.plugin.api.Hitmark;
import abyss.plugin.api.entities.Identifiable;
import abyss.plugin.api.entities.Locatable;
import abyss.plugin.api.entities.hitmarks.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class PathingEntityResultSet<T extends Hitmarks & Identifiable & Locatable> extends EntityResultSet<T> {
    public PathingEntityResultSet(List<T> results) {
        super(results);
    }

    public List<T> getDealtWithin(HitmarkType type, int min, int max) {
        return stream()
                .filter(e -> HitmarkManager.MANAGER.hasHitmarks(e.getIdentifier(), type))
                .filter(new HitmarkNumberRangeFilter<>(type, min, max))
                .collect(Collectors.toList());
    }

    public Optional<T> getMinDealt(HitmarkType type) {
        return stream()
                .filter(e -> HitmarkManager.MANAGER.hasHitmarks(e.getIdentifier(), type))
                .min(new HitmarkComparator<>(type));
    }

    public Optional<T> getMaxDealt(HitmarkType type) {
        return stream()
                .filter(e -> HitmarkManager.MANAGER.hasHitmarks(e.getIdentifier(), type))
                .max(new HitmarkComparator<>(type));
    }

    public PathingEntityResultSet<T> rememberHitmarks(HitmarkType... types) {
        stream().forEach(e -> {
            if (types.length == 0) {
                for (Hitmark hitmark : e.getHitmarks()) {
                    HitmarkType type = HitmarkType.forId(hitmark.getId());
                    if (type != null) {
                        HitmarkManager.MANAGER.addHit(e.getIdentifier(), type, hitmark.getNumber());
                    }
                }
            } else {
                for (HitmarkType type : types) {
                    for (Hitmark hitmark : e.getHitmarks(type.getId())) {
                        HitmarkManager.MANAGER.addHit(e.getIdentifier(), type, hitmark.getNumber());
                    }
                }
            }
        });
        return this;
    }

}
