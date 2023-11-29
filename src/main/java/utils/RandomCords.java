package utils;

import entities.Entity;
import org.joml.Vector3f;

import java.util.List;
import java.util.Random;

public class RandomCords {

    Random random = new Random();

    public RandomCords(){}

    public Vector3f random(){
            Vector3f cords = new Vector3f(random.nextFloat() * 800 - 400, 0,
                    random.nextFloat() * -600);

//        entities.add(new Entity(staticModelS, cords, 0, 0, 0, 3));
//        entities.add(new Entity(grass, cords,
//                random.nextFloat() * -600), 0, 0, 0, 1));
//        entities.add(new Entity(fern, new Vector3f(random.nextFloat() * 800 - 400, 0,
//                random.nextFloat() * -600), 0, 0, 0, 0.6f));
//
//        entities.add(new Entity(grass, new Vector3f(random.nextFloat() * 800 - 400, 0,
//                random.nextFloat() * -600), 0, 0, 0, 1));
//        List<Entity> cactus2 =  entityService.createEntityWithMaterial(loader,"res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f/10436_Cactus_v1_max2010_it2.obj","res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f",0,0,0);
//
//        cactus2.forEach(e->{
//            e.getModel().getTexture().setShineDamper(20);
//            e.getModel().getTexture().setReflectivity(1);
//
//            e.setScale(0.05f);
//            e.increaseRotation(-90,0,0);
//            e.increaseRotation(0, -150, 0);
//            e.getModel().getTexture().setHasTransparency(true); // включаем прозрачность текстур
//            e.getModel().getTexture().setUseFakeLighting(true); // включаем фальшивое освещение
//            e.setPosition(new Vector3f(random.nextFloat() * 800 - 400, 0,
//                    random.nextFloat() * -600));
            // });
            //entities.addAll(cactus2);

        return cords;

    }

}
