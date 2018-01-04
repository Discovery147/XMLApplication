package com.sizonenko.xmlapp.factory;

import com.sizonenko.xmlapp.builder.*;
import com.sizonenko.xmlapp.exception.EnumInvalidTypeException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class TouristVouchersBuilderFactory {
    public static final Logger LOGGER = Logger.getLogger(TouristVouchersBuilderFactory.class);
    public enum TypeParser {
        SAX, STAX, DOM, MARSHALLING
    }
    private TouristVouchersBuilderFactory(){
    }

    public static AbstractTouristVouchersBuilder createTouristVouchersBuilder(TypeParser typeParser){
        try {
            switch (typeParser) {
                case SAX:
                    return new TouristVouchersSAXBuilder();
                case STAX:
                    return new TouristVouchersStAXBuilder();
                case DOM:
                    return new TouristVouchersDOMBuilder();
                case MARSHALLING:
                    return new TouristVouchersMarshallerBuilder();
                default:
                    LOGGER.log(Level.ERROR, "EnumConstantNotPresentException: " + typeParser.getDeclaringClass() + ", " + typeParser.name());
                    throw new EnumConstantNotPresentException(typeParser.getDeclaringClass(), typeParser.name());
            }
        }catch(IllegalArgumentException | NullPointerException ex){
            LOGGER.log(Level.FATAL, "Enum is null");
            throw new RuntimeException();
        }
    }
}
