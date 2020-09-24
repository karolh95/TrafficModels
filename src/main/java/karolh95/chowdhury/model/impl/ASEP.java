package karolh95.chowdhury.model.impl;

import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.Road;

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
