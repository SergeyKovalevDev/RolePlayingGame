package com.dontexist.gameworld;

import com.dontexist.characters.*;
import com.dontexist.characters.FairytaleCharacter;
import lombok.Getter;
import lombok.Setter;

import java.util.function.BiPredicate;

@Getter
@Setter
public class GameWorld {
    private final Hero hero;
    private final Merchant merchant;
    private Goblin goblin;
    private Skeleton skeleton;

    BiPredicate<FairytaleCharacter, FairytaleCharacter> attackImplementation = (ch1, ch2) -> {
        if (ch1.getDexterity() * 3.0 > (Math.random() * 100)) ch2.setHealth(ch2.getHealth() - ch1.getStrength());
        return ch2.getHealth() <= 0;
    };

    public GameWorld(Hero hero) {
        this.hero = hero;
        this.merchant = new Merchant();
        this.goblin = new Goblin();
        this.skeleton = new Skeleton();
    }

    public boolean battle(Enemy enemy) {
        System.out.println("Начнем бой!");
        System.out.println(hero.getName() + " здоровье - " + hero.getHealth());
        System.out.println(enemy.getName() + " здоровье - " + enemy.getHealth());
        final boolean[] isHeroAttackFirst = {Math.random() >= 0.5};
        Thread battleThread = new Thread(() -> {
            boolean isExit = false;
            while (!isExit) {
                System.out.println("Атакует " + (isHeroAttackFirst[0] ? hero.getName() : enemy.getName()));
                isExit = isHeroAttackFirst[0] ? hero.attack(attackImplementation, enemy) : enemy.attack(attackImplementation, hero);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(hero.getName() + " здоровье - " + hero.getHealth());
                System.out.println(enemy.getName() + " здоровье - " + enemy.getHealth());
                isHeroAttackFirst[0] = !isHeroAttackFirst[0];
            }

            System.out.println("Бой окончен");
            if (hero.getHealth() <= 0) System.out.println("Увы, Вы проиграли");
            else {
                hero.setGold(hero.getGold() + enemy.getGold());
                hero.setExperience(hero.getExperience() + enemy.getExperience());
                System.out.printf("""
                        Поздравляем! Вы победили!
                        Вы забираете золото и получаете опыт
                        Золото: %d
                        Опыт: %d
                        Здоровье: %d%n""", hero.getGold(), hero.getExperience(), hero.getHealth());
            }
        });
        battleThread.start();
        try {
            battleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hero.getHealth() > 0;
    }

    public void trading() {
        System.out.println("Торговец еще не вышел на работу");
    }
}