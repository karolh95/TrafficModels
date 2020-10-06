package karolh95.chowdhury.service;

import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import karolh95.chowdhury.model.factory.ModelFactory;
import lombok.Getter;

@Service
public class ModelFactoryService {

	private ModelFactory factory;

	@Getter
	private Model model;

	public ModelFactoryService(ModelFactory factory) {

		changeModelFactory(factory);
	}

	public void changeModelFactory(ModelFactory factory) {

		this.factory = factory;
		this.model = factory.getModel();
	}

	public ModelDescriptor getDefaultModelDescriptor() {

		return factory.getDefaultModelDescriptor();
	}
}
