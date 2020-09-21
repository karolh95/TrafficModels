package karolh95.chowdhury.model.designer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import karolh95.chowdhury.model.descriptor.VehicleDescriptor;

@DisplayName("VehicleDesigner tests")
public class VehicleDesignerTests {

	VehiclesDesigner designer = new VehiclesDesigner();

	@Nested
	@DisplayName("setVehicles() tests")
	class SetVehiclesTests {

		@Test
		@DisplayName("Test with null vehicles descriptors list")
		void setNullVehiclesDescriptors() {

			assertThrows(NullPointerException.class, () -> {

				designer.setVehicles(null);
			}, "Should throw NullPointer exception");
		}

		@Test
		@DisplayName("Test with empty vehicles descriptors list")
		void setEmptyVehiclesDescriptors() {

			List<VehicleDescriptor> descriptors = new ArrayList<>();

			designer.setVehicles(descriptors);

			assertTrue(descriptors.isEmpty(), "Descriptors lists should be empty");
		}

		@ParameterizedTest
		@ArgumentsSource(DuplicatedVehiclesDescriptorsArgumentProvider.class)
		@DisplayName("Test with duplicated vehicles descriptors")
		void setVehiclesDescriptorsWithDuplicates(List<VehicleDescriptor> descriptors) {

			int descriptorsNumber = descriptors.size();

			designer.setVehicles(descriptors);

			assertEquals(descriptorsNumber / 2, descriptors.size(), "Descriptors number should descrease 2 times");
		}

		@ParameterizedTest
		@ArgumentsSource(ValidVehiclesDescriptorsArgumentProvider.class)
		@DisplayName("Test with valid vehicles descriptors")
		void setValidVehiclesDescriptors(List<VehicleDescriptor> descriptors) {

			int descriptorsNumber = descriptors.size();

			designer.setVehicles(descriptors);

			assertEquals(descriptorsNumber, descriptors.size(), "Descriptors number should equal");
		}
	}

	private static class DuplicatedVehiclesDescriptorsArgumentProvider implements ArgumentsProvider {

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) {

			List<Arguments> args = new ArrayList<>();

			IntStream.rangeClosed(1, 7).forEach(maxVelocity -> {

				List<VehicleDescriptor> descriptors = new ArrayList<>();

				IntStream.rangeClosed(1, maxVelocity).forEach(velocity -> {

					descriptors.add(new VehicleDescriptor(velocity, 5));
					descriptors.add(new VehicleDescriptor(velocity, 10));
				});

				args.add(Arguments.of(descriptors));
			});

			return args.stream();
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
