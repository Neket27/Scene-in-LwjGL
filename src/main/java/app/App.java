package app;

import de.javagl.obj.*;
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


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {
       DisplayManager.createDisplay();

        Loader loader = new Loader(); // загрузчик моделей

        InputStream objInputStream = new FileInputStream("res/Garage/GarageOBJ/garage.obj");
        Obj obj = ObjReader.read(objInputStream);
        obj = ObjUtils.convertToRenderable(obj);
        int faceVertexIndices[];
        float vertices[];
        float texCoords[];
        float normals[];
        List<Entity> entities = new ArrayList<>();
        List<Mtl> allMtls = new ArrayList<Mtl>();
        for (String mtlFileName : obj.getMtlFileNames())
        {
            InputStream mtlInputStream =
                    new FileInputStream("res/Garage/GarageOBJ/" + mtlFileName);
            List<Mtl> mtls = MtlReader.read(mtlInputStream);
            allMtls.addAll(mtls);
        }

        Map<String, Obj> materialGroups = ObjSplitting.splitByMaterialGroups(obj);
        for (Map.Entry<String, Obj> entry : materialGroups.entrySet())
        {
            String materialName = entry.getKey();
            Obj materialGroup = entry.getValue();

            System.out.println("Material name  : " + materialName);
            System.out.println("Material group : " + materialGroup);

            // Find the MTL that defines the material with the current name
            Mtl mtl = findMtlForName(allMtls, materialName);
          System.out.println("bump ="+  mtl.getBump());
            // Render the current material group with this material:


            faceVertexIndices = ObjData.getFaceVertexIndicesArray(materialGroup);
             vertices = ObjData.getVerticesArray(materialGroup);
             texCoords = ObjData.getTexCoordsArray(materialGroup, 2);
             normals = ObjData.getNormalsArray(materialGroup);
            RawModel m2= loader.loadToVao(vertices,texCoords,normals,faceVertexIndices);
//String name=mtl.getName();
            String name="res/Garage/GarageOBJ/mtl_4_spec.tga";
            ModelTexture texture = new ModelTexture(loader.loadTexture("res/Garage/GarageOBJ/"+mtl.getBump()));
            // Установка переменных блеска
            texture.setShineDamper(20);
            texture.setReflectivity(0f);
            //sendToRenderer(mtl, materialGroup);

            TexturedModel staticModel = new TexturedModel(m2, texture);



            Entity m =new Entity(staticModel, new Vector3f(0,0,-15),0,0,0,3);
            m.increacePosition(0,2,0);
            m.increaseRotation(0,150,0);
            entities.add(m);
        }

        IntBuffer indicesS = ObjData.getFaceVertexIndices(obj, 3);
        FloatBuffer verticesS = ObjData.getVertices(obj);
        FloatBuffer texCoordsS = ObjData.getTexCoords(obj, 2);
        FloatBuffer normalsS = ObjData.getNormals(obj);

//        int faceVertexIndices[] = ObjData.getFaceVertexIndicesArray(obj);
//        float vertices[] = ObjData.getVerticesArray(obj);
//        float texCoords[] = ObjData.getTexCoordsArray(obj, 2);
//        float normals[] = ObjData.getNormalsArray(obj);

        //System.out.println(vertices.length);
//        System.out.println("Indices:");
//        System.out.println(createString(indices, 3));
//
//        System.out.println("Vertices:");
//        System.out.println(createString(vertices, 3));
//
//        System.out.println("Texture coordinates:");
//        System.out.println(createString(texCoords, 2));
//
//        System.out.println("Normals:");
//        System.out.println(createString(normals, 3));
//       float[] a= toArrayFloat(vertices,3);
//       float[]t=toArrayFloat(texCoords,2);
//       float[]n=toArrayFloat(normals,3);
//       int[]i=toArrayFloat(indices,3);
//RawModel m2= loader.loadToVao(vertices,texCoords,normals,faceVertexIndices);
        float[] floatArray = new float[] { 3.4f, 3.5f, 3.6f, 3.7f, 3.8f, 3.1234f, 6.2344f, 8.34f, 9.8f };
        FloatBuffer bb = FloatBuffer.wrap(floatArray);
        System.out.println(Arrays.toString(bb.array()));

      //  System.out.println(indices.array().length);

//        try {
//            int capacity = 10;
//            // creating object of floatbuffer
//            // and allocating size capacity
//            FloatBuffer fb = FloatBuffer.allocate(capacity);
//
//            // putting the value in floatbuffer
//            fb.put(8.56F);
//            fb.put(2, 9.61F);
//          //  fb.rewind();
//
//            // getting array from fb FloatBuffer using array() method
//            float[] fbb = fb.array();
//            float[] fbb2 = vertices.array();
//
//            // printing the FloatBuffer fb
//            System.out.println("FloatBuffer:  "
//                    + Arrays.toString(fbb));
//
//            System.out.println("FloatBuffer2:  "
//                    + Arrays.toString(fbb2));
//
//        } finally {
//
//        };


        //RawModel r=loader.loadToVao(vertices.array(),texCoords.array(),normals.array(),indices.array());
//RawModel rawModel2=OBJLoader.loadObjModel(obj,loader);

        // загружаем модель в память OpenGL
        RawModel model = OBJLoader.loadObjModel("res/tutorial14/tree.obj", loader);
    //    RawModel model2 = OBJLoader.loadObjModel("res/Garage/GarageOBJ/garage.obj", loader);
        // загрузим текстуру используя загрузчик
//       ModelTexture texture = new ModelTexture(loader.loadTexture("res/11.png"));
//        // Установка переменных блеска
//        texture.setShineDamper(20);
//        texture.setReflectivity(0f);

        // Создание текстурной модели
        ModelTexture texture = new ModelTexture(loader.loadTexture("res/garage2020.png"));
        // Установка переменных блеска
        texture.setShineDamper(20);
        texture.setReflectivity(0f);
        //sendToRenderer(mtl, materialGroup);

        InputStream objInputStream2 = new FileInputStream("res/Garage/GarageOBJ/garage.obj");
        Obj obj2 = ObjReader.read(objInputStream2);
        obj2 = ObjUtils.convertToRenderable(obj2);
        faceVertexIndices = ObjData.getFaceVertexIndicesArray(obj2);
        vertices = ObjData.getVerticesArray(obj2);
        texCoords = ObjData.getTexCoordsArray(obj2, 2);
        normals = ObjData.getNormalsArray(obj2);
        RawModel m2= loader.loadToVao(vertices,texCoords,normals,faceVertexIndices);

        TexturedModel staticModel = new TexturedModel(m2, texture);
        Entity gr =new Entity(staticModel, new Vector3f(0,0,-15),0,0,0,3);
        gr.increacePosition(0,2,0);
        gr.increaseRotation(0,150,0);
        entities.add(gr);



        // создание источника света
        Light light = new Light(new Vector3f(3000, 2000, 3000), new Vector3f(1, 1, 1));

       Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("res/tutorial14/grass.png")));
        Terrain terrain2 = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("res/tutorial14/grass.png")));

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

    @SuppressWarnings("unused")
    private static void sendToRenderer(Mtl mtl, Obj obj)
    {
        // Extract the relevant material properties. These properties can
        // be used to set up the renderer. For example, they may be passed
        // as uniform variables to a shader
        FloatTuple diffuseColor = mtl.getKd();
        FloatTuple specularColor = mtl.getKs();
        // ...

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

    public static float[] toArray(final FloatBuffer buffer) {
        float[] array = new float[buffer.limit()];
        buffer.get(array);
        return array;
    }
    public static float[] toArrayFloat(FloatBuffer buffer, int stride){
        float[] arr= new float[buffer.capacity()];
        for (int i=0; i<buffer.capacity(); i+=stride){
            arr[i]=buffer.get(i);
        }
        return  arr;
    }

    public static int[] toArrayFloat(IntBuffer buffer, int stride){
        int[] arr= new int[buffer.capacity()];
        for (int i=0; i<buffer.capacity(); i+=stride){
            arr[i]=buffer.get(i);
        }
        return  arr;
    }

    private static String createString(IntBuffer buffer, int stride)
    {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<buffer.capacity(); i+=stride)
        {
            for (int j=0; j<stride; j++)
            {
                if (j > 0)
                {
                    sb.append(", ");
                }
                sb.append(buffer.get(i+j));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String createString(FloatBuffer buffer, int stride)
    {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<buffer.capacity(); i+=stride)
        {
            for (int j=0; j<stride; j++)
            {
                if (j > 0)
                {
                    sb.append(", ");
                }
                sb.append(buffer.get(i+j));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
