module Bullet {
    requires Common;
    requires CommonBullet;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.bullet.BulletPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.bullet.BulletLogic;
    provides dk.sdu.cbse.commonbullet.BulletSpawnerInterface with dk.sdu.cbse.bullet.BulletLogic;
   
}
