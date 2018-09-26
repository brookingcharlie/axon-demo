package com.example.axondemo.command

import com.example.axondemo.core.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.Repository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LoanApplicationCommandHandlers(@Autowired private val repository: Repository<LoanApplication>) {
    companion object {
        private val logger = LoggerFactory.getLogger(LoanApplicationCommandHandlers::class.java)
    }

    @CommandHandler
    fun handle(command: CreateLoanApplicationCommand) {
        repository.newInstance { LoanApplication(command.id) }
        logger.debug("Finished processing $command")
    }

    @CommandHandler
    fun handle(command: SubmitPersonalDetailsCommand) {
        val application = repository.load(command.applicationId)
        application.invoke { it.setPersonalDetails(command.familyName, command.givenNames) }
        logger.debug("Finished processing $command")
    }

    @CommandHandler
    fun handle(command: AttachDocumentCommand) {
        val application = repository.load(command.applicationId)
        application.invoke { it.attachDocument(command.id, command.name, command.content)}
        logger.debug("Finished processing $command")
    }
}