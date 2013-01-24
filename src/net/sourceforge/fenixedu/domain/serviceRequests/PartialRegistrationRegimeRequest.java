package net.sourceforge.fenixedu.domain.serviceRequests;

import net.sourceforge.fenixedu.dataTransferObject.serviceRequests.AcademicServiceRequestBean;
import net.sourceforge.fenixedu.dataTransferObject.serviceRequests.RegistrationAcademicServiceRequestCreateBean;
import net.sourceforge.fenixedu.domain.ExecutionSemester;
import net.sourceforge.fenixedu.domain.ExecutionYear;
import net.sourceforge.fenixedu.domain.StudentCurricularPlan;
import net.sourceforge.fenixedu.domain.accounting.EventType;
import net.sourceforge.fenixedu.domain.accounting.events.serviceRequests.PartialRegistrationRegimeRequestEvent;
import net.sourceforge.fenixedu.domain.accounting.postingRules.FixedAmountPR;
import net.sourceforge.fenixedu.domain.curricularRules.MaximumNumberOfCreditsForEnrolmentPeriod;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;
import net.sourceforge.fenixedu.domain.serviceRequests.documentRequests.AcademicServiceRequestType;
import net.sourceforge.fenixedu.domain.student.Registration;
import net.sourceforge.fenixedu.domain.student.RegistrationRegime;
import net.sourceforge.fenixedu.domain.student.RegistrationRegimeType;
import net.sourceforge.fenixedu.util.Money;

public class PartialRegistrationRegimeRequest extends PartialRegistrationRegimeRequest_Base {

    private PartialRegistrationRegimeRequest() {
	super();
    }

    public PartialRegistrationRegimeRequest(final RegistrationAcademicServiceRequestCreateBean bean) {
	this();
	super.init(bean);
    }

    @Override
    protected void checkRulesToChangeState(AcademicServiceRequestSituationType situationType) {
	super.checkRulesToChangeState(situationType);
	if (situationType == AcademicServiceRequestSituationType.PROCESSING) {
	    checkEctsCredits(getRegistration(), getExecutionYear());
	}
    }

    private void checkEctsCredits(final Registration registration, final ExecutionYear executionYear) {
	final StudentCurricularPlan studentCurricularPlan = registration.getLastStudentCurricularPlan();

	double enroledEctsCredits = 0d;
	for (final ExecutionSemester semester : executionYear.getExecutionPeriods()) {
	    enroledEctsCredits += studentCurricularPlan.getAccumulatedEctsCredits(semester);

	}

	if (enroledEctsCredits > MaximumNumberOfCreditsForEnrolmentPeriod.MAXIMUM_NUMBER_OF_CREDITS_PARTIAL_TIME) {
	    throw new DomainException("error.RegistrationRegime.semester.has.more.ects.than.maximum.allowed",
		    String.valueOf(enroledEctsCredits), executionYear.getQualifiedName(),
		    String.valueOf(MaximumNumberOfCreditsForEnrolmentPeriod.MAXIMUM_NUMBER_OF_CREDITS_PARTIAL_TIME));
	}
    }

    @Override
    protected void internalChangeState(final AcademicServiceRequestBean academicServiceRequestBean) {
	if (academicServiceRequestBean.isToCancelOrReject() && hasEvent()) {
	    getEvent().cancel(academicServiceRequestBean.getResponsible());

	} else if (academicServiceRequestBean.isToConclude()) {
	    if (isPayable() && !isPayed()) {
		throw new DomainException("AcademicServiceRequest.hasnt.been.payed");
	    }
	    academicServiceRequestBean.setSituationDate(getActiveSituation().getSituationDate().toYearMonthDay());
	}
    }

    @Override
    protected boolean isPayed() {
	return super.isPayed() || getEvent().isCancelled();
    }

    @Override
    protected void createAcademicServiceRequestSituations(AcademicServiceRequestBean academicServiceRequestBean) {
	super.createAcademicServiceRequestSituations(academicServiceRequestBean);

	if (academicServiceRequestBean.isToProcess() && !isFree()) {
	    FixedAmountPR partialRegistrationPostingRule = (FixedAmountPR) getAdministrativeOffice()
		    .getServiceAgreementTemplate().findPostingRuleByEventTypeAndDate(
			    EventType.PARTIAL_REGISTRATION_REGIME_REQUEST,
			    getExecutionYear().getBeginDateYearMonthDay().toDateTimeAtMidnight());

	    if (partialRegistrationPostingRule.getFixedAmount().greaterThan(Money.ZERO)) {
		new PartialRegistrationRegimeRequestEvent(getAdministrativeOffice(), getPerson(), this);
	    }
	} else if (academicServiceRequestBean.isToConclude()) {
	    AcademicServiceRequestSituation.create(this, new AcademicServiceRequestBean(
		    AcademicServiceRequestSituationType.DELIVERED, academicServiceRequestBean.getResponsible()));

	    if (!getRegistration().isPartialRegime(getExecutionYear())) {
		new RegistrationRegime(getRegistration(), getExecutionYear(), RegistrationRegimeType.PARTIAL_TIME);
	    }
	}
    }

    @Override
    public boolean isAvailableForTransitedRegistrations() {
	return false;
    }

    @Override
    public AcademicServiceRequestType getAcademicServiceRequestType() {
	return AcademicServiceRequestType.PARTIAL_REGIME_REQUEST;
    }

    @Override
    public EventType getEventType() {
	/*
	 * For 2010/2011 partial registration is not charged
	 */
	if (getExecutionYear().isAfterOrEquals(ExecutionYear.readExecutionYearByName("2010/2011"))) {
	    return null;
	}

	return EventType.PARTIAL_REGISTRATION_REGIME_REQUEST;
    }

    @Override
    public boolean hasPersonalInfo() {
	return false;
    }

    @Override
    public boolean isPayedUponCreation() {
	return true;
    }

    @Override
    public boolean isPossibleToSendToOtherEntity() {
	return false;
    }

    @Override
    public boolean isManagedWithRectorateSubmissionBatch() {
	return false;
    }

    @Override
    public boolean isToPrint() {
	return false;
    }

}
