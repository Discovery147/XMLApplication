
package com.sizonenko.xmlapp.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Info", propOrder = {
    "averageCost"
})
public class Info
    extends Request
{

    @XmlElement(name = "average-cost", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected int averageCost;

    public int getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(int value) {
        this.averageCost = value;
    }

}
