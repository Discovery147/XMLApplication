package test.com.sizonenko.xmlapp.factory;

import com.sizonenko.xmlapp.builder.TouristVouchersDOMBuilder;
import com.sizonenko.xmlapp.builder.TouristVouchersMarshallerBuilder;
import com.sizonenko.xmlapp.builder.TouristVouchersSAXBuilder;
import com.sizonenko.xmlapp.builder.TouristVouchersStAXBuilder;
import com.sizonenko.xmlapp.factory.TouristVouchersBuilderFactory;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class TouristVouchersBuilderFactoryTest {

    @Test
    public void createTouristVouchersSAXBuilder(){
        Object saxBuilder = TouristVouchersBuilderFactory.
                createTouristVouchersBuilder(TouristVouchersBuilderFactory.TypeParser.SAX);
        assertThat(saxBuilder, instanceOf(TouristVouchersSAXBuilder.class));
    }

    @Test
    public void createTouristVouchersDOMBuilder(){
        Object domBuilder = TouristVouchersBuilderFactory.
                createTouristVouchersBuilder(TouristVouchersBuilderFactory.TypeParser.DOM);
        assertThat(domBuilder, instanceOf(TouristVouchersDOMBuilder.class));
    }

    @Test
    public void createTouristVouchersStAXBuilder(){
        Object staxBuilder = TouristVouchersBuilderFactory.
                createTouristVouchersBuilder(TouristVouchersBuilderFactory.TypeParser.STAX);
        assertThat(staxBuilder, instanceOf(TouristVouchersStAXBuilder.class));
    }

    @Test
    public void createTouristVouchersMarshallerBuilder(){
        Object marshallerBuilder = TouristVouchersBuilderFactory.
                createTouristVouchersBuilder(TouristVouchersBuilderFactory.TypeParser.MARSHALLING);
        assertThat(marshallerBuilder, instanceOf(TouristVouchersMarshallerBuilder.class));
    }
}
