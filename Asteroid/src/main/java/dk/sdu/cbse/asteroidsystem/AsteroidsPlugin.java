package dk.sdu.cbse.asteroidsystem;

import java.util.Arrays;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class AsteroidsPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
    }

    @Override
    public void stop(GameData gameData, World world) {
        // TODO Auto-generated method stub
        System.out.println("AsteroidsPlugin stopped");
    }

    }
