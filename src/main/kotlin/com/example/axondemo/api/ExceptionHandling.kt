package com.example.axondemo.api

import com.example.axondemo.domain.InvalidCustomerException
import org.axonframework.commandhandling.CommandExecutionException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

class ApiError(val message: String?)

@ControllerAdvice
class ExceptionHandlerAdvice {
    @ExceptionHandler(Exception::class)
    fun handle(e: Exception): ResponseEntity<ApiError> {
        logger.error("exception(${e::class.simpleName}(${e.message}))")
        return when (e) {
            is CommandExecutionException -> handle(e.cause as Exception)
            is InvalidCustomerException -> ResponseEntity.status(400).body(ApiError(e.message))
            else -> ResponseEntity.status(500).body(ApiError("Unexpected error"))
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ExceptionHandlerAdvice::class.java)
    }
}