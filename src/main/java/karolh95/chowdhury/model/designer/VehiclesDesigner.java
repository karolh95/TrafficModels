package karolh95.chowdhury.model.designer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.descriptor.VehicleDescriptor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VehiclesDesigner {

	private HashMap<Integer, Integer> maxVelocityNumber = new HashMap<>();

	public void setVehicles(List<VehicleDescriptor> descriptors) {

		if (descriptors == null)
			throw new NullPointerException("Descriptors list should not be null");

		maxVelocityNumber.clear();

		descriptors.stream().filter(distinctByMaxVelocity()).forEach(this::addVehicleDescription);

		descriptors.clear();
		descriptors.addAll(
				maxVelocityNumber.entrySet().stream().map(this::entryToVehicleDescriptor).collect(Collectors.toList()));
	}

	public Set<Integer> getMaxVelocities() {

		return maxVelocityNumber.keySet();
	}

	public int getVehiclesNumber(int maxVelocity) {

		return maxVelocityNumber.get(maxVelocity);
	}

	private void addVehicleDescription(VehicleDescriptor descriptor) {

		maxVelocityNumber.put(descriptor.getMaxVelocity(), descriptor.getVehiclesNumber());
	}

	private VehicleDescriptor entryToVehicleDescriptor(Entry<Integer, Integer> entry) {

		return new VehicleDescriptor(entry.getKey(), entry.getValue());
	}

	private Predicate<VehicleDescriptor> distinctByMaxVelocity() {

		Set<Integer> map = new HashSet<>();
		return descriptor -> map.add(descriptor.getMaxVelocity());
	}
}
