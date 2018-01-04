
package com.sizonenko.xmlapp.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Voucher", propOrder = {
    "transport",
    "hotel",
    "cost",
    "included"
})
public class Voucher
    extends Request
{

    @XmlElement(required = true)
    protected String transport;
    @XmlElement(required = true)
    protected Hotel hotel;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected int cost;
    @XmlElement(required = true)
    protected String included;

    public String getTransport() {
        return transport;
    }

    public void setTransport(String value) {
        this.transport = value;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel value) {
        this.hotel = value;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int value) {
        this.cost = value;
    }

    public String getIncluded() {
        return included;
    }

    public void setIncluded(String value) {
        this.included = value;
    }

}
