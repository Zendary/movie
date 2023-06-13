package facades;

import dtos.ShowDTO;
import entities.Show;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

public class ShowFacade {

private static ShowFacade instance;
private static EntityManagerFactory emf;

    private ShowFacade() {
    }

    public static ShowFacade getShowFacade() {
        if (instance == null) {
            instance = new ShowFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ShowDTO createShow(ShowDTO showDTO) {
        EntityManager em = getEntityManager();

        Show show = new Show();
        show.setName(showDTO.getName());
        show.setDuration(showDTO.getDuration());
        show.setLocation(showDTO.getLocation());
        show.setStartDate(showDTO.getStartDate());
        show.setStartTime(showDTO.getStartTime());


        try {
            em.getTransaction().begin();
            em.persist(show);
            em.getTransaction().commit();

        } finally {
            em.close();
        }

        return new ShowDTO(show);
    }

    public List<ShowDTO> getAllShows() {
        EntityManager em = getEntityManager();
        try {
            List<Show> shows = em.createQuery("SELECT s FROM Show s", Show.class).getResultList();
            return shows.stream().map(ShowDTO::new).collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    public void deleteShow(Long showId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Show show = em.find(Show.class, showId);
            if (show != null) {
                em.remove(show);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        ShowFacade facade = getShowFacade();
//        facade.createShow(new ShowDTO(null,"Cirkus" , "Test", "Test", "Test", "Test"));
//        facade.getAllShows().forEach(dto -> System.out.println(dto));
//        facade.deleteShow(1L);
    }

}
