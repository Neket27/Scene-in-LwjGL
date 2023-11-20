package app;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import org.joml.Vector3f;
import render.DisplayManager;
import render.Loader;
import render.MasterRenderer;
import render.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {
       DisplayManager.createDisplay();

        Loader loader = new Loader(); // загрузчик моделей

        // загружаем модель в память OpenGL
        RawModel model = OBJLoader.loadObjModel("res/tutorial14/tree.obj", loader);
        RawModel model2 = OBJLoader.loadObjModel("res/Porsche_911_GT2.obj", loader);
        // загрузим текстуру используя загрузчик
       ModelTexture texture = new ModelTexture(loader.loadTexture("res/t1.png"));
        // Установка переменных блеска
        texture.setShineDamper(20);
        texture.setReflectivity(0f);

        // Создание текстурной модели

        TexturedModel staticModel = new TexturedModel(model2, texture);

       List<Entity> entities = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<500;i++){
            Entity m =new Entity(staticModel, new Vector3f(random.nextFloat()*800 - 400,0,random.nextFloat() * -600),0,0,0,3);
            m.increacePosition(0,2,0);
            entities.add(m);
        }
        // создание источника света
        Light light = new Light(new Vector3f(3000, 2000, 3000), new Vector3f(1, 1, 1));

       Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("res/tutorial06/image.png")));
        Terrain terrain2 = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("res/tutorial14/grassFlowers.png")));

        Camera camera = new Camera();

        MasterRenderer renderer = new MasterRenderer();

        // запускаем цикл пока пользователь не закроет окно
        while (DisplayManager.shouldDisplayClose()) {
            camera.move(); // двигаем камеру
            // рисуем объекты
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            for(Entity entity:entities){
                renderer.processEntity(entity);
            }
            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp(); // очищаем визуализацию
        loader.cleanUp(); // очищаем память от загруженной модели
        DisplayManager.closeDisplay();
    }
}
