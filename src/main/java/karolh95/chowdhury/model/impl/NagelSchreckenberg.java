package karolh95.chowdhury.model.impl;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.Road;
import karolh95.chowdhury.model.descriptor.VehicleDescriptor;
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

	private static final int FAST_VEHICLE_MAX_VELOCITY = 5;
	private static final int SLOW_VEHICLE_MAX_VELOCITY = 3;

	private static final int FAST_VEHICLES_NUMBER = 10;
	private static final int SLOW_VEHICLES_NUMBER = 15;

	private static final int LANES_NUMBER = 2;
	private static final  int LANES_LENGTH = 50;

	public NagelSchreckenberg(VehiclesDesigner vehiclesDesigner, VehiclesPositionDesigner positionDesigner, Road road) {

		super(vehiclesDesigner, positionDesigner, road);

	}

	@Override
	protected void applyDefaultDescriptors() {

		VehicleDescriptor slowVehiclesDescriptor = new VehicleDescriptor(SLOW_VEHICLE_MAX_VELOCITY,
				SLOW_VEHICLES_NUMBER);
		VehicleDescriptor fastVehiclesDescriptor = new VehicleDescriptor(FAST_VEHICLE_MAX_VELOCITY,
				FAST_VEHICLES_NUMBER);

		vehiclesDesigner.setVehicles(Arrays.asList(slowVehiclesDescriptor, fastVehiclesDescriptor));

		road.setLanesNumber(LANES_NUMBER);
		road.setLanesLength(LANES_LENGTH);
	}

	@Override
	public void update() {

	}
}
