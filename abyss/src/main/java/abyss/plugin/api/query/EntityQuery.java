package abyss.plugin.api.query;

import abyss.plugin.api.*;
import abyss.plugin.api.world.WorldTile;

import java.util.regex.Pattern;

public interface EntityQuery<T extends EntityQuery<T>> extends IdentityQuery<T> {

    T names(String... names);

    T names(Pattern pattern);

}