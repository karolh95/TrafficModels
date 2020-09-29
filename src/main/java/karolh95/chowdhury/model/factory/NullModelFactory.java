package karolh95.chowdhury.model.factory;

import java.util.ArrayList;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.NullModel;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import karolh95.chowdhury.model.descriptor.RoadDescriptor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Primary
@Component
@RequiredArgsConstructor
public class NullModelFactory implements ModelFactory {

	private static final int DEFAULT_LANES_NUMBER = 1;
	private static final int DEFAULT_LANES_LENGTH = 1;

	@Getter
	private final NullModel model;

	@Override
	public ModelDescriptor getDefaultModelDescriptor() {

		ModelDescriptor descriptor = new ModelDescriptor();

		descriptor.setRoadDescriptor(getRoadDescriptor());
		descriptor.setVehicleDescriptors(new ArrayList<>());

		return descriptor;
	}

	private RoadDescriptor getRoadDescriptor() {

		RoadDescriptor roadDescriptor = new RoadDescriptor();

		roadDescriptor.setLanesLength(DEFAULT_LANES_LENGTH);
		roadDescriptor.setLanesNumber(DEFAULT_LANES_NUMBER);

		return roadDescriptor;
	}
}
