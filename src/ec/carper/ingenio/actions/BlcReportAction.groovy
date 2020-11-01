package ec.carper.ingenio.actions

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
 
import org.openxava.actions.*;
import org.openxava.jpa.XPersistence;
import org.openxava.model.*;
import org.openxava.util.*;
import org.openxava.validators.*;

import ec.carper.ingenio.model.Blc;

public class BlcReportAction extends JasperReportBaseAction {

	private static Log log = LogFactory.getLog(BlcReportAction.class);
	
	private Blc blc;

	public Map getParameters() throws Exception {
        Messages errores = MapFacade.validate("Blc", getView().getValues());
        if (errores.contains()) throw new ValidationException(errores);
        Map parametros = new HashMap();
        //parametros.put("familia", getSubfamilia().getFamilia().getDescripcion());
        //parametros.put("subfamilia", getSubfamilia().getDescripcion());
        return parametros;
    }
	
	protected JRDataSource getDataSource() throws Exception {
		Vector collection = new Vector();
		collection.add( getBlc() );
        return new JRBeanCollectionDataSource( collection );
    }
	
	protected String getJRXML() {
        return "blc.jrxml"; // Para leer del classpath (carpeta "reports" o "informes")
        //return "/home/javi/Products.jrxml"; // Para leer del sistema de ficheros
    }

	private Blc getBlc() throws Exception {
		if (blc == null){
			String id = getView().getValueString("id");
			blc = (Blc) XPersistence.getManager().find(Blc.class, id);
		}
		return blc;
	}
}
