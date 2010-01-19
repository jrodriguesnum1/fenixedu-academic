/*
 * Created on Feb 4, 2005
 * 
 */
package net.sourceforge.fenixedu.applicationTier.Servico.teacher.inquiries;

import java.util.List;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.dataTransferObject.inquiries.InfoOldInquiriesTeachersRes;
import net.sourceforge.fenixedu.domain.Teacher;
import net.sourceforge.fenixedu.domain.inquiries.OldInquiriesTeachersRes;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import pt.ist.fenixWebFramework.security.accessControl.Checked;
import pt.ist.fenixWebFramework.services.Service;

/**
 * @author Jo�o Fialho & Rita Ferreira
 * 
 */
public class ReadOldInquiriesTeachersResByTeacherNumber extends FenixService {

    @Checked("RolePredicates.TEACHER_PREDICATE")
    @Service
    public static List run(Integer teacherNumber) throws FenixServiceException {
	Teacher teacher = Teacher.readByNumber(teacherNumber);

	if (teacher == null) {
	    throw new FenixServiceException("nullTeacherNumber");
	}

	List<OldInquiriesTeachersRes> oldInquiriesTeachersResList = teacher.getAssociatedOldInquiriesTeachersRes();

	CollectionUtils.transform(oldInquiriesTeachersResList, new Transformer() {

	    public Object transform(Object oldInquiriesTeachersRes) {
		InfoOldInquiriesTeachersRes ioits = new InfoOldInquiriesTeachersRes();
		try {
		    ioits.copyFromDomain((OldInquiriesTeachersRes) oldInquiriesTeachersRes);

		} catch (Exception ex) {
		}

		return ioits;
	    }
	});

	return oldInquiriesTeachersResList;
    }

}