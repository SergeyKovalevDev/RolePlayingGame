package com.dontexist.characters;

public class Goblin extends Enemy {
    private static final int INITIAL_GOBLIN_DEXTERITY = 10;
    private static final int INITIAL_GOBLIN_HEALTH = 50;
    private static final int INITIAL_GOBLIN_EXPERIENCE = 10;
    private static final int INITIAL_GOBLIN_GOLD = 20;
    private static final int INITIAL_GOBLIN_STRENGTH = 10;

    public Goblin() {
        super("Гоблин", INITIAL_GOBLIN_DEXTERITY, INITIAL_GOBLIN_HEALTH,
                INITIAL_GOBLIN_EXPERIENCE, INITIAL_GOBLIN_GOLD, INITIAL_GOBLIN_STRENGTH);
    }
}
