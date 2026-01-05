package com.project.order.infrastructure.outbox;

import com.project.order.adapter.messaging.producer.OrderProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutboxProcessor {
    private final OutboxRepository outboxRepository;
    private final OrderProducer orderProducer;
    public OutboxProcessor(OutboxRepository outboxRepository, OrderProducer orderProducer) {
        this.outboxRepository = outboxRepository;
        this.orderProducer = orderProducer;
    }

    @Scheduled(fixedRate = 5000)
    public void processOutbox() {
        var events = this.outboxRepository.findByIsPublished(false);
        if (events != null) {
            for(var event: events) {
                var kafkaTemplate = orderProducer.getKafkaTemplate();
                kafkaTemplate.send(event.getEvent() ,event.getPayload());
                System.out.println("Outbox processed: " + event.getEvent());
                event.setPublished(true);
                outboxRepository.save(event);
            }
        }
    }

}
