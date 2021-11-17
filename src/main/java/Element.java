import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import javax.crypto.spec.PSource;

public abstract class Element {
    private Position position;

    public Element(int x, int y) {
        position = new Position(x, y);
    }

    public void draw(TextGraphics textGraphics){}

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
