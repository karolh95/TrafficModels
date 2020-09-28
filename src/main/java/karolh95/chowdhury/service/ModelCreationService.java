package karolh95.chowdhury.service;

import java.util.List;

import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.component.Road;
import karolh95.chowdhury.model.component.Vehicle;
import karolh95.chowdhury.model.component.factory.VehicleFactory;
import karolh95.chowdhury.model.designer.VehiclesPositionDesigner;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelCreationService {

	private final Road road;
	private final VehicleFactory vehicleFactory;
	private final ModelFactoryService modelFactoryService;
	private final VehiclesPositionDesigner vehiclesPositionDesigner;

	public void createModel() {

		Model model = modelFactoryService.getModel();

		List<Vehicle> vehicles = vehicleFactory.createVehicles();

		road.createRoad();

		vehiclesPositionDesigner.placeVehicles(road, vehicles);

		model.setVehicles(vehicles);
	}
}
