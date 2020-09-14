package karolh95.chowdhury.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Road tests")
public class RoadTests {

	private static final String PATH = "/model/road/";

	private static final String POSITIONS_VALID = PATH + "positionsValid.csv";
	private static final String POSITIONS_INVALID = PATH + "positionsInvalid.csv";

	Road road = new Road();

	@Nested
	@DisplayName("createRoad() tests")
	class CreateRoadTests {

		@ParameterizedTest
		@DisplayName("Test with lanesNumber and lanesLength > 0")
		@CsvFileSource(resources = PATH + "createRoadValid.csv")
		void createRoadTest(int lanesNumber, int lanesLength) {

			road.setLanesNumber(lanesNumber);
			road.setLanesLength(lanesLength);

			assertDoesNotThrow(() -> {

				road.createRoad();
			}, "Should not throw any exceptions");
		}

		@ParameterizedTest
		@DisplayName("Test with lanesNumber or lanesLength <= 0")
		@CsvFileSource(resources = PATH + "createRoadInvalid.csv")
		void createRoadWithIllegalArgumentsTest(int lanesNumber, int lanesLength) {

			road.setLanesNumber(lanesNumber);
			road.setLanesLength(lanesLength);

			assertThrows(IllegalArgumentException.class, () -> {

				road.createRoad();
			}, "Should throw IllegalArgument exception");
		}
	}

	@Nested
	@DisplayName("at() tests")
	class AtTests {

		@ParameterizedTest
		@DisplayName("Test with null vehicle")
		@CsvFileSource(resources = POSITIONS_VALID)
		void atWithValidArgumentsAndNullVehicleTest(int lanesNumber, int lanesLength, int lane, int position) {

			road(lanesNumber, lanesLength);

			Vehicle vehicle = road.at(lane, position);

			assertNull(vehicle, "Vehicle should be null");
		}

		@ParameterizedTest
		@DisplayName("Test with not null vehicle")
		@CsvFileSource(resources = POSITIONS_VALID)
		void atWithValidArgumentsAndNotNullVehicleTest(int lanesNumber, int lanesLength, int lane, int position) {

			road(lanesNumber, lanesLength);
			road.set(new Vehicle(5), lane, position);

			Vehicle vehicle = road.at(lane, position);

			assertNotNull(vehicle, "Vehicle should not be null");
		}

		@ParameterizedTest
		@DisplayName("Test with invaid lane or position")
		@CsvFileSource(resources = POSITIONS_INVALID)
		void atWithInvalidArgumentsTest(int lanesNumber, int lanesLength, int lane, int position) {

			road(lanesNumber, lanesLength);

			assertThrows(IllegalArgumentException.class, () -> {

				road.at(lane, position);
			}, "Should throw IllegalArgument exception");
		}
	}

	@Nested
	@DisplayName("set() tests")
	class SetTests {

		@ParameterizedTest
		@DisplayName("Test with invalid lane or position")
		@CsvFileSource(resources = POSITIONS_INVALID)
		void setWithValidArguments(int lanesNumber, int lanesLength, int lane, int position) {

			road(lanesNumber, lanesLength);

			assertThrows(IllegalArgumentException.class, () -> {

				road.set(null, lane, position);
			}, "Should throw IllegalArgument exception");
		}

		@ParameterizedTest
		@DisplayName("Test with valid lane and position")
		@CsvFileSource(resources = POSITIONS_VALID)
		void setWithInvalidArgumentsTest(int lanesNumber, int lanesLength, int lane, int position) {

			road(lanesNumber, lanesLength);

			assertDoesNotThrow(() -> {

				road.set(null, lane, position);
			}, "Should not throw any exceptions");
		}
	}

	@Nested
	@DisplayName("remove() tests")
	class RemoveTests {

		@ParameterizedTest
		@DisplayName("Test with invalid lane or position")
		@CsvFileSource(resources = POSITIONS_INVALID)
		void removeWithInvalidArguments(int lanesNumber, int lanesLength, int lane, int position) {

			road(lanesNumber, lanesLength);

			assertThrows(IllegalArgumentException.class, () -> {

				road.remove(lane, position);
			}, "Should throw IllegalArgument Exception");
		}

		@ParameterizedTest
		@DisplayName("Test with valid lane and position")
		@CsvFileSource(resources = POSITIONS_VALID)
		void removeWithValidArguments(int lanesNumber, int lanesLength, int lane, int position) {

			road(lanesNumber, lanesLength);
			road.set(new Vehicle(5), lane, position);

			assertDoesNotThrow(() -> {

				road.remove(lane, position);
			}, "Should not throw any excpetions");

			assertNull(road.at(lane, position), "Vehicle should be null");
		}
	}

	@Nested
	@DisplayName("isEnoughtPlace() tests")
	class IsEnoughPlaceTests {

		@ParameterizedTest
		@ArgumentsSource(IsEnoughPlaceArgumentsProvider.class)
		@DisplayName("Test when should be enough place")
		void isEnoughPlaceTestForTrue(int lanesNumber, int lanesLength, int vehiclesNumber) {

			road(lanesNumber, lanesLength);

			assertTrue(road.isEnoughPlace(vehiclesNumber), "There should be enough place");
		}

		@ParameterizedTest
		@ArgumentsSource(IsNotEnoughPlaceArgumentsProvider.class)
		@DisplayName("Test when sould not be enough place")
		void isEnoughPlaceTestForFalse(int lanesNumber, int lanesLength, int vehiclesNumber) {

			road(lanesNumber, lanesLength);

			assertFalse(road.isEnoughPlace(vehiclesNumber), "There should not be enough place");
		}
	}

	@Nested
	@DisplayName("cellsPerVehicle() tests")
	class CellsPerVehicleTests {

		@ParameterizedTest
		@DisplayName("Test with vehiclesNumber <= 0")
		@ArgumentsSource(CellsPerVehicleInvalidArguments.class)
		void cellsPerVehicleTest(int vehiclesNumber) {

			assertThrows(IllegalArgumentException.class, () -> {

				road.getCellsPerVehicle(vehiclesNumber);
			}, "Should throw IllegalArgument exception");
		}

		@ParameterizedTest
		@DisplayName("Test with vehiclesNumber > 0")
		@CsvFileSource(resources = PATH + "cellsPerVehicle.csv")
		void cellsPerVehicleTest(int lanesNumber, int lanesLength, int vehiclesNumber, int expected) {

			road(lanesNumber, lanesLength);

			int cellsPerVehicle = road.getCellsPerVehicle(vehiclesNumber);

			assertEquals(expected, cellsPerVehicle, "Cells per vehicle should match!");
		}
	}

	private void road(int lanesNumber, int lanesLength) {

		road.setLanesNumber(lanesNumber);
		road.setLanesLength(lanesLength);
		road.createRoad();
	}

	private static void forEachConfiguration(int maxLanesLumber, int maxLanesLength,
			BiConsumer<Integer, Integer> consumer) {

		IntStream.rangeClosed(1, maxLanesLumber).forEach(lanesNumber -> {

			IntStream.rangeClosed(1, maxLanesLength).forEach(lanesLength -> {

				consumer.accept(lanesNumber, lanesLength);
			});
		});
	}

	private static class IsEnoughPlaceArgumentsProvider implements ArgumentsProvider {

		private static final int MAX_LANES_NUMBER = 10;
		private static final int MAX_LANES_LENGTH = 10;

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) {

			List<Arguments> args = new ArrayList<>();

			forEachConfiguration(MAX_LANES_NUMBER, MAX_LANES_LENGTH, (lanesNumber, lanesLength) -> {

				IntStream.rangeClosed(0, lanesNumber * lanesLength).forEach(vehiclesNumber -> {

					args.add(Arguments.of(lanesNumber, lanesLength, vehiclesNumber));
				});
			});

			return args.stream();
		}
	}

	private static class IsNotEnoughPlaceArgumentsProvider implements ArgumentsProvider {

		private static final int MAX_LANES_NUMBER = 10;
		private static final int MAX_LANES_LENGTH = 10;

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) {

			List<Arguments> args = new ArrayList<>();

			forEachConfiguration(MAX_LANES_NUMBER, MAX_LANES_LENGTH, (lanesNumber, lanesLength) -> {

				int roadSize = lanesNumber * lanesLength;
				args.add(Arguments.of(lanesNumber, lanesLength, roadSize + 1));
			});

			return args.stream();
		}
	}

	private static class CellsPerVehicleInvalidArguments implements ArgumentsProvider {

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) throws Exception {

			return IntStream.rangeClosed(-5, 0).mapToObj(i -> Arguments.of(i));
		}
	}
}