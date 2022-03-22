package com.dontexist.characters;

import com.dontexist.potions.Potion;

public class Merchant extends FairytaleCharacter implements Seller {
    private static final int INITIAL_DEXTERITY = 15;
    private static final int INITIAL_HEALTH = 100;
    private static final int INITIAL_EXPERIENCE = 0;
    private static final int INITIAL_GOLD = 0;
    private static final int INITIAL_STRENGTH = 20;

    public Merchant() {
        super("Торговец", INITIAL_DEXTERITY, INITIAL_HEALTH,
                INITIAL_EXPERIENCE, INITIAL_GOLD, INITIAL_STRENGTH);
    }

    @Override
    public void sellPotion(Potion potion) {
        gold += potion.getCost();
    }
}
