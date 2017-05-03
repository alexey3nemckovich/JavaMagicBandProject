package main.com.bsuir.autoservice.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import main.com.bsuir.autoservice.binding.AutoServiceShopModule;
import main.com.bsuir.autoservice.servlet.FrontServlet;

public class ServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new AutoServiceShopModule(){

            @Override
            protected void configureServlets() {
                serve("*.ass").with(FrontServlet.class);
                configBindings();
            }
        });
    }
}
