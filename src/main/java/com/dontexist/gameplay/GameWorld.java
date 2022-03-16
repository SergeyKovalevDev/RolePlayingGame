package com.dontexist.gameplay;

import com.dontexist.characters.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameWorld {
    private final Hero hero;
    private final Merchant merchant;
    private Goblin goblin;
    private Skeleton skeleton;

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
                isExit = isHeroAttackFirst[0] ? hero.attack(enemy) : enemy.attack(hero);

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
                        Ваше золото: %d
                        Ваш опыт: %d%n""", hero.getGold(), hero.getExperience());
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
