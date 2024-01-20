package programs;

import entities.FilmEntity;
import entities.InventoryEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class InformePelicula {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SAKILA-MARIADB");
        EntityManager em = emf.createEntityManager();

        try {
            Scanner scanner = new Scanner(System.in);
            short filmId;

            do {
                System.out.print("Introduce el ID de la película (0 para salir): ");
                filmId = scanner.nextShort();

                if (filmId != 0) {
                    mostrarInformePelicula(em, filmId);
                }

            } while (filmId != 0);

        } finally {
            em.close();
            emf.close();
        }
    }

    private static void mostrarInformePelicula(EntityManager em, short filmId) {
        FilmEntity pelicula = em.find(FilmEntity.class, filmId);

        if (pelicula != null) {
            // Mostrar datos de la película
            System.out.println("Datos de la Película:");
            System.out.println("ID: " + pelicula.getFilmId());
            System.out.println("Título: " + pelicula.getTitle());
            System.out.println("Descripción: " + pelicula.getDescription());
            System.out.println("Año de Estreno: " + pelicula.getReleaseYear());
            System.out.println("Duración de Alquiler: " + pelicula.getRentalDuration() + " días");
            System.out.println("Tarifa de Alquiler: $" + pelicula.getRentalRate());
            System.out.println("Costo de Reemplazo: $" + pelicula.getReplacementCost());
            System.out.println("Clasificación: " + pelicula.getRating());
            System.out.println("Características Especiales: " + pelicula.getSpecialFeatures());

            // Mostrar idioma original de la película si está presente
            System.out.println("Idioma Original: " + (pelicula.getOriginalLanguage() != null ? pelicula.getOriginalLanguage().getName() : "Desconocido"));

            // Mostrar idioma de la película si está presente
            System.out.println("Idioma de la Película: " + (pelicula.getLanguage() != null ? pelicula.getLanguage().getName() : "Desconocido"));

            // Mostrar categorías de la película
            System.out.println("\nCategorías de la Película:");
            pelicula.getCategories().forEach(categoria ->
                    System.out.println("ID de Categoría: " + categoria.getCategoryId() + " - Nombre: " + categoria.getName()));

            // Mostrar actores que participan en la película
            System.out.println("\nActores que Participan:");
            pelicula.getActors().forEach(actor ->
                    System.out.println("ID de Actor: " + actor.getActorId() + " - Nombre: " + actor.getFirstName() + " " + actor.getLastName()));

            // Mostrar copias disponibles en el inventario
            System.out.println("\nCopias Disponibles en el Inventario:");
            List<InventoryEntity> inventarios = pelicula.getInventories(em); // Pasa el EntityManager como argumento
            if (inventarios.isEmpty()) {
                System.out.println("No hay copias disponibles en el inventario.");
            } else {
                inventarios.forEach(inventario -> {
                    System.out.println("ID de Inventario: " + inventario.getInventoryId());
                    System.out.println("Tienda: " + inventario.getStore().getStoreId());
                    System.out.println("Dirección de la Tienda: " + (inventario.getStore().getAddress() != null ? inventario.getStore().getAddress().getAddress() : "Desconocido"));
                    System.out.println("Ciudad: " + (inventario.getStore().getAddress() != null ? inventario.getStore().getAddress().getCity().getCity() : "Desconocido"));
                    System.out.println("Provincia: " + (inventario.getStore().getAddress() != null ? inventario.getStore().getAddress().getCity().getCountry().getCountry() : "Desconocido"));
                    System.out.println("-----");
                });
            }
            System.out.println("-----\n");

        } else {
            System.out.println("Película no encontrada.");
        }
    }

}
