package app;

import de.javagl.obj.*;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.TexturedModel;
import org.joml.Vector3f;
import render.DisplayManager;
import render.Loader;
import render.MasterRenderer;
import render.OBJLoader;
import service.EntityService;
import service.LandscapeService;
import service.PlayerService;
import terrains.Terrain;
import textures.ModelTexture;
import utils.RandomCords;
import utils.UtilsLoader;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) throws IOException {
        DisplayManager.createDisplay();
        LandscapeService landscapeService =new LandscapeService();
        EntityService entityService = new EntityService();
        PlayerService playerService = new PlayerService();

        Loader loader = new Loader(); // загрузчик моделей
        Terrain terrain = landscapeService.create(loader,0,-1,"res/whileFone.png","res/a3.png","res/yellow.png","res/tutorial21/heightmap.png","res/mapForautoDrom3.png");
        Terrain terrain2 = landscapeService.create(loader,-1,-1,"res/whileFone.png","res/a3.png","res/yellow.png","res/tutorial21/heightmap.png","res/mapForautoDrom3.png");
        //Player player = playerService.create(loader);
        List<Player> player =playerService.create(loader,"res/bugatti/bugatti.obj","res/bugatti");
        Camera camera = new Camera(player);
        MasterRenderer renderer = new MasterRenderer();

        List<Entity> entities = new ArrayList<>();
        List <Entity> garage =entityService.createEntityWithMaterial(loader,"res/Garage/GarageOBJ/garage.obj","res/Garage/GarageOBJ",-100,0,-30,6);
        garage.forEach(e->e.increaseRotation(0,140,0));
        entities.addAll(garage);

        List<Entity> house = entityService.createEntityWithMaterial(loader, "res/Bambo_House_obj/Bambo_House.obj", "res/Bambo_House_obj", -45, 0, -25, 5);
        house.forEach(e->{
            e.getModel().getTexture().setUseFakeLighting(true);
            e.getModel().getTexture().setReflectivity(0.1f);
            e.increaseRotation(0,-45,0);
        });

        entities.addAll(house);
        entities.addAll(entityService.createEntityWithMaterial(loader,"res/bugatti/bugatti.obj","res/bugatti",120,0,-30,2));
        entities.addAll(entityService.createEntityWithMaterial(loader,"res/Range_Rover_Sport_OBJ/2016 Custom Range Rover Sport.obj","res/Range_Rover_Sport_OBJ",-75,1,-60,6));

        // создание источника света
        Light light = new Light(new Vector3f(3000, 2000, -3000), new Vector3f(1, 1, 1));

       // entities.addAll(entityService.createEntityWithMaterial(loader,"res/Bambo_House_obj/Bambo_House.obj","res/Bambo_House_obj",0,0,0,3));
      //  List<Entity> cactus2 =  entityService.createEntityWithMaterial(loader,"res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f/10436_Cactus_v1_max2010_it2.obj","res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f",0,0,0);

        List<Entity> cactus2 =  entityService.createEntityWithMaterial(loader,"res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f/10436_Cactus_v1_max2010_it2.obj","res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f",10,0,0,-90,0,0,0.06f);
        RandomCords randomCords = new RandomCords();
        for (int i = 0; i < 100; i++) {
            List<Entity> cactus = cactus2.stream().map(Entity::clone).toList();
            entities.add(entityService.createEntityWithTexture(loader,"res/tutorial15/grassModel.obj","res/tutorial15/grassTexture.png",true,true,randomCords.random(),0,0,0,1));
            entities.add(entityService.createEntityWithTexture(loader,"res/tutorial15/fern.obj","res/tutorial15/fern.png",true,true,randomCords.random(), 0, 0, 0, 1));

            cactus.forEach(e->e.setPosition(randomCords.random()));
            entities.addAll(cactus);

            //entities.add(new Entity(UtilsLoader))
//            entities.add(new Entity(gras0, 0, 0, 3s, new Vector3f(random.nextFloat() * 800 - 400, 0,
//                    random.nextFloat() * -600), 0, 0, 0, 1));
//            entities.add(new Entity(fern, new Vector3f(random.nextFloat() * 800 - 400, 0,
//                    random.nextFloat() * -600), 0, 0, 0, 0.6f));
////// трава
//        TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("res/tutorial15/grassModel.obj", loader), new ModelTexture(
//                loader.loadTexture("res/tutorial15/grassTexture.png")));
//// папоротник
//        TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("res/tutorial15/fern.obj", loader), new ModelTexture(
//                loader.loadTexture("res/tutorial15/fern.png")));
//            entities.add(new Entity(grass, new Vector3f(random.nextFloat() * 800 - 400, 0,
//                    random.nextFloat() * -600), 0, 0, 0, 1));
        //  List<Entity> cactus2 =  entityService.createEntityWithMaterial(loader,"res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f/10436_Cactus_v1_max2010_it2.obj","res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f",0,0,0);

//          cactus2.forEach(e->{
//              e.getModel().getTexture().setShineDamper(20);
//              e.getModel().getTexture().setReflectivity(1);
//
//              e.setScale(0.05f);
//              e.increaseRotation(-90,0,0);
//              e.increaseRotation(0, -150, 0);
//              e.getModel().getTexture().setHasTransparency(true); // включаем прозрачность текстур
//              e.getModel().getTexture().setUseFakeLighting(true); // включаем фальшивое освещение
//              e.setPosition(new Vector3f(random.nextFloat() * 800 - 400, 0,
//                      random.nextFloat() * -600));
//          });
//entities.addAll(cactus2);

                 }

        // запускаем цикл пока пользователь не закроет окно
        while (DisplayManager.shouldDisplayClose()) {
            camera.move(); // двигаем камеру
            //player.move(); // двигаем игрока
            player.forEach(p->{
                p.move();// двигаем игрока
                renderer.processEntity(p); // рисуем игрока
            });

            // рисуем объекты
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            for (Entity entity : entities) {
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
