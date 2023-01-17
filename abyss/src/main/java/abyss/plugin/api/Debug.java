package abyss.plugin.api;

import abyss.bindings.MethodBuilder;
import abyss.plugin.api.entities.Player;
import abyss.plugin.api.query.players.PlayerQuery;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.BiConsumer;

import static abyss.bindings.NativeLoader.newMethod;

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

    public static void bind(BiConsumer<Class<?>, MethodBuilder> registerNativeMethod) {
        registerNativeMethod.accept(Debug.class, newMethod("log").addParam(String.class));
    }
}
