package abyss.plugin.api.plugin.attributes;

import abyss.plugin.api.Debug;

import java.io.*;

public final class GlobalAttributes {

    public static final Attributes GLOBAL = new Attributes();

    private GlobalAttributes() {}

    public static void save() {
        File file = new File("global_attributes.bin");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Debug.printStackTrace("Failed to create global attributes file", e);
            }
        }
        try (FileOutputStream stream = new FileOutputStream(file)) {
            GLOBAL.flushAndWrite(stream);
        } catch (IOException e) {
            Debug.printStackTrace("Failed to open global attributes", e);
        }
    }

    public static void load() {
        File file = new File("global_attributes.bin");
        if(!file.exists()) {
            return;
        }
        try (FileInputStream stream = new FileInputStream(file)) {
            GLOBAL.read(stream);
        } catch (IOException e) {
            Debug.printStackTrace("Failed to open global attributes", e);
        }
    }

}