package org.opentele.server.milou

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import org.opentele.server.model.Measurement

@TestFor(MilouExportService)
@TestMixin(GrailsUnitTestMixin)
class MilouExportServiceTest {
    void testExportsNewCtgMeasurementsAndMarksThemAsExported() {
        def singleMeasurement = new Measurement()

        def ctgMeasurementServiceControl = mockFor(CtgMeasurementService, false)
        ctgMeasurementServiceControl.demand.ctgMeasurementsToExport { -> [singleMeasurement]}
        ctgMeasurementServiceControl.demand.markAsExported { measurement ->
            assert measurement == singleMeasurement
        }

        def milouWebServiceClientServiceControl = mockFor(MilouWebServiceClientService)
        milouWebServiceClientServiceControl.demand.sendCtgMeasurement { measurement ->
            assert measurement == singleMeasurement
            true
        }

        def ctgExportService = new MilouExportService()
        ctgExportService.ctgMeasurementService = ctgMeasurementServiceControl.createMock()
        ctgExportService.milouWebServiceClientService = milouWebServiceClientServiceControl.createMock()

        ctgExportService.exportToMilou()

        ctgMeasurementServiceControl.verify()
    }

    void testDoesNotMarkMeasurementsAsExportedIfExportFails() {
        def ctgMeasurementServiceControl = mockFor(CtgMeasurementService, false)
        ctgMeasurementServiceControl.demand.ctgMeasurementsToExport { -> [new Measurement()]}

        def milouWebServiceClientServiceControl = mockFor(MilouWebServiceClientService)
        milouWebServiceClientServiceControl.demand.sendCtgMeasurement { measurement ->
            false
        }

        def ctgExportService = new MilouExportService()
        ctgExportService.ctgMeasurementService = ctgMeasurementServiceControl.createMock()
        ctgExportService.milouWebServiceClientService = milouWebServiceClientServiceControl.createMock()

        ctgExportService.exportToMilou()

        ctgMeasurementServiceControl.verify()
    }
}
