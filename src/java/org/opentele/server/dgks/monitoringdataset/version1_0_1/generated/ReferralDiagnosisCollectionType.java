
package org.opentele.server.dgks.monitoringdataset.version1_0_1.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReferralDiagnosisCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferralDiagnosisCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oio:medcom:chronicdataset:1.0.0}ReferralDiagnosis" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferralDiagnosisCollectionType", propOrder = {
    "referralDiagnosis"
})
public class ReferralDiagnosisCollectionType {

    @XmlElement(name = "ReferralDiagnosis", required = true)
    protected List<ReferralDiagnosisType> referralDiagnosis;

    /**
     * Gets the value of the referralDiagnosis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the referralDiagnosis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReferralDiagnosis().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferralDiagnosisType }
     * 
     * 
     */
    public List<ReferralDiagnosisType> getReferralDiagnosis() {
        if (referralDiagnosis == null) {
            referralDiagnosis = new ArrayList<ReferralDiagnosisType>();
        }
        return this.referralDiagnosis;
    }

}
