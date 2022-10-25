package abyss.plugin.api.query;

public interface SpiritQuery <T extends EntityQuery<T>> extends EntityQuery<T> {

    T health(int value, int deviation);

    T serverIndex(int index);

    T options(String... options);

    T interactIndex(int index);

    T activeStatusBar(float mineValue, float maxValue);

    T activeStatusBar(int barId);

}