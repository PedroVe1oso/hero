import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

public class Arena {
    private int height;
    private int width;

    private Hero hero;

    private List<Wall> walls;
    private List<Coin> coins;

    public Arena(int x, int y, int height, int width) {
        hero = new Hero(x,y);
        this.height = height;
        this.width = width;
        this.walls = createWalls();
        this.coins = createCoins();
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        List<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) +
                    1, random.nextInt(height - 2) + 1));
        return coins;
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new
                TerminalSize(width, height), ' ');
        hero.draw(textGraphics);
        for (Wall wall : walls)
            wall.draw(textGraphics);
        for (Coin coin : coins)
            coin.draw(textGraphics);
    }

    private boolean canHeroMove(Position position){
        boolean flag = true;
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public void moveHero(Position position) {
        if(canHeroMove(position))
            setHero(position);
    }

    public void processKey(KeyStroke key){
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            default:
        }
        System.out.println(key);
/*
        Another way to write the switch statement

        switch (key.getKeyType()) {
            case ArrowUp -> moveHero(hero.moveUp());
            case ArrowDown -> moveHero(hero.moveDown());
            case ArrowRight -> moveHero(hero.moveRight());
            case ArrowLeft -> moveHero(hero.moveLeft());
            default -> {
            }
        }*/
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Position position) {
        hero.setPosition(position);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
