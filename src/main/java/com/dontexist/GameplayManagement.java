package com.dontexist;

import com.dontexist.characters.Enemy;
import com.dontexist.characters.Goblin;
import com.dontexist.characters.Hero;
import com.dontexist.characters.Skeleton;
import com.dontexist.gameworld.GameWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameplayManagement {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private GameWorld gameWorld;
    private static final String MM_TRADE = "1";
    private static final String MM_BATTLE = "2";
    private static final String MM_EXIT = "3";
    private static final String BMM_RETURN = "1";
    private static final String BMM_CONTINUE = "2";
    private static final String TMM_HEALTH_POTION = "1";
    private static final String TMM_DEXTERITY_POTION = "2";
    private static final String TMM_STRENGTH_POTION = "3";
    private static final String TMM_EXIT = "4";


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
        System.out.println("Вы сейчас в городе");
        showMainMenu();
        boolean isExit = false;
        while (!isExit) {
            switch (reader.readLine()) {
                case MM_TRADE -> {
                    tradeManagement();
                    showMainMenu();
                }
                case MM_BATTLE -> isExit = battleManagement();
                case MM_EXIT -> {
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
        System.out.printf("""
                Куда вы хотите пойти?
                %s. К торговцу
                %s. В темный лес
                %s. На выход%n""",
                MM_TRADE, MM_BATTLE, MM_EXIT);
    }

    private boolean battleManagement() throws IOException {
        Enemy enemy = Math.random() >= 0.5 ? gameWorld.getGoblin() : gameWorld.getSkeleton();
        boolean isHeroAlive = gameWorld.battle(enemy);
        if (isHeroAlive) {
            if (enemy instanceof Goblin) gameWorld.setGoblin(new Goblin());
            else gameWorld.setSkeleton(new Skeleton());
            showBattleManagementMenu();
            switch (reader.readLine()) {
                case BMM_RETURN -> showMainMenu();
                case BMM_CONTINUE -> {
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
        System.out.printf("""
                Вы можете:
                %s. Вернуться в город
                %s. Продолжить бой%n""",
                BMM_RETURN, BMM_CONTINUE);
    }

    private void tradeManagement() throws IOException {
        System.out.println("Приветствую тебя, Воин!");
        showTradeManagementMenu();
        boolean isExit = false;
        while (!isExit) {
            switch (reader.readLine()) {
                case TMM_HEALTH_POTION -> gameWorld.potionTrading(gameWorld.getHealthPotion());
                case TMM_DEXTERITY_POTION -> gameWorld.potionTrading(gameWorld.getDexterityPotion());
                case TMM_STRENGTH_POTION -> gameWorld.potionTrading(gameWorld.getStrengthPotion());
                case TMM_EXIT -> {
                    System.out.println("Приходи еще, Воин");
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
                        Что желаешь приобрести:
                        %s. Зелье здоровья, добавляет %d единиц к здоровю - %d золотых монет
                        %s. Зелье ловкости, добавляет %d единиц к ловкости - %d золотых монет
                        %s. Зелье силы, добавляет %d единиц к силе - %d золотых монет
                        %s. Выйти не приобретая зелье%n""",
                TMM_HEALTH_POTION, gameWorld.getHealthPotion().getEffect(), gameWorld.getHealthPotion().getCost(),
                TMM_DEXTERITY_POTION, gameWorld.getDexterityPotion().getEffect(), gameWorld.getDexterityPotion().getCost(),
                TMM_STRENGTH_POTION, gameWorld.getStrengthPotion().getEffect(), gameWorld.getStrengthPotion().getCost(),
                TMM_EXIT);
    }
}
