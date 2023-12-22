package service;

import de.javagl.obj.Obj;
import de.javagl.obj.ObjData;
import de.javagl.obj.ObjReader;
import de.javagl.obj.ObjUtils;
import entities.Entity;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import org.joml.Vector3f;
import render.Loader;
import textures.ModelTexture;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PlayerService {

    private EntityService entityService =new EntityService();
    public Player create(Loader loader) throws IOException {
        // Игрок
        int[] faceVertexIndices3;
        float[] vertices3;
        float[] texCoords3;
        float[] normals3;
        InputStream objInputStream2 = new FileInputStream("res/bugatti.obj");
        Obj obj3 = ObjReader.read(objInputStream2);
        obj3 = ObjUtils.convertToRenderable(obj3);
        faceVertexIndices3 = ObjData.getFaceVertexIndicesArray(obj3);
        vertices3 = ObjData.getVerticesArray(obj3);
        texCoords3 = ObjData.getTexCoordsArray(obj3, 2);
        normals3 = ObjData.getNormalsArray(obj3);
        RawModel m3 = loader.loadToVao(vertices3, texCoords3, normals3, faceVertexIndices3);
        ModelTexture texture3 = new ModelTexture(loader.loadTexture("res/white.png"));
        TexturedModel stanfordBunny = new TexturedModel(m3,texture3);
        return new Player(stanfordBunny, new Vector3f(100, 0, -50), 0, 0, 0, 1);
    }

    public List<Player> create(Loader loader, String pathObj, String pathToMaterial) throws IOException {
        // Игрок
       List<Entity> entity= entityService.createEntityWithMaterial(loader,pathObj,pathToMaterial,0,0,0,2.25f,1,0,false,false);
        List<Player>player= new ArrayList<>();
        entity.forEach(e->player.add(new Player(e.getModel(),e.getPosition(),e.getRotationX(),e.getRotationY(),e.getRotationZ(),e.getScale())));
        return player;
    }
}
