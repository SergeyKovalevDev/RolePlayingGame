package com.dontexist;

import com.dontexist.characters.Enemy;
import com.dontexist.characters.Goblin;
import com.dontexist.characters.Hero;
import com.dontexist.characters.Skeleton;
import com.dontexist.gameplay.GameWorld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Gameplay {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private GameWorld gameWorld;

    static {
        System.out.println("===Welcome to Role-playing Game!===");
    }

    public static void main(String[] args) throws IOException {
        Gameplay starter = new Gameplay();
        starter.start();
    }

    private void start() throws IOException {
        System.out.print("Введите имя героя: ");
        String heroName = reader.readLine();
        gameWorld = new GameWorld(new Hero(heroName));
        showMainMenu();
        boolean isExit = false;
        while (!isExit) {
            switch (reader.readLine()) {
                case "1" -> gameWorld.trading();
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
                3. На выход""");
    }

    private boolean battleManagement() throws IOException {
        Enemy enemy = Math.random() >= 0.5 ? gameWorld.getGoblin() : gameWorld.getSkeleton();
        boolean isHeroAlive = gameWorld.battle(enemy);
        if (isHeroAlive) {
            if (enemy instanceof Goblin) gameWorld.setGoblin(new Goblin());
            else gameWorld.setSkeleton(new Skeleton());
            showBattleManagementMenu();
            switch (reader.readLine()) {
                case "1" -> showMainMenu();
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
                2. Продолжить бой""");
    }
}
