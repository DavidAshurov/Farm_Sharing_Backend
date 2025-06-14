package farm_sharing.client.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import farm_sharing.client.model.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // JpaRepository provides methods like save, findById, deleteById, existsById, etc.

    List<Client> findByCityIgnoreCase(String city); // Find clients by city, ignoring case
    List<Client> findByNameIgnoreCase(String name); // Find clients by name, ignoring case
    List<Client> findByPhoneNumber(String phoneNumber); // Find clients by phone number
    List<Client> findByEmailIgnoreCase(String email); // Find clients by email, ignoring case

    boolean existsClientByPhoneNumber(String phoneNumber);

    boolean existsClientByEmail(String email);
}
