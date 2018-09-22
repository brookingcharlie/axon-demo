package com.example.jpaaxondemo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SimpleAxonConfiguration {
    @Bean
    fun eventSerializer(objectMapper: ObjectMapper): Serializer{
        objectMapper.registerKotlinModule()
        return JacksonSerializer(objectMapper)
    }
}