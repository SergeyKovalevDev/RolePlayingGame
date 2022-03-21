package com.dontexist.characters;

import com.dontexist.potions.Potion;

public class Hero extends FairytaleCharacter {
    private static final int INITIAL_HERO_DEXTERITY = 20;
    private static final int INITIAL_HERO_HEALTH = 100;
    private static final int INITIAL_HERO_EXPERIENCE = 0;
    private static final int INITIAL_HERO_GOLD = 0;
    private static final int INITIAL_HERO_STRENGTH = 20;

    public Hero(String name) {
        super(name, INITIAL_HERO_DEXTERITY, INITIAL_HERO_HEALTH,
                INITIAL_HERO_EXPERIENCE, INITIAL_HERO_GOLD, INITIAL_HERO_STRENGTH);
    }

    public void purchasePotion(Potion potion) {
        gold = gold - potion.getCost();
        potion.application(this, potion);
    }
}
