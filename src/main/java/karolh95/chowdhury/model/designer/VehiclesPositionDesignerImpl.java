package karolh95.chowdhury.model.designer;

import java.util.List;

import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.Road;
import karolh95.chowdhury.model.Vehicle;

@Component
public class VehiclesPositionDesignerImpl implements VehiclesPositionDesigner {

	private int laneLength;
	private int laneNumber;
	private int position;
	private int gap;

	@Override
	public void placeVehicles(Road road, List<Vehicle> vehicles) {

		if (road == null)
			throw new IllegalArgumentException("Road should not be null");

		if (vehicles == null)
			throw new IllegalArgumentException("Vehicles list should not be null");

		if (vehicles.isEmpty())
			return;

		int vehiclesNumber = vehicles.size();

		if (isEnoughPlace(road, vehiclesNumber)) {
			throw new IllegalArgumentException("Too many vehicles");
		}

		laneLength = road.getLanesLength();
		gap = road.getCellsPerVehicle(vehiclesNumber);
		laneNumber = 0;
		position = 0;

		for (Vehicle vehicle : vehicles) {

			road.set(vehicle, laneNumber, position);

			vehicle.setLane(laneNumber);
			vehicle.setPosition(position);

			nextPosition();
		}
	}

	private boolean isEnoughPlace(Road road, int vehiclesNumber) {

		return vehiclesNumber > road.getLanesNumber() * road.getLanesLength();
	}

	private void nextPosition() {

		position += gap;

		while (position >= laneLength) {
			position -= laneLength;
			laneNumber++;
		}
	}
}
