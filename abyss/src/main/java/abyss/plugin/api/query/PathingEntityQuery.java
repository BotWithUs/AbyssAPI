package abyss.plugin.api.query;

public interface PathingEntityQuery<T extends EntityQuery<T>> extends EntityQuery<T> {

    T health(int value, int deviation);

    T serverIndex(int index);

    T options(String... options);

    T interactIndex(int index);

    T activeHeadBar(int headbarId, float mineValue, float maxValue);

    T hitmark(int hitmarkId, int min, int max);
}