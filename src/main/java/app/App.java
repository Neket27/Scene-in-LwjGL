package app;

import de.javagl.obj.*;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import org.joml.Vector3f;
import org.joml.Vector4f;
import render.DisplayManager;
import render.Loader;
import render.MasterRenderer;
import render.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import utils.UtilsLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class App {
    public static void main(String[] args) throws IOException {
        DisplayManager.createDisplay();

        Loader loader = new Loader(); // загрузчик моделей
        Terrain terrain =  UtilsLoader.loadLandscape(loader,0,-1);
        Terrain terrain2 = UtilsLoader.loadLandscape(loader,-1,-1);

        Player player = UtilsLoader.loadPlayer(loader);


        List<Entity> entities = new ArrayList<>();

        entities.addAll(createEntityWithMaterial(loader,"res/Garage/GarageOBJ/garage.obj","res/Garage/GarageOBJ",0));

//
//        IntBuffer indicesS = ObjData.getFaceVertexIndices(obj, 3);
//        FloatBuffer verticesS = ObjData.getVertices(obj);
//        FloatBuffer texCoordsS = ObjData.getTexCoords(obj, 2);
//        FloatBuffer normalsS = ObjData.getNormals(obj);

        // загружаем модель в память OpenGL
        RawModel model = OBJLoader.loadObjModel("res/tutorial14/tree.obj", loader);


        // Создание текстурной модели
        ModelTexture texture = new ModelTexture(loader.loadTexture("res/garage2020.png"));
        // Установка переменных блеска
        texture.setShineDamper(20);
        texture.setReflectivity(0f);
        //sendToRenderer(mtl, materialGroup);

//        InputStream objInputStream3 = new FileInputStream("res/double_garage.obj");
//        Obj obj2 = ObjReader.read(objInputStream3);
//        obj2 = ObjUtils.convertToRenderable(obj2);
//        faceVertexIndices = ObjData.getFaceVertexIndicesArray(obj2);
//        vertices = ObjData.getVerticesArray(obj2);
//        texCoords = ObjData.getTexCoordsArray(obj2, 2);
//        normals = ObjData.getNormalsArray(obj2);
//        RawModel m2 = loader.loadToVao(vertices, texCoords, normals, faceVertexIndices);
//
//        TexturedModel staticModel = new TexturedModel(m2, texture);
//        Entity gr = new Entity(staticModel, new Vector3f(40, 0, -15), 0, 0, 0, 3);
//        gr.increasePosition(0, 2, 0);
//        gr.increaseRotation(0, 150, 0);


        //entities.addAll(createEntityWithMaterial(loader,"res/double_garage.obj","res",30));
        //   entities.addAll(createEntityWithMaterial(loader,"res/Bambo_House_obj/Bambo_House.obj","res/Bambo_House_obj",60));
        entities.addAll(createEntityWithMaterial(loader,"res/bugatti/bugatti.obj","res/bugatti",120));
        entities.addAll(createEntityWithMaterial(loader,"res/Range_Rover_Sport_OBJ/2016 Custom Range Rover Sport.obj","res/Range_Rover_Sport_OBJ",180));
        // создание источника света
        Light light = new Light(new Vector3f(3000, 2000, 3000), new Vector3f(1, 1, 1));

        Camera camera = new Camera(player);

        MasterRenderer renderer = new MasterRenderer();


        // Загрузка текстурных моделей
// дерево
        TexturedModel staticModelS = new TexturedModel(OBJLoader.loadObjModel("res/tutorial15/tree.obj", loader),
                new ModelTexture(loader.loadTexture("res/tutorial15/tree.png")));
// трава
        TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("res/tutorial15/grassModel.obj", loader), new ModelTexture(
                loader.loadTexture("res/tutorial15/grassTexture.png")));
// папоротник
        TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("res/tutorial15/fern.obj", loader), new ModelTexture(
                loader.loadTexture("res/tutorial15/fern.png")));

        grass.getTexture().setHasTransparency(true); // включаем прозрачность текстур
        //grass.getTexture().setUseFakeLighting(true); // включаем фальшивое освещение


        Random random = new Random();
        for (int i = 0; i < 500; i++) {
            entities.add(new Entity(staticModelS, new Vector3f(random.nextFloat() * 800 - 400, 0,
                    random.nextFloat() * -600), 0, 0, 0, 3));
            entities.add(new Entity(grass, new Vector3f(random.nextFloat() * 800 - 400, 0,
                    random.nextFloat() * -600), 0, 0, 0, 1));
            entities.add(new Entity(fern, new Vector3f(random.nextFloat() * 800 - 400, 0,
                    random.nextFloat() * -600), 0, 0, 0, 0.6f));

            entities.add(new Entity(grass, new Vector3f(random.nextFloat() * 800 - 400, 0,
                    random.nextFloat() * -600), 0, 0, 0, 1));
          List<Entity> cactus2 =  createEntityWithMaterial(loader,"res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f/10436_Cactus_v1_max2010_it2.obj","res/cactus/107466_open3dmodel.com/10436_Cactus_v1_L3.123ce67a3113-eb68-4881-a89e-34941294e48f",0);

          cactus2.forEach(e->{
              e.getModel().getTexture().setShineDamper(20);
              e.getModel().getTexture().setReflectivity(1);

              e.setScale(0.05f);
              e.increaseRotation(-90,0,0);
              e.increaseRotation(0, -150, 0);
              e.getModel().getTexture().setHasTransparency(true); // включаем прозрачность текстур
              e.getModel().getTexture().setUseFakeLighting(true); // включаем фальшивое освещение
              e.setPosition(new Vector3f(random.nextFloat() * 800 - 400, 0,
                      random.nextFloat() * -600));
          });
entities.addAll(cactus2);

        }
        // запускаем цикл пока пользователь не закроет окно
        while (DisplayManager.shouldDisplayClose()) {
            camera.move(); // двигаем камеру
            player.move(); // двигаем игрока
            renderer.processEntity(player); // рисуем игрока
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

    @SuppressWarnings("unused")
    private static void sendToRenderer(Mtl mtl, Obj obj)
    {
        // Extract the relevant material properties. These properties can
        // be used to set up the renderer. For example, they may be passed
        // as uniform variables to a shader
        FloatTuple diffuseColor = mtl.getKd();
        FloatTuple specularColor = mtl.getKs();

        // Extract the geometry data. This data can be used to create
        // the vertex buffer objects and vertex array objects for OpenGL
        IntBuffer indices = ObjData.getFaceVertexIndices(obj);
        FloatBuffer vertices = ObjData.getVertices(obj);
        FloatBuffer texCoords = ObjData.getTexCoords(obj, 2);
        FloatBuffer normals = ObjData.getNormals(obj);
        // ...

        // Print some information about the object that would be rendered
        System.out.println(
                "Rendering an object with " + (indices.capacity() / 3)
                        + " triangles with " + mtl.getName()
                        + ", having diffuse color " + diffuseColor);

    }

    /**
     * Returns the Mtl from the given sequence that has the given name,
     * or <code>null</code> if no such Mtl can be found
     *
     * @param mtls The Mtl instances
     * @param name The material name
     * @return The Mtl with the given material name
     */
    private static Mtl findMtlForName(Iterable<? extends Mtl> mtls, String name)
    {
        for (Mtl mtl : mtls)
        {
            if (mtl.getName().equals(name))
            {
                return mtl;
            }
        }
        return null;
    }

    private static List<Entity> createEntityWithMaterial(Loader loader, String objPath, String folderPathWithTexture,int x) throws IOException {
       List<Entity> entities =new ArrayList<>();
        InputStream objInputStream = new FileInputStream(objPath);
        Obj obj = ObjReader.read(objInputStream);
        obj = ObjUtils.convertToRenderable(obj);
        List<Mtl> allMtls = new ArrayList<Mtl>();

        for (String mtlFileName : obj.getMtlFileNames()) {
            InputStream mtlInputStream = new FileInputStream(folderPathWithTexture+"/" + mtlFileName);
            List<Mtl> mtls = MtlReader.read(mtlInputStream);
            allMtls.addAll(mtls);
        }

        Map<String, Obj> materialGroups = ObjSplitting.splitByMaterialGroups(obj);
        int[] faceVertexIndices;
        float[] vertices;
        float[] texCoords;
        float[] normals;
        Entity m = null;
        for (Map.Entry<String, Obj> entry : materialGroups.entrySet()) {
            String materialName = entry.getKey();
            Obj materialGroup = entry.getValue();

         //   System.out.println("Material name  : " + materialName);
          //  System.out.println("Material group : " + materialGroup);

            // Find the MTL that defines the material with the current name
            Mtl mtl = findMtlForName(allMtls, materialName);
            FloatTuple diffuseColor = mtl.getKd();
            assert mtl != null;
          //  if(mtl.getMapKdOptions()!=null)
          //  System.out.println("bump =" + mtl.getMapKdOptions().getFileName());
            //  System.out.println("v =" + materialGroup.getVertex(0));
            // Render the current material group with this material:


            faceVertexIndices = ObjData.getFaceVertexIndicesArray(materialGroup);
            vertices = ObjData.getVerticesArray(materialGroup);
            texCoords = ObjData.getTexCoordsArray(materialGroup, 2);
            normals = ObjData.getNormalsArray(materialGroup);
            RawModel m2 = loader.loadToVao(vertices, texCoords, normals, faceVertexIndices);
//String name=mtl.getName();
            String name = "res/Garage/GarageOBJ/mtl_4_spec.tga";
          //  ModelTexture texture = new ModelTexture(loader.loadTexture(folderPathWithTexture+"/" + mtl.getMapKdOptions().getFileName()));
            ModelTexture texture = new ModelTexture();
          //  System.out.println("diffuseColor= "+diffuseColor);
            if(mtl.getMapKdOptions()!=null)
            texture = new ModelTexture(loader.loadTexture(folderPathWithTexture+"/" + mtl.getMapKdOptions().getFileName()));
            else
               //texture = new ModelTexture(loader.loadTexture("res/11.png"));
            texture.setColor(new Vector4f(diffuseColor.getX(), diffuseColor.getY(), diffuseColor.getZ(),1));
            // Установка переменных блеска
            texture.setShineDamper(20);
            texture.setReflectivity(0f);
            //sendToRenderer(mtl, materialGroup);

            TexturedModel staticModel = new TexturedModel(m2, texture);

            m = new Entity(staticModel, new Vector3f(x, 0, -15), 0, 0, 0, 3);
            m.increasePosition(0, 2, 0);
            m.increaseRotation(0, 150, 0);
            entities.add(m);

        }
        return entities;
    }


}
