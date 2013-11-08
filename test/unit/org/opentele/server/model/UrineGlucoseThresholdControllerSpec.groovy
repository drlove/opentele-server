package org.opentele.server.model

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import org.opentele.server.ThresholdService
import spock.lang.Specification

@TestFor(UrineGlucoseThresholdController)
@Build([UrineGlucoseThreshold, PatientGroup, MeasurementType, StandardThresholdSet, Threshold])
class UrineGlucoseThresholdControllerSpec extends Specification {

    UrineGlucoseThreshold urineGlucoseThreshold
    PatientGroup patientGroup
    StandardThresholdSet standardThresholdSet

    UrineGlucoseThresholdCommand command

    def setup() {
        controller.thresholdService = Mock(ThresholdService)
        controller.standardThresholdSetService = Mock(StandardThresholdSetService)
        command = Mock(UrineGlucoseThresholdCommand)
        urineGlucoseThreshold = UrineGlucoseThreshold.build()
        patientGroup = PatientGroup.build()
        standardThresholdSet = StandardThresholdSet.build(patientGroup: patientGroup)
        session.lastController = "lastController"
        session.lastAction = "lastAction"
    }


    @SuppressWarnings("GroovyAssignabilityCheck")
    void "when editing an unknown threshold expect a redirect to last controller and action with a not found message"() {
        when:
        controller.edit()

        then:
        1 * controller.thresholdService.getThresholdCommandForEdit(null)
        0 * controller.standardThresholdSetService.findStandardThresholdSetForThreshold(_ as Threshold)

        flash.message == "default.not.found.message"
        response.redirectedUrl == '/lastController/lastAction'
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    void "when editing an existing threshold expect the edit view to be rendered with the correct model"() {
        given:
        params.id = urineGlucoseThreshold.id

        when:
        controller.edit()

        then:
        1 * command.getProperty('threshold') >> urineGlucoseThreshold
        1 * controller.thresholdService.getThresholdCommandForEdit(urineGlucoseThreshold) >> command
        1 * controller.standardThresholdSetService.findStandardThresholdSetForThreshold(urineGlucoseThreshold) >> standardThresholdSet
        view == '/threshold/edit'
        model.command == command
        model.patientGroup == patientGroup
    }

    void "when updating an unknown threshold expect a redirect to last controller and action with a not found message"() {
        when:
        controller.update(command)

        then:
        1 * controller.thresholdService.updateThreshold(command)
        1 * command.hasErrors() >> true
        1 * command.getProperty('threshold') >> null

        flash.message == "default.not.found.message"
        response.redirectedUrl == '/lastController/lastAction'
    }

    void "when updating an existing threshold with a command that has validation errors expect the edit view to be rendered with the correct model"() {
        when:
        controller.update(command)

        then:
        1 * controller.thresholdService.updateThreshold(command)
        1 * command.hasErrors() >> true
        1 * command.getProperty('threshold') >> urineGlucoseThreshold
        1 * controller.standardThresholdSetService.findStandardThresholdSetForThreshold(urineGlucoseThreshold) >> standardThresholdSet

        view == '/threshold/edit'
        model.command == command
        model.patientGroup == patientGroup
    }

    void "when updating an existing threshold with a command without validation errors expect a redirect to last controller and action with an update message"() {
        when:
        controller.update(command)

        then:
        1 * controller.thresholdService.updateThreshold(command)
        1 * command.hasErrors() >> false
        1 * command.getProperty('threshold') >> urineGlucoseThreshold
        flash.message == "default.updated.message"
        response.redirectedUrl == '/lastController/lastAction'
    }
}
