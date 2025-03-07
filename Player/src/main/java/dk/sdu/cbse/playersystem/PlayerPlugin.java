package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.Entity;


public class PlayerPlugin {

    private Entity player;

    public PlayerPlugin() {
        
    }

    public void setPosition(int x, int y) {
        player.setPosition(x, y);
    }
    
}
