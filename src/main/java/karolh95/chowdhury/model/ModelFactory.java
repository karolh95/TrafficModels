package karolh95.chowdhury.model;

import karolh95.chowdhury.model.descriptor.ModelDescriptor;

public interface ModelFactory {

	Model getModel();

	ModelDescriptor getDefaultModelDescriptor();
}
