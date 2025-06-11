package telran.java57.farm_sharing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import telran.java57.farm_sharing.model.Clients;

import java.util.List;

public interface ClientRepository extends JpaRepository<Clients, Long> {
    // JpaRepository provides methods like save, findById, deleteById, existsById, etc.
    boolean existsClientById(@NonNull Long clientId);// Check if a client exists by ID
//    //boolean existsByPhoneNumber(String phoneNumber); // Check if a client exists by phone number
//    //вот тут (11-12 строки) я запуталась:)- если у нас есть уникальный идентификатор клиента, то зачем нам проверять по номеру телефона?

    List<Clients> findByCityIgnoreCase(String city); // Find clients by city, ignoring case
    List<Clients> findByNameIgnoreCase(String name); // Find clients by name, ignoring case
    List<Clients> findByPhoneNumber(String phoneNumber); // Find clients by phone number
    List<Clients> findByEmailIgnoreCase(String email); // Find clients by email, ignoring case
}
