package karolh95.chowdhury.model;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

@Component
public class VehicleDescriptorValidator implements Predicate<VehicleDescriptor> {

	private Set<Integer> maxVelocities = new HashSet<>();

	public void clear() {

		maxVelocities.clear();
	}

	public void addAllowedMaxVelocity(int maxVelocity) {

		maxVelocities.add(maxVelocity);
	}

	@Override
	public boolean test(VehicleDescriptor descriptor) {

		int maxVelocity = descriptor.getMaxVelocity();
		int vehiclesNumber = descriptor.getVehiclesNumber();

		return isMaxVelocityAllowed(maxVelocity) && isPositive(vehiclesNumber);
	}

	private boolean isMaxVelocityAllowed(int maxVelocity) {

		return maxVelocities.contains(maxVelocity);
	}

	private boolean isPositive(int number) {

		return number > 0;
	}
}
