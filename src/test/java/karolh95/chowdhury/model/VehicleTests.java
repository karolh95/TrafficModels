package karolh95.chowdhury.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import karolh95.chowdhury.model.component.Vehicle;

@DisplayName("Vehicle Tests")
public class VehicleTests {

	Vehicle vehicle;

	@Nested
	@DisplayName("constructor tests")
	class ConstructorTests {

		@ParameterizedTest
		@ArgumentsSource(InvalidMaxVelocityArgumentsProvider.class)
		@DisplayName("Test with maxVelocity <= 0")
		void createVehicleWithInvalidArgumentsTest(int maxVelocity) {

			assertThrows(IllegalArgumentException.class, () -> {

				new Vehicle(maxVelocity);
			}, "Vehicle() should throw IllegalArgument Exception");
		}

		@ParameterizedTest
		@ArgumentsSource(ValidMaxVelocityArgumentsProvider.class)
		@DisplayName("Test with maxVelocity > 0")
		void createVehicleWithValidArgumentsTest(int maxVelocity) {

			assertDoesNotThrow(() -> {

				vehicle = new Vehicle(maxVelocity);
			});

			assertNotNull(vehicle, "Vehicle should not be null");
			assertEquals(maxVelocity, vehicle.getMaxVelocity(), "Vehicle maxVelocity should equals");
		}
	}

	@Nested
	@DisplayName("SetVelocity() tests")
	class SetVelocityTests {

		@ParameterizedTest
		@ArgumentsSource(SetVelocityInvalidArgumentsProvider.class)
		@DisplayName("Test with velocity <=0 or velocity > maxVelocity")
		void setVelocityWithInvalidArgumentsTest(int maxVelocity, int velocity) {

			vehicle = new Vehicle(maxVelocity);

			assertThrows(IllegalArgumentException.class, () -> {

				vehicle.setVelocity(velocity);
			}, "Should throw IllegalArgument exception");
		}

		@ParameterizedTest
		@ArgumentsSource(SetVelocityValidArgumentsProvider.class)
		@DisplayName("Test with 0 < velocity <= maxVelocity")
		void setVelocityWithValidArgumentsTest(int maxVelocity, int velocity) {

			vehicle = new Vehicle(maxVelocity);

			assertDoesNotThrow(() -> {

				vehicle.setVelocity(velocity);
			}, "Should not throw any exception");

			assertEquals(velocity, vehicle.getVelocity(), "Vehicle velocity should equals");
		}
	}

	@Nested
	@DisplayName("SpeedUp() tests")
	class SpeedUpTests {

		@ParameterizedTest
		@ArgumentsSource(ValidMaxVelocityArgumentsProvider.class)
		@DisplayName("Test with velocity == maxVelocity")
		void speedUpWithMaxVelocityTest(int maxVelocity) {

			vehicle = new Vehicle(maxVelocity);
			vehicle.setVelocity(maxVelocity);

			vehicle.speedUp();

			assertEquals(maxVelocity, vehicle.getVelocity(), "Velocity should not increase");
		}

		@ParameterizedTest
		@ArgumentsSource(SpeedUpValidArgumentsProvidere.class)
		@DisplayName("Test with velocity != maxVelocity")
		void speedUpTest(int maxVelocity, int velocity) {

			vehicle = new Vehicle(maxVelocity);
			vehicle.setVelocity(velocity);

			vehicle.speedUp();

			assertEquals(velocity + 1, vehicle.getVelocity(), "Velocity should increase");
		}
	}

	@Nested
	@DisplayName("SpeedDown() tests")
	class SpeedDownTests {

		@ParameterizedTest
		@ArgumentsSource(ValidMaxVelocityArgumentsProvider.class)
		@DisplayName("Test with velocity == 0")
		void speedDownWithMaxVelocityTest(int maxVelocity) {

			vehicle = new Vehicle(maxVelocity);
			vehicle.setVelocity(0);

			vehicle.speedDown();

			assertEquals(0, vehicle.getVelocity(), "Velocity should equals to 0");
		}

		@ParameterizedTest
		@ArgumentsSource(SpeedDownInvalidArgumentsProvider.class)
		@DisplayName("Test with velocity != 0")
		void speedDownTest(int maxVelocity, int velocity) {

			vehicle = new Vehicle(maxVelocity);
			vehicle.setVelocity(velocity);

			vehicle.speedDown();

			assertEquals(velocity - 1, vehicle.getVelocity(), "Velocity should decrease");
		}
	}

	private static class InvalidMaxVelocityArgumentsProvider implements ArgumentsProvider {

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) {

			return IntStream.rangeClosed(-5, 0).mapToObj(i -> Arguments.of(i));
		}
	}

	private static class ValidMaxVelocityArgumentsProvider implements ArgumentsProvider {

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) {

			return IntStream.range(1, 10).mapToObj(i -> Arguments.of(i));
		}
	}

	private static class SetVelocityInvalidArgumentsProvider implements ArgumentsProvider {

		private static final int MAX_VELOCITY = 5;

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) {

			Stream<Arguments> negativeVelocities = IntStream.range(-5, 0).mapToObj(i -> Arguments.of(MAX_VELOCITY, i));

			Stream<Arguments> greaterThanMaxVelocities = IntStream.range(MAX_VELOCITY + 1, MAX_VELOCITY + 10)
					.mapToObj(i -> Arguments.of(MAX_VELOCITY, i));

			return Stream.concat(negativeVelocities, greaterThanMaxVelocities);
		}
	}

	private static class SetVelocityValidArgumentsProvider implements ArgumentsProvider {

		private static final int MAX_VELOCITY = 7;

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) {

			return IntStream.range(1, MAX_VELOCITY).mapToObj(i -> Arguments.of(MAX_VELOCITY, i));
		}
	}

	private static class SpeedUpValidArgumentsProvidere extends SetVelocityValidArgumentsProvider {

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) {

			return Stream.concat(Stream.of(Arguments.of(7, 0)), super.provideArguments(context));
		}
	}

	private static class SpeedDownInvalidArgumentsProvider extends SetVelocityValidArgumentsProvider {

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) {

			return Stream.concat(super.provideArguments(context), Stream.of(Arguments.of(7, 7)));
		}
	}
}
