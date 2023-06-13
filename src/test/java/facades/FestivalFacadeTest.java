package facades;

import dtos.FestivalDTO;
import entities.Festival;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FestivalFacadeTest {

    private static EntityManagerFactory emf;
    private static FestivalFacade facade;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = FestivalFacade.getFestivalFacade(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Festival.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterAll
    public static void tearDownClass() {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Festival.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }



    @Test
    void getAllFestivals() {

        FestivalDTO festivalDTO1 = new FestivalDTO(1L,"Festival 1", "City 1", "2023-06-20", "2 days");
        FestivalDTO festivalDTO2 = new FestivalDTO(2L,"Festival 2", "City 2", "2023-07-01", "3 days");

        facade.createFestival(festivalDTO1);
        facade.createFestival(festivalDTO2);
        List<FestivalDTO> festivals = facade.getALlFestivals();

        assertNotNull(festivals);
        assertEquals(2, festivals.size());

        FestivalDTO festival1 = festivals.get(0);
        assertEquals("Festival 1", festival1.getName());
        assertEquals("City 1", festival1.getCity());
        assertEquals("2023-06-20", festival1.getStartDate());
        assertEquals("2 days", festival1.getDuration());

        FestivalDTO festival2 = festivals.get(1);
        assertEquals("Festival 2", festival2.getName());
        assertEquals("City 2", festival2.getCity());
        assertEquals("2023-07-01", festival2.getStartDate());
        assertEquals("3 days", festival2.getDuration());
    }
}