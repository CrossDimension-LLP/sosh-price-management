package in.soshealth.pricemanagement.service.impl;

import in.soshealth.pricemanagement.business.UtilityModule;
import in.soshealth.pricemanagement.ms.ck.resource.pbm.model.TransactionHistory;
import in.soshealth.pricemanagement.repository.TransactionManagementRepository;
import in.soshealth.pricemanagement.service.interfaces.TransactionManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionManagementServiceImpl implements TransactionManagementService {
    private  final UtilityModule utils;
    private final TransactionManagementRepository transactionManagementRepository;

    @Override
    public Mono<TransactionHistory> createTransactions(TransactionHistory transactionHistory) {
        transactionHistory.setId(utils.uniqueIdGenerator("TXN"));
        return transactionManagementRepository.save(transactionHistory);
    }

    @Override
    public Flux<TransactionHistory> getTransactions(String transactionId, String transactionStatus, String transactionMode) {
        if (!Objects.isNull(transactionId) && transactionId.equalsIgnoreCase("All"))
            return transactionManagementRepository.findAll();
        else if (!Objects.isNull(transactionId) && Arrays.asList(transactionId.split(",")).size() > 0)
            return transactionManagementRepository.findAllById(Arrays.asList(transactionId.split(",")));
        else if (!Objects.isNull(transactionStatus) && !Objects.isNull(transactionMode))
            return transactionManagementRepository.findTransactionsFilters(transactionStatus.split(","),
                    transactionMode.split(","));
        else
            if (Objects.isNull(transactionStatus))
                return transactionManagementRepository.findTransactionsBySelectiveFilter(new String[] {}, transactionMode.split(","));
            else
                return transactionManagementRepository.findTransactionsBySelectiveFilter(transactionStatus.split(","), new String[] {});
    }
}
