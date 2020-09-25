package karolh95.chowdhury.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import karolh95.chowdhury.model.ModelType;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import karolh95.chowdhury.service.ModelChangingService;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class ActiveModelController {

	private final ModelChangingService modelChangingService;

	@GetMapping("model/{modelType}")
	public ModelDescriptor changeActiveModel(@PathVariable ModelType modelType) {

		return modelChangingService.changeModelFactory(modelType);
	}

	@GetMapping("models")
	public ModelType[] getModelsTypes() {

		return ModelType.values();
	}

}
