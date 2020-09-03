package karolh95.chowdhury.model.impl;

import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.Road;
import karolh95.chowdhury.model.VehicleDescriptorValidator;
import karolh95.chowdhury.model.designer.VehiclesDesigner;
import karolh95.chowdhury.model.designer.VehiclesPositionDesigner;
import lombok.Setter;

/**
 * <li>if V(n) < Vmax, then V(n) = V(n) + 1,</li>
 * <li>if V (n) > ( A X : (n) -- 1), then V(n) = dX(n) - 1,</li>
 * <li>if V(n) > 0 and ran f< Pd, then V(n) = V(n) - 1,</li>
 * <li>X(n) = X(n) + V(n),</li>
 * </ol>
 * where {@code Pd} is the probability of a random deceleration and
 * {@code ran f} is a random fraction uniformly distributed between 0 and 1.
 * </p>
 **/
@Setter
@Component
public class NagelSchreckenberg extends Model {

	public static final int FAST_VEHICLE_MAX_VELOCITY = 5;
	public static final int SLOW_VEHICLE_MAX_VELOCITY = 3;

	private static final int LANES_NUMBER = 2;

	public NagelSchreckenberg(VehicleDescriptorValidator validator, VehiclesDesigner vehiclesDesigner,
			VehiclesPositionDesigner positionDesigner, Road road) {

		super(validator, vehiclesDesigner, positionDesigner, road);

	}

	@Override
	public void update() {

		vehicles.forEach(vehicle -> {
			// TODO: vehicle update logic
		});
	}

	@Override
	public void reset() {

		setLanesNumber();
		resetValidator();
	}

	private void setLanesNumber() {

		road.setLanesNumber(LANES_NUMBER);
	}

	private void resetValidator() {

		validator.clear();
		validator.addAllowedMaxVelocity(SLOW_VEHICLE_MAX_VELOCITY);
		validator.addAllowedMaxVelocity(FAST_VEHICLE_MAX_VELOCITY);
	}
}
