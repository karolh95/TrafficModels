package karolh95.chowdhury.model;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public abstract class Model {

	protected final Road road;

	@Getter
	@Setter
	private List<Vehicle> vehicles;

	public abstract void update();

	protected void forEachVehicle(Consumer<Vehicle> action) {

		List<Vehicle> shuffledVehicleList = vehicles.stream().collect(Collectors.toList());

		Collections.shuffle(shuffledVehicleList, new Random());

		shuffledVehicleList.forEach(action);
	}

	protected void changeLane(Vehicle vehicle, int newLane) {

		remove(vehicle);
		vehicle.setLane(newLane);
		set(vehicle);
	}

	protected void move(Vehicle vehicle) {

		remove(vehicle);
		vehicle.setPosition(getNextPosition(vehicle));
		set(vehicle);
	}

	protected boolean isEmpty(int lane, int position) {

		return road.at(lane, position) == null;
	}

	private void remove(Vehicle vehicle) {

		road.set(null, vehicle.getLane(), vehicle.getPosition());
	}

	private void set(Vehicle vehicle) {

		road.set(vehicle, vehicle.getLane(), vehicle.getPosition());
	}

	private int getNextPosition(Vehicle vehicle) {

		return (vehicle.getPosition() + vehicle.getVelocity()) % road.getLanesLength();
	}
}
