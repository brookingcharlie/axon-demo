package com.example.axondemo.query

import javax.persistence.*


@Entity(name = "application")
class LoanApplicationView() {
    @Id
    lateinit var id: String

    var familyName: String? = null

    var givenNames: String? = null

    @OneToMany(cascade = [CascadeType.ALL], mappedBy="application")
    var documents: MutableList<DocumentView> = mutableListOf()

    constructor(id: String) : this() {
        this.id = id
    }
}

@Entity(name = "document")
class DocumentView() {
    @ManyToOne
    lateinit var application: LoanApplicationView

    @Id
    lateinit var id: String

    lateinit var name: String

    lateinit var content: String

    constructor(application: LoanApplicationView, id: String, name: String, content: String) : this() {
        this.application = application
        this.id = id
        this.name = name
        this.content = content
    }
}
