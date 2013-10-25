package org.opentele.server.kihdb

import org.opentele.server.model.Measurement
import org.opentele.server.model.types.MeasurementTypeName

class KihDbMeasurementService {
    def measurementsToExport() {
        return Measurement.createCriteria().list {
            and {
                eq('exportedToKih', false)
                measurementType {
                    not {
                        inList('name', Measurement.notToBeExportedMeasurementTypes)
                    }
                }
            }
        }
    }

    def markAsExported(Measurement measurement) {
        measurement.exportedToKih = true
        measurement.save(failOnError: true)
    }

}