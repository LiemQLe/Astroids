package dk.sdu.cbse.main;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

@Configuration
@ComponentScan(basePackages = "dk.sdu.cbse")
public class ModuleConfig {
    public ModuleConfig() {

    }

    @Bean
    public Game game() {
        Game game = new Game();
        game.setPluginServices(gamePluginService());
        game.setEntityProcessingServices(entityProcessingServices());
        game.setPostEntityProcessingServices(postEntityProcessingServices());
        return game;
    }

    // Use ServiceLoader to populate the beans
    @Bean
    public List<IGamePluginService> gamePluginService() {
        return ServiceLoader
                .load(IGamePluginService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServices() {
        return ServiceLoader
                .load(IEntityProcessingService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLoader
                .load(IPostEntityProcessingService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
