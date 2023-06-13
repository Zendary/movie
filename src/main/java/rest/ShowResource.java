package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ShowDTO;
import facades.ShowFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("show")
public class ShowResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final ShowFacade FACADE = ShowFacade.getShowFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllShows() {
        List<ShowDTO> shows = FACADE.getAllShows();
        String json = GSON.toJson(shows);
        return Response.ok(json).build();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createShow(String showJson) {
        ShowDTO showDTO = GSON.fromJson(showJson, ShowDTO.class);
        ShowDTO createdShow = FACADE.createShow(showDTO);
        String json = GSON.toJson(createdShow);
        return Response.ok(json).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteShow(@PathParam("id") Long id) {
        FACADE.deleteShow(id);
        return Response.ok().build();
    }

}
