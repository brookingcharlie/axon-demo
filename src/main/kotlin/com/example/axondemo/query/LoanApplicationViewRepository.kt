package com.example.axondemo.query

import org.springframework.data.jpa.repository.JpaRepository

interface LoanApplicationViewRepository : JpaRepository<LoanApplicationView, String>