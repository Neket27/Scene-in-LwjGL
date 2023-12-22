package app;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import org.joml.Vector3f;
import render.DisplayManager;
import render.Loader;
import render.MasterRenderer;
import service.EntityService;
import service.LandscapeService;
import service.PlayerService;
import terrains.Terrain;
import utils.DarkeningOfSky.DarkeningOfSky;
import utils.RandomCords;
import utils.openDoor.OpenDoor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        DisplayManager.createDisplay();
        LandscapeService landscapeService =new LandscapeService();
        EntityService entityService = new EntityService();
        PlayerService playerService = new PlayerService();

        Loader loader = new Loader(); // загрузчик моделей
        Terrain terrain = landscapeService.create(loader,0,-1,"res/grass.png","res/wate.png","res/pesok-peschinki-plyazh-pustynya.png","res/asphalt-1-2.png","res/mapForautoDrom4.png");
        Terrain terrain2 = landscapeService.create(loader,-1,-1,"res/grass.png","res/wate.png","res/pesok-peschinki-plyazh-pustynya.png","res/asphalt-1-2.png","res/mapForautoDrom4.png");
        List<Player> player =playerService.create(loader,"res/bugatti/bugatti.obj","res/bugatti");
        player.forEach(p->p.setPosition(new Vector3f(-220f,0f,-650f)));
        player.forEach(p->p.increaseRotation(0,200,0));
        Camera camera = new Camera(player);
        MasterRenderer renderer = new MasterRenderer();
        // создание источника света
        Light light;
        // рандом координат
        RandomCords randomCords = new RandomCords();

        List<Entity> entities = new ArrayList<>();
        List <Entity> garage =entityService.createEntityWithMaterial(loader,"res/Garage/GarageOBJ/garage_without_door/garage_without_door.obj","res/Garage/GarageOBJ/garage_without_door",-220,0,-650,6,1,0,false,false);
        garage.forEach(e->e.increaseRotation(0,30,0));
        entities.addAll(garage);

        List <Entity> garageLeftDoor =entityService.createEntityWithMaterial(loader,"res/Garage/GarageOBJ/left_door/left_door.obj","res/Garage/GarageOBJ/left_door",-220,0,-650,6,1,0,false,false);
        garageLeftDoor.forEach(e->e.increaseRotation(0,30,0));
        entities.addAll(garageLeftDoor);

        List <Entity> garageRightDoor =entityService.createEntityWithMaterial(loader,"res/Garage/GarageOBJ/right_door/right_door.obj","res/Garage/GarageOBJ/right_door",-220,0,-650,6,1,0,false,false);
        garageRightDoor.forEach(e->e.increaseRotation(0,30,0));
        entities.addAll(garageRightDoor);

        List<Entity> house = entityService.createEntityWithMaterial(loader, "res/Bambo_House_obj/Bambo_House.obj", "res/Bambo_House_obj", -195, 0, -460, 5,1,0,false,false);
        house.forEach(e->{
            e.getModel().getTexture().setUseFakeLighting(true);
            e.getModel().getTexture().setReflectivity(0.1f);
            e.increaseRotation(0,-45,0);
        });

        entities.addAll(house);
        entities.addAll(entityService.createEntityWithMaterial(loader,"res/Range_Rover_Sport_OBJ/2016 Custom Range Rover Sport.obj","res/Range_Rover_Sport_OBJ",-180,1,-700,6,1,0,false,false));
        entities.addAll(entityService.createEntityWithMaterial(loader,"res/sphere/sphere.obj","res/sphere",100,100,-100,0,0,0,20000,1,1.0f,true,true));

        //размещение травы
        for (int i = 0; i < 100; i++) {
            entities.add(entityService.createEntityWithTexture(loader,"res/grass/grassModel.obj","res/grass/grassTexture.png",true,true,randomCords.random(),0,0,0,1));
            entities.add(entityService.createEntityWithTexture(loader,"res/grass/fern.obj","res/grass/fern.png",true,true,randomCords.random(), 0, 0, 0, 1));
                 }

        // запускаем цикл пока пользователь не закроет окно
        while (DisplayManager.shouldDisplayClose()) {
            camera.move(); // двигаем камеру
            player.forEach(p->{
                p.move();// двигаем игрока
                renderer.processEntity(p); // рисуем игрока
            });

            OpenDoor.openLeftDoorGarage(garageLeftDoor);
            OpenDoor.openRightDoorGarage(garageRightDoor);
            light=DarkeningOfSky.start();

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
