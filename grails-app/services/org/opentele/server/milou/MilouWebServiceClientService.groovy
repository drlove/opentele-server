package org.opentele.server.milou

import org.opentele.server.model.Measurement
import wslite.soap.SOAPFaultException

class MilouWebServiceClientService {
    def milouSoapClient

    boolean sendCtgMeasurement(Measurement measurement) {
        try {
            milouSoapClient.send(SOAPAction: "http://tempuri.org/ICtgImport/ImportCtg") {
                body {
                    ImportCtg(xmlns: 'http://tempuri.org/') {
                        mkp.yieldUnescaped MilouCTGMeasurementXMLBuilder.build(measurement)
                    }
                }
            }

            true

        } catch (Exception ex) {


            log.error("Error occured while trying to upload CTG measurement (id:'${measurement.id}') to: '${milouSoapClient.serviceURL}'", ex)

            false
        }
    }
}
