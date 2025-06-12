package telran.java57.farm_sharing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import telran.java57.farm_sharing.model.Clients;

import java.util.List;

public interface ClientRepository extends JpaRepository<Clients, Long> {
    // JpaRepository provides methods like save, findById, deleteById, existsById, etc.

    List<Clients> findByCityIgnoreCase(String city); // Find clients by city, ignoring case
    List<Clients> findByNameIgnoreCase(String name); // Find clients by name, ignoring case
    List<Clients> findByPhoneNumber(String phoneNumber); // Find clients by phone number
    List<Clients> findByEmailIgnoreCase(String email); // Find clients by email, ignoring case
}
