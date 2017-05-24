package main.com.bsuir.autoservice.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import main.com.bsuir.autoservice.binding.AutoServiceShopModule;
import main.com.bsuir.autoservice.servlet.FrontServlet;
import main.com.bsuir.autoservice.servlet.filter.DatabaseConnectionFilter;
import main.com.bsuir.autoservice.servlet.filter.PermissionFilter;

public class ServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new AutoServiceShopModule(){

            @Override
            protected void configureServlets() {
                configBindings();
                bindServlets();
                bindFilters();
            }

            private void bindServlets() {
                serve("*.ass").with(FrontServlet.class);
            }

            private void bindFilters() {
                filter("*.ass").through(PermissionFilter.class);
                filter("*").through(DatabaseConnectionFilter.class);
            }
        });
    }
}
