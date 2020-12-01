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

import ec.carper.ingenio.model.CanaMolida;

public class CanaMolidaReportAction extends JasperReportBaseAction {

	private static Log log = LogFactory.getLog(CanaMolidaReportAction.class);
	
	private CanaMolida canaMolida;

	public Map getParameters() throws Exception {
        Messages errores = MapFacade.validate("CanaMolida", getView().getValues());
        if (errores.contains()) throw new ValidationException(errores);
        Map parametros = new HashMap();
        //parametros.put("familia", getSubfamilia().getFamilia().getDescripcion());
        //parametros.put("subfamilia", getSubfamilia().getDescripcion());
        return parametros;
    }
	
	protected JRDataSource getDataSource() throws Exception {
		Vector collection = new Vector();
		collection.add( getCanaMolida() );
        return new JRBeanCollectionDataSource( collection );
    }
	
	protected String getJRXML() {
        return "canamolida.jrxml"; // Para leer del classpath (carpeta "reports" o "informes")
        //return "/home/javi/Products.jrxml"; // Para leer del sistema de ficheros
    }

	private CanaMolida getCanaMolida() throws Exception {
		if (canaMolida == null){
			String id = getView().getValueString("id");
			canaMolida = (CanaMolida) XPersistence.getManager().find(CanaMolida.class, id);
		}
		return canaMolida;
	}
}
