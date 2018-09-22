package com.example.axondemo.query

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryController(@Autowired private val repository: LoanApplicationViewRepository) {
    @GetMapping("/applications")
    fun participantsInRoom(): List<String> {
        return repository.findAll()
            .map { app ->
                "${app.id} by ${app.givenNames} ${app.familyName} " +
                "with documents ${app.documents.map { doc -> doc.name}.joinToString()}"
            }
    }
}
