package programs;
import entities.CustomerEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Scanner;

public class InformeCliente {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SAKILA-MARIADB");
        EntityManager em = emf.createEntityManager();

        try {
            Scanner scanner = new Scanner(System.in);
            int customerId;

            do {
                System.out.print("Introduce el ID del cliente (0 para salir): ");
                customerId = scanner.nextInt();

                if (customerId != 0) {
                    mostrarInformeCliente(em, customerId);
                }

            } while (customerId != 0);

        } finally {
            em.close();
            emf.close();
        }
    }

    private static void mostrarInformeCliente(EntityManager em, int customerId) {
        CustomerEntity cliente = em.find(CustomerEntity.class, (short) customerId);

        if (cliente != null) {
            // Mostrar datos del cliente
            System.out.println("Datos del Cliente:");
            System.out.println("ID: " + cliente.getCustomerId());
            System.out.println("Nombre: " + cliente.getFirstName());
            System.out.println("Apellido: " + cliente.getLastName());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Activo: " + (cliente.getActive() == 1 ? "Sí" : "No"));
            System.out.println("Fecha de Creación: " + cliente.getCreateDate());
            System.out.println("Fecha de Modificación: " + cliente.getLastUpdate());

            // Mostrar dirección del cliente
            System.out.println("\nDirección del Cliente:");
            System.out.println("Dirección: " + cliente.getAddress().getAddress());
            System.out.println("Ciudad: " + cliente.getAddress().getCity().getCity());
            System.out.println("Provincia: " + cliente.getAddress().getCity().getCountry().getCountry());

            // Mostrar tienda asociada al cliente
            System.out.println("\nTienda Asociada:");
            System.out.println("ID de Tienda: " + cliente.getStore().getStoreId());
            System.out.println("Dirección de Tienda: " + cliente.getStore().getAddress().getAddress());
            System.out.println("Ciudad de Tienda: " + cliente.getStore().getAddress().getCity().getCity());
            System.out.println("Provincia de Tienda: " + cliente.getStore().getAddress().getCity().getCountry().getCountry());

            // Mostrar alquileres del cliente
            System.out.println("\nAlquileres Realizados:");
            cliente.getRentals().forEach(rental -> {
                System.out.println("ID de Alquiler: " + rental.getRentalId());
                System.out.println("Fecha de Alquiler: " + rental.getRentalDate());
                System.out.println("Título de Película: " + rental.getInventory().getFilm().getTitle());
                System.out.println("-----");
            });

            // Mostrar pagos realizados por el cliente
            System.out.println("\nPagos Realizados:");
            cliente.getPayments().forEach(pago -> {
                System.out.println("ID de Pago: " + pago.getPaymentId());
                System.out.println("Fecha de Pago: " + pago.getPaymentDate());
                System.out.println("Monto: " + pago.getAmount());
                System.out.println("-----");
            });

            System.out.println("-----\n");

        } else {
            System.out.println("Cliente no encontrado.");
        }
    }
}
