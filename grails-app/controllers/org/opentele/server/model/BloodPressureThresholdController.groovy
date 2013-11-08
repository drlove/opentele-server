package org.opentele.server.model
import grails.plugins.springsecurity.Secured
import org.opentele.server.ThresholdService
import org.opentele.server.annotations.SecurityWhiteListController
import org.opentele.server.model.types.PermissionName

@Secured(PermissionName.NONE)
class BloodPressureThresholdController {
    ThresholdService thresholdService
    StandardThresholdSetService standardThresholdSetService
    static allowedMethods = [update: "POST"]

    @Secured(PermissionName.THRESHOLD_WRITE)
    @SecurityWhiteListController
    def edit(Long id) {
        def command = thresholdService.getThresholdCommandForEdit(BloodPressureThreshold.get(id))
        def threshold = command?.threshold
        if (!threshold) {
            return notFound(id)
        }

        def standardThresholdSet = standardThresholdSetService.findStandardThresholdSetForThreshold(threshold)

        render(view: '/threshold/edit', model: [
                command: command,
                patientGroup: standardThresholdSet?.patientGroup
        ])
    }

    @Secured(PermissionName.THRESHOLD_WRITE)
    @SecurityWhiteListController
    def update(BloodPressureThresholdCommand command) {
        thresholdService.updateThreshold(command)
        if(command.hasErrors()) {
            def threshold = command?.threshold
            if(!threshold) {
                return notFound(params.id)
            }
            def standardThresholdSet = standardThresholdSetService.findStandardThresholdSetForThreshold(threshold)
            render(view: '/threshold/edit', model: [
                    command: command,
                    patientGroup: standardThresholdSet?.patientGroup
            ])
        } else {
            flash.message = message(code: 'default.updated.message', args: [message(code: 'standardThreshold.label', default: 'StandardThreshold'), command.threshold.id])
            redirect(action: session.lastAction, controller: session.lastController, params: session.lastParams)
        }
    }

    private notFound(def id) {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'standardThreshold.label', default: 'StandardThreshold'), id])
        redirect(action: session.lastAction, controller: session.lastController)
    }

}
