package com.example.axondemo.anotherquery

import javax.persistence.*

@Entity(name = "another_application")
class AnotherLoanApplicationView() {
    @Id
    lateinit var id: String

    var name: String? = null

    constructor(id: String) : this() {
        this.id = id
    }
}
