package com.dontexist.gameworld;

import com.dontexist.characters.*;
import com.dontexist.potions.Potion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameWorld {
    private final Hero hero;
    private final Merchant merchant;
    private Goblin goblin;
    private Skeleton skeleton;

    Potion healthPotion = new Potion("зелье здоровья", 10, 20,
            (hero, potion) -> hero.setHealth(hero.getHealth() + potion.getEffect()));
    Potion dexterityPotion = new Potion("зелье ловкости", 30, 100,
            (hero, potion) -> hero.setDexterity(hero.getDexterity() + potion.getEffect()));
    Potion strengthPotion = new Potion("зелье силы", 20, 50,
            (hero, potion) -> hero.setStrength(hero.getStrength() + potion.getEffect()));

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
                        Золото: %d
                        Опыт: %d
                        Здоровье: %d%n""", hero.getGold(), hero.getExperience(), hero.getHealth());
            }
        });
        battleThread.start();
        // Непонятно зачем в задании требуется запустить бой в отдельном потоке
        // Все равно приходится дожидаться окончания боя
        try {
            battleThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hero.getHealth() > 0;
    }

    public void potionTrading(Potion potion) {
        if (hero.getGold() >= potion.getCost()) {
            merchant.sellPotion(potion);
            hero.purchasePotion(potion);
            System.out.println("Вы приобрели " + potion.getPrintedName());
            System.out.println(hero);
            System.out.println("Какое зелье желаете проибрести еще?");
        } else {
            System.out.println("Увы, у вас недостаточно золота");
        }
    }
}
