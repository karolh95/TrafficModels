package karolh95.chowdhury.model;

import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.component.Road;
import lombok.Setter;

/**
 * <li>if V(n) < Vmax, then V(n) = V(n) + 1,</li>
 * <li>if V (n) > ( A X : (n) -- 1), then V(n) = dX(n) - 1,</li>
 * <li>if V(n) > 0 and ran f< Pd, then V(n) = V(n) - 1,</li>
 * <li>X(n) = X(n) + V(n),</li>
 * </ol>
 * where {@code Pd} is the probability of a random deceleration and
 * {@code ran f} is a random fraction uniformly distributed between 0 and 1.
 * </p>
 **/
@Setter
@Component
public class NagelSchreckenberg extends Model {

	public static final int SLOW_VEHICLE_MAX_VELOCITY = 3;
	public static final int FAST_VEHICLE_MAX_VELOCITY = 5;

	public NagelSchreckenberg(Road road) {
		super(road);
	}
}
