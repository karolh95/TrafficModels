package karolh95.chowdhury.model;

import karolh95.chowdhury.model.component.Road;
import lombok.Setter;

@Setter
public class NagelSchreckenberg extends Model {

	public static final int SLOW_VEHICLE_MAX_VELOCITY = 3;
	public static final int FAST_VEHICLE_MAX_VELOCITY = 5;

	public NagelSchreckenberg(Road road) {
		super(road);
	}
}
