package dk.sdu.cbse.testplayer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.main.Game;
import dk.sdu.cbse.playersystem.Player;
import dk.sdu.cbse.playersystem.PlayerPlugin;

public class PlayerControlSystemTest {

    private GameData gameData;
    private World world;

    private PlayerPlugin playerPlugin;
    private Player player;

    @BeforeEach
    public void setup() {
        playerPlugin = new PlayerPlugin();
        gameData = mock(GameData.class);
        world = mock(World.class);
        Entity entity = playerPlugin.createShip(gameData);
        player = (Player) entity;
    }

    @Test
    public void testPlayerCreation() {
        assertNotNull(player, "Player should not be null");
    }

    @Test
    public void testPlayerMovementUp() {
        player.setY(player.getY() + player.getSpeed());
        assertTrue(player.getY() > 0, "Player should move up");
    }

    @Test
    public void testPlayerMovementDown() {
        player.setY(player.getY() - player.getSpeed());
        assertTrue(player.getY() < gameData.getDisplayHeight(), "Player should move down");
    }

    @Test
    public void testPlayerMovementLeft() {
        player.setX(player.getX() - player.getSpeed());
        assertTrue(player.getX() < gameData.getDisplayWidth(), "Player should move left");
    }

    @Test
    public void testPlayerMovementRight() {
        player.setX(player.getX() + player.getSpeed());
        assertTrue(player.getX() > 0, "Player should move right");
    }
}
