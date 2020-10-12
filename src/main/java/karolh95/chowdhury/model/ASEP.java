package karolh95.chowdhury.model;

import karolh95.chowdhury.model.component.Road;
import karolh95.chowdhury.model.component.Vehicle;

public class ASEP extends Model {

	public static final int MAX_VELOCITY = 1;

	public ASEP(Road road) {
		super(road);
	}

	@Override
	protected void beforeVehiclePositionUpdate(Vehicle vehicle) {

		int velocity = isNextCellEmpty(vehicle) ? 0 : MAX_VELOCITY;

		vehicle.setVelocity(velocity);
	}

	private boolean isNextCellEmpty(Vehicle vehicle) {

		return isEmpty(vehicle.getLane(), vehicle.getPosition() + 1);
	}
}
