package net.sourceforge.fenixedu.domain.space;

import org.joda.time.YearMonthDay;

import net.sourceforge.fenixedu.domain.DomainTestBase;

public class FloorTest extends DomainTestBase {

    public void testFloorConstructor() {
        final OldBuilding building = new OldBuilding("building1");

        try {
            new Floor((Space) null);
            fail("Floor cannot be created without a surrounding space.");
        } catch (NullPointerException ex) {
            assertEquals("error.surrounding.space", ex.getMessage());
        }

        final Floor floor = new Floor(building);
        assertEquals(1, floor.getSpaceInformationsCount());
        final SpaceInformation spaceInformation = floor.getSpaceInformation();
        assertSame(FloorInformation.class, spaceInformation.getClass());
        assertSame(building, floor.getSuroundingSpace());
    }

    public void testGetSpaceInformation() {
        final OldBuilding building = new OldBuilding("building1");

        final Floor floor = new Floor(building);

        assertSame(FloorInformation.class, floor.getSpaceInformation().getClass());
        assertSame(FloorInformation.class, floor.getSpaceInformation(new YearMonthDay()).getClass());
    }

}
