package com.dontexist.potions;

import com.dontexist.characters.Hero;
import lombok.Getter;

import java.util.function.BiConsumer;

@Getter
public class Potion {
    private final String printedName;
    private final int effect;
    private final int cost;
    private final BiConsumer<Hero, Potion> implementation;

    public Potion(String printedName, int effect, int cost, BiConsumer<Hero, Potion> implementation) {
        this.printedName = printedName;
        this.effect = effect;
        this.cost = cost;
        this.implementation = implementation;
    }

    public void application(Hero hero, Potion potion) {
        implementation.accept(hero, potion);
    }
}
