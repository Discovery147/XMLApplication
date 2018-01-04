
package com.sizonenko.xmlapp.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _Request_QNAME = new QName("", "request");
    private final static QName _Voucher_QNAME = new QName("", "voucher");
    private final static QName _Info_QNAME = new QName("", "info");

    public ObjectFactory() {
    }

    public Request createRequest() {
        return new Request();
    }

    public TouristVouchers createTouristVouchers() {
        return new TouristVouchers();
    }

    public Voucher createVoucher() {
        return new Voucher();
    }

    public Info createInfo() {
        return new Info();
    }

    public Hotel createHotel() {
        return new Hotel();
    }

    @XmlElementDecl(namespace = "", name = "request")
    public JAXBElement<Request> createRequest(Request value) {
        return new JAXBElement<Request>(_Request_QNAME, Request.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "voucher", substitutionHeadNamespace = "", substitutionHeadName = "request")
    public JAXBElement<Voucher> createVoucher(Voucher value) {
        return new JAXBElement<Voucher>(_Voucher_QNAME, Voucher.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "info", substitutionHeadNamespace = "", substitutionHeadName = "request")
    public JAXBElement<Info> createInfo(Info value) {
        return new JAXBElement<Info>(_Info_QNAME, Info.class, null, value);
    }

}
