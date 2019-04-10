package com.example.axondemo.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.axonframework.config.EventProcessingConfigurer
import org.axonframework.eventhandling.PropagatingErrorHandler
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class AxonConfiguration {
    @Bean
    @Primary
    fun serializer(objectMapper: ObjectMapper): Serializer {
        objectMapper.registerKotlinModule()
        return JacksonSerializer.builder().objectMapper(objectMapper).build()
    }

    @Autowired
    fun configurationEventHandling(config: EventProcessingConfigurer) {
        config.registerDefaultListenerInvocationErrorHandler { PropagatingErrorHandler.instance() };
    }
}