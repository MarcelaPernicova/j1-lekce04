package cz.czechitas.kockamyssyr;

import cz.czechitas.kockamyssyr.api.*;

import java.awt.*;
import java.util.Random;

/**
 * Hlaví třída pro hru Kočka–myš–sýr.
 */
public class HlavniProgram {
    private final Random random = new Random();

    private final int VELIKOST_PRVKU = 50;
    private final int SIRKA_OKNA = 1000 - VELIKOST_PRVKU;
    private final int VYSKA_OKNA = 600 - VELIKOST_PRVKU;

    private Cat tom;
    private Mouse jerry;

    /**
     * Spouštěcí metoda celé aplikace.
     *
     * @param args
     */
    public static void main(String[] args) {
        new HlavniProgram().run();
    }

    /**
     * Hlavní metoda obsahující výkonný kód.
     */
    public void run() {
        tom = vytvorKocku();
        tom.setBrain(new KeyboardBrain(KeyCode.W, KeyCode.A, KeyCode.S, KeyCode.D));

        jerry = vytvorMys();
        jerry.setBrain(new KeyboardBrain());

        vytvorVeci(0);
        chytMys();
    }

    public void chytMys() {
        // TODO: Sem vepište svůj program

        JdiKMysi();

    }

    public void JdiKMysi() {

        int maxY = 20;
        int maxX = 50;
        while (jerry.isAlive()) {
            if (jerry.getX() < tom.getX()) {
                otocDoleva();
                jestliMuzuPohnuSe(tom.getX() - jerry.getX(), maxX);
            } else if (jerry.getX() > tom.getX()) {
                otocDoprava();
                jestliMuzuPohnuSe(jerry.getX() - tom.getX(), maxX);
            }

            if (jerry.getY() < tom.getY()) {
                otocNahoru();
                jestliMuzuPohnuSe(tom.getY() - jerry.getY(), maxY);
            } else if (jerry.getY() > tom.getY()) {
                otocDolu();
                jestliMuzuPohnuSe(jerry.getY() - tom.getY(), maxY);
            }
        }
    }
    public void jestliMuzuPohnuSe(int oKolik, int maxPohnout) {
        if (tom.isPossibleToMoveForward()) {
            tom.moveForward(Math.min(oKolik, maxPohnout));
        }
    }
    public void otocNahoru() {
        if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnRight();
            tom.turnRight();
        } else if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnRight();
        }

    }

    public void otocDoleva() {
        if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnRight();
        } else if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnLeft();
            tom.turnLeft();
        }
    }

    public void otocDoprava() {
        if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnRight();
        } else if (tom.getOrientation() == PlayerOrientation.DOWN) {
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnRight();
            tom.turnRight();
        }
    }

    public void otocDolu() {
        if (tom.getOrientation() == PlayerOrientation.UP) {
            tom.turnLeft();
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.LEFT) {
            tom.turnLeft();
        } else if (tom.getOrientation() == PlayerOrientation.RIGHT) {
            tom.turnRight();
        }
    }

    public void vytvorVeci(int pocetStromu) {
        for (int i = 0; i < pocetStromu; i++) {
            vytvorStrom();
        }
        vytvorSyr();
        vytvorJitrnici();
    }

    public Tree vytvorStrom() {
        return new Tree(vytvorNahodnyBod());
    }

    public Cat vytvorKocku() {
        return new Cat(vytvorNahodnyBod());
    }

    public Mouse vytvorMys() {
        return new Mouse(vytvorNahodnyBod());
    }

    public Cheese vytvorSyr() {
        return new Cheese(vytvorNahodnyBod());
    }

    public Meat vytvorJitrnici() {
        return new Meat(vytvorNahodnyBod());
    }

    private Point vytvorNahodnyBod() {
        return new Point(random.nextInt(SIRKA_OKNA), random.nextInt(VYSKA_OKNA));
    }

}
