package utils.DarkeningOfSky;

import entities.Light;
import io.Keyboard;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class DarkeningOfSky {

    private static float lightR = 1;
    private static float lightG = 1;
    private static float lightB = 1;
    private static float lightPositionX=3000;
    private static float lightPositionY=30000;
    private static float lightPositionZ=-10000;

    static public Light start() {
        if (Keyboard.isKeyDown(GLFW_KEY_V)) {
            lightR -= 0.01;
            lightG -= 0.01;
            lightB -= 0.01;
        }
        if (Keyboard.isKeyDown(GLFW_KEY_B)) {
            lightR += 0.01;
            lightG += 0.01;
            lightB += 0.01;
        }

        if (Keyboard.isKeyDown(GLFW_KEY_Z)) {
            lightPositionY -= 200;
        } else if (Keyboard.isKeyDown(GLFW_KEY_X)) {
            lightPositionY += 200;
        }

        return new Light(new Vector3f(lightPositionX, lightPositionY, lightPositionZ), new Vector3f(lightR, lightG, lightB));
    }
}
