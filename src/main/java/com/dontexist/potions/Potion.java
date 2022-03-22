package com.dontexist.potions;

import com.dontexist.characters.FairytaleCharacter;
import lombok.Getter;

import java.util.function.BiConsumer;

@Getter
public class Potion {
    private final String printedName;
    private final int effect;
    private final int cost;
    private final BiConsumer<FairytaleCharacter, Potion> effectImplementation;

    public Potion(String printedName, int effect, int cost, BiConsumer<FairytaleCharacter, Potion> effectImplementation) {
        this.printedName = printedName;
        this.effect = effect;
        this.cost = cost;
        this.effectImplementation = effectImplementation;
    }

    public void application(FairytaleCharacter character, Potion potion) {
        effectImplementation.accept(character, potion);
    }
}
