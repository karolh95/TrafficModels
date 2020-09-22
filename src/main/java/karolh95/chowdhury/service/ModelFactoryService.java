package karolh95.chowdhury.service;

import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.ModelFactory;
import karolh95.chowdhury.model.Road;
import karolh95.chowdhury.model.designer.VehiclesDesigner;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelFactoryService {

	private final Road road;
	private final VehiclesDesigner vehiclesDesigner;
	private final ModelCreationService modelCreationService;

	private ModelFactory factory;

	public void changeModelFactory(ModelFactory factory) {

		this.factory = factory;

		road.setLanesNumber(factory.getDefaultLanesNumber());
		road.setLanesLength(factory.getDefaultLanesLength());
		
		vehiclesDesigner.setVehicles(factory.getDefaultVehiclesDescriptors());
		
		modelCreationService.createModel(factory.getModel());
	}

	public Model getModel() {

		return factory.getModel();
	}
}
