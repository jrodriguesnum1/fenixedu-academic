package net.sourceforge.fenixedu.domain.precedences;

import java.util.List;

import net.sourceforge.fenixedu.domain.IAnnouncement;
import net.sourceforge.fenixedu.domain.curriculum.CurricularCourseEnrollmentType;

/**
 * @author David Santos in Jun 9, 2004
 */

public class Precedence extends Precedence_Base {

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Precedence:\n");
        stringBuffer.append(this.getCurricularCourse()).append("\n");
        List restrictions = this.getRestrictions();
        for (int i = 0; i < restrictions.size(); i++) {
            IRestriction restriction = (IRestriction) restrictions.get(i);
            stringBuffer.append(restriction).append("\n");
        }
        stringBuffer.append("---------\n");
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof IPrecedence) {
            final IPrecedence precedence = (IPrecedence) obj;
            return this.getIdInternal().equals(precedence.getIdInternal());
        }
        return false;
    }
    
    
    public CurricularCourseEnrollmentType evaluate(PrecedenceContext precedenceContext) {
        List restrictions = getRestrictions();

        int size = restrictions.size();

        CurricularCourseEnrollmentType evaluate = ((IRestriction) restrictions.get(0))
                .evaluate(precedenceContext);

        for (int i = 1; i < size; i++) {
            IRestriction restriction = (IRestriction) restrictions.get(i);
            evaluate = evaluate.and(restriction.evaluate(precedenceContext));
        }

        return evaluate;
    }

}
