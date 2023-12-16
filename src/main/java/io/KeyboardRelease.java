package io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

/**
 * Класс обработки нажатия клавишь
 */
public class KeyboardRelease extends GLFWKeyCallback {
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action == GLFW.GLFW_PRESS;
    }

    public static boolean isKeyDown(int keycode) {
        return keys[keycode];
    }
}

