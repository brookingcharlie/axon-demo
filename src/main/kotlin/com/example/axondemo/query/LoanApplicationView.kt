package com.example.axondemo.query

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name="application")
class LoanApplicationView() {
    @Id
    lateinit var id: String

    var familyName: String? = null

    var givenNames: String? = null

    constructor(id: String) : this() {
        this.id = id
    }
}