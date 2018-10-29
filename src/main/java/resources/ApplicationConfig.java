/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author LIZ
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(resources.AccountFacadeREST.class);
        resources.add(resources.AdresFacadeREST.class);
        resources.add(resources.ArtikelFacadeREST.class);
        resources.add(resources.BestellingFacadeREST.class);
        resources.add(resources.BestelregelFacadeREST.class);
        resources.add(resources.KlantFacadeREST.class);
    }
    
}
