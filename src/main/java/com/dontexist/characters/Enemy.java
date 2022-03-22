package com.dontexist.characters;

public class Enemy extends FairytaleCharacter implements Attacker {

    public Enemy(String name, int dexterity, int health, int experience, int gold, int strength) {
        super(name, dexterity, health, experience, gold, strength);
    }

    @Override
    public boolean attack(FairytaleCharacter opponent) {
        if (this.getDexterity() * 3.0 > (Math.random() * 100)) {
            opponent.setHealth(Math.max((opponent.getHealth() - this.getStrength()), 0));
        }
        return opponent.getHealth() <= 0;
    }
}
