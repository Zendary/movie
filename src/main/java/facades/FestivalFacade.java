package facades;

import dtos.FestivalDTO;
import entities.Festival;
import entities.Show;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class FestivalFacade {

    private static FestivalFacade instance;
    private static EntityManagerFactory emf;

    private FestivalFacade() {}

    public static FestivalFacade getFestivalFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FestivalFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public FestivalDTO createFestival(FestivalDTO festivalDTO) {
        EntityManager em = getEntityManager();

        Festival festival = new Festival();
        festival.setName(festivalDTO.getName());
        festival.setDuration(festivalDTO.getDuration());
        festival.setCity(festivalDTO.getCity());
        festival.setStartDate(festivalDTO.getStartDate());

        try {
            em.getTransaction().begin();
            em.persist(festival);
            em.getTransaction().commit();

        } finally {
            em.close();
        }

        return new FestivalDTO(festival);

    }

    public List<FestivalDTO> getALlFestivals(){
        EntityManager em = getEntityManager();
        try {
            List<Festival> festivals = em.createQuery("SELECT f FROM Festival f", Festival.class).getResultList();
            return festivals.stream().map(FestivalDTO::new).collect(java.util.stream.Collectors.toList());
        } finally {
            em.close();
        }
    }

    public void deleteFestival(Long festivalId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Festival festival = em.find(Festival.class, festivalId);
            if (festival != null) {
                em.remove(festival);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
