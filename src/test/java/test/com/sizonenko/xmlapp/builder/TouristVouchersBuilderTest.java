package test.com.sizonenko.xmlapp.builder;

import com.sizonenko.xmlapp.builder.TouristVouchersDOMBuilder;
import com.sizonenko.xmlapp.builder.TouristVouchersMarshallerBuilder;
import com.sizonenko.xmlapp.builder.TouristVouchersSAXBuilder;
import com.sizonenko.xmlapp.builder.TouristVouchersStAXBuilder;
import com.sizonenko.xmlapp.factory.TouristVouchersBuilderFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TouristVouchersBuilderTest {
    private final static String PATH_FILE = "data/tourist_vouchers.xml";

    @Test
    public void buildSAXSetRequests(){
        int expected = 16;
        TouristVouchersSAXBuilder saxBuilder = (TouristVouchersSAXBuilder)TouristVouchersBuilderFactory.
                createTouristVouchersBuilder(TouristVouchersBuilderFactory.TypeParser.SAX);
        saxBuilder.buildSetRequests(PATH_FILE);
        Assert.assertEquals(expected, saxBuilder.getRequests().size());
    }

    @Test
    public void buildDOMSetRequests(){
        int expected = 16;
        TouristVouchersDOMBuilder domBuilder = (TouristVouchersDOMBuilder)TouristVouchersBuilderFactory.
                createTouristVouchersBuilder(TouristVouchersBuilderFactory.TypeParser.DOM);
        domBuilder.buildSetRequests(PATH_FILE);
        Assert.assertEquals(expected, domBuilder.getRequests().size());
    }

    @Test
    public void buildStAXSetRequests(){
        int expected = 16;
        TouristVouchersStAXBuilder staxBuilder = (TouristVouchersStAXBuilder)TouristVouchersBuilderFactory.
                createTouristVouchersBuilder(TouristVouchersBuilderFactory.TypeParser.STAX);
        staxBuilder.buildSetRequests(PATH_FILE);
        Assert.assertEquals(expected, staxBuilder.getRequests().size());
    }

    @Test
    public void buildMarshallerSetRequests(){
        int expected = 16;
        TouristVouchersMarshallerBuilder marshBuilder = (TouristVouchersMarshallerBuilder)TouristVouchersBuilderFactory.
                createTouristVouchersBuilder(TouristVouchersBuilderFactory.TypeParser.MARSHALLING);
        marshBuilder.buildSetRequests(PATH_FILE);
        Assert.assertEquals(expected, marshBuilder.getRequests().size());
    }
}
