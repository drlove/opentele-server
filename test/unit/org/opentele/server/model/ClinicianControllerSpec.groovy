package org.opentele.server.model

import grails.buildtestdata.mixin.Build
import grails.plugins.springsecurity.SpringSecurityService
import grails.test.mixin.TestFor
import org.opentele.server.ClinicianService
import org.opentele.server.PasswordService
import org.springframework.dao.DataIntegrityViolationException
import spock.lang.Specification

@TestFor(ClinicianController)
@Build([Clinician, User, PatientGroup, Role])
class ClinicianControllerSpec extends Specification {

    def setup() {
        controller.passwordService = Mock(PasswordService)
        controller.clinicianService = Mock(ClinicianService)
        controller.springSecurityService = Mock(SpringSecurityService)
        PatientGroup.build()
        Role.build()
    }


    def "test index action"() {
        when:
        controller.index()
        then:
        response.redirectedUrl == "/clinician/list"
    }

    def "test list action"() {

        when:
        def model = controller.list()

        then:
        model.clinicianInstanceList.size() == 0
        model.clinicianInstanceTotal == 0
    }

    def "test create action"() {
        when:
        def model = controller.create()
        then:
        model.cmd != null
    }

    def "test save action with sunshine scenario"() {
        setup:
        def command = buildClinicianCommand()
        def clinician = Clinician.build()
        controller.clinicianService.createClinician(command) >> clinician

        when:
        controller.save(command)

        then:
        response.redirectedUrl == "/clinician/show/1"
        flash.message == "default.created.message"
    }

    def "test save action with missing parameters in command"() {
        setup:
        def command = buildClinicianCommand(firstName: "")
        def clinician = Clinician.build()
        controller.clinicianService.createClinician(command) >> clinician

        when:
        controller.save(command)

        then:
        view == "/clinician/create"
        model == [cmd: command]
    }

    def "test save action with errors saving in service"() {
        setup:
        def command = buildClinicianCommand()
        def clinician = Clinician.build()
        clinician.firstName = ""
        clinician.validate()

        controller.clinicianService.createClinician(command) >> clinician

        when:
        controller.save(command)

        then:
        view == "/clinician/create"
        model == [cmd: command]
    }

    def "test show action in sunshine scenario"() {
        setup:
        def clinician = Clinician.build()

        when:
        def model = controller.show(clinician.id)

        then:
        model.clinicianInstance

    }

    def "test show action with wrong id"() {
        when:
        def model = controller.show(1)

        then:
        response.redirectedUrl == "/clinician/list"
        flash.message == "default.not.found.message"
    }

    def "test edit in sunshine scenario"() {
        setup:
        controller.clinicianService.getClinicianCommandForEdit(_) >> buildClinicianCommand()

        when:
        def model = controller.edit(1)

        then:
        model.cmd
    }

    def "test edit with wrong id"() {
        when:
        controller.edit(1)

        then:
        response.redirectedUrl == "/clinician/list"
        flash.message == "default.not.found.message"
    }

    def "test update in sunshine scenario"() {
        setup:
        def command = buildClinicianCommand()
        def clinician = Clinician.build()
        controller.clinicianService.update(_,_) >> clinician

        when:
        controller.update(command, clinician.id, clinician.version)

        then:
        response.redirectedUrl == "/clinician/show/${clinician.id}"
        flash.message == "default.updated.message"
        command.id == clinician.id
        command.version == clinician.version
    }

    def "test update where id is wrong"() {
        when:
        controller.update(buildClinicianCommand(), 1, 1)

        then:
        response.redirectedUrl == "/clinician/list"
        flash.message == "default.not.found.message"
    }

    def "test update where update in service fails due to validation error in clinician instance"() {
        setup:
        def command = buildClinicianCommand()
        def clinician = Clinician.build()
        clinician.firstName = ""
        clinician.validate()
        controller.clinicianService.update(_,_) >> clinician

        when:
        controller.update(command, clinician.id, clinician.version)

        then:
        view == "/clinician/edit"
        model == [cmd: command]
        command.id == clinician.id
        command.version == clinician.version

    }

    def "test update where update in service fails due to validation error in command"() {
        setup:
        def command = buildClinicianCommand(firstName: "")
        command.validate()
        def clinician = Clinician.build()
        controller.clinicianService.update(_,_) >> clinician

        when:
        controller.update(command, clinician.id, clinician.version)

        then:
        view == "/clinician/edit"
        model == [cmd: command]
        command.id == clinician.id
        command.version == clinician.version

    }

    def "test delete in sunshine scenario"() {
        setup:
        def clinician = Clinician.build()

        when:
        controller.delete(clinician.id)

        then:
        response.redirectedUrl == "/clinician/list"
        flash.message == "default.deleted.message"
    }

    def "test delete with wrong id"() {
        when:
        controller.delete(0)

        then:
        0 * controller.clinicianService.delete(_)
        response.redirectedUrl == "/clinician/list"
        flash.message == "default.not.found.message"
    }

    def "test delete where service throws data integrity violation exception"() {
        setup:
         def clinician = Clinician.build()
         when:
         controller.delete(clinician.id)

         then:
         1 * controller.clinicianService.delete(clinician) >> { throw new DataIntegrityViolationException("")}
         response.redirectedUrl == "/clinician/show/${clinician.id}"
         flash.message == "default.not.deleted.message"
    }

    def "test resetPassword in sunshine scenario"() {
        setup:
        def user = new User(username: "username")
        def clinician = Clinician.build(user: user)

        when:
        controller.resetPassword(clinician.id)

        then:
        1 * controller.clinicianService.resetPassword(clinician)
        response.redirectedUrl == "/clinician/show/${clinician.id}"
        flash.message == "clinician.reset-password.done"
    }

    def "test resetPassword where id is wrong"() {
        when:
        controller.resetPassword(0)

        then:
        0 * controller.clinicianService.resetPassword(_)
        response.redirectedUrl == "/clinician/list"
        flash.message == "default.not.found.message"
    }

    private buildClinicianCommand(Map defaults = [:]) {
        def cmd = new ClinicianCommand(firstName: "First", lastName: "Last", username: "user", cleartextPassword: "abcd1234", roleIds: Role.list()*.id, groupIds: PatientGroup.list()*.id)
        defaults.each { key, value ->
            cmd."$key" = value
        }
        return cmd
    }
}
