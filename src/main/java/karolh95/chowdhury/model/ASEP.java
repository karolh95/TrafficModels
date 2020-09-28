package karolh95.chowdhury.model;

import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.component.Road;

@Component
public class ASEP extends Model {

	public static final int MAX_VELOCITY = 1;

	public ASEP(Road road) {
		super(road);
	}

	@Override
	public void update() {

		forEachVehicle(vehicle -> {

		});
	}
}
