import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private int height;
    private int width;

    private Hero hero;

    public Arena(int x, int y, int height, int width) {
        hero = new Hero(x,y);
        this.height = height;
        this.width = width;
    }

    public void draw(TextGraphics textGraphics){
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new
                TerminalSize(width, height), ' ');
        hero.draw(textGraphics);
    }
    private boolean canHeroMove(Position position){
        return position.getX() < height &&
                position.getX() >= 0 &&
                position.getY() < width &&
                position.getY() >= 0;
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

    public Hero getHero() {
        return hero;
    }

    public void setHero(Position position) {
        hero.setPosition(position);
    }
}
