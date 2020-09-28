package karolh95.chowdhury.model.designer;

import java.util.List;

import karolh95.chowdhury.model.component.Road;
import karolh95.chowdhury.model.component.Vehicle;

public interface VehiclesPositionDesigner {

	public void placeVehicles(Road road, List<Vehicle> vehicles);
}
