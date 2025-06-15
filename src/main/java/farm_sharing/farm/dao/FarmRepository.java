package farm_sharing.farm.dao;

import farm_sharing.farm.model.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
    boolean existsFarmByEmail(String email);

    boolean existsFarmByPhoneNumber(String phoneNumber);
}
