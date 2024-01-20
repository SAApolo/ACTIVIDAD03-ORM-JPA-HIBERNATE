package programs;

import entities.StaffEntity;
import entities.FilmEntity;
import entities.CustomerEntity;
import entities.InventoryEntity;
import entities.RentalEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
/*
* NO CONSIGO HALLAR LA LÓGICA NECESARIA PARA DARLE VALOR A INVENTARIO_ID Y PODER CREAR UN NUEVO ALQUILER
*
* Exception in thread "main" org.hibernate.exception.GenericJDBCException: could not execute statement [(conn=15)
* Field 'inventory_id' doesn't have a default value] [insert into rental (last_update,rental_date,return_date) values (?,?,?)]
* at programs.AlquilerPelicula.realizarAlquiler(AlquilerPelicula.java:70)
* at programs.AlquilerPelicula.main(AlquilerPelicula.java:36)
* */
public class AlquilerPelicula {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SAKILA-MARIADB");
        EntityManager em = emf.createEntityManager();

        try {
            Scanner scanner = new Scanner(System.in);
            short staffId, filmId, customerId;

            System.out.print("Introduce el ID del empleado: ");
            staffId = scanner.nextShort();

            System.out.print("Introduce el ID de la película: ");
            filmId = scanner.nextShort();

            System.out.print("Introduce el ID del cliente: ");
            customerId = scanner.nextShort();

            realizarAlquiler(em, staffId, filmId, customerId);

        } finally {
            em.close();
            emf.close();
        }
    }

    private static void realizarAlquiler(EntityManager em, short staffId, short filmId, short customerId) {
        StaffEntity empleado = em.find(StaffEntity.class, staffId);
        FilmEntity pelicula = em.find(FilmEntity.class, filmId);
        CustomerEntity cliente = em.find(CustomerEntity.class, customerId);

        if (empleado != null && pelicula != null && cliente != null) {
            // Obtener tienda del empleado
            short storeId = empleado.getStoreId();

            // Comprobar si la película está en el inventario de la tienda
            InventoryEntity inventario = obtenerInventario(em, filmId, storeId);

            if (inventario != null) {
                // Comprobar si hay copias disponibles
                if (copiasDisponibles(em, filmId, storeId)) {
                    // Crear nuevo alquiler
                    Timestamp rentalDate = new Timestamp(System.currentTimeMillis());
                    Timestamp returnDate = new Timestamp(System.currentTimeMillis());
                    Timestamp lastUpdate = new Timestamp(System.currentTimeMillis());

                    // Crear nuevo alquiler con la película e inventory
                    RentalEntity nuevoAlquiler = new RentalEntity(empleado, inventario, cliente, rentalDate, returnDate, lastUpdate);

                    // Guardar el nuevo alquiler en la base de datos
                    em.getTransaction().begin();
                    em.persist(nuevoAlquiler);
                    em.getTransaction().commit();

                    System.out.println("Alquiler realizado con éxito.");
                } else {
                    System.out.println("Error: No hay copias disponibles de la película.");
                }
            } else {
                System.out.println("Error: La película no está en el inventario de la tienda.");
            }
        } else {
            System.out.println("Error: Empleado, película o cliente no encontrados.");
        }
    }

    private static InventoryEntity obtenerInventario(EntityManager em, short filmId, short storeId) {
        TypedQuery<InventoryEntity> query = em.createQuery(
                "SELECT i FROM InventoryEntity i WHERE i.film.filmId = :filmId AND i.store.storeId = :storeId",
                InventoryEntity.class);
        query.setParameter("filmId", filmId);
        query.setParameter("storeId", storeId);
        List<InventoryEntity> inventarios = query.getResultList();

        return inventarios.isEmpty() ? null : inventarios.get(0);
    }

    private static boolean copiasDisponibles(EntityManager em, short filmId, short storeId) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(i) FROM InventoryEntity i WHERE i.film.filmId = :filmId AND i.store.storeId = :storeId ",
                Long.class);
        query.setParameter("filmId", filmId);
        query.setParameter("storeId", storeId);
        return query.getSingleResult() > 0;
    }
}
