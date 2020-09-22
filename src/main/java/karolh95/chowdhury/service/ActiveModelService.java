package karolh95.chowdhury.service;

import org.springframework.stereotype.Service;

import karolh95.chowdhury.model.Model;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActiveModelService {

	private final ModelCreationService modelCreationService;
	
	@Getter
	private Model model;

	public void changeModel(Model model) {

		this.model = model;
		modelCreationService.createModel(model);
	}

	public void updateModel() {

		if (model != null) {
			model.update();
		}
	}
}
