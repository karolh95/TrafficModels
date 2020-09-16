package karolh95.chowdhury.model.descriptor;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDescriptor {

	@Min(1)
	private int maxVelocity;

	@Min(1)
	private int vehiclesNumber;
}
