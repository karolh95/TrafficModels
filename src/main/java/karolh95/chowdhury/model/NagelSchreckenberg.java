package karolh95.chowdhury.model;

import java.util.Random;

import karolh95.chowdhury.model.component.Road;
import karolh95.chowdhury.model.component.Vehicle;
import lombok.Setter;

@Setter
public class NagelSchreckenberg extends Model {

	public static final int SLOW_VEHICLE_MAX_VELOCITY = 3;
	public static final int FAST_VEHICLE_MAX_VELOCITY = 5;

	private static final int MAX_PROBABILITY = 100;
	private static final int DECELERATION_PROBABILITY = 10;

	public NagelSchreckenberg(Road road) {
		super(road);
	}

	@Override
	protected void beforeVehiclePositionUpdate(Vehicle vehicle) {

		vehicle.speedUp();

		keepDistance(vehicle);

		randomDeceleration(vehicle);
	}

	private void keepDistance(Vehicle vehicle) {

		int distance = 1;

		while (isEmpty(vehicle.getLane(), vehicle.getPosition() + distance)) {
			distance++;
		}

		if (vehicle.getVelocity() > distance - 1) {
			vehicle.setVelocity(distance - 1);
		}
	}

	private void randomDeceleration(Vehicle vehicle) {

		Random random = new Random();

		if (random.nextInt(MAX_PROBABILITY) < DECELERATION_PROBABILITY) {
			vehicle.speedDown();
		}

	}
}
