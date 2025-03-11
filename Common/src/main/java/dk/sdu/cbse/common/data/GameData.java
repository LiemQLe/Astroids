package dk.sdu.cbse.common.data;

public class GameData {
    private final GameKeys keys = new GameKeys();
    private int displayWidth = 800;
    private int displayHeight = 800;

    public GameKeys getKeys(){
        return this.keys;
    }

    public int getDisplayWidth(){
        return this.displayWidth;
    }

    public int getDisplayHeight(){
        return this.displayHeight;
    }

    public void setDisplayWidth(int width){
        this.displayWidth = width;
    }

    public void setDisplayHeight(int height){
        this.displayHeight = height;
    }
}
