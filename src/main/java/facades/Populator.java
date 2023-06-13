package facades;

import entities.Role;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Populator {
    public static void populate() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Create and persist roles
            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            em.persist(userRole);
            em.persist(adminRole);

            // Create and persist users
            User user = new User("user", "test123");
            user.addRole(userRole); // Add user role to user
            em.persist(user);

            User admin = new User("admin", "test123");
            admin.addRole(adminRole); // Add admin role to admin
            em.persist(admin);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    public static void main(String[] args) {
        populate();
    }
}
