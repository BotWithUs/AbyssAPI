package abyss.plugin.api.query.results;

import java.util.*;
import java.util.stream.Stream;

public interface ResultSet<T> extends Iterable<T> {

    Random random = new Random();

    Optional<T> first();
    Optional<T> last();

    List<T> sort(Comparator<T> comparator);

    Optional<T> get(int index);

    Optional<T> random();

    Stream<T> stream();

}