package karolh95.chowdhury.model.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.ModelFactory;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import karolh95.chowdhury.model.descriptor.RoadDescriptor;
import karolh95.chowdhury.model.descriptor.VehicleDescriptor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ASEPModelFactory implements ModelFactory {

	private static final int DEFAULT_LANES_NUMBER = 1;
	private static final int DEFAULT_LANES_LENGTH = 10;

	private static final int DEFAULT_VEHICLES_NUMBER = 5;

	private final ASEP model;

	@Override
	public Model getModel() {

		return model;
	}

	@Override
	public ModelDescriptor getDefaultModelDescriptor() {

		ModelDescriptor modelDescriptor = new ModelDescriptor();

		modelDescriptor.setVehicleDescriptors(getDefaultVehiclesDescriptors());
		modelDescriptor.setRoadDescriptor(getDefaultRoadDescriptor());

		return modelDescriptor;
	}

	private List<VehicleDescriptor> getDefaultVehiclesDescriptors() {

		List<VehicleDescriptor> descriptors = new ArrayList<>();

		VehicleDescriptor descriptor = new VehicleDescriptor(ASEP.MAX_VELOCITY, DEFAULT_VEHICLES_NUMBER);

		descriptors.add(descriptor);

		return descriptors;
	}

	private RoadDescriptor getDefaultRoadDescriptor() {

		RoadDescriptor roadDescriptor = new RoadDescriptor();

		roadDescriptor.setLanesNumber(DEFAULT_LANES_NUMBER);
		roadDescriptor.setLanesLength(DEFAULT_LANES_LENGTH);

		return roadDescriptor;
	}

}
