package com.sizonenko.xmlapp.builder;

import com.sizonenko.xmlapp.entity.Request;
import com.sizonenko.xmlapp.entity.TouristVouchers;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.Set;
import org.apache.log4j.Level;

public class TouristVouchersMarshallerBuilder extends AbstractTouristVouchersBuilder{

    JAXBContext context;
    Unmarshaller um;

    public TouristVouchersMarshallerBuilder(){
    }

    public TouristVouchersMarshallerBuilder(Set<Request> set){
        super(set);
    }

    @Override
    public void buildSetRequests(String filename) {
        try{
            context = JAXBContext.newInstance(TouristVouchers.class);
            um = context.createUnmarshaller();
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(filename.split("\\.")[0]+".xsd");

            Schema schema = factory.newSchema(schemaLocation);
            um.setSchema(schema);
            TouristVouchers vouchers = (TouristVouchers)um.unmarshal(new File(filename.split("\\.")[0] + ".xml"));
            requests = vouchers.getTransformRequest();
        } catch (JAXBException ex) {
            LOGGER.log(Level.ERROR, "File " + filename + " not validate");
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, "SAX exception");
        }
    }
}
