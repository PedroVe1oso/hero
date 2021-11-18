import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element{

    public Coin(int x, int y) {
        super(x,y);
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new
                TerminalPosition(getPosition().getX(), getPosition().getY()), "-");
    }
}

