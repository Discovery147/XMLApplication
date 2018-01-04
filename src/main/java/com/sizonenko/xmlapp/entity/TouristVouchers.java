
package com.sizonenko.xmlapp.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request"
})
@XmlRootElement(name = "tourist-vouchers")
public class TouristVouchers {

    @XmlElementRef(name = "request", type = JAXBElement.class)
    protected List<JAXBElement<? extends Request>> request;

    public List<JAXBElement<? extends Request>> getRequest() {
        if (request == null) {
            request = new ArrayList<JAXBElement<? extends Request>>();
        }
        return this.request;
    }

    public Set<Request> getTransformRequest(){
        Set<Request> set = new HashSet<>();
        request.forEach(elem->set.add(elem.getValue()));
        return set;
    }
}
