
package org.opentele.server.cpr.stamdatalookup.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NamePersonQueryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NamePersonQueryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://rep.oio.dk/ebxml/xml/schemas/dkcc/2003/02/13/}PersonGivenName"/>
 *         &lt;element ref="{http://rep.oio.dk/ebxml/xml/schemas/dkcc/2003/02/13/}PersonMiddleName" minOccurs="0"/>
 *         &lt;element ref="{http://rep.oio.dk/ebxml/xml/schemas/dkcc/2003/02/13/}PersonSurnameName"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NamePersonQueryType", namespace = "http://nsi.dk/2011/09/23/StamdataCpr/", propOrder = {
    "personGivenName",
    "personMiddleName",
    "personSurnameName"
})
public class NamePersonQueryType {

    @XmlElement(name = "PersonGivenName", namespace = "http://rep.oio.dk/ebxml/xml/schemas/dkcc/2003/02/13/", required = true)
    protected String personGivenName;
    @XmlElement(name = "PersonMiddleName", namespace = "http://rep.oio.dk/ebxml/xml/schemas/dkcc/2003/02/13/")
    protected String personMiddleName;
    @XmlElement(name = "PersonSurnameName", namespace = "http://rep.oio.dk/ebxml/xml/schemas/dkcc/2003/02/13/", required = true)
    protected String personSurnameName;

    /**
     * Gets the value of the personGivenName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonGivenName() {
        return personGivenName;
    }

    /**
     * Sets the value of the personGivenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonGivenName(String value) {
        this.personGivenName = value;
    }

    /**
     * Gets the value of the personMiddleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonMiddleName() {
        return personMiddleName;
    }

    /**
     * Sets the value of the personMiddleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonMiddleName(String value) {
        this.personMiddleName = value;
    }

    /**
     * Gets the value of the personSurnameName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonSurnameName() {
        return personSurnameName;
    }

    /**
     * Sets the value of the personSurnameName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonSurnameName(String value) {
        this.personSurnameName = value;
    }

}
