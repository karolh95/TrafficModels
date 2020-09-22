package karolh95.chowdhury.model;

import java.util.List;

import karolh95.chowdhury.model.descriptor.VehicleDescriptor;

public interface ModelFactory {

	Model getModel();

	int getDefaultLanesNumber();

	int getDefaultLanesLength();

	List<VehicleDescriptor> getDefaultVehiclesDescriptors();
}
