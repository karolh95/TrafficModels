package karolh95.chowdhury.model.descriptor;

import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoadDescriptor {

	@Min(1)
	private int lanesNumber;

	@Min(1)
	private int lanesLength;
}
