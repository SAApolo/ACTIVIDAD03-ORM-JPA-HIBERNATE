package programs;

import entities.StaffEntity;
import entities.RentalEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class InformeEmpleado {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SAKILA-MARIADB");
        EntityManager em = emf.createEntityManager();

        try {
            Scanner scanner = new Scanner(System.in);
            byte staffId;

            do {
                System.out.print("Introduce el ID del empleado (0 para salir): ");
                staffId = scanner.nextByte();

                if (staffId != 0) {
                    mostrarInformeEmpleado(em, staffId);
                }

            } while (staffId != 0);

        } finally {
            em.close();
            emf.close();
        }
    }

    private static void mostrarInformeEmpleado(EntityManager em, byte staffId) {
        StaffEntity empleado = em.find(StaffEntity.class, staffId);

        if (empleado != null) {
            // Mostrar datos del empleado
            System.out.println("Datos del Empleado:");
            System.out.println("ID: " + empleado.getStaffId());
            System.out.println("Nombre: " + empleado.getFirstName() + " " + empleado.getLastName());
            System.out.println("Email: " + empleado.getEmail());
            System.out.println("Cargo: " + empleado.getUsername());
            System.out.println("Fecha de Contratación: " + empleado.getLastUpdate());

            // Mostrar dirección del empleado
            System.out.println("\nDirección del Empleado:");
            System.out.println("Dirección: " + empleado.getAddress().getAddress());
            System.out.println("Ciudad: " + empleado.getAddress().getCity().getCity());
            System.out.println("Provincia: " + empleado.getAddress().getCity().getCountry().getCountry());

            // Mostrar tienda asociada al empleado
            System.out.println("\nTienda Asociada:");
            System.out.println("ID de Tienda: " + empleado.getStore().getStoreId());

            // Mostrar dirección de la tienda asociada al empleado
            System.out.println("\nDirección de la Tienda Asociada:");
            System.out.println("Dirección: " + empleado.getStore().getAddress().getAddress());
            System.out.println("Ciudad: " + empleado.getStore().getAddress().getCity().getCity());
            System.out.println("Provincia: " + empleado.getStore().getAddress().getCity().getCountry().getCountry());

            // Mostrar alquileres realizados por el empleado
            System.out.println("\nAlquileres Realizados:");
            List<RentalEntity> alquileres = empleado.getRentals();
            if (alquileres.isEmpty()) {
                System.out.println("El empleado no ha realizado alquileres.");
            } else {
                alquileres.forEach(alquiler -> {
                    System.out.println("ID de Alquiler: " + alquiler.getRentalId());
                    System.out.println("Fecha de Alquiler: " + alquiler.getRentalDate());
                    System.out.println("Título de Película: " + alquiler.getInventory().getFilm().getTitle());

                    // Verificar disponibilidad de la copia en el inventario basándonos en la fecha de retorno
                    System.out.println("Estado del Alquiler: " +
                            (alquiler.getReturnDate() == null ? "Activo (No disponible)" : "Devuelto (Disponible)"));

                    System.out.println("-----");
                });
            }

            System.out.println("-----\n");

        } else {
            System.out.println("Empleado no encontrado.");
        }
    }
}
