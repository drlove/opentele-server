
package org.opentele.server.cpr.stamdatalookup.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddressAccessType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressAccessType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://rep.oio.dk/cpr.dk/xml/schemas/core/2005/03/18/}MunicipalityCode"/>
 *         &lt;element ref="{http://rep.oio.dk/cpr.dk/xml/schemas/core/2005/03/18/}StreetCode"/>
 *         &lt;element ref="{http://rep.oio.dk/ebxml/xml/schemas/dkcc/2003/02/13/}StreetBuildingIdentifier"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressAccessType", namespace = "http://rep.oio.dk/xkom.dk/xml/schemas/2005/03/15/", propOrder = {
    "municipalityCode",
    "streetCode",
    "streetBuildingIdentifier"
})
public class AddressAccessType {

    @XmlElement(name = "MunicipalityCode", namespace = "http://rep.oio.dk/cpr.dk/xml/schemas/core/2005/03/18/", required = true)
    protected String municipalityCode;
    @XmlElement(name = "StreetCode", namespace = "http://rep.oio.dk/cpr.dk/xml/schemas/core/2005/03/18/", required = true)
    protected String streetCode;
    @XmlElement(name = "StreetBuildingIdentifier", namespace = "http://rep.oio.dk/ebxml/xml/schemas/dkcc/2003/02/13/", required = true)
    protected String streetBuildingIdentifier;

    /**
     * Gets the value of the municipalityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipalityCode() {
        return municipalityCode;
    }

    /**
     * Sets the value of the municipalityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipalityCode(String value) {
        this.municipalityCode = value;
    }

    /**
     * Gets the value of the streetCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetCode() {
        return streetCode;
    }

    /**
     * Sets the value of the streetCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetCode(String value) {
        this.streetCode = value;
    }

    /**
     * Gets the value of the streetBuildingIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetBuildingIdentifier() {
        return streetBuildingIdentifier;
    }

    /**
     * Sets the value of the streetBuildingIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetBuildingIdentifier(String value) {
        this.streetBuildingIdentifier = value;
    }

}
