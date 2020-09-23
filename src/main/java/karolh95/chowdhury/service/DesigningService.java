package karolh95.chowdhury.service;

import java.util.List;

import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.Road;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import karolh95.chowdhury.model.descriptor.RoadDescriptor;
import karolh95.chowdhury.model.descriptor.VehicleDescriptor;
import karolh95.chowdhury.model.designer.VehiclesDesigner;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DesigningService {

	private final SchedulingService schedulingService;
	private final VehiclesDesigner vehiclesDesigner;
	private final Road road;

	public ModelDescriptor saveModelDescriptor(ModelDescriptor modelDescriptor) {

		schedulingService.cancelScheduledTask();

		saveRoadDescriptor(modelDescriptor.getRoadDescriptor());
		saveVehicleDescriptor(modelDescriptor.getVehicleDescriptors());

		return modelDescriptor;
	}

	private void saveRoadDescriptor(RoadDescriptor descriptor) {

		road.setLanesNumber(descriptor.getLanesNumber());
		road.setLanesLength(descriptor.getLanesLength());
	}

	private void saveVehicleDescriptor(List<VehicleDescriptor> descriptors) {

		vehiclesDesigner.setVehicles(descriptors);
	}
}
