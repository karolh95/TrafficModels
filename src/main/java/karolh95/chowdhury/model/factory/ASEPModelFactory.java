package karolh95.chowdhury.model.factory;

import java.util.ArrayList;
import java.util.List;

import karolh95.chowdhury.model.ASEP;
import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.component.Road;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import karolh95.chowdhury.model.descriptor.RoadDescriptor;
import karolh95.chowdhury.model.descriptor.VehicleDescriptor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ASEPModelFactory implements ModelFactory {

	public static final String MODEL_NAME = "asep";

	private static final int DEFAULT_LANES_NUMBER = 1;
	private static final int DEFAULT_LANES_LENGTH = 10;

	private static final int DEFAULT_VEHICLES_NUMBER = 5;

	private final Road road;

	@Override
	public Model getModel() {

		return new ASEP(road);
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

		descriptors.add(new SimpleVehicleDescriptor());

		return descriptors;
	}

	private RoadDescriptor getDefaultRoadDescriptor() {

		RoadDescriptor roadDescriptor = new RoadDescriptor();

		roadDescriptor.setLanesNumber(DEFAULT_LANES_NUMBER);
		roadDescriptor.setLanesLength(DEFAULT_LANES_LENGTH);

		return roadDescriptor;
	}

	private static class SimpleVehicleDescriptor extends VehicleDescriptor {

		public SimpleVehicleDescriptor() {

			super(ASEP.MAX_VELOCITY, DEFAULT_VEHICLES_NUMBER);
		}
	}

}
