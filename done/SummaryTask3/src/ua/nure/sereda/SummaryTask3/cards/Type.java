//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.04.18 at 08:17:22 PM EEST 
//


package ua.nure.sereda.SummaryTask3.cards;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Type", propOrder = {

})
public class Type {

    @XmlElement(name = "TypeName", required = true)
    @XmlSchemaType(name = "string")
    private TypeName typeName;
    @XmlAttribute(name = "Send")
    protected Boolean send;


    /**Getter for Type Name
     *
     * @return type name
     */
    public TypeName getTypeName() {
        return typeName;
    }

    /**
     * Setter for Type Name
     *
     * @param value to set
     */
    public void setTypeName(TypeName value) {
        this.typeName = value;
    }

    /**
     * Getter for send attribute
     *
     * @return true or false
     */
    public boolean isSend() {
        if (send == null) {
            return false;
        } else {
            return send;
        }
    }

    /**
     * Setter for Send attribute
     *
     * @param value to set(true or false)
     */
    public void setSend(Boolean value) {
        this.send = value;
    }

}
