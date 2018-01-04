
package com.sizonenko.xmlapp.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hotel", propOrder = {
    "stars",
    "food",
    "room",
    "conditioner",
    "tv",
    "gym"
})
public class Hotel {

    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected int stars;
    @XmlElement(required = true, defaultValue = "no")
    protected String food;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected int room;
    @XmlElement(defaultValue = "false")
    protected boolean conditioner;
    @XmlElement(defaultValue = "false")
    protected boolean tv;
    @XmlElement(defaultValue = "false")
    protected boolean gym;

    public int getStars() {
        return stars;
    }

    public void setStars(int value) {
        this.stars = value;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String value) {
        this.food = value;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int value) {
        this.room = value;
    }

    public boolean isConditioner() {
        return conditioner;
    }

    public void setConditioner(boolean value) {
        this.conditioner = value;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean value) {
        this.tv = value;
    }

    public boolean isGym() {
        return gym;
    }

    public void setGym(boolean value) {
        this.gym = value;
    }

}
