package karolh95.chowdhury.service;

import java.util.List;

import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.Vehicle;
import karolh95.chowdhury.model.designer.VehiclesDesigner;
import karolh95.chowdhury.model.designer.VehiclesPositionDesigner;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelCreationService {

	private final VehiclesDesigner vehiclesDesigner;
	private final VehiclesPositionDesigner vehiclesPositionDesigner;

	public void createModel(Model model) {

		List<Vehicle> vehicles = vehiclesDesigner.createVehicles();

		vehiclesPositionDesigner.placeVehicles(vehicles);
		model.setVehicles(vehicles);
	}
}
