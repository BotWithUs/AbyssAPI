package abyss.plugin.api;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;
import static abyss.plugin.api.Rng.*;
import static abyss.plugin.api.Time.*;

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
    public static native void pressKey(int vk);

    /**
     * Presses a key down on the keyboard.
     *
     * @param vk The virtual key code of the key to press.
     * @param vc The virtual key char of the key to press.
     */
    public static native void pressKey(int vk, char vc);

    /**
     * Releases a key on the keyboard.
     *
     * @param vk The virtual key code of the key to release.
     */
    public static native void releaseKey(int vk);

    /**
     * Presses and releases a key on the keyboard.
     *
     * @param vk The virtual key code of the key to press.
     */
    public static void typeKey(int vk) {
        pressKey(vk);
        waitFor(Rng.i64(30, 250));
        releaseKey(vk);
    }

    /**
     * Presses and releases a key on the keyboard.
     *
     * @param vk The virtual key code of the key to press.
     * @param vc The virtual key char of the key to press.
     */
    public static void typeKey(int vk, char vc) {
        pressKey(vk, vc);
        waitFor(Rng.i64(30, 250));
        releaseKey(vk);
    }

    /**
     * Enters a series of characters into the game client. Does not press enter.
     *
     * @param text     The string to enter.
     * @param delayMin The minimum delay to wait between key presses.
     * @param delayMax The maximum delay to wait between key presses.
     */
    public static void type(String text, long delayMin, long delayMax) {
        for (char c : text.toCharArray()) {
            waitFor(i64(delayMin, delayMax));
            //TODO only shift is currently supported as far as meta keys go and the keycode differs on some keyboards.
            if (Character.isUpperCase(c)) {
                pressKey(VK_SHIFT);
                waitFor(i64(delayMin, delayMax));
                typeKey(toKeyCode(c), c);
                waitFor(i64(delayMin, delayMax));
                releaseKey(VK_SHIFT);
            } else {
                typeKey(toKeyCode(c));
            }
        }
    }

    /**
     * Enters a series of characters into the game client. Does not press enter.
     *
     * @param text The string to enter.
     */
    public static void type(String text) {
        type(text, 20, 200);
    }

    public static int toKeyCode(char character) {
        //TODO characters should be passed to the native side directly so we can translate the keycodes based
        //on keyboard layout and locale. This would also allow us to more easily analyze the state of meta keys
        //such as ctrl, alt, shift, and the windows key.
        return switch (character) {
            case 'a', 'A' -> VK_A;
            case 'b', 'B' -> VK_B;
            case 'c', 'C' -> VK_C;
            case 'd', 'D' -> VK_D;
            case 'e', 'E' -> VK_E;
            case 'f', 'F' -> VK_F;
            case 'g', 'G' -> VK_G;
            case 'h', 'H' -> VK_H;
            case 'i', 'I' -> VK_I;
            case 'j', 'J' -> VK_J;
            case 'k', 'K' -> VK_K;
            case 'l', 'L' -> VK_L;
            case 'm', 'M' -> VK_M;
            case 'n', 'N' -> VK_N;
            case 'o', 'O' -> VK_O;
            case 'p', 'P' -> VK_P;
            case 'q', 'Q' -> VK_Q;
            case 'r', 'R' -> VK_R;
            case 's', 'S' -> VK_S;
            case 't', 'T' -> VK_T;
            case 'u', 'U' -> VK_U;
            case 'v', 'V' -> VK_V;
            case 'w', 'W' -> VK_W;
            case 'x', 'X' -> VK_X;
            case 'y', 'Y' -> VK_Y;
            case 'z', 'Z' -> VK_Z;
            case '`', '~' -> VK_BACK_QUOTE;
            case '0' -> VK_0;
            case '1' -> VK_1;
            case '2' -> VK_2;
            case '3' -> VK_3;
            case '4' -> VK_4;
            case '5', '%' -> VK_5;
            case '6' -> VK_6;
            case '7' -> VK_7;
            case '8' -> VK_8;
            case '9' -> VK_9;
            case '-' -> VK_MINUS;
            case '=' -> VK_EQUALS;
            case '!' -> VK_EXCLAMATION_MARK;
            case '@' -> VK_AT;
            case '#' -> VK_NUMBER_SIGN;
            case '$' -> VK_DOLLAR;
            case '^' -> VK_CIRCUMFLEX;
            case '&' -> VK_AMPERSAND;
            case '*' -> VK_ASTERISK;
            case '(' -> VK_LEFT_PARENTHESIS;
            case ')' -> VK_RIGHT_PARENTHESIS;
            case '_' -> VK_UNDERSCORE;
            case '+' -> VK_PLUS;
            case '\t' -> VK_TAB;
            case '\n' -> VK_ENTER;
            case '[', '{' -> VK_OPEN_BRACKET;
            case ']', '}' -> VK_CLOSE_BRACKET;
            case '\\', '|' -> VK_BACK_SLASH;
            case ';' -> VK_SEMICOLON;
            case ':' -> VK_COLON;
            case '\'' -> VK_QUOTE;
            case '"' -> VK_QUOTEDBL;
            case ',', '<' -> VK_COMMA;
            case '.' -> VK_PERIOD;
            case '/' -> VK_SLASH;
            case ' ' -> VK_SPACE;
            default -> throw new IllegalArgumentException("Cannot type character " + character);
        };
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
}
