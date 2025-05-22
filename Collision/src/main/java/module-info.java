module Collision {
    requires Common;
    requires javafx.graphics;
    requires CommonBullet;

    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.collisionsystem.CollisionProcessor;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.collisionsystem.CollisionProcessor;
}
