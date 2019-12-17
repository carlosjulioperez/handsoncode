package ec.carper.ingenio.test;
 
import java.util.List;

import ec.carper.ingenio.model.BrixDensidadWp;
import java.math.*;
import javax.persistence.Query;
import org.apache.commons.logging.*;
import org.openxava.tests.*;
import static org.openxava.jpa.XPersistence.*;

public class BrixDensidadWpTest extends ModuleTestBase {

    private static Log log = LogFactory.getLog(BrixDensidadWpTest.class);
 
    public BrixDensidadWpTest(String testName) {
        super(testName, "Ingenio", "BrixDensidadWp");
    }
 
    protected void setUp() throws Exception { // setUp() is always executed
                                              // before each test
        super.setUp(); // It's needed because ModuleTestBase uses it for
                        // initializing, even JPA is initialized here.
    }
 
    protected void tearDown() throws Exception { // tearDown() is always
                                                 // executed after each test
        super.tearDown(); // It's needed, ModuleTestBase closes resources here
    }

    public BigDecimal getP(BigDecimal w){
        Query query = getManager().
            createQuery("select o.p from BrixDensidadWp o where o.w <= :w order by o.w desc")
        query.setParameter("w", w);
        
        List records = query.getResultList();
        return records.isEmpty() ? BigDecimal.ZERO: (BigDecimal) records.get(0);
    }

    public void testGetP(){
        log.warn ("============================================================");
        log.warn (getP(new BigDecimal(14.93)));
        //System.out.println(getP(new BigDecimal(14.93)));
    }

}
