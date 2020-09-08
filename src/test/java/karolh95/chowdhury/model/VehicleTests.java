package karolh95.chowdhury.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Vehicle Tests")
public class VehicleTests {

	Vehicle vehicle;

	@ParameterizedTest
	@MethodSource
	@DisplayName("Vehicle() test with invalid arguments")
	void createVehicleWithInvalidArgumentsTest(int maxVelocity) {

		assertThrows(IllegalArgumentException.class, () -> {

			new Vehicle(maxVelocity);
		}, "Vehicle() should throw IllegalArgument Exception");
	}

	@ParameterizedTest
	@MethodSource
	@DisplayName("Vehicle() test with valid arguments")
	void createVehicleWithValidArgumentsTest(int maxVelocity) {

		assertDoesNotThrow(() -> {

			vehicle = new Vehicle(maxVelocity);
		});

		assertNotNull(vehicle, "Vehicle should not be null");
		assertEquals(maxVelocity, vehicle.getMaxVelocity(), "Vehicle maxVelocity should equals");
	}

	@ParameterizedTest
	@MethodSource
	@DisplayName("setVelocity() test with invalid arguments")
	void setVelocityWithInvalidArgumentsTest(int maxVelocity, int velocity) {

		vehicle = new Vehicle(maxVelocity);

		assertThrows(IllegalArgumentException.class, () -> {

			vehicle.setVelocity(velocity);
		}, "Should throw IllegalArgument exception");
	}

	@ParameterizedTest
	@MethodSource
	@DisplayName("setVelocity() test with valid arguments")
	void setVelocityWithValidArgumentsTest(int maxVelocity, int velocity) {

		vehicle = new Vehicle(maxVelocity);

		assertDoesNotThrow(() -> {

			vehicle.setVelocity(velocity);
		}, "Should not throw any exception");

		assertEquals(velocity, vehicle.getVelocity(), "Vehicle velocity should equals");
	}

	@ParameterizedTest
	@MethodSource("createVehicleWithValidArgumentsTest")
	@DisplayName("speedUp() test with velocity == maxVelocity")
	void speedUpWithMaxVelocityTest(int maxVelocity) {

		vehicle = new Vehicle(maxVelocity);
		vehicle.setVelocity(maxVelocity);

		vehicle.speedUp();

		assertEquals(maxVelocity, vehicle.getVelocity(), "Velocity should not increase");
	}

	@ParameterizedTest
	@MethodSource
	@DisplayName("speedUp() test with velocity != maxVelocity")
	void speedUpTest(int maxVelocity, int velocity) {

		vehicle = new Vehicle(maxVelocity);
		vehicle.setVelocity(velocity);

		vehicle.speedUp();

		assertEquals(velocity + 1, vehicle.getVelocity(), "Velocity should increase");
	}

	@ParameterizedTest
	@MethodSource("createVehicleWithValidArgumentsTest")
	@DisplayName("speedDown() test with velocity == 0")
	void speedDownWithMaxVelocityTest(int maxVelocity) {

		vehicle = new Vehicle(maxVelocity);
		vehicle.setVelocity(0);

		vehicle.speedDown();

		assertEquals(0, vehicle.getVelocity(), "Velocity should equals to 0");
	}

	@ParameterizedTest
	@MethodSource
	@DisplayName("speedDown() test with velocity != 0")
	void speedDownTest(int maxVelocity, int velocity) {

		vehicle = new Vehicle(maxVelocity);
		vehicle.setVelocity(velocity);

		vehicle.speedDown();

		assertEquals(velocity - 1, vehicle.getVelocity(), "Velocity should decrease");
	}

	private static IntStream createVehicleWithInvalidArgumentsTest() {

		return IntStream.rangeClosed(-5, 0);
	}

	private static IntStream createVehicleWithValidArgumentsTest() {

		return IntStream.range(1, 10);
	}

	private static Stream<Arguments> setVelocityWithInvalidArgumentsTest() {

		int maxVelocity = 5;

		Stream<Arguments> negativeVelocities = IntStream.range(-5, 0).mapToObj(i -> Arguments.of(maxVelocity, i));

		Stream<Arguments> greaterThanMaxVelocities = IntStream.range(maxVelocity + 1, maxVelocity + 10)
				.mapToObj(i -> Arguments.of(maxVelocity, i));

		return Stream.concat(negativeVelocities, greaterThanMaxVelocities);
	}

	private static Stream<Arguments> setVelocityWithValidArgumentsTest() {

		int maxVelocity = 7;

		return IntStream.range(1, maxVelocity).mapToObj(i -> Arguments.of(maxVelocity, i));
	}

	private static Stream<Arguments> speedUpTest() {

		return Stream.concat(Stream.of(Arguments.of(7, 0)), setVelocityWithValidArgumentsTest());
	}

	private static Stream<Arguments> speedDownTest() {

		return Stream.concat(setVelocityWithValidArgumentsTest(), Stream.of(Arguments.of(7, 7)));
	}
}
