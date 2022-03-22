package com.dontexist.characters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class FairytaleCharacter {
    private static final int DEXTERITY_INC = 5;
    private final String name;
    private int dexterity;
    private int health;
    private int experience;
    int gold;
    private int strength;

    public FairytaleCharacter(String name, int dexterity, int health, int experience, int gold, int strength) {
        this.name = name;
        this.dexterity = dexterity;
        this.health = health;
        this.experience = experience;
        this.gold = gold;
        this.strength = strength;
    }

    public boolean attack(FairytaleCharacter enemy) {
        if (this.getDexterity() * 3.0 > (Math.random() * 100)) {
            enemy.setHealth(Math.max((enemy.getHealth() - this.getStrength()), 0));
        }
        return enemy.getHealth() <= 0;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public boolean addExperience(int experience) {
//        if ((this.experience + experience) / 100 > this.experience / 100) dexterity += DEXTERITY_INC;
//        this.experience += experience;
        this.experience += experience;
        if(this.experience / 100 > (this.experience - experience) / 100) {
            dexterity += DEXTERITY_INC;
            return true;
        }
        else return false;
    }

    public void addHealth(int health) {
        this.health = Math.min(this.health + health, 100);
    }

    public void addDexterity(int dexterity) {
        this.dexterity += dexterity;
    }

    public void addStrength(int strength) {
        this.strength += strength;
    }

    @Override
    public String toString() {
        return String.format("""
                Здоровье - %d
                Ловкость - %d
                Опыт - %d
                Сила - %d
                Золото - %d""",
                health,
                dexterity,
                experience,
                strength,
                gold);
    }
}
