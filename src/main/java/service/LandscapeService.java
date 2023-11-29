package service;

import render.Loader;
import terrains.Terrain;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class LandscapeService {

    public LandscapeService(){}
   public Terrain create(Loader loader, int x, int z, String pathBackgroundTexture,String pathRTexture, String pathGTexture,String pathBTexture,String pathBlendMap){
        // загрузка текстур ландшафта
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("res/textuteSphere.png"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("res/a3.png"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("res/yellow.png")); //-
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("res/tutorial21/heightmap.png"));
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        // загрузка карты смешивания текстур ландшафта
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("res/mapForautoDrom3.png"));
        return new Terrain(x, z, loader, texturePack, blendMap);
    }
}
