import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{

    public Monster(int x, int y){
        super(x, y);
    }

    public Position move(Arena arena){
        Random random = new Random();
        while(true) {
            Position newPosition = new Position(this.getPosition().getX() + random.nextInt(3) - 1,
                                                this.getPosition().getY() + random.nextInt(3) - 1);
            if(newPosition.getX() > 0 && newPosition.getX() < arena.getWidth()-1
                    && newPosition.getY() > 0 && newPosition.getY() < arena.getHeight()-1)
                return newPosition;
        }
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new
                TerminalPosition(getPosition().getX(), getPosition().getY()), "?");
    }
}
