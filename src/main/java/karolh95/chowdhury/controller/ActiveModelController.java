package karolh95.chowdhury.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import karolh95.chowdhury.model.ModelFactory;
import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import karolh95.chowdhury.service.ModelChangingService;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class ActiveModelController {

	private final ModelChangingService modelChangingService;

	private final ModelFactory nagelSchreckenbergModelFactory;

	@GetMapping("model/nagel_schreckenber")
	public ModelDescriptor changeActiveModel() {

		return modelChangingService.changeModelFactory(nagelSchreckenbergModelFactory);
	}

}
