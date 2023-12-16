package entities;

import io.Keyboard;
import io.KeyboardRelease;
import models.TexturedModel;
import org.joml.Vector3f;
import render.DisplayManager;

import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Entity {
    /** скорость передвижения */
    private static final float RUN_SPEED = 45;
    /** градусы в секунду */
    private static final float TURN_SPEED = 160;

    /** текущая скорость */
    private float currentSpeed;
    /** текущая скорость поворота */
    private float currentTurnSpeed;

    public Player(TexturedModel model, Vector3f position, float rotationX, float rotationY, float rotationZ, float scale) {
        super(model, position, rotationX, rotationY, rotationZ, scale);
    }

//    public Player(List<Entity> entity, Vector3f position, float rotationX, float rotationY, float rotationZ, float scale) {
//        super(, position, rotationX, rotationY, rotationZ, scale);
//    }

    /**
     * Движение игрока
     */
    public void move() {
        checkInputs(); // обработка нажатия клавиш

        // расчет движения
        // рассчитываем поворот игрока
        super.increaseRotation(0, currentTurnSpeed * (float) DisplayManager.getDeltaInSeconds(), 0);
        // рассчитываем дистанцию в зависимости скорости к пройденному времени
        float distance = currentSpeed * (float) DisplayManager.getDeltaInSeconds();
        // по оси Х смещение
        float dx = (float) (distance * Math.sin(Math.toRadians(super.getRotationY())));
        // по оси Z смещение
        float dz = (float) (distance * Math.cos(Math.toRadians(super.getRotationY())));
        super.increasePosition(dx, 0, dz); // смещаем игрока
    }

    /**
     * Проверка и обработка ввода из клавиатуры
     */
    private void checkInputs() {
        // Движение вперед и назад
        if (Keyboard.isKeyDown(GLFW_KEY_W) || Keyboard.isKeyDown(GLFW_KEY_UP)) {
            currentSpeed = RUN_SPEED;
        } else if (Keyboard.isKeyDown(GLFW_KEY_S) || KeyboardRelease.isKeyDown(GLFW_KEY_DOWN)) {
            currentSpeed = -RUN_SPEED;
        } else {
            currentSpeed = 0;
        }

        // поворот влево и вправо
        if (Keyboard.isKeyDown(GLFW_KEY_D) || Keyboard.isKeyDown(GLFW_KEY_RIGHT)) {
            currentTurnSpeed = -TURN_SPEED;
        } else if (Keyboard.isKeyDown(GLFW_KEY_A) || Keyboard.isKeyDown(GLFW_KEY_LEFT)) {
            currentTurnSpeed = TURN_SPEED;
        } else {
            currentTurnSpeed = 0;
        }
    }


}
