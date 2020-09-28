package karolh95.chowdhury.model.component.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.component.Vehicle;
import karolh95.chowdhury.model.designer.VehiclesDesigner;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DesignedVehicleFactory implements VehicleFactory {

	private final VehiclesDesigner vehicleDesigner;

	@Override
	public List<Vehicle> createVehicles() {

		List<Vehicle> vehicles = new ArrayList<>();

		for (int maxVelocity : vehicleDesigner.getMaxVelocities()) {

			int vehiclesNumber = vehicleDesigner.getVehiclesNumber(maxVelocity);

			for (int i = 0; i < vehiclesNumber; i++) {

				vehicles.add(new Vehicle(maxVelocity));
			}
		}

		return vehicles;
	}
}
