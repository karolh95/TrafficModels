package karolh95.chowdhury.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Road tests")
public class RoadTests {

	private static final String PATH = "/model/road/";

	private static final String POSITIONS_VALID = PATH + "positionsValid.csv";
	private static final String POSITIONS_INVALID = PATH + "positionsInvalid.csv";
	Road road = new Road();

	@ParameterizedTest
	@DisplayName("Create road with valid arguments test")
	@CsvFileSource(resources = PATH + "createRoadValid.csv")
	void createRoadTest(int lanesNumber, int lanesLength) {

		road.setLanesNumber(lanesNumber);
		road.setLanesLength(lanesLength);

		assertDoesNotThrow(() -> {

			road.createRoad();
		}, "Should not throw any exceptions");
	}

	@ParameterizedTest
	@DisplayName("Create road with invalid arguments test")
	@CsvFileSource(resources = PATH + "createRoadInvalid.csv")
	void createRoadWithIllegalArgumentsTest(int lanesNumber, int lanesLength) {

		road.setLanesNumber(lanesNumber);
		road.setLanesLength(lanesLength);

		assertThrows(IllegalArgumentException.class, () -> {

			road.createRoad();
		}, "Should throw IllegalArgument exception");
	}

	@ParameterizedTest
	@DisplayName("Road.at() with valid arguments and null vehicle test")
	@CsvFileSource(resources = POSITIONS_VALID)
	void atWithValidArgumentsAndNullVehicleTest(int lanesNumber, int lanesLength, int lane, int position) {

		road(lanesNumber, lanesLength);

		Vehicle vehicle = road.at(lane, position);

		assertNull(vehicle, "Vehicle should be null");
	}

	@ParameterizedTest
	@DisplayName("Road.at() with valid arguments and not null vehicle test")
	@CsvFileSource(resources = POSITIONS_VALID)
	void atWithValidArgumentsAndNotNullVehicleTest(int lanesNumber, int lanesLength, int lane, int position) {

		road(lanesNumber, lanesLength);
		road.set(new Vehicle(5), lane, position);

		Vehicle vehicle = road.at(lane, position);

		assertNotNull(vehicle, "Vehicle should not be null");
	}

	@ParameterizedTest
	@DisplayName("Road.at() with invalid arguments test")
	@CsvFileSource(resources = POSITIONS_INVALID)
	void atWithInvalidArgumentsTest(int lanesNumber, int lanesLength, int lane, int position) {

		road(lanesNumber, lanesLength);

		assertThrows(IllegalArgumentException.class, () -> {

			road.at(lane, position);
		}, "Should throw IllegalArgument exception");
	}

	@ParameterizedTest
	@DisplayName("Road.set() with invalid arguments test")
	@CsvFileSource(resources = POSITIONS_INVALID)
	void setWithValidArguments(int lanesNumber, int lanesLength, int lane, int position) {

		road(lanesNumber, lanesLength);

		assertThrows(IllegalArgumentException.class, () -> {

			road.set(null, lane, position);
		}, "Should throw IllegalArgument exception");
	}

	@ParameterizedTest
	@DisplayName("Road.set() with valid arguments test")
	@CsvFileSource(resources = POSITIONS_VALID)
	void setWithInvalidArgumentsTest(int lanesNumber, int lanesLength, int lane, int position) {

		road(lanesNumber, lanesLength);

		assertDoesNotThrow(() -> {

			road.set(null, lane, position);
		}, "Should not throw any exceptions");
	}

	@ParameterizedTest
	@DisplayName("Road.remove() with invalid arguments")
	@CsvFileSource(resources = POSITIONS_INVALID)
	void removeWithInvalidArguments(int lanesNumber, int lanesLength, int lane, int position) {

		road(lanesNumber, lanesLength);

		assertThrows(IllegalArgumentException.class, () -> {

			road.remove(lane, position);
		}, "Should throw IllegalArgument Exception");
	}

	@ParameterizedTest
	@DisplayName("Road.remove() with valid arguments")
	@CsvFileSource(resources = POSITIONS_VALID)
	void removeWithValidArguments(int lanesNumber, int lanesLength, int lane, int position) {

		road(lanesNumber, lanesLength);
		road.set(new Vehicle(5), lane, position);

		assertDoesNotThrow(() -> {

			road.remove(lane, position);
		}, "Should not throw any excpetions");

		assertNull(road.at(lane, position), "Vehicle should be null");
	}

	@ParameterizedTest
	@DisplayName("Cells per vehicle test")
	@CsvFileSource(resources = PATH + "cellsPerVehicle.csv")
	void cellsPerVehicleTest(int lanesNumber, int lanesLength, int vehiclesNumber, int expected) {

		road(lanesNumber, lanesLength);

		int cellsPerVehicle = road.getCellsPerVehicle(vehiclesNumber);

		assertEquals(expected, cellsPerVehicle, "Cells per vehicle should match!");
	}

	private void road(int lanesNumber, int lanesLength) {

		road.setLanesNumber(lanesNumber);
		road.setLanesLength(lanesLength);
		road.createRoad();
	}
}
