package facades;

import dtos.UserDTO;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import java.util.List;

public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    private UserFacade() {
    }

    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVerifiedUser(String username, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        User user;
        try {
            user = em.createNamedQuery("User.getUserByUsername", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
            if (user == null) {
                throw new AuthenticationException("Invalid user name or password");
            }
            System.out.println("Stored Hash: " + user.getUserPass());
            System.out.println("Input Password: " + password);
            if (!user.verifyPassword(password)) {
                System.out.println("Password did not match for username: " + username);
                throw new AuthenticationException("Invalid user name or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException("Server error during authentication");
        } finally {
            em.close();
        }
        return user;
    }




    public List<UserDTO> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        try {
            List<User> users = em.createNamedQuery("User.getAllUsers").getResultList();
            return UserDTO.getDtos(users);
        } finally {
            em.close();
        }
    }

    public UserDTO create(UserDTO userDTO) {
        EntityManager em = emf.createEntityManager();
        User user = new User(userDTO.getUserName(), userDTO.getPassword());
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new UserDTO(user);
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        UserFacade uf = UserFacade.getUserFacade(emf);
        uf.getAllUsers().forEach(dto->System.out.println(dto));
    }
}
