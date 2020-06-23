package ec.carper.ingenio.actions

import org.openxava.actions.*
import org.openxava.model.*
import org.openxava.model.meta.*
import org.openxava.validators.*

public class RestringirDeleteSelectedAction extends TabBaseAction implements IChainActionWithArgv {

	private String nextAction = null;

	public void execute() throws Exception {
        addMessage("accion_restringida")
        getView().clear()
	}

	private MetaModel getMetaModel() {
		return MetaModel.get(getTab().getModelName());
	}

	public String getNextAction() throws Exception {
		return nextAction;
	}
	
	public String getNextActionArgv() throws Exception {
		return "row=" + getRow();
	}
}
