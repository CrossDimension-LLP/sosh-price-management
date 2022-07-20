package in.soshealth.pricemanagement.controller;

import in.soshealth.pricemanagement.ms.ck.resource.pbm.model.Price;
import in.soshealth.pricemanagement.ms.ck.resource.pbm.model.PriceInput;
import in.soshealth.pricemanagement.service.interfaces.PriceManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PriceManagementController {

    private final PriceManagementService priceManagementService;

    @PostMapping("/pricing")
    Mono<Price> createPriceDetails(@RequestBody PriceInput priceObject) {
        return priceManagementService.createPriceDetails(priceObject);
    }

    @GetMapping("/pricing")
    Flux<Price> getPriceDetails(@RequestParam("serviceId") String serviceId) {
        return priceManagementService.getPriceDetails(serviceId);
    }
}
