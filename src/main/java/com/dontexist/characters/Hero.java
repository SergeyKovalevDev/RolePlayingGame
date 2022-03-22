package com.dontexist.characters;

import com.dontexist.potions.Potion;

public class Hero extends FairytaleCharacter implements Attacker, Buyer{
    private static final int INITIAL_DEXTERITY = 20;
    private static final int INITIAL_HEALTH = 100;
    private static final int INITIAL_EXPERIENCE = 0;
    private static final int INITIAL_GOLD = 0;
    private static final int INITIAL_STRENGTH = 20;

    public Hero(String name) {
        super(name, INITIAL_DEXTERITY, INITIAL_HEALTH,
                INITIAL_EXPERIENCE, INITIAL_GOLD, INITIAL_STRENGTH);
    }

    @Override
    public void purchasePotion(Potion potion) {
        gold = gold - potion.getCost();
        potion.application(this, potion);
    }

    @Override
    public boolean attack(FairytaleCharacter opponent) {
        if (this.getDexterity() * 3.0 > (Math.random() * 100)) {
            opponent.setHealth(Math.max((opponent.getHealth() - this.getStrength()), 0));
        }
        return opponent.getHealth() <= 0;
    }
}
