//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.04.04 at 09:07:25 PM WIB 
//


package generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="minlat" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="minlon" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="maxlat" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *       &lt;attribute name="maxlon" type="{http://www.w3.org/2001/XMLSchema}double" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "bounds")
public class Bounds {

    @XmlAttribute(name = "minlat")
    protected Double minlat;
    @XmlAttribute(name = "minlon")
    protected Double minlon;
    @XmlAttribute(name = "maxlat")
    protected Double maxlat;
    @XmlAttribute(name = "maxlon")
    protected Double maxlon;

    /**
     * Gets the value of the minlat property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinlat() {
        return minlat;
    }

    /**
     * Sets the value of the minlat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinlat(Double value) {
        this.minlat = value;
    }

    /**
     * Gets the value of the minlon property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinlon() {
        return minlon;
    }

    /**
     * Sets the value of the minlon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinlon(Double value) {
        this.minlon = value;
    }

    /**
     * Gets the value of the maxlat property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxlat() {
        return maxlat;
    }

    /**
     * Sets the value of the maxlat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxlat(Double value) {
        this.maxlat = value;
    }

    /**
     * Gets the value of the maxlon property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaxlon() {
        return maxlon;
    }

    /**
     * Sets the value of the maxlon property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaxlon(Double value) {
        this.maxlon = value;
    }

}