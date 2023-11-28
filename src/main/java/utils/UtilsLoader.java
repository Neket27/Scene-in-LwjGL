package utils;

import entities.Player;
import render.Loader;
import terrains.Terrain;

import java.io.IOException;

public class UtilsLoader implements LoaderLandscape,LoaderPlayer{
    public static Terrain loadLandscape(Loader loader, int x, int z){
        return LoaderLandscape.loadLandscape(loader,x,z);
    }

    public static Player loadPlayer(Loader loader) throws IOException {

        return LoaderPlayer.loadPlayer(loader);
    }

}
