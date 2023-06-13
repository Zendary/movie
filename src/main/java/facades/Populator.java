package facades;

import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;

public class Populator {
    public static void main(String[] args) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();

    }
}
