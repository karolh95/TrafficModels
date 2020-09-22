package karolh95.chowdhury.model.descriptor;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelDescriptor {

	@Valid
	@NotNull
	private RoadDescriptor roadDescriptor;

	private List<@Valid VehicleDescriptor> vehicleDescriptors;
}
