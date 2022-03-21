package com.dontexist;

import com.dontexist.characters.*;
import com.dontexist.gameworld.GameWorld;
import com.dontexist.potions.Potion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameplayManagement {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private GameWorld gameWorld;

    static {
        System.out.println("===Welcome to Role-playing Game!===");
    }

    public static void main(String[] args) throws IOException {
        new GameplayManagement().startGame();
    }

    private void startGame() throws IOException {
        System.out.print("Введите имя героя: ");
        String heroName = reader.readLine();
        gameWorld = new GameWorld(new Hero(heroName));
        showMainMenu();
        boolean isExit = false;
        while (!isExit) {
            switch (reader.readLine()) {//TODO заменить символы на константы
                case "1" -> {
                    tradeManagement();
                    showMainMenu();
                }
                case "2" -> isExit = battleManagement();
                case "3" -> {
                    System.out.println("Выход из игры. Ждем Вас снова в нашей увлекательной игре!");
                    isExit = true;
                }
                default -> {
                    System.out.println("Команда не распознана, повторите ввод");
                    showMainMenu();
                }
            }
        }
    }

    private void showMainMenu() {
        System.out.println("""
                Вы сейчас в городе.
                Куда вы хотите пойти?
                1. К торговцу
                2. В темный лес
                3. На выход""");//TODO заменить символы на константы
    }

    private boolean battleManagement() throws IOException {
        Enemy enemy = Math.random() >= 0.5 ? gameWorld.getGoblin() : gameWorld.getSkeleton();
        boolean isHeroAlive = gameWorld.battle(enemy);
        if (isHeroAlive) {
            if (enemy instanceof Goblin) gameWorld.setGoblin(new Goblin());
            else gameWorld.setSkeleton(new Skeleton());
            showBattleManagementMenu();
            switch (reader.readLine()) {
                case "1" -> showMainMenu();//TODO заменить символы на константы
                case "2" -> {
                    return battleManagement();
                }
                default -> {
                    System.out.println("Команда не распознана, повторите ввод");
                    showBattleManagementMenu();
                }
            }
            return false;
        } else {
            System.out.println("Игра окончена. Ждем Вас снова в нашей увлекательной игре!");
            return true;
        }
    }

    private void showBattleManagementMenu() {
        System.out.println("""
                Вы можете:
                1. Вернуться в город
                2. Продолжить бой""");//TODO заменить символы на константы
    }

    private void tradeManagement() throws IOException {
        showTradeManagementMenu();
        boolean isExit = false;
        while (!isExit) {
            switch (reader.readLine()) {//TODO заменить символы на константы
                case "1" -> potionTrading(gameWorld.getHealthPotion());
                case "2" -> potionTrading(gameWorld.getDexterityPotion());
                case "3" -> potionTrading(gameWorld.getStrengthPotion());
                case "4" -> {
                    System.out.println("Приходи еще, воин");
                    isExit = true;
                }
                default -> {
                    System.out.println("Команда не распознана, повторите ввод");
                    showTradeManagementMenu();
                }
            }
        }
    }

    private void showTradeManagementMenu() {
        System.out.printf("""
                        Приветствую тебя, воин!
                        Что желаешь приобрести:
                        1. Зелье здоровья, добавляет %d единиц к здоровю - %d золотых монет
                        2. Зелье ловкости, добавляет %d единиц к ловкости - %d золотых монет
                        3. Зелье силы, добавляет %d единиц к силе - %d золотых монет
                        4. Выйти не приобретая зелье%n""",
                gameWorld.getHealthPotion().getEffect(), gameWorld.getHealthPotion().getCost(),
                gameWorld.getDexterityPotion().getEffect(), gameWorld.getDexterityPotion().getCost(),
                gameWorld.getStrengthPotion().getEffect(), gameWorld.getStrengthPotion().getCost()
        );//TODO заменить символы на константы
    }

    private void potionTrading(Potion potion) {
        Hero hero = gameWorld.getHero();
        Merchant merchant = gameWorld.getMerchant();
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
