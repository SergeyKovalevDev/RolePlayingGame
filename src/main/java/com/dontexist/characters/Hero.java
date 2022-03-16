package com.dontexist.characters;

import com.dontexist.interfaces.Attack;
import com.dontexist.interfaces.Customer;

public class Hero extends Character implements Customer, Attack {
    private static final int INITIAL_HERO_DEXTERITY = 80;
    private static final int INITIAL_HERO_HEALTH = 100;
    private static final int INITIAL_HERO_EXPERIENCE = 50;
    private static final int INITIAL_HERO_GOLD = 20;
    private static final int INITIAL_HERO_STRENGTH = 100;

    public Hero(String name) {
        super(name, INITIAL_HERO_DEXTERITY, INITIAL_HERO_HEALTH,
                INITIAL_HERO_EXPERIENCE, INITIAL_HERO_GOLD, INITIAL_HERO_STRENGTH);
    }

    @Override
    // TODO Убрать повторяющийся код
    public boolean attack(Character opponent) {
        if (dexterity * 3.0 > (Math.random() * 100)) opponent.health -= strength;
        return opponent.health <= 0;
    }

    @Override
    public void buying() {

    }
}
