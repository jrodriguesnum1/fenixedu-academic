package net.sourceforge.fenixedu.applicationTier.Servico.manager;

import java.util.Date;

import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.domain.DegreeCurricularPlan;
import net.sourceforge.fenixedu.domain.EnrolmentPeriodInClasses;
import net.sourceforge.fenixedu.domain.EnrolmentPeriodInCurricularCourses;
import net.sourceforge.fenixedu.domain.EnrolmentPeriodInCurricularCoursesSpecialSeason;
import net.sourceforge.fenixedu.domain.EnrolmentPeriodInImprovementOfApprovedEnrolment;
import net.sourceforge.fenixedu.domain.ExecutionDegree;
import net.sourceforge.fenixedu.domain.ExecutionSemester;
import net.sourceforge.fenixedu.domain.ReingressionPeriod;
import net.sourceforge.fenixedu.domain.RootDomainObject;
import net.sourceforge.fenixedu.domain.degree.DegreeType;
import pt.ist.fenixWebFramework.security.accessControl.Checked;
import pt.ist.fenixWebFramework.services.Service;

public class CreateEnrolmentPeriods {

    @Service
    @Checked("RolePredicates.MANAGER_PREDICATE")
    public static void run(final Integer executionPeriodID, final DegreeType degreeType, final String enrolmentPeriodClassName,
	    final Date startDate, final Date endDate) throws FenixServiceException {

	final ExecutionSemester executionSemester = RootDomainObject.getInstance().readExecutionSemesterByOID(executionPeriodID);
	for (final ExecutionDegree executionDegree : executionSemester.getExecutionYear().getExecutionDegrees()) {

	    final DegreeCurricularPlan degreeCurricularPlan = executionDegree.getDegreeCurricularPlan();
	    if (degreeType == null || degreeType == degreeCurricularPlan.getDegree().getDegreeType()) {

		createPeriod(enrolmentPeriodClassName, startDate, endDate, executionSemester, degreeCurricularPlan);
	    }
	}
    }

    private static void createPeriod(final String enrolmentPeriodClassName, final Date startDate, final Date endDate,
	    final ExecutionSemester executionSemester, final DegreeCurricularPlan degreeCurricularPlan)
	    throws FenixServiceException {

	if (EnrolmentPeriodInClasses.class.getName().equals(enrolmentPeriodClassName)) {

	    new EnrolmentPeriodInClasses(degreeCurricularPlan, executionSemester, startDate, endDate);

	} else if (EnrolmentPeriodInCurricularCourses.class.getName().equals(enrolmentPeriodClassName)) {

	    new EnrolmentPeriodInCurricularCourses(degreeCurricularPlan, executionSemester, startDate, endDate);

	} else if (EnrolmentPeriodInCurricularCoursesSpecialSeason.class.getName().equals(enrolmentPeriodClassName)) {

	    new EnrolmentPeriodInCurricularCoursesSpecialSeason(degreeCurricularPlan, executionSemester, startDate, endDate);

	} else if (EnrolmentPeriodInImprovementOfApprovedEnrolment.class.getName().equals(enrolmentPeriodClassName)) {

	    new EnrolmentPeriodInImprovementOfApprovedEnrolment(degreeCurricularPlan, executionSemester, startDate, endDate);

	} else if (ReingressionPeriod.class.getName().equals(enrolmentPeriodClassName)) {

	    new ReingressionPeriod(degreeCurricularPlan, executionSemester, startDate, endDate);

	} else {
	    throw new FenixServiceException("error.invalid.enrolment.period.class.name");
	}
    }

}