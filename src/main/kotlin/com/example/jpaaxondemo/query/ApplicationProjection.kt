package com.example.jpaaxondemo.query

import com.example.jpaaxondemo.core.ApplicationCreatedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name="application")
class ApplicationView() {
    @Id
    lateinit var id: String

    constructor(id: String) : this() {
        this.id = id
    }
}

interface ApplicationViewRepository : JpaRepository<ApplicationView, String>

@Service
class ApplicationProjection(@Autowired private val repository: ApplicationViewRepository) {
    @EventHandler
    fun on(event: ApplicationCreatedEvent) {
        repository.save(ApplicationView(event.id))
    }
}
