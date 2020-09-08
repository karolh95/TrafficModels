package karolh95.chowdhury.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle {

	private static final int INIT_VELOCITY = 1;
	private static final int MIN_VELOCITY = 0;

	@Setter(value = AccessLevel.PRIVATE)
	private int maxVelocity;
	private int velocity;

	private int lane;
	private int position;

	public Vehicle(int maxVelocity) {

		if (maxVelocity <= MIN_VELOCITY)
			throw new IllegalArgumentException();

		setMaxVelocity(maxVelocity);
		setVelocity(INIT_VELOCITY);
	}

	public void setVelocity(int velocity) {

		if (velocity < MIN_VELOCITY)
			throw new IllegalArgumentException();

		if (velocity > maxVelocity)
			throw new IllegalArgumentException();

		this.velocity = velocity;
	}

	public void speedUp() {

		velocity = Math.min(velocity + 1, maxVelocity);
	}

	public void speedDown() {

		velocity = Math.max(velocity - 1, MIN_VELOCITY);
	}
}
