package com.sizonenko.xmlapp.builder;

import com.sizonenko.xmlapp.entity.Hotel;
import com.sizonenko.xmlapp.entity.Info;
import com.sizonenko.xmlapp.entity.Request;
import com.sizonenko.xmlapp.entity.Voucher;
import com.sizonenko.xmlapp.exception.EnumInvalidTypeException;
import com.sizonenko.xmlapp.parsing.RequestEnum;
import org.apache.log4j.Level;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class TouristVouchersStAXBuilder extends AbstractTouristVouchersBuilder{
    private XMLInputFactory inputFactory;

    public TouristVouchersStAXBuilder(){
    }

    public TouristVouchersStAXBuilder(Set<Request> set){
        super(set);
    }

    @Override
    public void buildSetRequests(String filename){
        inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader;
        String name;
        try(FileInputStream inputStream = new FileInputStream(new File(filename))){
            reader = inputFactory.createXMLStreamReader(inputStream);
            while(reader.hasNext()){
                int type = reader.next();
                if(type == XMLStreamConstants.START_ELEMENT){
                    name = reader.getLocalName();
                    if (name.contains("-")){
                        name = name.replace('-','_');
                    }
                    if(RequestEnum.valueOf(name.toUpperCase()) == RequestEnum.VOUCHER){
                        Voucher voucher = buildVoucher(reader);
                        requests.add(voucher);
                    }
                    if(RequestEnum.valueOf(name.toUpperCase()) == RequestEnum.INFO){
                        Info info = buildInfo(reader);
                        requests.add(info);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.ERROR, "File not found: " + filename);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "IO thread exception");
        } catch (XMLStreamException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }

    private Voucher buildVoucher(XMLStreamReader reader) throws XMLStreamException{
        Voucher voucher = new Voucher();
        voucher.setRequestId(reader.getAttributeValue(null,RequestEnum.REQUEST_ID.getValue()));
        voucher.setType(reader.getAttributeValue(null,RequestEnum.TYPE.getValue()));

        String name;
        while(reader.hasNext()){
            int type = reader.next();
            switch(type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    if(name.contains("-")){
                        name = name.replace('-','_');
                    }
                    switch(RequestEnum.valueOf(name.toUpperCase())){
                        case COUNTRY:
                            voucher.setCountry(getXMLText(reader));
                            break;
                        case DAYS:
                            voucher.setDays(Integer.parseInt(getXMLText(reader)));
                            break;
                        case NIGHTS:
                            voucher.setNights(Integer.parseInt(getXMLText(reader)));
                            break;
                        case TRANSPORT:
                            voucher.setTransport(getXMLText(reader));
                            break;
                        case HOTEL:
                            voucher.setHotel(getXMLHotel(reader));
                            break;
                        case COST:
                            voucher.setCost(Integer.parseInt(getXMLText(reader)));
                            break;
                        case INCLUDED:
                            voucher.setIncluded(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if(RequestEnum.valueOf(name.toUpperCase()) == RequestEnum.VOUCHER){
                        return voucher;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag Voucher");
    }

    private Info buildInfo(XMLStreamReader reader) throws XMLStreamException{
        Info info = new Info();
        info.setRequestId(reader.getAttributeValue(null,RequestEnum.REQUEST_ID.getValue()));
        info.setType(reader.getAttributeValue(null,RequestEnum.TYPE.getValue()));

        String name;
        while(reader.hasNext()){
            int type = reader.next();
            switch(type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    if(name.contains("-")){
                        name = name.replace('-','_');
                    }
                    switch(RequestEnum.valueOf(name.toUpperCase())){
                        case COUNTRY:
                            info.setCountry(getXMLText(reader));
                            break;
                        case DAYS:
                            info.setDays(Integer.parseInt(getXMLText(reader)));
                            break;
                        case NIGHTS:
                            info.setNights(Integer.parseInt(getXMLText(reader)));
                            break;
                        case AVERAGE_COST:
                            info.setAverageCost(Integer.parseInt(getXMLText(reader)));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if(name.contains("-")){
                        name = name.replace('-','_');
                    }
                    if(RequestEnum.valueOf(name.toUpperCase()) == RequestEnum.INFO){
                        return info;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag Info");
    }

    private Hotel getXMLHotel(XMLStreamReader reader)throws XMLStreamException{
        Hotel hotel = new Hotel();
        int type;
        String name;
        while(reader.hasNext()){
            type = reader.next();
            switch(type){
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    if(name.contains("-")){
                        name = name.replace('-','_');
                    }
                    switch(RequestEnum.valueOf(name.toUpperCase())){
                        case STARS:
                            hotel.setStars(Integer.parseInt(getXMLText(reader)));
                            break;
                        case FOOD:
                            hotel.setFood(getXMLText(reader));
                            break;
                        case ROOM:
                            hotel.setRoom(Integer.parseInt(getXMLText(reader)));
                            break;
                        case CONDITIONER:
                            hotel.setConditioner(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case TV:
                            hotel.setTv(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case GYM:
                            hotel.setGym(Boolean.valueOf(getXMLText(reader)));

                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if(name.contains("-")){
                        name = name.replace('-','_');
                    }
                    if(RequestEnum.valueOf(name.toUpperCase()) == RequestEnum.HOTEL){
                        return hotel;
                    }

            }
        }
        throw new XMLStreamException("Unknown element in tag Hotel");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException{
        String text = null;
        if(reader.hasNext()){
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
