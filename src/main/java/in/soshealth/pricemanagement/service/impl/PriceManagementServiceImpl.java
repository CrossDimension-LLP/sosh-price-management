package in.soshealth.pricemanagement.service.impl;

import in.soshealth.pricemanagement.business.PriceCalculation;
import in.soshealth.pricemanagement.ms.ck.resource.pbm.model.Price;
import in.soshealth.pricemanagement.ms.ck.resource.pbm.model.PriceInput;
import in.soshealth.pricemanagement.repository.PriceManagementRepository;
import in.soshealth.pricemanagement.service.interfaces.PriceManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Slf4j
public class PriceManagementServiceImpl implements PriceManagementService {

    private final PriceManagementRepository priceManagementRepository;
    private final PriceCalculation priceCalculation;

    @Override
    public Mono<Price> createPriceDetails(PriceInput priceObject) {
        Price priceModule = new Price();
        priceModule.setId(priceObject.getServiceProviderId());
        priceModule.setServiceProviderId(priceObject.getServiceProviderId());
        priceModule.actualPrice(priceObject.getActualPrice());
        priceModule.setCustomerPrice(
                priceCalculation.customerPriceCalculation(priceObject.getActualPrice(), priceObject.getDeductionRate()));
        priceModule.setTaxDeductedServiceCharge(
                priceCalculation.taxDeductedPriceCalculation(priceModule.getCustomerPrice(), priceObject.getTaxRate()));
        priceModule.setTaxAmount(
                priceCalculation.calculateTaxAmount(priceModule.getCustomerPrice(), priceModule.getTaxDeductedServiceCharge()));
        return priceManagementRepository.save(priceModule);
    }

    @Override
    public Flux<Price> getPriceDetails(String serviceId) {
        if (serviceId.equalsIgnoreCase("all"))
            return priceManagementRepository.findAll();
        return priceManagementRepository.findAllById(Arrays.asList(serviceId.split(",")));
    }
}