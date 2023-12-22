package utils.openDoor;

import entities.Entity;
import io.Keyboard;
import org.joml.Vector3f;

import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_M;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_N;

public class OpenDoor {
   private static boolean rightDoorOpen=false;
   private static boolean leftDoorOpen=false;

   private static boolean doOpenRightDoor=false;
   private static boolean doOpenLeftDoor=false;
    public static void openRightDoorGarage(List<Entity>garageRightDoor){
        if(Keyboard.isKeyDown(GLFW_KEY_M)&&!doOpenRightDoor) {
            rightDoorOpen=!rightDoorOpen;
            doOpenRightDoor=true;

        }
        else if(!Keyboard.isKeyDown(GLFW_KEY_M)&&doOpenRightDoor){
            openRightDoor(garageRightDoor,rightDoorOpen);
            doOpenRightDoor=false;
        }

    }

    public static void openLeftDoorGarage(List<Entity>garageLeftDoor){
        if(Keyboard.isKeyDown(GLFW_KEY_N)&&!doOpenLeftDoor) {
            leftDoorOpen=!leftDoorOpen;
            doOpenLeftDoor =true;
        }
        if(!Keyboard.isKeyDown(GLFW_KEY_N)&& doOpenLeftDoor){
            openLeftDoor(garageLeftDoor,leftDoorOpen);
            doOpenLeftDoor =false;
        }
    }

    private static void openLeftDoor(List<Entity> entities, boolean pushOpenLeftDoor){
        if(pushOpenLeftDoor){
            for (Entity entity: entities) {
                // entity.setRotationY(228.4f);
                entity.increaseRotation(0,-91,0);
            }
            for (Entity entity: entities) {
                entity.increasePosition(new Vector3f(24.6f,0,22.7f));
            }
        }
        else {
            for (Entity entity: entities) {
                // entity.setRotationY(228.4f);
                entity.increaseRotation(0,91,0);
            }
            for (Entity entity: entities) {
                entity.increasePosition(new Vector3f(-24.6f,0,-22.7f));
            }
        }
    }


    private static void openRightDoor(List<Entity> entities, boolean pushOpenRightDoor){
        if(pushOpenRightDoor){
            for (Entity entity: entities) {
                // entity.setRotationY(228.4f);
                entity.increaseRotation(0,90,0);
            }
            for (Entity entity: entities) {
                entity.increasePosition(new Vector3f(6.8f,0,33.0f));
            }
        }
        else {
            for (Entity entity: entities) {
                // entity.setRotationY(228.4f);
                entity.increaseRotation(0,-90,0);
            }
            for (Entity entity: entities) {
                entity.increasePosition(new Vector3f(-6.8f,0,-33.0f));
            }
        }


    }
}
