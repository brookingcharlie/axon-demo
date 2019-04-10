package com.example.axondemo.tracker

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "application2")
class ApplicationView2 {
    @Id lateinit var id: String
    lateinit var customer: String
    var course: String? = null
    var submitted: Boolean = false
}