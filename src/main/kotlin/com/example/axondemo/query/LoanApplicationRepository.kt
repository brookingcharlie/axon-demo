package com.example.axondemo.query

import com.example.axondemo.command.LoanApplication
import org.springframework.data.jpa.repository.JpaRepository

interface LoanApplicationRepository : JpaRepository<LoanApplication, String>