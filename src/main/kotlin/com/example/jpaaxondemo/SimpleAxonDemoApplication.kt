package com.example.jpaaxondemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleAxonDemoApplication

fun main(args: Array<String>) {
    runApplication<SimpleAxonDemoApplication>(*args)
}
