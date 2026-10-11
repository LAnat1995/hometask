package com.github.LAnat.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic newOrdersTopic(
            @Value("${kafka.topic.partitions}") int partitions,
            @Value("${kafka.topic.replicas}") int replicas) {
        return TopicBuilder.name("new_orders").partitions(partitions).replicas(replicas).build();
    }

    @Bean
    public NewTopic payedOrdersTopic(
            @Value("${kafka.topic.partitions}") int partitions,
            @Value("${kafka.topic.replicas}") int replicas) {
        return TopicBuilder.name("payed_orders").partitions(partitions).replicas(replicas).build();
    }

    @Bean
    public NewTopic sentOrdersTopic(
            @Value("${kafka.topic.partitions}") int partitions,
            @Value("${kafka.topic.replicas}") int replicas) {
        return TopicBuilder.name("sent_orders").partitions(partitions).replicas(replicas).build();
    }
}
