package com.example.invoiceservice.kafka;

import com.example.invoiceservice.dto.UploadedFileDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.server}")
    private String kafkaServer;
    @Value(("${kafka.producer.id}"))
    private String kafkaProducerId;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        configs.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configs;
    }

    @Bean(name = "fileProducerFactory")
    public ProducerFactory<Long, Set<UploadedFileDto>> fileProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, Set<UploadedFileDto>> kafkaUploadedFileBatchTemplate() {
        KafkaTemplate<Long, Set<UploadedFileDto>> template = new KafkaTemplate<>(fileProducerFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public ProducerFactory<Long, Long> idProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, Long> idKafkaTemplate() {
        KafkaTemplate<Long, Long> template = new KafkaTemplate<>(idProducerFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

}
