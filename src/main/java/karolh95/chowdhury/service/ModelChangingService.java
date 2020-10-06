package karolh95.chowdhury.service;

import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.ModelType;
import karolh95.chowdhury.model.component.Road;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import karolh95.chowdhury.model.factory.ModelFactory;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelChangingService {

	private final ModelFactoryService modelFactoryService;
	private final DesigningService designingService;
	private final ModelCreationService creationService;

	private final Road road;

	public ModelDescriptor changeModelFactory(ModelType modelType) {

		ModelFactory factory = modelType.getFactory(road);

		modelFactoryService.changeModelFactory(factory);

		ModelDescriptor descriptor = designingService.saveModelDescriptor(factory.getDefaultModelDescriptor());

		creationService.createModel();

		return descriptor;
	}
}
