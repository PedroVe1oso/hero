import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;



public class Hero extends Element{

    public Hero(int x, int y){
        super(x,y);
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new
                TerminalPosition(getPosition().getX(), getPosition().getY()), "X");
    }

    public Position moveUp(){
        return new Position(getPosition().getX(), getPosition().getY() - 1);
    }

    public Position moveDown(){
        return new Position(getPosition().getX(), getPosition().getY() + 1);
    }

    public Position moveRight(){
        return new Position(getPosition().getX() + 1, getPosition().getY());
    }

    public Position moveLeft(){
        return new Position(getPosition().getX() - 1, getPosition().getY());
    }
}
