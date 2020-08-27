package karolh95.chowdhury.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle {

	private static final int INIT_VELOCITY = 1;


	@Setter(value = AccessLevel.PRIVATE)
	private int maxVelocity;
	private int velocity;

	private int lane;
	private int position;

	public Vehicle(int maxVelocity) {

		setMaxVelocity(maxVelocity);
		setVelocity(INIT_VELOCITY);
	}
}
