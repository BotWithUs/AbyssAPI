package abyss.plugin.api.query;

public interface SpiritQuery <T extends EntityQuery<T>> extends EntityQuery<T> {

    T health(int value);

    T serverIndex(int index);

    T interactIndex(int index);

    T activeStatusBar(float mineValue, float maxValue);

    T activeStatusBar(int barId);

}