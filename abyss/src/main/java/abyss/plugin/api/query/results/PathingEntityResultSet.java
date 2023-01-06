package abyss.plugin.api.query.results;

import abyss.plugin.api.entities.markers.Identifiable;
import abyss.plugin.api.entities.markers.Locatable;

import java.util.List;

public final class PathingEntityResultSet<T extends Identifiable & Locatable> extends EntityResultSet<T> {
    public PathingEntityResultSet(List<T> results) {
        super(results);
    }
    //TODO add hitmark and headbar queries.
}
