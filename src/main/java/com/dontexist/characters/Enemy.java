package com.dontexist.characters;

import com.dontexist.interfaces.Attack;

public abstract class Enemy extends Character implements Attack {

    public Enemy(String name, int dexterity, int health, int experience, int gold, int strength) {
        super(name, dexterity, health, experience, gold, strength);
    }
}
