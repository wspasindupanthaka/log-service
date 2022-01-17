package com.pasindu.logservice.consumer.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pasindu.logservice.consumer.KafkaConsumer;
import com.pasindu.logservice.kafka.admin.client.KafkaAdminClient;
import com.pasindu.logservice.kafka.config.data.KafkaConfigData;
import com.pasindu.logservice.model.LogAvroModel;
import com.pasindu.logservice.model.LogLine;
import com.pasindu.logservice.service.LogService;
import com.pasindu.logservice.service.transformer.LogAvroModelToLogLineTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsKafkaConsumer implements KafkaConsumer<Long, LogAvroModel> {

    private static final Logger LOG = LoggerFactory.getLogger(LogsKafkaConsumer.class);

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    private final KafkaAdminClient kafkaAdminClient;

    private final KafkaConfigData kafkaConfigData;

    private final LogAvroModelToLogLineTransformer logAvroModelToLogLineTransformer;

    private LogService logService;

    public LogsKafkaConsumer(KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry,
                             KafkaAdminClient kafkaAdminClient,
                             KafkaConfigData kafkaConfigData,
                             LogService logService,
                             LogAvroModelToLogLineTransformer logAvroModelToLogLineTransformer) {
        this.kafkaListenerEndpointRegistry = kafkaListenerEndpointRegistry;
        this.kafkaAdminClient = kafkaAdminClient;
        this.kafkaConfigData = kafkaConfigData;
        this.logService = logService;
        this.logAvroModelToLogLineTransformer = logAvroModelToLogLineTransformer;
    }

    @EventListener
    public void onAppStarted(ApplicationStartedEvent event) {
        kafkaAdminClient.checkTopicsCreated();
        LOG.info("Topics with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
        kafkaListenerEndpointRegistry.getListenerContainer("logsTopicListener").start();
    }

    @Override
    @KafkaListener(id = "logsTopicListener", topics = "${kafka-config.topic-name}")
    public void receive(@Payload List<LogAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Integer> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<LogLine> logLines = objectMapper.readValue(messages.toString(), new TypeReference<List<LogLine>>() {
            });
            logService.insertLog(logLines);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
