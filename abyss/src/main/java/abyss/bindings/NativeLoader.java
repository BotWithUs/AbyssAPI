package abyss.bindings;

import abyss.plugin.api.*;
import abyss.plugin.api.query.chat.ChatQuery;
import abyss.plugin.api.query.items.GroundItemQuery;
import abyss.plugin.api.query.npc.NpcQuery;
import abyss.plugin.api.query.objects.ObjectQuery;
import abyss.plugin.api.query.players.PlayerQuery;
import abyss.plugin.api.query.projectiles.ProjectileQuery;
import abyss.plugin.api.query.spot_animations.SpotAnimationQuery;

import java.util.List;

public final class NativeLoader {
    private NativeLoader() {
    }
    private static int registerMethodCount = 0;

    public static MethodBuilder newMethod(String name) {
        return new MethodBuilder(name);
    }

    private static native boolean registerNatives(int count, String classpath, String methodName, String methodSig);

    private static void registerNativeMethod(Class<?> clazz, MethodBuilder method) {
        if(registerMethodCount <= -1) {
            return;
        }
        //System.out.println(clazz.getName().replace(".", "/") + " " + method.getName() + " " + method.build().replace(" ", ""));
        if(registerNatives(registerMethodCount, clazz.getName().replace(".", "/"), method.getName(), method.build().replace(" ", ""))) {
            registerMethodCount++;
        } else {
            registerMethodCount = -1;
        }
    }

    public static void main(String[] args) {
        createNativeBindings();
    }

    private static void createNativeBindings() {
        Debug.bind(NativeLoader::registerNativeMethod);
        PlayerQuery.bind(NativeLoader::registerNativeMethod);
        Inventories.bind(NativeLoader::registerNativeMethod);
        PluginContext.bind(NativeLoader::registerNativeMethod);
        Interface.bind(NativeLoader::registerNativeMethod);
        Interfaces.bind(NativeLoader::registerNativeMethod);
        Component.bind(NativeLoader::registerNativeMethod);

        MethodBuilder results = newMethod("results").setReturnType(List.class);
        registerNativeMethod(NpcQuery.class, results);
        registerNativeMethod(ObjectQuery.class, results);
        registerNativeMethod(ChatQuery.class, results);
        registerNativeMethod(GroundItemQuery.class, results);
        registerNativeMethod(SpotAnimationQuery.class, results);
        registerNativeMethod(ProjectileQuery.class, results);

    }


}
