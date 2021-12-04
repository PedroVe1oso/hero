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
    private List<Monster> monsters;
    public Arena(int x, int y, int height, int width) {
        hero = new Hero(x,y);
        this.height = height;
        this.width = width;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new
                TerminalSize(width, height), ' ');
        hero.draw(textGraphics);
        retrieveCoins();
        for (Wall wall : walls)
            wall.draw(textGraphics);
        for (Coin coin : coins)
            coin.draw(textGraphics);
        for (Monster monster : monsters)
            monster.draw(textGraphics);
    }

    public void processKey(KeyStroke key){
        switch (key.getKeyType()) {
            case ArrowUp:
                moveMonsters();
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveMonsters();
                moveHero(hero.moveDown());
                break;
            case ArrowRight:
                moveMonsters();
                moveHero(hero.moveRight());
                break;
            case ArrowLeft:
                moveMonsters();
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

    public boolean verifyMonsterCollisions(){
        for(Monster monster : monsters){
            if(monster.getPosition().equals(hero.getPosition())){
                System.out.println("Death.");
                return true;
            }
        }
        return false;
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
        Coin coin;
        for (int i = 0; i < 30; i++) {
            do{
                coin = new Coin(random.nextInt(width - 2) +
                        1, random.nextInt(height - 2) + 1);
            }while ((!canCreateCoin(coin.getPosition())));
            coins.add(coin);
        }
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        List<Monster> monsters = new ArrayList<>();
        Monster monster;
        for (int i = 0; i < 5; i++) {
            do{
                monster = new Monster(random.nextInt(width - 2) +
                        1, random.nextInt(height - 2) + 1);
            }while ((!canCreateMonster(monster.getPosition())));
            monsters.add(monster);
        }
        return monsters;
    }

    private boolean canCreateMonster(Position position){
        if (monsters != null) {
            for (Monster monster : monsters)
                if (monster.getPosition().equals(position))
                    return false;
            for (Coin coin: coins)
                if (coin.getPosition().equals(position))
                    return false;
        }
        return !position.equals(hero.getPosition());
    }

    private boolean canCreateCoin(Position position){
        boolean result = !position.equals(hero.getPosition());
        if (coins != null) {
            for (Coin coin : coins)
                if (coin.getPosition().equals(position)) {
                    result = false;
                    break;
                }
        }
        return result;
    }

    private void retrieveCoins(){
        for (Coin coin : coins)
            if (coin.getPosition().equals(hero.getPosition())) {
                coins.remove(coin);
                break;
            }
    }

    private boolean canMonsterMove(Position position){
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }
        for (Coin coin : coins) {
            if (coin.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
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

    private void moveMonsters(){
        for(Monster monster : monsters){
            monster.setPosition(monster.move(this));
        }
    }

    private void moveHero(Position position) {
        if(canHeroMove(position)) {
            setHero(position);
        }
    }

    public void setHero(Position position) {
        hero.setPosition(position);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
