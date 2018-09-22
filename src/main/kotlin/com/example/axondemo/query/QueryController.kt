package com.example.axondemo.query

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors.toList

@RestController
class QueryController(@Autowired private val repository: ApplicationViewRepository) {
    @GetMapping("/applications")
    fun participantsInRoom(): List<String> {
        return repository.findAll()
            .stream()
            .map { "${it.id} by ${it.givenNames} ${it.familyName}" }
            .collect(toList())
    }
}
