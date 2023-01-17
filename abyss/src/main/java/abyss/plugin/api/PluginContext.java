package abyss.plugin.api;

import abyss.bindings.MethodBuilder;
import abyss.bindings.NativeLoader;

import java.util.function.BiConsumer;

/**
 * Contains contextual information about a plugin.
 */
public final class PluginContext {

    // internal values, attempting to use these will break the client

    private long internal1;

    /**
     * Do not make instances of this.
     */
    private PluginContext() {
    }

    /**
     * Retrieves the entry-point plugin object.
     *
     * @return The entry-point plugin object.
     */
    public Plugin getEntry() {
        return getEntry0(internal1);
    }

    private native Plugin getEntry0(long address);



    /**
     * Retrieves the name of the plugin.
     *
     * @return The name of the plugin.
     */
    public byte[] getNameBinary() {
        return getNameBinary0(internal1);
    }
    private native byte[] getNameBinary0(long address);

    /**
     * Retrieves the name of the plugin.
     *
     * @return The name of the plugin.
     */
    public String getName() {
        return new String(getNameBinary());
    }

    public void setCategory(String category) {
        setCategory0(internal1, category);
    }
    private native void setCategory0(long address, String category);

    public String getCategory() {
        return getCategory0(internal1);
    }
    private native String getCategory0(long address);


    /**
     * Sets the name of the plugin.
     *
     * @param name The new name to set.
     */
    public void setName(String name) {
        setName0(internal1, name);
    }
    private native void setName0(long address, String name);

    /**
     * Sets the SDK version that this plugin uses.
     *
     * @param version The SDK version that this plugin uses.
     */
    public void setApiVersion(int version) {
        setApiVersion0(internal1, version);
    }
    private native void setApiVersion0(long address, int version);

    /**
     * Retrieves the persistent data for this plugin. This data will be saved to disk.
     *
     * @return The persistent data for this plugin.
     */
    public byte[] getPersistentData() {
        return getPersistentData0(internal1);
    }
    private native byte[] getPersistentData0(long address);

    /**
     * Stores new persistent data for this plugin.
     *
     * @param data The new persistent data to store.
     */
    public void setPersistentData(byte[] data) {
        setPersistentData0(internal1, data);
    }
    private native void setPersistentData0(long address, byte[] data);

    /**
     * Determines whether or not this plugin is enabled.
     *
     * @return Whether or not this plugin is enabled.
     */
    public boolean isEnabled() {
        return isEnabled0(internal1);
    }
    private native boolean isEnabled0(long address);

    /**
     * Changes whether or not this plugin is enabled.
     *
     * @param enabled Whether this plugin will be enabled or disabled.
     */
    public void setEnabled(boolean enabled) {
        setEnabled0(internal1, enabled);
    }
    private native void setEnabled0(long address, boolean enabled);


    public static void bind(BiConsumer<Class<?>, MethodBuilder> registerNativeMethod) {
        registerNativeMethod.accept(PluginContext.class, NativeLoader
                .newMethod("getEntry0")
                .addParam(long.class)
                .setReturnType(Plugin.class));
        registerNativeMethod.accept(PluginContext.class, NativeLoader
                .newMethod("getNameBinary0")
                .addParam(long.class)
                .setReturnType(byte[].class));
        registerNativeMethod.accept(PluginContext.class, NativeLoader
                .newMethod("setCategory0")
                .addParam(long.class)
                .addParam(String.class));
        registerNativeMethod.accept(PluginContext.class, NativeLoader
                .newMethod("getCategory0")
                .addParam(long.class)
                .setReturnType(String.class));
        registerNativeMethod.accept(PluginContext.class, NativeLoader
                .newMethod("setName0")
                .addParam(long.class)
                .addParam(String.class));
        registerNativeMethod.accept(PluginContext.class, NativeLoader
                .newMethod("setApiVersion0")
                .addParam(long.class)
                .addParam(int.class));
        registerNativeMethod.accept(PluginContext.class, NativeLoader
                .newMethod("getPersistentData0")
                .addParam(long.class)
                .setReturnType(byte[].class));
        registerNativeMethod.accept(PluginContext.class, NativeLoader
                .newMethod("setPersistentData0")
                .addParam(long.class)
                .addParam(byte[].class));
        registerNativeMethod.accept(PluginContext.class, NativeLoader
                .newMethod("isEnabled0")
                .addParam(long.class)
                .setReturnType(boolean.class));
        registerNativeMethod.accept(PluginContext.class, NativeLoader
                .newMethod("setEnabled0")
                .addParam(long.class)
                .addParam(boolean.class));
    }
}
