package kraken.plugin.api;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static kraken.plugin.api.Rng.*;
import static kraken.plugin.api.Time.*;

/**
 * Provides access to real game input.
 * <p>
 * Key codes must be one of the codes within the {@link KeyEvent KeyEvent} class. Using undefined key codes
 * may cause undefined behavior within the RuneScape client.
 */
public final class Input {

    public static final int MOUSE_LEFT = 0;
    public static final int MOUSE_RIGHT = 1;
    public static final int MOUSE_MIDDLE = 2;

    private Input() {
    }

    /**
     * Presses a key down on the keyboard.
     *
     * @param vk The virtual key code of the key to press.
     */
    public static native void press(int vk);

    /**
     * Releases a key on the keyboard.
     *
     * @param vk The virtual key code of the key to release.
     */
    public static native void release(int vk);

    /**
     * Presses and releases a key on the keyboard.
     *
     * @param vk The virtual key code of the key to press.
     */
    public static void key(int vk) {
        press(vk);
        waitFor(250);
        release(vk);
    }

    /**
     * Moves the mouse.
     *
     * @param x The x coordinate to move the mouse to.
     * @param y The y coordinate to move the mouse to.
     */
    public static native void moveMouse(int x, int y);

    /**
     * Clicks the mouse at the last known mouse coordinates.
     *
     * @param button The mouse button to click.
     */
    public static native void clickMouse(int button);

    /**
     * Enters a series of characters into the game client. Does not press enter.
     *
     * @param s        The string to enter.
     * @param delayMin The minimum delay to wait between key presses.
     * @param delayMax The maximum delay to wait between key presses.
     */
    public static void enter(String s, long delayMin, long delayMax) {
        for (char c : s.toCharArray()) {
            waitFor(i64(delayMin, delayMax));
            if(Character.isUpperCase(c)) {
                press(VK_SHIFT);
                waitFor(i64(delayMin, delayMax));
                key(type(c));
                waitFor(i64(delayMin, delayMax));
                release(VK_SHIFT);
            } else {
                key(c);
            }
        }
    }

    /**
     * Enters a series of characters into the game client. Does not press enter.
     *
     * @param s The string to enter.
     */
    public static void enter(String s) {
        enter(s, 10, 30);
    }

    public static int type(char character) {
        switch (character) {
            case 'a':
            case 'A':
                return VK_A;
            case 'b':
            case 'B':
                return VK_B;
            case 'c':
            case 'C':
                return VK_C;
            case 'd':
            case 'D':
                return VK_D;
            case 'e':
            case 'E':
                return VK_E;
            case 'f':
            case 'F':
                return VK_F;
            case 'g':
            case 'G':
                return VK_G;
            case 'h':
            case 'H':
                return VK_H;
            case 'i':
            case 'I':
                return VK_I;
            case 'j':
            case 'J':
                return VK_J;
            case 'k':
            case 'K':
                return VK_K;
            case 'l':
            case 'L':
                return VK_L;
            case 'm':
            case 'M':
                return VK_M;
            case 'n':
            case 'N':
                return VK_N;
            case 'o':
            case 'O':
                return VK_O;
            case 'p':
            case 'P':
                return VK_P;
            case 'q':
            case 'Q':
                return VK_Q;
            case 'r':
            case 'R':
                return VK_R;
            case 's':
            case 'S':
                return VK_S;
            case 't':
            case 'T':
                return VK_T;
            case 'u':
            case 'U':
                return VK_U;
            case 'v':
            case 'V':
                return VK_V;
            case 'w':
            case 'W':
                return VK_W;
            case 'x':
            case 'X':
                return VK_X;
            case 'y':
            case 'Y':
                return VK_Y;
            case 'z':
            case 'Z':
                return VK_Z;
            case '`':
            case '~':
                return VK_BACK_QUOTE;
            case '0':
                return VK_0;
            case '1':
                return VK_1;
            case '2':
                return VK_2;
            case '3':
                return VK_3;
            case '4':
                return VK_4;
            case '5':
            case '%':
                return VK_5;
            case '6':
                return VK_6;
            case '7':
                return VK_7;
            case '8':
                return VK_8;
            case '9':
                return VK_9;
            case '-':
                return VK_MINUS;
            case '=':
                return VK_EQUALS;
            case '!':
                return VK_EXCLAMATION_MARK;
            case '@':
                return VK_AT;
            case '#':
                return VK_NUMBER_SIGN;
            case '$':
                return VK_DOLLAR;
            case '^':
                return VK_CIRCUMFLEX;
            case '&':
                return VK_AMPERSAND;
            case '*':
                return VK_ASTERISK;
            case '(':
                return VK_LEFT_PARENTHESIS;
            case ')':
                return VK_RIGHT_PARENTHESIS;
            case '_':
                return VK_UNDERSCORE;
            case '+':
                return VK_PLUS;
            case '\t':
                return VK_TAB;
            case '\n':
                return VK_ENTER;
            case '[':
            case '{':
                return VK_OPEN_BRACKET;
            case ']':
            case '}':
                return VK_CLOSE_BRACKET;
            case '\\':
            case '|':
                return VK_BACK_SLASH;
            case ';':
                return VK_SEMICOLON;
            case ':':
                return VK_COLON;
            case '\'':
                return VK_QUOTE;
            case '"':
                return VK_QUOTEDBL;
            case ',':
            case '<':
                return VK_COMMA;
            case '.':
                return VK_PERIOD;
            case '/':
                return VK_SLASH;
            case ' ':
                return VK_SPACE;
            default:
                throw new IllegalArgumentException("Cannot type character " + character);
        }
    }

}
