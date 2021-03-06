import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import java.io.IOException;

public class Game{
    private TerminalSize terminalSize ;
    private Terminal terminal;
    private Screen screen;

    private Arena arena;


    public Game() throws IOException {
        terminalSize = new TerminalSize(50, 50);
        DefaultTerminalFactory terminalFactory = new
                DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        arena = new Arena(10, 10, 50, 50);
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key){
        arena.processKey(key);
    }

    public void run() throws IOException {
        KeyStroke key;
        while(true){
            draw();
            key = screen.readInput();
            processKey(key);
            if(arena.verifyMonsterCollisions()){
                screen.close();
                break;
            }
            if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                    || key.getKeyType() == KeyType.EOF) {
                screen.close();
                break;
            }
        }
    }
}