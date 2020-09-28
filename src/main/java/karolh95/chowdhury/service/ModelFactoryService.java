package karolh95.chowdhury.service;

import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import karolh95.chowdhury.model.factory.ModelFactory;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelFactoryService {

	private ModelFactory factory;

	public void changeModelFactory(ModelFactory factory) {

		this.factory = factory;
	}

	public Model getModel() {

		return factory.getModel();
	}

	public ModelDescriptor getDefaultModelDescriptor() {

		return factory.getDefaultModelDescriptor();
	}
}
