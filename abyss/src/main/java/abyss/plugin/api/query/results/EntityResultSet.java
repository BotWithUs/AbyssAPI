package abyss.plugin.api.query.results;

import abyss.plugin.api.Player;
import abyss.plugin.api.Vector3i;
import abyss.plugin.api.entities.Locatable;
import abyss.plugin.api.query.players.PlayerQuery;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityResultSet<T extends Locatable> implements ResultSet<T> {

    private final List<T> results;

    public EntityResultSet(List<T> results) {
        this.results = results;
    }

    public Optional<T> nearest() {
        Player player = PlayerQuery.self();
        if (player == null) {
            return Optional.empty();
        }
        return nearestTo(player.getGlobalPosition());
    }

    public Optional<T> nearestTo(Vector3i vector3i) {
        List<T> results = sortedByDistance(vector3i);
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(0));
    }

    public Optional<T> furthest() {
        Player player = PlayerQuery.self();
        if (player == null) {
            return Optional.empty();
        }
        List<T> results = sortedByDistance(player.getGlobalPosition());
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(results.size() - 1));
    }

    @Override
    public Optional<T> first() {
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Optional<T> last() {
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(results.size() - 1));
    }

    @Override
    public List<T> sort(Comparator<T> comparator) {
        return stream().sorted(comparator).collect(Collectors.toList());
    }
    public List<T> sortedByDistance(Vector3i from) {
        return sort(Comparator.comparingInt(o -> o.getGlobalPosition().distance(from)));
    }

    @Override
    public Optional<T> get(int index) {
        if (results.isEmpty()) {
            return Optional.empty();
        }
        if (index >= results.size()) {
            return Optional.empty();
        }
        return Optional.of(results.get(index));
    }

    @Override
    public Optional<T> random() {
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(random.nextInt(0, results.size())));
    }

    @Override
    public Stream<T> stream() {
        return results.stream();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return results.iterator();
    }
}