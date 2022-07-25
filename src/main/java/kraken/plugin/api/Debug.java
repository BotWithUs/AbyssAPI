package kraken.plugin.api;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Provides various debugging utilities.
 */
public final class Debug {

    private Debug() {
    }

    /**
     * Logs a message to the internal client console.
     */
    public static native void log(String s);

    /**
     * Prints a stack trace to the in-game console.
     */
    public static void printStackTrace(String cause, Throwable t) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        t.printStackTrace(new PrintStream(bos));

        Debug.log("Error occurred during " + cause);
        for (String s : bos.toString().split("\n")) {
            Debug.log(s);
        }
    }


}
