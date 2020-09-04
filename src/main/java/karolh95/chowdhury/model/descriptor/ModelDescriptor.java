package karolh95.chowdhury.model.descriptor;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModelDescriptor {

	private RoadDescriptor roadDescriptor;
	private List<VehicleDescriptor> vehicleDescriptors;
}
