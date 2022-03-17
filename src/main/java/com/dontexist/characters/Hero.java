package com.dontexist.characters;

import com.dontexist.interfaces.Customer;

import java.util.function.BiPredicate;

public class Hero extends FairytaleCharacter implements Customer {
    private static final int INITIAL_HERO_DEXTERITY = 20;
    private static final int INITIAL_HERO_HEALTH = 100;
    private static final int INITIAL_HERO_EXPERIENCE = 0;
    private static final int INITIAL_HERO_GOLD = 0;
    private static final int INITIAL_HERO_STRENGTH = 20;

    public Hero(String name) {
        super(name, INITIAL_HERO_DEXTERITY, INITIAL_HERO_HEALTH,
                INITIAL_HERO_EXPERIENCE, INITIAL_HERO_GOLD, INITIAL_HERO_STRENGTH);
    }

    @Override
    public void buying() {

    }
}
