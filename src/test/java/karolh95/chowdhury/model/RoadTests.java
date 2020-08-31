package karolh95.chowdhury.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Road tests")
public class RoadTests {

	Road road = new Road();

	@ParameterizedTest
	@CsvFileSource(resources = "/cellsPerVehicleTestData.csv")
	@DisplayName("Cells per vehicle test")
	void cellsPerVehicleTest(int lanesNumber, int laneLength, int vehiclesNumber, int expected) {

		road.create(lanesNumber, laneLength);

		int cellsPerVehicle = road.getCellsPerVehicle(vehiclesNumber);

		assertEquals(expected, cellsPerVehicle, "Cells per vehicle should match!");
	}
}