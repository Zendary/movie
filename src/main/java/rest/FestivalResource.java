package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.FestivalDTO;
import dtos.ShowDTO;
import entities.Festival;
import facades.FestivalFacade;
import facades.ShowFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("festival")
public class FestivalResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final FestivalFacade FACADE = FestivalFacade.getFestivalFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getALlFestivals() {
        List<FestivalDTO> festivals = FACADE.getALlFestivals();
        String json = GSON.toJson(festivals);
        return Response.ok(json).build();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFestival(String showJson) {
        FestivalDTO festivalDTO = GSON.fromJson(showJson, FestivalDTO.class);
        FestivalDTO createdFestival = FACADE.createFestival(festivalDTO);
        String json = GSON.toJson(createdFestival);
        return Response.ok(json).build();
    }

    @DELETE
    @Path("delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFestival(@PathParam("id") Long id) {
        FACADE.deleteFestival(id);
        return Response.ok().build();
    }
}
