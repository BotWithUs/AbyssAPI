package abyss.plugin.api.query.results;

import abyss.plugin.api.Component;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ComponentResultSet implements ResultSet<Component> {
    private final List<Component> results;

    public ComponentResultSet(List<Component> results) {
        this.results = results;
    }

    @Override
    public Optional<Component> first() {
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Optional<Component> last() {
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(results.size() - 1));
    }

    @Override
    public List<Component> sort(Comparator<Component> comparator) {
        return stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public Optional<Component> get(int index) {
        if (results.isEmpty() || index < 0 || index >= results.size()) {
            return Optional.empty();
        }
        return Optional.of(results.get(index));
    }

    @Override
    public Optional<Component> random() {
        if(results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(random.nextInt(0, results.size())));
    }

    @Override
    public Stream<Component> stream() {
        return results.stream();
    }

    @NotNull
    @Override
    public Iterator<Component> iterator() {
        return results.iterator();
    }
}