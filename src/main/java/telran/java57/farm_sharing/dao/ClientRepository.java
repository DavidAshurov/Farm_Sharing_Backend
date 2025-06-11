package telran.java57.farm_sharing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import telran.java57.farm_sharing.model.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    // JpaRepository provides methods like save, findById, deleteById, existsById, etc.
    boolean existsById(@NonNull Long id); // Check if a client exists by ID
    //boolean existsByPhoneNumber(String phoneNumber); // Check if a client exists by phone number
    //вот тут (11-12 строки) я запуталась:)- если у нас есть уникальный идентификатор клиента, то зачем нам проверять по номеру телефона?

    List<Client> findByCityIgnoreCase(String city); // Find clients by city, ignoring case
    List<Client> findByNameIgnoreCase(String name); // Find clients by name, ignoring case
    List<Client> findByPhoneNumber(String phoneNumber); // Find clients by phone number
    List<Client> findByEmailIgnoreCase(String email); // Find clients by email, ignoring case
}
