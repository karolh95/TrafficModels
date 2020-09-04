package karolh95.chowdhury.model.designer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import karolh95.chowdhury.model.Vehicle;
import karolh95.chowdhury.model.descriptor.VehicleDescriptor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VehiclesDesigner {

	private HashMap<Integer, Integer> maxVelocityNumber;
	private List<Vehicle> vehicles;

	public void setVehicles(List<VehicleDescriptor> descriptors) {

		maxVelocityNumber = new HashMap<>();

		descriptors.stream().filter(distinctByMaxVelocity()).forEach(this::addVehicleDescription);

		descriptors.clear();
		descriptors.addAll(
				maxVelocityNumber.entrySet().stream().map(this::entryToVehicleDescriptor).collect(Collectors.toList()));
	}

	public List<Vehicle> createVehicles() {

		vehicles = new ArrayList<>();

		for (int maxVelocity : maxVelocityNumber.keySet()) {

			int number = maxVelocityNumber.get(maxVelocity);

			for (int i = 0; i < number; i++) {
				vehicles.add(new Vehicle(maxVelocity));
			}
		}
		return vehicles;
	};

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
