package karolh95.chowdhury.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import karolh95.chowdhury.model.ModelFactory;
import karolh95.chowdhury.service.ModelFactoryService;
import karolh95.chowdhury.service.SchedulingService;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class ActiveModelController {

	private final SchedulingService schedulingService;
	private final ModelFactoryService modelFactoryService;

	private final ModelFactory nagelSchreckenbergModelFactory;

	@GetMapping("model/nagel_schreckenber")
	public void changeActiveModel() {

		changeModelFactory(nagelSchreckenbergModelFactory);
	}

	private void changeModelFactory(ModelFactory factory) {

		schedulingService.cancelScheduledTask();

		modelFactoryService.changeModelFactory(factory);
	}
}
