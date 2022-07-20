package in.soshealth.pricemanagement.repository;

import in.soshealth.pricemanagement.ms.ck.resource.pbm.model.TransactionHistory;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionManagementRepository extends ReactiveCrudRepository<TransactionHistory, String> {
    @Query("{$or:[{transactionStatus: ?0}, {transactionMode: ?1}]}")
    Flux<TransactionHistory> findTransactionsBySelectiveFilter(String transactionStatus, String transactionMode);

    @Query("{$and:[{transactionStatus: {$in: ?0}}, {transactionMode: {$in: ?1}}]}")
    Flux<TransactionHistory> findTransactionsFilters(String [] transactionStatus, String [] transactionMode);
}
