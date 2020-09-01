package karolh95.chowdhury.model.designer;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import karolh95.chowdhury.model.Road;
import karolh95.chowdhury.model.Vehicle;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Component
@RequiredArgsConstructor
public class VehiclesPositionDesigner {

	private final Road road;

	@Setter(value = AccessLevel.PRIVATE)
	private int laneLength;

	private int laneNumber;
	private int position;
	private int gap;

	public void placeVehicles(List<Vehicle> vehicles) {

		setLaneLength(road.getLanesLength());
		calculateGap(vehicles.size());
		resetPosition();
		shuffle(vehicles);

		for (Vehicle vehicle : vehicles) {

			road.set(vehicle, laneNumber, position);

			vehicle.setLane(laneNumber);
			vehicle.setPosition(position);

			nextPosition();
		}
	}

	private void calculateGap(int vehiclesNumber) {

		gap = road.getCellsPerVehicle(vehiclesNumber);
	}

	private void resetPosition() {

		laneNumber = 0;
		position = 0;
	}

	private void shuffle(List<Vehicle> vehicles) {

		Collections.shuffle(vehicles, new Random());
	}

	private void nextPosition() {

		position += gap;

		if (position > laneLength) {
			position -= laneLength;
			laneNumber++;
		}
	}
}
