package com.dontexist.characters;

import com.dontexist.interfaces.Seller;

public class Merchant extends Character implements Seller {

    public Merchant() {
        super("Merchant", 15, 100, 30, 100, 20);
    }

    @Override
    public void selling() {

    }
}
