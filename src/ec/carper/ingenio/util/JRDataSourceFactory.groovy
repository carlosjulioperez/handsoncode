package ec.carper.ingenio.util

import java.time.LocalDate; 

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

/**
 * JRDataSourceFactory utility class
 * @author carper
 */
class JRDataSourceFactory {

    static DiaTrabajo getDiaTrabajo(){
        // DiaTrabajo d = new DiaTrabajo()
        return new DiaTrabajo( id: "1", fecha: LocalDate.now(), numeroDia: 8 )
    }

	static Blc getBlc(){
        Blc blc = new Blc()
        blc.diaTrabajo = diaTrabajo
        blc.descripcion = "PRUEBA"
		return blc
    }

    static def createBeanCollection(){
        def lista = []
        lista << blc
        return lista
    }
	
	// public static Collection createBeanCollection(){
	// 	Vector collection = new Vector();
	// 	collection.add( getBlc() );
	// 	return collection;
	// }
}

