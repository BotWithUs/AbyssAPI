package abyss.plugin.api.queries;

public interface PathingEntityQuery<T extends EntityQuery<T>> extends EntityQuery<T> {

    T health(int value, int deviation);

    T serverIndex(int index);

    T options(String... options);

    T interactIndex(int index);

    T activeHeadBar(float mineValue, float maxValue);

    T activeHeadBar(int barId);

}