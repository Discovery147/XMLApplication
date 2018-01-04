package com.sizonenko.xmlapp.handler;

import com.sizonenko.xmlapp.entity.Hotel;
import com.sizonenko.xmlapp.entity.Info;
import com.sizonenko.xmlapp.entity.Request;
import com.sizonenko.xmlapp.entity.Voucher;
import com.sizonenko.xmlapp.parsing.RequestEnum;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class RequestHandler extends DefaultHandler{
    public static final Logger LOGGER = Logger.getLogger(RequestHandler.class);
    private Set<Request> requests;
    private Voucher currentVoucher;
    private Info currentInfo;
    private RequestEnum currentEnum;
    private EnumSet<RequestEnum> withText;

    public RequestHandler(){
        requests = new HashSet<>();
        withText = EnumSet.range(RequestEnum.COUNTRY, RequestEnum.INFO);
    }

    public Set<Request> getRequests() {
        return requests;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch(localName){
            case "voucher":
                currentVoucher = new Voucher();
                currentVoucher.setHotel(new Hotel());
                currentVoucher.setType(attributes.getValue("type"));
                currentVoucher.setRequestId(attributes.getValue("request-id"));
                break;
            case "info":
                currentInfo = new Info();
                currentInfo.setType(attributes.getValue("type"));
                currentInfo.setRequestId(attributes.getValue("request-id"));
                break;
            default:
                if(localName.contains("-")){
                    localName = localName.replace('-','_');
                }
                RequestEnum temp = RequestEnum.valueOf(localName.toUpperCase());
                if(withText.contains(temp)){
                    currentEnum = temp;
                }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch(localName) {
            case "voucher":
                requests.add(currentVoucher);
                currentVoucher = null;
                break;
            case "info":
                requests.add(currentInfo);
                currentInfo = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {

        String s = new String (ch, start, length).trim();
        if(currentEnum != null){
            switch(currentEnum){
                case COUNTRY:
                    if(currentInfo!=null){
                        currentInfo.setCountry(s);
                    }else {
                        currentVoucher.setCountry(s);
                    }
                    break;
                case DAYS:
                    if(currentInfo!=null){
                        currentInfo.setDays(Integer.parseInt(s));
                    }else {
                        currentVoucher.setDays(Integer.parseInt(s));
                    }
                    break;
                case NIGHTS:
                    if(currentInfo!=null){
                        currentInfo.setNights(Integer.parseInt(s));
                    }else {
                        currentVoucher.setNights(Integer.parseInt(s));
                    }
                    break;
                case TRANSPORT:
                    currentVoucher.setTransport(s);
                    break;
                case COST:
                    currentVoucher.setCost(Integer.parseInt(s));
                    break;
                case INCLUDED:
                    currentVoucher.setIncluded(s);
                    break;
                case STARS:
                    currentVoucher.getHotel().setStars(Integer.parseInt(s));
                    break;
                case FOOD:
                    currentVoucher.getHotel().setFood(s);
                    break;
                case ROOM:
                    currentVoucher.getHotel().setRoom(Integer.parseInt(s));
                    break;
                case CONDITIONER:
                    currentVoucher.getHotel().setConditioner(Boolean.valueOf(s));
                    break;
                case TV:
                    currentVoucher.getHotel().setTv(Boolean.valueOf(s));
                    break;
                case GYM:
                    currentVoucher.getHotel().setGym(Boolean.valueOf(s));
                    break;
                case AVERAGE_COST:
                    currentInfo.setAverageCost(Integer.valueOf(s));
                    break;
                default:
                    LOGGER.log(Level.ERROR, "EnumConstantNotPresentException: " + currentEnum.getDeclaringClass() + ", " + currentEnum.name());
                    throw new EnumConstantNotPresentException(currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
