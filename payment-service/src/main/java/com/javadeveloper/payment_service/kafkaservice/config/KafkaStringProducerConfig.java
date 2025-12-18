package com.javadeveloper.payment_service.kafkaservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaStringProducerConfig {

    @Bean
    public ProducerFactory<String, String> producerStringFactory() {
            Map<String, Object> config = new HashMap<>();
            config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaStringTemplate() {
            return new KafkaTemplate<>(producerStringFactory());
    }

        

    @Bean
    public DefaultErrorHandler errorHandler(KafkaTemplate<String, String> template) {
            DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(template);
            return new DefaultErrorHandler(recoverer, new FixedBackOff(1000L, 3));
    }

}
