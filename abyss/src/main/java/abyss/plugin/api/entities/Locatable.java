package abyss.plugin.api.entities;

import abyss.plugin.api.Vector3i;

@FunctionalInterface
public interface Locatable {

    Vector3i getGlobalPosition();

}