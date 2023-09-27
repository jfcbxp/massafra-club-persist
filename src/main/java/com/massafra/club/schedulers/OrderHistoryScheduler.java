package com.massafra.club.schedulers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.massafra.club.constants.PersistRabbitMq;
import com.massafra.club.publishers.Publisher;
import com.massafra.club.repositories.OrcamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderHistoryScheduler {
    private final OrcamentoRepository orcamentoRepository;

    private final Publisher publisher;

    @Scheduled(cron = "${scheduler.order-history.cron-value}", zone = "America/Sao_Paulo")
    public void run() throws JsonProcessingException {
        String schedulerId = UUID.randomUUID().toString();

        log.info("OrderHistoryScheduler.run - start - schedulerIdentifier: {}", schedulerId);

        var orcamento = orcamentoRepository.findByNumeroAndEmpresa("000001","01");

        if(orcamento.isPresent()){
              log.info("OrderHistoryScheduler.run - processing - schedulerIdentifier: {}, order: {}", schedulerId, orcamento.get().getNomeCliente());
              publisher.sendAsString(PersistRabbitMq.EXCHANGE_PROTHEUS_COMMANDS,PersistRabbitMq.CREATE_ORDER_ROUTING_KEY,orcamento.get());
        }


        log.info("OrderHistoryScheduler.run - end - schedulerIdentifier: {}", schedulerId);
    }


}