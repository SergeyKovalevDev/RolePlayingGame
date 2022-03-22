package com.dontexist.characters;

public class Skeleton extends Enemy {
    private static final int INITIAL_DEXTERITY = 20;
    private static final int INITIAL_HEALTH = 25;
    private static final int INITIAL_EXPERIENCE = 10;
    private static final int INITIAL_GOLD = 10;
    private static final int INITIAL_STRENGTH = 20;

    public Skeleton() {
        super("Скелетон", INITIAL_DEXTERITY, INITIAL_HEALTH,
                INITIAL_EXPERIENCE, INITIAL_GOLD, INITIAL_STRENGTH);
    }
}
