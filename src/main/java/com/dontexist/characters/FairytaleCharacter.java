package com.dontexist.characters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class FairytaleCharacter {
    final String name;
    int dexterity;
    int health;
    int experience;
    int gold;
    int strength;

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
