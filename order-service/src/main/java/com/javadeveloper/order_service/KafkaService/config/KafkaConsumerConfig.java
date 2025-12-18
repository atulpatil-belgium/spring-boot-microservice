package com.javadeveloper.order_service.KafkaService.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.javadeveloper.order_service.KafkaService.model.PaymentResultEvent;

@Configuration
public class KafkaConsumerConfig {

    @Bean
        public ConsumerFactory<String, PaymentResultEvent> consumerFactory() {

                Map<String, Object> props = new HashMap<>();
                props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
                props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
                props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
                props.put(JsonDeserializer.VALUE_DEFAULT_TYPE,
            "com.javadeveloper.order_service.KafkaService.model.PaymentResultEvent");
                props.put(ConsumerConfig.GROUP_ID_CONFIG, "order-service");
                props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
                return new DefaultKafkaConsumerFactory<>(props,
            new StringDeserializer(),
            new JsonDeserializer<>());
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<String, PaymentResultEvent>
        kafkaListenerContainerFactory() {

                ConcurrentKafkaListenerContainerFactory<String, PaymentResultEvent> factory =
                        new ConcurrentKafkaListenerContainerFactory<>();

                factory.setConsumerFactory(consumerFactory());
                return factory;
        }
        
}

