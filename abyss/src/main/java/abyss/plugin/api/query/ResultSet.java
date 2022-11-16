package abyss.plugin.api.query;

import abyss.plugin.api.Player;
import abyss.plugin.api.Vector3i;
import abyss.plugin.api.entities.Locatable;
import abyss.plugin.api.query.players.PlayerQuery;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultSet<T extends Locatable> implements Iterable<T> {

    private final List<T> results;

    private ResultSet() {
        results = new ArrayList<>();
    }

    public T nearest() {
        Player player = PlayerQuery.self();
        if(player == null) {
            return null;
        }
        List<T> results = sortedByDistance(player.getGlobalPosition());
        if(results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    public T furthest() {
        Player player = PlayerQuery.self();
        if(player == null) {
            return null;
        }
        List<T> results = sortedByDistance(player.getGlobalPosition());
        if(results.isEmpty()) {
            return null;
        }
        return results.get(results.size() - 1);
    }

    public T firstOrNull() {
        return results.isEmpty() ? null : results.get(0);
    }

    public T lastOrNull() {
        if(results.isEmpty()) {
            return null;
        }
        return results.get(results.size() - 1);
    }

    public List<T> sort(Comparator<T> comparator) {
        return stream().sorted(comparator).collect(Collectors.toList());
    }

    public List<T> sortedByDistance(Vector3i from) {
        return stream()
        .sorted(Comparator.comparingInt(o -> o.getGlobalPosition().distance(from)))
        .collect(Collectors.toList());
    }

    public T get(int index) {
        if(results.isEmpty()) {
            return null;
        }
        if(index >= results.size()) {
            return null;
        }
        return results.get(index);
    }

    public Stream<T> stream() {
        return results.stream();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return results.iterator();
    }
}