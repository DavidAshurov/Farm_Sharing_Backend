package farm_sharing.farmer.dao;

import farm_sharing.farmer.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    boolean existsFarmerByEmail(String email);

    boolean existsFarmerByPhoneNumber(String phoneNumber);
}
