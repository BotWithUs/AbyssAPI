package abyss.plugin.api.query.results;

import abyss.plugin.api.entities.ProjectileAnimation;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ProjectileResultSet implements ResultSet<ProjectileAnimation> {

    private List<ProjectileAnimation> projectileAnimations;

    public ProjectileResultSet(List<ProjectileAnimation> projectileAnimations) {
        this.projectileAnimations = projectileAnimations;
    }

    @Override
    public Optional<ProjectileAnimation> first() {
        if(projectileAnimations.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(projectileAnimations.get(0));
    }

    @Override
    public Optional<ProjectileAnimation> last() {
        if(projectileAnimations.isEmpty()) {
            return Optional.empty();
        }
        int index = projectileAnimations.size() - 1;
        return Optional.of(projectileAnimations.get(index));
    }

    @Override
    public List<ProjectileAnimation> sort(Comparator<ProjectileAnimation> comparator) {
        return stream().sorted(comparator).collect(Collectors.toList());
    }

    @Override
    public Optional<ProjectileAnimation> get(int index) {
        if(projectileAnimations.isEmpty()) {
            return Optional.empty();
        }
        int size = projectileAnimations.size();
        if(index >= size) {
            return Optional.empty();
        }
        if(index < 0) {
            return Optional.empty();
        }
        return Optional.of(projectileAnimations.get(index));
    }

    @Override
    public Optional<ProjectileAnimation> random() {
        if(projectileAnimations.isEmpty()) {
            return Optional.empty();
        }
        int size = projectileAnimations.size();
        int index = random.nextInt(0, size - 1);
        return Optional.of(projectileAnimations.get(index));
    }

    @Override
    public Stream<ProjectileAnimation> stream() {
        return projectileAnimations.stream();
    }

    @NotNull
    @Override
    public Iterator<ProjectileAnimation> iterator() {
        return projectileAnimations.iterator();
    }
}
