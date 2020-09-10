package karolh95.chowdhury.model.designer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import karolh95.chowdhury.model.Road;
import karolh95.chowdhury.model.Vehicle;
import lombok.Getter;

public class VehiclePositionDesignerTests {

	VehiclesPositionDesigner designer;

	@Test
	@DisplayName("placeVehicles() with null vehicles list test")
	void placeNullVehicleListTest() {

		designer = new VehiclesPositionDesigner(getRoad());

		assertThrows(IllegalArgumentException.class, () -> {

			designer.placeVehicles(null);
		}, "Should throw IllegalArgument exception");
	}

	@Test
	@DisplayName("placeVehicles() with empty vehicles list test")
	void placeEmptyVehicleListTest() {

		designer = new VehiclesPositionDesigner(getRoad());

		assertDoesNotThrow(() -> {

			designer.placeVehicles(Collections.emptyList());
		}, "Should not throw any excpetion");
	}

	@DisplayName("placeVehicles() test")
	@ParameterizedTest(name = "[{index}] {0}, {1}")
	@MethodSource
	void placeVehiclesTest(VehiclesWrapper vehiclesWrapper, RoadWrapper roadWrapper) {

		List<Vehicle> vehicles = vehiclesWrapper.getVehicles();
		Road road = roadWrapper.getRoad();

		designer = new VehiclesPositionDesigner(road);
		int space = road.getCellsPerVehicle(vehicles.size());

		designer.placeVehicles(vehicles);

		for (int i = 0; i < vehicles.size() - 1; i++) {

			Vehicle a = vehicles.get(i);
			Vehicle b = vehicles.get(i + 1);

			int aPos = a.getPosition() + a.getLane() * road.getLanesLength();
			int bPos = b.getPosition() + b.getLane() * road.getLanesLength();

			assertEquals(space, bPos - aPos, "Space between vehicles should be equal");
		}

	}

	private Road getRoad() {

		Road road = new Road();
		road.setLanesNumber(1);
		road.setLanesLength(1);
		road.createRoad();

		return road;
	}

	private static Stream<Arguments> placeVehiclesTest() {

		final int MAX_LANES_NUMBER = 5;
		final int MAX_LANES_LENGTH = 30;

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
