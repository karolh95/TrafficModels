package karolh95.chowdhury.model;

import org.springframework.stereotype.Component;
import lombok.Getter;

@Getter
@Component
public class Road {

	private int lanesLength;
	private int lanesNumber;

	private Vehicle[][] cells;

	public void create(int lanesNumber, int lanesLength) {

		this.lanesNumber = lanesNumber;
		this.lanesLength = lanesLength;

		cells = new Vehicle[lanesNumber][lanesLength];
	}

	public Vehicle at(int lane, int position) {

		return cells[lane][position];
	}

	public void set(Vehicle vehicle, int lane, int position) {

		cells[lane][position] = vehicle;
	}

	public void remove(int lane, int position) {

		cells[lane][position] = null;
	}

	public int getCellsPerVehicle(int vehiclesNumber) {

		int cellsNumber = lanesNumber * lanesLength;
		int cellsPerVehicle = cellsNumber / vehiclesNumber;

		return cellsPerVehicle;
	}
}
