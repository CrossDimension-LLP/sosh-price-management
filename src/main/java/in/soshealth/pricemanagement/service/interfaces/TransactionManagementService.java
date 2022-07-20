package in.soshealth.pricemanagement.service.interfaces;

import in.soshealth.pricemanagement.ms.ck.resource.pbm.model.TransactionHistory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionManagementService {
    Mono<TransactionHistory> createTransactions(TransactionHistory transactionHistory);
    Flux<TransactionHistory> getTransactions(String transactionId, String transactionStatus, String transactionMode);
}
