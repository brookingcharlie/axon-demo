package com.example.axondemo.query

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "application")
class ApplicationView {
    @Id lateinit var id: String
    lateinit var customer: String
    var course: String? = null
    var submitted: Boolean = false
}