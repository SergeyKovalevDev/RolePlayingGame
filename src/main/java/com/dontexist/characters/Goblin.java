package com.dontexist.characters;

public class Goblin extends Enemy{
    private static final int INITIAL_GOBLIN_DEXTERITY = 50;
    private static final int INITIAL_GOBLIN_HEALTH = 100;
    private static final int INITIAL_GOBLIN_EXPERIENCE = 80;
    private static final int INITIAL_GOBLIN_GOLD = 20;
    private static final int INITIAL_GOBLIN_STRENGTH = 100;

    public Goblin() {
        super("Гоблин", INITIAL_GOBLIN_DEXTERITY, INITIAL_GOBLIN_HEALTH,
                INITIAL_GOBLIN_EXPERIENCE, INITIAL_GOBLIN_GOLD, INITIAL_GOBLIN_STRENGTH);
    }

    @Override
    // TODO Убрать повторяющийся код
    public boolean attack(Character opponent) {
        if (dexterity * 3.0 > (Math.random() * 100)) opponent.health -= strength;
        return opponent.health <= 0;
    }
}
