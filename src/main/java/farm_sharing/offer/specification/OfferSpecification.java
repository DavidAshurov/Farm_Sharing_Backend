package farm_sharing.offer.specification;

import farm_sharing.offer.model.Offer;
import farm_sharing.user.model.User;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OfferSpecification {
    public static Specification<Offer> offerSpecification(String category,
                                                          String search,
                                                          Double minPrice,
                                                          Double maxPrice) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (category != null && !category.isEmpty()) {
                predicates.add(cb.equal(root.get("category"), category));
            }

            if (minPrice != null && maxPrice != null && minPrice < maxPrice) {
                predicates.add(cb.between(root.get("price"),minPrice,maxPrice));
            }

            if (search != null && !search.isEmpty()) {
                String pattern = "%" + search.toLowerCase() + "%";
                Join<Offer, User> farmJoin = root.join("farm", JoinType.INNER);

                Predicate offerTitlePred = cb.like(cb.lower(root.get("title")), pattern);
                Predicate farmNicknamePred = cb.like(cb.lower(farmJoin.get("nickname")), pattern);
                Predicate cityPred = cb.like(cb.lower(farmJoin.get("city")), pattern);

                predicates.add(cb.or(offerTitlePred, farmNicknamePred, cityPred));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
