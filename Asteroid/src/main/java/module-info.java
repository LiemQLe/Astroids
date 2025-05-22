
module Asteroids {
    requires Common;

    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.asteroidsystem.AsteroidsPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.asteroidsystem.AsteroidsProcessor;

}
