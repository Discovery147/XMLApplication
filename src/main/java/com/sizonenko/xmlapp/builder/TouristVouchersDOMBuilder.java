package com.sizonenko.xmlapp.builder;

import com.sizonenko.xmlapp.entity.Hotel;
import com.sizonenko.xmlapp.entity.Info;
import com.sizonenko.xmlapp.entity.Request;
import com.sizonenko.xmlapp.entity.Voucher;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;
import org.apache.log4j.Level;

public class TouristVouchersDOMBuilder extends AbstractTouristVouchersBuilder {
    private DocumentBuilder docBuilder;

    public TouristVouchersDOMBuilder() {
    }

    public TouristVouchersDOMBuilder(Set <Request> set){
        super(set);
    }

    @Override
    public void buildSetRequests(String filename){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.ERROR, "Configuration error");

        }
        Document doc;
        try{
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList voucherList = root.getElementsByTagName("voucher");
            for(int i = 0; i < voucherList.getLength(); i++){
                Element voucherElement = (Element) voucherList.item(i);
                Voucher voucher = buildVoucher(voucherElement);
                requests.add(voucher);
            }
            NodeList infoList = root.getElementsByTagName("info");
            for(int i = 0; i < infoList.getLength(); i++){
                Element infoElement = (Element) infoList.item(i);
                Info info = buildInfo(infoElement);
                requests.add(info);
            }

        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "SAX exception");
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "IO thread error");
        }
    }

    private Voucher buildVoucher(Element voucherElement){
        Voucher voucher = new Voucher();

        voucher.setRequestId(voucherElement.getAttribute("request-id"));
        voucher.setType(voucherElement.getAttribute("type"));
        voucher.setCountry(getElementTextContent(voucherElement,"country"));
        voucher.setDays(Integer.parseInt(getElementTextContent(voucherElement,"days")));
        voucher.setNights(Integer.parseInt(getElementTextContent(voucherElement,"nights")));
        voucher.setTransport(getElementTextContent(voucherElement,"transport"));

        Element hotelElement = (Element) voucherElement.getElementsByTagName("hotel").item(0);
        Hotel hotel = new Hotel();
        hotel.setGym(Boolean.valueOf(getElementTextContent(hotelElement,"gym")));
        hotel.setTv(Boolean.valueOf(getElementTextContent(hotelElement,"tv")));
        hotel.setConditioner(Boolean.valueOf(getElementTextContent(hotelElement,"conditioner")));
        hotel.setFood(getElementTextContent(hotelElement,"food"));
        hotel.setRoom(Integer.parseInt(getElementTextContent(hotelElement,"room")));
        hotel.setStars(Integer.parseInt(getElementTextContent(hotelElement,"stars")));
        voucher.setHotel(hotel);

        voucher.setCost(Integer.parseInt(getElementTextContent(voucherElement,"cost")));
        voucher.setIncluded(getElementTextContent(voucherElement,"included"));
        return voucher;
    }

    private Info buildInfo(Element infoElement) {
        Info info = new Info();

        info.setRequestId(infoElement.getAttribute("request-id"));
        info.setType(infoElement.getAttribute("type"));
        info.setCountry(getElementTextContent(infoElement,"country"));
        info.setDays(Integer.parseInt(getElementTextContent(infoElement,"days")));
        info.setNights(Integer.parseInt(getElementTextContent(infoElement,"nights")));
        info.setAverageCost(Integer.parseInt(getElementTextContent(infoElement,"average-cost")));

        return info;
    }

    private static String getElementTextContent(Element element, String elementName){
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
