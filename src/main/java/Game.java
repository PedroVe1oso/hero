import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Game{
    private TerminalSize terminalSize ;
    private DefaultTerminalFactory terminalFactory;
    private Terminal terminal;
    private Screen screen;
    
    public Game() throws IOException {
            terminalSize = new TerminalSize(40, 20);
            terminalFactory = new
                    DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
    }

    private void draw() throws IOException {
            screen.clear();
            screen.setCharacter(10, 10,
                    TextCharacter.fromCharacter('X')[0]);
            screen.refresh();
    }

    public void run() throws IOException {
        draw();
    }
}