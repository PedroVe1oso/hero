import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;



public class Hero {
    private Position position;

    public Hero(int x, int y) {
        position = new Position(x, y);
    }


    public void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new
                        TerminalPosition(position.getX(), position.getY()), "X");
    }

    public Position moveUp(){
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown(){
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveRight(){
        return new Position(position.getX() + 1, position.getY());
    }

    public Position moveLeft(){
        return new Position(position.getX() - 1, position.getY());
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
