package karolh95.chowdhury.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.ModelFactory;
import karolh95.chowdhury.model.ModelType;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelChangingService {

	private final ApplicationContext context;

	private final ModelFactoryService modelFactoryService;
	private final DesigningService designingService;
	private final ModelCreationService creationService;

	public ModelDescriptor changeModelFactory(ModelType modelType) {

		ModelFactory factory = (ModelFactory) context.getBean(modelType.getModelFactoryBeanName());

		modelFactoryService.changeModelFactory(factory);

		ModelDescriptor descriptor = designingService.saveModelDescriptor(factory.getDefaultModelDescriptor());

		creationService.createModel();

		return descriptor;
	}
}
