package in.soshealth.pricemanagement.repository;

import in.soshealth.pricemanagement.ms.ck.resource.pbm.model.Price;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PriceManagementRepository extends ReactiveCrudRepository<Price, String> {
}
