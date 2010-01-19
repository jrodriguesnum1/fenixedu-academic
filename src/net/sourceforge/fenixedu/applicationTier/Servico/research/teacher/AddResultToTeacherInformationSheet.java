package net.sourceforge.fenixedu.applicationTier.Servico.research.teacher;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.domain.Teacher;
import net.sourceforge.fenixedu.domain.research.result.ResearchResult;
import net.sourceforge.fenixedu.util.PublicationArea;
import pt.ist.fenixWebFramework.security.accessControl.Checked;
import pt.ist.fenixWebFramework.services.Service;

public class AddResultToTeacherInformationSheet extends FenixService {

    @Checked("RolePredicates.TEACHER_PREDICATE")
    @Service
    public static void run(Teacher teacher, Integer resultId, String publicationArea) throws Exception {

	ResearchResult result = (ResearchResult) rootDomainObject.readResearchResultByOID(resultId);
	teacher.addToTeacherInformationSheet(result, PublicationArea.getEnum(publicationArea));
    }

}