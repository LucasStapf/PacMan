package com.pacman.graphicinterface;

import com.pacman.systemelements.Arena;
import com.pacman.systemelements.Floor;
import com.pacman.systemelements.SceneElement;
import com.pacman.systemelements.Wall;

public class Print {

    public static void printArena(Arena arena){

        for (int i = 0; i < arena.getArena().size(); i++) {

            for (int j = 0; j < arena.getArena().get(i).size(); j++) {

                if (arena.getArena().get(i).get(j) == null) {
                    System.out.println(":");
                    continue;
                }

                SceneElement se = arena.getArena().get(i).get(j).getLast();

                if (arena.getArena().get(i).get(j).getLast() instanceof Wall) {

                    switch (((Wall) se).orientation) {

                        case HORIZONTAL:
                            System.out.print('-');
                            break;

                        case VERTICAL:
                            System.out.print('|');
                            break;

                        case CORNER:
                            System.out.print('+');
                            break;
                    }
                } else if (arena.getArena().get(i).get(j).getLast() instanceof Floor) {
                    if (((Floor) se).highlighted) System.out.print('@');
                    else System.out.print(' ');
                }
            }

            System.out.println();
        }
    }
}
