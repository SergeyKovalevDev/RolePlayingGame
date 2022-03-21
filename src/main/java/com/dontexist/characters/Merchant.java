package com.dontexist.characters;

import com.dontexist.potions.Potion;
import lombok.Getter;

@Getter
public class Merchant extends FairytaleCharacter {


    public Merchant() {
        super("Торговец", 15, 100, 30, 100, 20);
    }

    public void sellPotion(Potion potion) {
        gold += potion.getCost();
    }
}
