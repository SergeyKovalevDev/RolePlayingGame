package com.dontexist.gameworld;

import com.dontexist.characters.*;
import com.dontexist.potions.Potion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameWorld {
    private final Hero hero;
    private final Merchant merchant = new Merchant();
    private Goblin goblin = new Goblin();
    private Skeleton skeleton = new Skeleton();

    private final Potion healthPotion = new Potion("зелье здоровья", 50, 20,
            (hero, potion) -> hero.addHealth(potion.getEffect()));
    private final Potion dexterityPotion = new Potion("зелье ловкости", 5, 100,
            (hero, potion) -> hero.addDexterity(potion.getEffect()));
    private final Potion strengthPotion = new Potion("зелье силы", 5, 50,
            (hero, potion) -> hero.addStrength(potion.getEffect()));

    public GameWorld(Hero hero) {
        this.hero = hero;
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
                hero.addGold(enemy.getGold());
                boolean isDexterityInc = hero.addExperience(enemy.getExperience());
                System.out.printf("""
                        Поздравляем! Вы победили!
                        Вы забираете золото и получаете опыт
                        Золото: %d
                        Опыт: %d
                        Здоровье: %d%n""", hero.getGold(), hero.getExperience(), hero.getHealth());
                if (isDexterityInc) {
                    System.out.println("У Вас увеличилась ловкость!\n" +
                            "Ловкость: " + hero.getDexterity());
                }
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
