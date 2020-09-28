package karolh95.chowdhury.model.factory;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;

public interface ModelFactory {

	Model getModel();

	ModelDescriptor getDefaultModelDescriptor();
}
