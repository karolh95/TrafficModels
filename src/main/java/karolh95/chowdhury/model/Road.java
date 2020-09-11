package karolh95.chowdhury.model;

import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Getter
@Component
public class Road {

	@Setter
	private int lanesLength;
	@Setter
	private int lanesNumber;

	private Vehicle[][] cells;

	public void createRoad() {

		if (lanesNumber <= 0)
			throw new IllegalArgumentException("Lanes Number should be greater than zero!");

		if (lanesLength <= 0)
			throw new IllegalArgumentException("Lanes Length should be greater than zero!");

		cells = new Vehicle[lanesNumber][lanesLength];
	}

	public Vehicle at(int lane, int position) {

		checkLaneNumber(lane);
		checkPosition(position);

		return cells[lane][position];
	}

	public void set(Vehicle vehicle, int lane, int position) {

		checkLaneNumber(lane);
		checkPosition(position);

		cells[lane][position] = vehicle;
	}

	public void remove(int lane, int position) {

		checkLaneNumber(lane);
		checkPosition(position);

		cells[lane][position] = null;
	}

	public boolean isEnoughPlace(int vehiclesNumber) {

		return vehiclesNumber <= lanesNumber * lanesLength;
	}

	public int getCellsPerVehicle(int vehiclesNumber) {

		if (vehiclesNumber <= 0)
			throw new IllegalArgumentException("Vehicles Number should be greater than zero!");

		int cellsNumber = lanesNumber * lanesLength;
		int cellsPerVehicle = cellsNumber / vehiclesNumber;

		return cellsPerVehicle;
	}

	private void checkLaneNumber(int laneNumber) {

		if (laneNumber < 0)
			throw new IllegalArgumentException("Lane Number should not be lower than zero!");

		if (laneNumber >= this.lanesNumber)
			throw new IllegalArgumentException("Lane Number should not be greater or equal than Lanes Number");
	}

	private void checkPosition(int position) {

		if (position < 0)
			throw new IllegalArgumentException("Position should not be lower than zero!");

		if (position >= this.lanesLength)
			throw new IllegalArgumentException("Position should not be greater or equal than lanesLength");
	}
}
