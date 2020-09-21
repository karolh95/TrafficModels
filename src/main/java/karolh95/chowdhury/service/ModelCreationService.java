package karolh95.chowdhury.service;

import java.util.List;

import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.Road;
import karolh95.chowdhury.model.Vehicle;
import karolh95.chowdhury.model.VehicleFactory;
import karolh95.chowdhury.model.designer.VehiclesPositionDesigner;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelCreationService {

	private final Road road;
	private final VehicleFactory vehicleFactory;
	private final VehiclesPositionDesigner vehiclesPositionDesigner;

	public void createModel(Model model) {

		List<Vehicle> vehicles = vehicleFactory.createVehicles();

		vehiclesPositionDesigner.placeVehicles(road, vehicles);
		model.setVehicles(vehicles);
	}
}
