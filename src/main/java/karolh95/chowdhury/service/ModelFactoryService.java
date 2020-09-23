package karolh95.chowdhury.service;

import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.ModelFactory;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelFactoryService {

	private final DesigningService designingService;
	private final ModelCreationService modelCreationService;

	private ModelFactory factory;

	public void changeModelFactory(ModelFactory factory) {

		this.factory = factory;

		designingService.saveModelDescriptor(factory.getDefaultModelDescriptor());

		modelCreationService.createModel(factory.getModel());
	}

	public Model getModel() {

		return factory.getModel();
	}
}
