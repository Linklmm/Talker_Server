package net.qiujuer.web.italker.push;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import net.qiujuer.web.italker.push.service.AccountService;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Logger;

public class Application extends ResourceConfig {


    public Application(){
        packages(AccountService.class.getPackage().getName());

        register(JacksonJsonProvider.class);

        register(Logger.class);
    }

}
