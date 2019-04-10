package com.example.axondemo.query

import org.springframework.data.jpa.repository.JpaRepository

interface ApplicationViewRepository : JpaRepository<ApplicationView, String>