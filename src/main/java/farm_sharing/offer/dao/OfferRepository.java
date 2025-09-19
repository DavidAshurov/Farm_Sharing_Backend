package farm_sharing.offer.dao;

import farm_sharing.offer.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface OfferRepository extends JpaRepository<Offer, Long>, JpaSpecificationExecutor<Offer> {
    @Query("SELECT MAX(o.price) FROM Offer o")
    Double findMaxPrice();
    @Query("SELECT MIN(o.price) FROM Offer o")
    Double findMinPrice();
}
