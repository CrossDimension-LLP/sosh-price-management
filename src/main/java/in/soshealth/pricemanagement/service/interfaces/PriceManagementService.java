package in.soshealth.pricemanagement.service.interfaces;

import in.soshealth.pricemanagement.ms.ck.resource.pbm.model.Price;
import in.soshealth.pricemanagement.ms.ck.resource.pbm.model.PriceInput;
import reactor.core.publisher.Mono;

public interface PriceManagementService {
    Mono<Price> createPriceDetails(PriceInput priceObject);
}
