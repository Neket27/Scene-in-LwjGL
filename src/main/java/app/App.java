package app;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import io.Keyboard;
import org.joml.Vector3f;
import render.DisplayManager;
import render.Loader;
import render.MasterRenderer;
import service.EntityService;
import service.LandscapeService;
import service.PlayerService;
import terrains.Terrain;
import utils.RandomCords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class App {
    public static void main(String[] args) throws IOException {
        DisplayManager.createDisplay();
        LandscapeService landscapeService =new LandscapeService();
        EntityService entityService = new EntityService();
        PlayerService playerService = new PlayerService();

        Loader loader = new Loader(); // загрузчик моделей
        Terrain terrain = landscapeService.create(loader,0,-1,"res/wate.png","res/a3.png","res/pesok-peschinki-plyazh-pustynya.png","res/tutorial21/heightmap.png","res/mapForautoDrom3.png");
        Terrain terrain2 = landscapeService.create(loader,-1,-1,"res/wate.png","res/a3.png","res/pesok-peschinki-plyazh-pustynya.png","res/tutorial21/heightmap.png","res/mapForautoDrom3.png");
        //Player player = playerService.create(loader);
        List<Player> player =playerService.create(loader,"res/bugatti/bugatti.obj","res/bugatti");
        Camera camera = new Camera(player);
        MasterRenderer renderer = new MasterRenderer();

        List<Entity> entities = new ArrayList<>();
        List <Entity> garage =entityService.createEntityWithMaterial(loader,"res/Garage/GarageOBJ/garage_without_door/garage_without_door.obj","res/Garage/GarageOBJ/garage_without_door",-100,0,-30,6,1,0,false,false);
        garage.forEach(e->e.increaseRotation(0,140,0));
        entities.addAll(garage);

        float x =0;
        float y =0;
        float z=0;
        float x2=0;
        float y2 =0;
        float z2=0;
        int pX=-100;
        int pZ=-30;
        float rY=140;
        float ppX=-100;
        float ppZ=-30;
        float ppY=140;
        boolean pushOpenLeftDoor=true;
        boolean pushOpenRightDoor=true;


        List <Entity> garageLeftDoor =entityService.createEntityWithMaterial(loader,"res/Garage/GarageOBJ/left_door/left_door.obj","res/Garage/GarageOBJ/left_door",-100,0,-30,6,1,0,false,false);
        garageLeftDoor.forEach(e->e.increaseRotation(0,140,0));
        entities.addAll(garageLeftDoor);



        List <Entity> garageRightDoor =entityService.createEntityWithMaterial(loader,"res/Garage/GarageOBJ/right_door/right_door.obj","res/Garage/GarageOBJ/right_door",-100,0,-30,6,1,0,false,false);
        garageRightDoor.forEach(e->e.increaseRotation(0,140,0));
        entities.addAll(garageRightDoor);

        List<Entity> house = entityService.createEntityWithMaterial(loader, "res/Bambo_House_obj/Bambo_House.obj", "res/Bambo_House_obj", -45, 0, -25, 5,1,0,false,false);
        house.forEach(e->{
            e.getModel().getTexture().setUseFakeLighting(true);
            e.getModel().getTexture().setReflectivity(0.1f);
            e.increaseRotation(0,-45,0);
        });

        entities.addAll(house);
       // entities.addAll(entityService.createEntityWithMaterial(loader,"res/bugatti/bugatti.obj","res/bugatti",120,0,-30,2));
        entities.addAll(entityService.createEntityWithMaterial(loader,"res/Range_Rover_Sport_OBJ/2016 Custom Range Rover Sport.obj","res/Range_Rover_Sport_OBJ",-75,1,-60,6,1,0,false,false));
       // entities.add(entityService.createEntityWithTexture(loader,"res/cube2/cube.obj","res/cube/mikepanskyboxtextures/dayfair.jpg",true,true,new Vector3f(0,10,100), 0, 0, 0, 30));
        entities.addAll(entityService.createEntityWithMaterial(loader,"res/sphere/sphere.obj","res/sphere",100,100,-100,0,0,0,20000,1,1.0f,true,true));
        // создание источника света
        Light light = new Light(new Vector3f(3000, 400000, -3000), new Vector3f(1f, 1f, 1f));

       // entities.addAll(entityService.createEntityWithMaterial(loader,"res/Bambo_House_obj/Bambo_House.obj","res/Bambo_House_obj",0,0,0,3));
      //  List<Entity> cactus2 =  entityService.createEntityWithMaterial(loader,"res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f/10436_Cactus_v1_max2010_it2.obj","res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f",0,0,0);

        List<Entity> cactus2 =  entityService.createEntityWithMaterial(loader,"res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f/10436_Cactus_v1_max2010_it2.obj","res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f",10,0,0,-90,0,0,0.06f,1,0,false,false);
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


        boolean rightDoorOpen=false;
        boolean leftDoorOpen=false;

        boolean interdictionOpenDoor=false;
        boolean doOpenRightDoor=false;
        boolean doOpenLeftDoor=false;
        float incX=0;
        float incZ=0;

        // запускаем цикл пока пользователь не закроет окно
        while (DisplayManager.shouldDisplayClose()) {
            camera.move(); // двигаем камеру
            //player.move(); // двигаем игрока
            player.forEach(p->{
                p.move();// двигаем игрока
                renderer.processEntity(p); // рисуем игрока
            });


            if (Keyboard.isKeyDown(GLFW_KEY_I)   ) {
                x+= 0.1F;
                incX+=0.1f;
                System.out.println(x+" "+y+" "+z);
            } else if (Keyboard.isKeyDown(GLFW_KEY_O)) {
                y+= 0.1F;
                System.out.println(x+" "+y+" "+z);
            } else if (Keyboard.isKeyDown(GLFW_KEY_P)) {
                z+= 0.1F;
                incZ+=0.1f;
                System.out.println(x+" "+y+" "+z);
            }
//
            if (Keyboard.isKeyDown(GLFW_KEY_J) ) {
                x+= -0.1;
                incX-=0.1f;
                System.out.println(x+" "+y+" "+z);
            } else if (Keyboard.isKeyDown(GLFW_KEY_K)) {
                y+= -0.1;
                System.out.println(x+" "+y+" "+z);
            } else if (Keyboard.isKeyDown(GLFW_KEY_L)) {
                z+= -0.1;
                incZ-=0.1f;
                System.out.println(x+" "+y+" "+z);
            }


for (Entity entity:garageLeftDoor){
    entity.increasePosition(x,0,z);
    entity.increaseRotation(0,y,0);
}
            ppX+=x;
            ppZ+=z;
            ppY+=y;
            System.out.println("позиция: "+"X="+(ppX)+"Z="+(ppZ)+"Градусы= "+(ppY));
            //rotationRightDoor(garageRightDoor);
            x=0;y=0;z=0;

            if(Keyboard.isKeyDown(GLFW_KEY_M)&&!doOpenRightDoor) {
                rightDoorOpen=!rightDoorOpen;
                doOpenRightDoor=true;

            }
            else if(!Keyboard.isKeyDown(GLFW_KEY_M)&&doOpenRightDoor){
                openRightDoor(garageRightDoor,rightDoorOpen);
                doOpenRightDoor=false;
            }



            if(Keyboard.isKeyDown(GLFW_KEY_N)&&!doOpenLeftDoor) {
                leftDoorOpen=!leftDoorOpen;
                doOpenLeftDoor =true;
            }
             if(!Keyboard.isKeyDown(GLFW_KEY_N)&& doOpenLeftDoor){
                openLeftDoor(garageLeftDoor,leftDoorOpen);
                doOpenLeftDoor =false;
            }



//                if (Keyboard.isKeyDown(GLFW_KEY_ENTER)) {
//                    pushOpenRightDoor = true;
//                    openRightDoor(garageRightDoor, pushOpenRightDoor);



//                if (Keyboard.isKeyDown(GLFW_KEY_ENTER)) {
//                    pushOpenRightDoor = true;
//                    openRightDoor(garageRightDoor, pushOpenRightDoor);



          //  System.out.println("M= "+ Keyboard.isKeyDown(GLFW_KEY_M));
            //System.out.println("rightDoorOpen= "+ rightDoorOpen);
            System.out.println("position= "+ garageRightDoor.get(garageRightDoor.size()-1).getPosition().toString());
            System.out.println("inc="+incX+" "+incZ);

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



    static  void openLeftDoor(List<Entity> entities, boolean pushOpenLeftDoor){
        if(pushOpenLeftDoor){
            for (Entity entity: entities) {
                // entity.setRotationY(228.4f);
                entity.increaseRotation(0,-89,0);
            }
            for (Entity entity: entities) {
                entity.increasePosition(new Vector3f(12.7f,0,-30.6f));
            }
        }
        else {
            for (Entity entity: entities) {
                // entity.setRotationY(228.4f);
                entity.increaseRotation(0,89,0);
            }
            for (Entity entity: entities) {
                entity.increasePosition(new Vector3f(-12.7f,0,30.6f));
            }
        }
        }


    static void openRightDoor(List<Entity> entities, boolean pushOpenRightDoor){
        if(pushOpenRightDoor){
            for (Entity entity: entities) {
               // entity.setRotationY(228.4f);
                entity.increaseRotation(0,88,0);
            }
            for (Entity entity: entities) {
                entity.increasePosition(new Vector3f(28.5f,0,-17.9f));
            }
        }
        else {
            for (Entity entity: entities) {
                // entity.setRotationY(228.4f);
                entity.increaseRotation(0,-88,0);
            }
            for (Entity entity: entities) {
                entity.increasePosition(new Vector3f(-28.5f,0,17.9f));
            }
        }


    }


}
