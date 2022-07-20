package in.soshealth.pricemanagement.controller;

import in.soshealth.pricemanagement.ms.ck.resource.pbm.model.TransactionHistory;
import in.soshealth.pricemanagement.service.interfaces.TransactionManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionManagementController {

    private final TransactionManagementService transactionManagementService;

    @PostMapping("/transaction")
    public Mono<TransactionHistory> createTransaction(@RequestBody TransactionHistory transactions) {
        return transactionManagementService.createTransactions(transactions);
    }

    @GetMapping("/transaction")
    public Flux<TransactionHistory> getTransactions(@RequestParam(value = "transactionId", required = false) String transactionId,
                                                    @RequestParam(value = "transactionStatus", required = false) String transactionStatus,
                                                    @RequestParam(value = "transactionMode", required = false) String transactionMode) {
        return transactionManagementService.getTransactions(transactionId,transactionStatus,transactionMode);
    }
}
