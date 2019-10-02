package me.codeminions;

import me.codeminions.provider.GsonProvider;
import me.codeminions.service.PicKnowService;
import me.codeminions.service.TestService;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;

public class Application extends ResourceConfig {

    public Application() {
        packages(PicKnowService.class.getPackage().getName());

        register(GsonProvider.class);
        register(MultiPartFeature.class);

        register(Logger.class);
    }
}
