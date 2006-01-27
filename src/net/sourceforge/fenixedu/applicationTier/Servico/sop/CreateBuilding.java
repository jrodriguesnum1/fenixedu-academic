package net.sourceforge.fenixedu.applicationTier.Servico.sop;

import java.util.List;

import net.sourceforge.fenixedu.applicationTier.Service;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.ExistingServiceException;
import net.sourceforge.fenixedu.domain.Campus;
import net.sourceforge.fenixedu.domain.DomainFactory;
import net.sourceforge.fenixedu.domain.space.OldBuilding;
import net.sourceforge.fenixedu.persistenceTier.ExcepcaoPersistencia;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class CreateBuilding extends Service {

    public void run(final String buildingName, final Integer campusID) throws ExcepcaoPersistencia, ExistingServiceException {
        final List buildings = (List) persistentObject.readAll(OldBuilding.class);

        if (exists(buildings, buildingName)) {
            throw new ExistingServiceException();
        }

        final Campus campus = (Campus) persistentObject.readByOID(Campus.class, campusID);

        final OldBuilding building = DomainFactory.makeOldBuilding();
        building.setName(buildingName);
        building.setCampus(campus);
    }

    protected boolean exists(final List buildings, final String buildingName) {
        final OldBuilding building = (OldBuilding) CollectionUtils.find(buildings, new Predicate() {
            public boolean evaluate(Object arg0) {
                final OldBuilding building = (OldBuilding) arg0;
                return building.getName().equalsIgnoreCase(buildingName);
            }});

        return building != null;
    }

}