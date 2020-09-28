package karolh95.chowdhury.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import karolh95.chowdhury.model.component.Vehicle;
import karolh95.chowdhury.model.component.factory.DesignedVehicleFactory;
import karolh95.chowdhury.model.component.factory.VehicleFactory;
import karolh95.chowdhury.model.descriptor.VehicleDescriptor;
import karolh95.chowdhury.model.designer.VehiclesDesigner;

@DisplayName("Designed VehicleFactory tests")
public class DesignedVehicleFactoryTests {

	VehiclesDesigner designer;
	VehicleFactory factory;

	@Nested
	@DisplayName("createVehicles() tests")
	class CreateVehiclesTests {

		@BeforeEach()
		void beforeEach() {

			designer = new VehiclesDesigner();
			factory = new DesignedVehicleFactory(designer);
		}

		@Test
		@DisplayName("Test with no descriptors")
		void createVehiclesEmpty() {

			List<Vehicle> vehicles = factory.createVehicles();

			assertTrue(vehicles.isEmpty(), "Vehicles list should be empty");
		}

		@ParameterizedTest
		@ArgumentsSource(ValidVehiclesDescriptorsArgumentProvider.class)
		@DisplayName("Test with valid vehicles descriptors")
		void createVehicles(List<VehicleDescriptor> descriptors) {

			int totalVehiclesNumber = descriptors.stream().map(VehicleDescriptor::getVehiclesNumber).reduce(0,
					Integer::sum);

			designer.setVehicles(descriptors);

			List<Vehicle> vehicles = factory.createVehicles();

			assertEquals(totalVehiclesNumber, vehicles.size(), "Total vehicles number should match");

			descriptors.forEach(descriptor -> {

				long vehiclesNumber = vehicles.stream()
						.filter(vehicle -> vehicle.getMaxVelocity() == descriptor.getMaxVelocity()).count();

				assertEquals(descriptor.getVehiclesNumber(), vehiclesNumber, "Vehicles number should match");
			});
		}

	}

	private static class ValidVehiclesDescriptorsArgumentProvider implements ArgumentsProvider {

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) {

			List<Arguments> args = new ArrayList<>();

			IntStream.rangeClosed(1, 7).forEach(maxVelocity -> {

				List<VehicleDescriptor> descriptors = new ArrayList<>();

				IntStream.rangeClosed(1, maxVelocity).forEach(velocity -> {

					descriptors.add(new VehicleDescriptor(velocity, 10));
				});

				args.add(Arguments.of(descriptors));
			});

			return args.stream();
		}
	}
}
