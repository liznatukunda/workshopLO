/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sessions.BestellingFacade;
import workshop3lo.domain.Bestelling;

/**
 *
 * @author LIZ
 */
@Stateless
@Path("bestelling")
public class BestellingFacadeREST {

    @EJB
    private BestellingFacade dao;

    public BestellingFacadeREST() {

    }

    @POST

    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Bestelling entity) {
        dao.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Bestelling entity) {
        dao.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        dao.remove(dao.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bestelling find(@PathParam("id") Integer id) {
        return dao.find(id);
    }

    @GET

    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bestelling> findAll() {
        return dao.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Bestelling> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return dao.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(dao.count());
    }

//    @GET
//    @Path("search/{query}")
//    @Produces({MediaType.APPLICATION_JSON})
//    public List<Bestelling> getAlleBestellingenPerKlant(@PathParam("query") String query) {
//        return getAlleBestellingenPerKlant(query);
//    }
}
