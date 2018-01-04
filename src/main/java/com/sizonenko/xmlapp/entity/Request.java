
package com.sizonenko.xmlapp.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Request", propOrder = {
    "country",
    "days",
    "nights"
})
@XmlSeeAlso({
    Voucher.class,
    Info.class
})
public class Request {

    @XmlElement(required = true)
    protected String country;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected int days;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected int nights;
    @XmlAttribute(name = "request-id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String requestId;
    @XmlAttribute(name = "type")
    protected String type;

    public String getCountry() {
        return country;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int value) {
        this.days = value;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int value) {
        this.nights = value;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String value) {
        this.requestId = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

}
