package com.dontexist.characters;

public class Skeleton extends Enemy{
    private static final int INITIAL_SKELETON_DEXTERITY = 50;
    private static final int INITIAL_SKELETON_HEALTH = 100;
    private static final int INITIAL_SKELETON_EXPERIENCE = 80;
    private static final int INITIAL_SKELETON_GOLD = 20;
    private static final int INITIAL_SKELETON_STRENGTH = 100;

    public Skeleton() {
        super("Скелетон", INITIAL_SKELETON_DEXTERITY, INITIAL_SKELETON_HEALTH,
                INITIAL_SKELETON_EXPERIENCE, INITIAL_SKELETON_GOLD, INITIAL_SKELETON_STRENGTH);
    }

    @Override
    // TODO Убрать повторяющийся код
    public boolean attack(Character opponent) {
        if (dexterity * 3.0 > (Math.random() * 100)) opponent.health -= strength;
        return opponent.health <= 0;
    }
}
