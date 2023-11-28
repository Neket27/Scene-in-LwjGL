package utils;

import render.Loader;
import terrains.Terrain;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public interface LoaderLandscape {

    static Terrain loadLandscape(Loader loader, int x, int z){
        // загрузка текстур ландшафта
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("res/asphalt.png"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("res/a3.png"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("res/yellow.png")); //-
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("res/tutorial21/heightmap.png"));
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        // загрузка карты смешивания текстур ландшафта
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("res/mapForautoDrom3.png"));
        return new Terrain(x, z, loader, texturePack, blendMap);
    }
}
