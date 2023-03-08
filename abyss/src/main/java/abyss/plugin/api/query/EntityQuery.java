package abyss.plugin.api.query;

import java.util.regex.Pattern;

public interface EntityQuery<T extends EntityQuery<T>> extends IdentityQuery<T> {

    T names(String... names);

    T names(Pattern pattern);

    /**
     * Ensure that string comparisons towards entities are equal and not comtains
     * @param strict Whether we should compare strings as equals or contains
     * @return The EntityQuery
     */

    T strict(boolean strict);

}