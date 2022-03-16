package com.dontexist.characters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Character {
    final String name;
    int dexterity;
    int health;
    int experience;
    int gold;
    int strength;

    public Character(String name, int dexterity, int health, int experience, int gold, int strength) {
        this.name = name;
        this.dexterity = dexterity;
        this.health = health;
        this.experience = experience;
        this.gold = gold;
        this.strength = strength;
    }
}
