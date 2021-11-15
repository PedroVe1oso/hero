import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;

import javax.swing.*;
import java.io.IOException;

public class Game{
    private TerminalSize terminalSize ;
    private DefaultTerminalFactory terminalFactory;
    private Terminal terminal;
    private Screen screen;

    private Hero hero;

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

        hero = new Hero(10, 10);
    }

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

    private void processKey(KeyStroke key){
        switch (key.getKeyType()) {
            case ArrowUp:
                hero.moveUp();
                break;
            case ArrowDown:
                hero.moveDown();
                break;
            case ArrowRight:
                hero.moveRight();
                break;
            case ArrowLeft:
                hero.moveLeft();
                break;
            default:
        }
        System.out.println(key);
    }

    public void run() throws IOException {
        while(true){
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                || key.getKeyType() == KeyType.EOF) {
                screen.close();
                break;
            }
        }

    }
}