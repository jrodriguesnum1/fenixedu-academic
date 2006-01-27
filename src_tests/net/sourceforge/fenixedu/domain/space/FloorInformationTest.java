package net.sourceforge.fenixedu.domain.space;

import net.sourceforge.fenixedu.domain.DomainTestBase;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;

import org.joda.time.YearMonthDay;

public class FloorInformationTest extends DomainTestBase {

    private class SpaceImpl extends Space {
    }

    public void testFloorInformationConstructor() {
        try {
            new FloorInformation((Floor) null);
            fail("Expected a NullPointerException: floor information must be relative to a floor.");
        } catch (NullPointerException ex) {
            assertEquals("error.floor.cannot.be.null", ex.getMessage());
        }

        final OldBuilding building = new OldBuilding("building1");

        final Floor floor = new Floor(building);
        final FloorInformation floorInformation = (FloorInformation) floor.getSpaceInformation();
        assertSame(floor, floorInformation.getSpace());

        final FloorInformation newFloorInformation = new FloorInformation(floor);
        assertSame(floor, newFloorInformation.getSpace());
        assertSame(floor, floorInformation.getSpace());
        assertEquals(new YearMonthDay(), floorInformation.getValidUntil());
        assertNull(newFloorInformation.getValidUntil());
    }

    public void testSetSpace() {
        final OldBuilding building = new OldBuilding("building1");

        final Floor floor = new Floor(building);
        final FloorInformation floorInformation = floor.getSpaceInformation();

        try {
            floorInformation.setSpace(null);
            fail("Expected a NullPointerException: floor information must be relative to a floor.");
        } catch (NullPointerException ex) {
            assertEquals("error.floor.cannot.be.null", ex.getMessage());
        }

        try {
            floorInformation.setSpace(floor);
            fail("Expected a DomainException: cannot change the floor of a floor information.");
        } catch (DomainException ex) {
            assertEquals("error.cannot.change.floor", ex.getMessage());
        }

        try {
            floorInformation.setSpace(new Floor(building));
            fail("Expected a DomainException: cannot change the floor of a floor information.");
        } catch (DomainException ex) {
            assertEquals("error.cannot.change.floor", ex.getMessage());
        }

        try {
            floorInformation.setSpace(new SpaceImpl());
            fail("Expected a DomainException: cannot associate a floor information with a space that is not a floor.");
        } catch (DomainException ex) {
            assertEquals("error.incompatible.space", ex.getMessage());
        }
    }

}
