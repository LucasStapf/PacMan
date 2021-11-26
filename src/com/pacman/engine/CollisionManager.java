package com.pacman.engine;

import com.pacman.systemelements.Arena;
import com.pacman.systemelements.GameObject;

import java.util.LinkedList;

public class CollisionManager {

    private Arena arena;
    private LinkedList<GameObject> dynamicObjects, staticObjects;

    public CollisionManager(Arena arena) throws NullPointerException {

        if (arena == null) throw new NullPointerException("Arena cant be null!");
        else this.arena = arena;

        dynamicObjects = new LinkedList<>();
        staticObjects = new LinkedList<>();
    }

    private void updateDynamicObjects() {

    }
}
