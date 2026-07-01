package farm_sharing.offer.dao;

import farm_sharing.offer.model.Offer;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long>, JpaSpecificationExecutor<Offer> {
    @Query("SELECT MAX(o.price) FROM Offer o")
    Double findMaxPrice();

    @Query("SELECT MIN(o.price) FROM Offer o")
    Double findMinPrice();

    Page<Offer> findAllByFarm_Nickname(String farm, Pageable pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            select o
            from Offer o
            where o.id in :ids
    """)
    List<Offer> findAllByIdsForUpdate(List<Long> ids);
}
