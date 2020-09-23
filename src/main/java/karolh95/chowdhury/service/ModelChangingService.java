package karolh95.chowdhury.service;

import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.ModelFactory;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelChangingService {

	private final ModelFactoryService modelFactoryService;
	private final DesigningService designingService;
	private final ModelCreationService creationService;

	public ModelDescriptor changeModelFactory(ModelFactory factory) {

		modelFactoryService.changeModelFactory(factory);

		ModelDescriptor descriptor = designingService.saveModelDescriptor(factory.getDefaultModelDescriptor());

		creationService.createModel();

		return descriptor;
	}
}
