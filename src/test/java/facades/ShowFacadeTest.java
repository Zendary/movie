package facades;

import dtos.ShowDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowFacadeTest {

    private static EntityManagerFactory emf;
    private static ShowFacade facade;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = ShowFacade.getShowFacade(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            em.createNamedQuery("Show.deleteAllRows").executeUpdate();
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
            em.createNamedQuery("Show.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void createShow() {

        ShowDTO showDTO = new ShowDTO();

        ShowDTO createdShow = facade.createShow(showDTO);

        assertEquals(showDTO.getName(), createdShow.getName());
        assertEquals(showDTO.getDuration(), createdShow.getDuration());
        assertEquals(showDTO.getLocation(), createdShow.getLocation());
    }

}