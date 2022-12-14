package abyss.plugin.api.query;

import java.util.regex.Pattern;

public interface EntityQuery<T extends EntityQuery<T>> extends IdentityQuery<T> {

    T names(String... names);

    T names(Pattern pattern);

}