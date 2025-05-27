module Enemy {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.cbse.commonbullet.BulletSpawnerService;
    
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.enemysystem.EnemyPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.enemysystem.EnemyControlSystem;
}
