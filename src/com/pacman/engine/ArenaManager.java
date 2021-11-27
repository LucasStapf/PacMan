package com.pacman.engine;

import com.pacman.systemelements.Arena;

public class ArenaManager {

    private Arena arena;

    public ArenaManager(Arena arena) {
        this.arena = arena;
    }

    public Arena getArena() {
        return arena;
    }
}
