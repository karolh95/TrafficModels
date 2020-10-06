package karolh95.chowdhury.model.factory;

import java.util.ArrayList;
import java.util.List;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.NagelSchreckenberg;
import karolh95.chowdhury.model.component.Road;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import karolh95.chowdhury.model.descriptor.RoadDescriptor;
import karolh95.chowdhury.model.descriptor.VehicleDescriptor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NagelSchreckenbergModelFactory implements ModelFactory {

	private static final int DEFAULT_LANES_NUMBER = 2;
	private static final int DEFAULT_LANES_LENGTH = 20;

	private static final int DEFAULT_SLOW_VEHICLES_NUMBER = 5;
	private static final int DEFAULT_FAST_VEHICLES_NUMBER = 5;

	private final Road road;

	@Override
	public Model getModel() {

		return new NagelSchreckenberg(road);
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

		descriptors.add(new SlowVehicleDescriptor());
		descriptors.add(new FastVehicleDescriptor());

		return descriptors;
	}

	private RoadDescriptor getDefaultRoadDescriptor() {

		RoadDescriptor roadDescriptor = new RoadDescriptor();

		roadDescriptor.setLanesNumber(DEFAULT_LANES_NUMBER);
		roadDescriptor.setLanesLength(DEFAULT_LANES_LENGTH);

		return roadDescriptor;
	}

	private static class SlowVehicleDescriptor extends VehicleDescriptor {

		public SlowVehicleDescriptor() {

			super(NagelSchreckenberg.SLOW_VEHICLE_MAX_VELOCITY, DEFAULT_SLOW_VEHICLES_NUMBER);
		}
	}

	private static class FastVehicleDescriptor extends VehicleDescriptor {

		public FastVehicleDescriptor() {

			super(NagelSchreckenberg.FAST_VEHICLE_MAX_VELOCITY, DEFAULT_FAST_VEHICLES_NUMBER);
		}
	}
}
