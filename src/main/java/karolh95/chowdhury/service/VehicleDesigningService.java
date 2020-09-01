package karolh95.chowdhury.service;

import java.util.List;
import org.springframework.stereotype.Service;
import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.VehicleDescriptor;
import karolh95.chowdhury.model.designer.VehiclesDesigner;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleDesigningService {

	private final Model model;
	private final VehiclesDesigner designer;

	public List<VehicleDescriptor> setVehicles(List<VehicleDescriptor> descriptors) {

		boolean areValid = descriptors.stream().allMatch(model::isValidDescriptor);

		if (areValid) {
			return designer.setVehicles(descriptors);
		} else {
			return null;
		}
	}
}
