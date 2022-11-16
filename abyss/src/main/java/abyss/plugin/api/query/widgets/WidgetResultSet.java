package abyss.plugin.api.query.widgets;

import abyss.plugin.api.components.WidgetComponent;
import abyss.plugin.api.query.ResultSet;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class WidgetResultSet implements ResultSet<WidgetComponent> {

    private final List<WidgetComponent> results;

    public WidgetResultSet(List<WidgetComponent> results) {
        this.results = results;
    }

    @Override
    public Optional<WidgetComponent> first() {
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Optional<WidgetComponent> last() {
        if(results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.get(results.size() - 1));
    }

    @Override
    public List<WidgetComponent> sort(Comparator<WidgetComponent> comparator) {
        return stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public Optional<WidgetComponent> get(int index) {
        if(results.isEmpty() || index < 0 || index >= results.size()) {
            return Optional.empty();
        }
        return Optional.of(results.get(index));
    }

    @Override
    public Stream<WidgetComponent> stream() {
        return results.stream();
    }

    @NotNull
    @Override
    public Iterator<WidgetComponent> iterator() {
        return results.iterator();
    }
}