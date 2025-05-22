module Collision {
    requires Common;

    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.collision.CollisionProcessor;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.collision.CollisionPlugin;
}
