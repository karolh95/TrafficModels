package karolh95.chowdhury.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import karolh95.chowdhury.model.component.Road;
import karolh95.chowdhury.model.component.Vehicle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public abstract class Model {

	private final Road road;

	@Getter
	@Setter
	private List<Vehicle> vehicles;

	public final void update() {

		beforeVehiclesMoves();
		forEachVehicle(this::vehiclePositionUpdate);
		afterVehiclesMoves();
	};

	protected void beforeVehiclesMoves() {

	}

	protected void afterVehiclesMoves() {

	}

	private void vehiclePositionUpdate(Vehicle vehicle) {

		beforeVehiclePositionUpdate(vehicle);
		move(vehicle);
		afterVehiclePositionUpdate(vehicle);
	}

	protected void beforeVehiclePositionUpdate(Vehicle vehicle) {

	}

	protected void afterVehiclePositionUpdate(Vehicle vehicle) {

	}

	protected void forEachVehicle(Consumer<Vehicle> action) {

		List<Vehicle> shuffledVehicleList = new ArrayList<>(vehicles);

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

		lane += position / road.getLanesLength();
		position %= road.getLanesLength();

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
