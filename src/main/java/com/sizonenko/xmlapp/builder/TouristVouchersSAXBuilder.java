package com.sizonenko.xmlapp.builder;

import com.sizonenko.xmlapp.entity.Request;
import com.sizonenko.xmlapp.handler.RequestHandler;
import org.apache.log4j.Level;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class TouristVouchersSAXBuilder extends AbstractTouristVouchersBuilder{
    private RequestHandler sh;
    private XMLReader reader;

    public TouristVouchersSAXBuilder(){
    }

    public TouristVouchersSAXBuilder(Set<Request> set){
        super(set);
    }

    @Override
    public void buildSetRequests(String filename){
        try{
            sh = new RequestHandler();
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(sh);
            reader.parse(filename);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "SAX parser error");
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "IO thread error");
        }
        requests = sh.getRequests();
    }
}
