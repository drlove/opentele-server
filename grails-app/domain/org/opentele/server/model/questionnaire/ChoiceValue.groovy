package org.opentele.server.model.questionnaire

import org.opentele.server.model.AbstractObject 
import org.opentele.server.model.Clinician
import org.opentele.server.model.patientquestionnaire.PatientInputNode;
import org.opentele.server.model.types.Severity


class ChoiceValue extends AbstractObject {
	
    InputNode inputNode
    
    String label
    String value
    int ordering
    
    static constraints = {
        label(nullable:false)
        value(nullable:false)
        ordering(nullable:false)
    }
}
