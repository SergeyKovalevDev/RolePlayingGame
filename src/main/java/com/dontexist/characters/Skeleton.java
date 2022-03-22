package com.dontexist.characters;

public class Skeleton extends Enemy{
    private static final int INITIAL_SKELETON_DEXTERITY = 20;
    private static final int INITIAL_SKELETON_HEALTH = 25;
    private static final int INITIAL_SKELETON_EXPERIENCE = 10;
    private static final int INITIAL_SKELETON_GOLD = 10;
    private static final int INITIAL_SKELETON_STRENGTH = 20;

    public Skeleton() {
        super("Скелетон", INITIAL_SKELETON_DEXTERITY, INITIAL_SKELETON_HEALTH,
                INITIAL_SKELETON_EXPERIENCE, INITIAL_SKELETON_GOLD, INITIAL_SKELETON_STRENGTH);
    }
}
