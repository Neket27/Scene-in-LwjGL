package service;

import de.javagl.obj.*;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import org.joml.Vector3f;
import org.joml.Vector4f;
import render.Loader;
import render.OBJLoader;
import textures.ModelTexture;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntityService {

    public EntityService() {}

    public Entity createEntityWithTexture(Loader loader, String objPath, String pathFolderWithTexture,boolean hasTransparency, boolean useFakeLighting, Vector3f cords, float scale) throws IOException {
        return createEntityWithTexture(loader,objPath,pathFolderWithTexture,hasTransparency,useFakeLighting,cords, 0, 0, 0, scale);
    }

    public Entity createEntityWithTexture(Loader loader, String pathObj, String pathTexture, boolean hasTransparency, boolean useFakeLighting, Vector3f cords, float rotationX,float rotationY, float rotationZ, float scale){
        // Загрузка текстурных моделей
        TexturedModel texturedModel = new TexturedModel(OBJLoader.loadObjModel(pathObj, loader),
                new ModelTexture(loader.loadTexture(pathTexture)));
        //texturedModel.getTexture().setHasTransparency(hasTransparency); // включаем прозрачность текстур
       // texturedModel.getTexture().setUseFakeLighting(useFakeLighting); // включаем фальшивое освещение

        return  new Entity(texturedModel,cords,rotationX,rotationY,rotationZ,scale);
    }

    public   List<Entity> createEntityWithMaterial(Loader loader, String objPath, String folderPathWithTexture, Vector3f cords) throws IOException {
        return createEntityWithMaterial( loader,objPath,folderPathWithTexture,(int)cords.x,(int)cords.y,(int)cords.z,0,0,0,1);
    }

    public   List<Entity> createEntityWithMaterial(Loader loader, String objPath, String folderPathWithTexture, int x,int y, int z) throws IOException {
        return createEntityWithMaterial( loader,objPath,folderPathWithTexture,x,y,z,0,0,0,1);
    }

    public   List<Entity> createEntityWithMaterial(Loader loader, String objPath, String folderPathWithTexture, int x,int y, int z, float scale) throws IOException {
        return createEntityWithMaterial( loader,objPath,folderPathWithTexture,x,y,z,0,0,0,scale);
    }

    public   List<Entity> createEntityWithMaterial(Loader loader, String objPath, String folderPathWithTexture, int x,int y, int z, float rotationX, float rotationY, float rotationZ, float scale) throws IOException {
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

            faceVertexIndices = ObjData.getFaceVertexIndicesArray(materialGroup);
            vertices = ObjData.getVerticesArray(materialGroup);
            texCoords = ObjData.getTexCoordsArray(materialGroup, 2);
            normals = ObjData.getNormalsArray(materialGroup);
            RawModel m2 = loader.loadToVao(vertices, texCoords, normals, faceVertexIndices);

            ModelTexture texture = new ModelTexture();
            //  System.out.println("diffuseColor= "+diffuseColor);
            if(mtl.getMapKdOptions()!=null)
                texture = new ModelTexture(loader.loadTexture(folderPathWithTexture+"/" + mtl.getMapKdOptions().getFileName()));
            else
                //texture = new ModelTexture(loader.loadTexture("res/11.png"));
                texture.setColor(new Vector4f(diffuseColor.getX(), diffuseColor.getY(), diffuseColor.getZ(),1));
            // Установка переменных блеска
           // texture.setShineDamper(20);
           // texture.setReflectivity(0f);

            TexturedModel staticModel = new TexturedModel(m2, texture);

            m = new Entity(staticModel, new Vector3f(x, y, z), rotationX, rotationY, rotationZ, scale);
           // m.increasePosition(0, 2, 0);
          //  m.increaseRotation(0, 150, 0);
            entities.add(m);

        }
        return entities;
    }

    /**
     * Возвращает Mtl из заданной последовательности, имеющей заданное имя,
     * или <code>null</code>, если такой Mtl не может быть найден
     *
     * @param mtls Экземпляры Mtl
     * @param name Название материала
     * @return Mtl с заданным названием материала
     */


    private Mtl findMtlForName(Iterable<? extends Mtl> mtls, String name)
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

}
