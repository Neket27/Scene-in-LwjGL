package io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

/**
 * Класс обработки нажатия клавишь
 */
public class Keyboard extends GLFWKeyCallback {
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];

//    @Override
//    public void invoke(long window, int key, int scancode, int action, int mods) {
//        keys[key] = (action == GLFW.GLFW_PRESS) || (action ==GLFW.GLFW_REPEAT);
//    }
    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if(key==GLFW.GLFW_KEY_M || key==GLFW.GLFW_KEY_N){
            keys[key] = action == GLFW.GLFW_PRESS;
        }else{
            keys[key] = action ==GLFW.GLFW_REPEAT;
        }
    }

    public static boolean isKeyDown(int keycode) {
        return keys[keycode];
    }
}

