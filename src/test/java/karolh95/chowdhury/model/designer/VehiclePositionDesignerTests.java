package karolh95.chowdhury.model.designer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import karolh95.chowdhury.model.Road;
import karolh95.chowdhury.model.Vehicle;
import lombok.Getter;

@DisplayName("VehiclePositionDesignerImpl tests")
public class VehiclePositionDesignerTests {

	VehiclesPositionDesigner designer;

	@Nested
	@DisplayName("placeVehicles() tests")
	class PlaceVehiclesTests {

		@Test
		@DisplayName("Test with null road")
		void placeVehiclesWithNullRoad() {

			designer = new VehiclesPositionDesignerImpl();

			assertThrows(IllegalArgumentException.class, () -> {

				designer.placeVehicles(null, null);
			}, "Should throw IllegalArgument exception");
		}

		@Test
		@DisplayName("Test with null vehicles list")
		void placeNullVehicleListTest() {

			designer = new VehiclesPositionDesignerImpl();

			assertThrows(IllegalArgumentException.class, () -> {

				designer.placeVehicles(getRoad(), null);
			}, "Should throw IllegalArgument exception");
		}

		@Test
		@DisplayName("Test with empty vehicles list")
		void placeEmptyVehicleListTest() {

			designer = new VehiclesPositionDesignerImpl();

			assertDoesNotThrow(() -> {

				designer.placeVehicles(getRoad(), Collections.emptyList());
			}, "Should not throw any excpetion");
		}

		@DisplayName("Test with vehicles")
		@ParameterizedTest(name = "[{index}] {0}, {1}")
		@ArgumentsSource(VehiclesArgumentsProfider.class)
		void placeVehiclesTest(VehiclesWrapper vehiclesWrapper, RoadWrapper roadWrapper) {

			List<Vehicle> vehicles = vehiclesWrapper.getVehicles();
			Road road = roadWrapper.getRoad();

			designer = new VehiclesPositionDesignerImpl();
			int space = road.getCellsPerVehicle(vehicles.size());

			designer.placeVehicles(road, vehicles);

			for (int i = 0; i < vehicles.size() - 1; i++) {

				Vehicle a = vehicles.get(i);
				Vehicle b = vehicles.get(i + 1);

				int aPos = a.getPosition() + a.getLane() * road.getLanesLength();
				int bPos = b.getPosition() + b.getLane() * road.getLanesLength();

				assertEquals(space, bPos - aPos, "Space between vehicles should be equal");
			}

		}
	}

	private Road getRoad() {

		Road road = new Road();
		road.setLanesNumber(1);
		road.setLanesLength(1);
		road.createRoad();

		return road;
	}

	private static class VehiclesArgumentsProfider implements ArgumentsProvider {

		private static final int MAX_LANES_NUMBER = 5;
		private static final int MAX_LANES_LENGTH = 30;

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) {

			List<Arguments> args = new ArrayList<>();

			RoadWrapper roadWrapper;
			VehiclesWrapper vehiclesWrapper;

			for (int lanesNumber = 1; lanesNumber <= MAX_LANES_NUMBER; lanesNumber++) {

				for (int lanesLength = 1; lanesLength <= MAX_LANES_LENGTH; lanesLength++) {

					for (int vehiclesNumber = 1; vehiclesNumber <= lanesNumber * lanesLength; vehiclesNumber++) {

						roadWrapper = new RoadWrapper(lanesNumber, lanesLength);
						vehiclesWrapper = new VehiclesWrapper();
						vehiclesWrapper.add(vehiclesNumber);

						args.add(Arguments.of(vehiclesWrapper, roadWrapper));
					}
				}
			}

			return args.stream();
		}
	}

	@Getter
	private static class VehiclesWrapper {

		private List<Vehicle> vehicles;

		public VehiclesWrapper() {

			this.vehicles = new ArrayList<>();
		}

		public void add(int x) {

			for (int i = 0; i < x; i++) {
				this.vehicles.add(new Vehicle(1));
			}
		}

		@Override
		public String toString() {

			return String.format("Vehicles: %d", vehicles.size());
		}
	}

	@Getter
	private static class RoadWrapper {

		private final Road road;

		RoadWrapper(int lanesNumber, int lanesLength) {

			road = new Road();
			road.setLanesNumber(lanesNumber);
			road.setLanesLength(lanesLength);
			road.createRoad();
		}

		@Override
		public String toString() {

			return String.format("Road: %d x %d", road.getLanesNumber(), road.getLanesLength());
		}
	}
}
