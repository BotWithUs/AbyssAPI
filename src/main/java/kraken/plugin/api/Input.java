package kraken.plugin.api;

import java.awt.event.KeyEvent;

import static kraken.plugin.api.Rng.*;
import static kraken.plugin.api.Time.*;

/**
 * Provides access to real game input.
 *
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
    public static native void key(int vk);

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
     * @param s The string to enter.
     * @param delayMin The minimum delay to wait between key presses.
     * @param delayMax The maximum delay to wait between key presses.
     */
    public static void enter(String s, long delayMin, long delayMax) {
        for (char c : s.toCharArray()) {
            waitFor(i64(delayMin, delayMax));
            key(c);
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

}
