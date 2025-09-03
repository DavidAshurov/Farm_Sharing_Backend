package farm_sharing.offer.specification;

import farm_sharing.offer.model.Offer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OfferSpecification {
    public static Specification<Offer> offerSpecification(String category) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (category != null) {
                predicates.add(cb.equal(root.get("category"),category));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
