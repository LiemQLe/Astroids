module Core {
    requires javafx.controls;
    requires javafx.graphics;
    requires Common;
    
    requires spring.core;
    requires spring.beans;
    requires spring.context;


    opens dk.sdu.cbse.main to javafx.graphics, spring.core, spring.beans, spring.context;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
    uses dk.sdu.cbse.common.services.IGamePluginService;
}
