package abyss.plugin.api.query.results;

import abyss.plugin.api.Hitmark;
import abyss.plugin.api.entities.Identifiable;
import abyss.plugin.api.entities.Locatable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class PathingEntityResultSet<T extends Identifiable & Locatable> extends EntityResultSet<T> {
    public PathingEntityResultSet(List<T> results) {
        super(results);
    }
    //TODO add hitmark and headbar queries.
}
