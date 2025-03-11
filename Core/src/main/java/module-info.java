module Core {
    requires javafx.controls;
    requires javafx.graphics;
    requires Common;
    opens dk.sdu.cbse.main to javafx.graphics;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
    uses dk.sdu.cbse.common.services.IGamePluginService;
}
