package DataBeans;

import java.util.List;

/**
 * @author Fernanda Quit�rio
 * 25/06/2003
 *
 */
public class InfoSiteEvaluation implements ISiteComponent {
	
	private List infoEvaluations;


	/**
	 * @return
	 */
	public List getInfoEvaluations() {
		return infoEvaluations;
	}

	/**
	 * @param infoEvaluations
	 */
	public void setInfoEvaluations(List infoEvaluations) {
		this.infoEvaluations = infoEvaluations;
	}

}
