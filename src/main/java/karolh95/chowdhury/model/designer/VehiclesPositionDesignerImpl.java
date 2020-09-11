package karolh95.chowdhury.model.designer;

import java.util.List;

import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.Road;
import karolh95.chowdhury.model.Vehicle;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@RequiredArgsConstructor
public class VehiclesPositionDesignerImpl implements VehiclesPositionDesigner {

	private final Road road;

	@Setter(value = AccessLevel.PRIVATE)
	private int laneLength;

	private int laneNumber;
	private int position;
	private int gap;

	@Override
	public void placeVehicles(List<Vehicle> vehicles) {

		if (vehicles == null)
			throw new IllegalArgumentException("Vehicles list should not be null");

		if (vehicles.isEmpty())
			return;

		if (isEnoughPlace(vehicles.size())) {
			throw new IllegalArgumentException("Too many vehicles");
		}

		setLaneLength(road.getLanesLength());
		calculateGap(vehicles.size());
		resetPosition();

		for (Vehicle vehicle : vehicles) {

			road.set(vehicle, laneNumber, position);

			vehicle.setLane(laneNumber);
			vehicle.setPosition(position);

			nextPosition();
		}
	}

	private boolean isEnoughPlace(int vehiclesNumber) {

		return vehiclesNumber > road.getLanesNumber() * road.getLanesLength();
	}

	private void calculateGap(int vehiclesNumber) {

		gap = road.getCellsPerVehicle(vehiclesNumber);
	}

	private void resetPosition() {

		laneNumber = 0;
		position = 0;
	}

	private void nextPosition() {

		position += gap;

		while (position >= laneLength) {
			position -= laneLength;
			laneNumber++;
		}
	}
}
