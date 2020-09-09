package karolh95.chowdhury.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import karolh95.chowdhury.model.Model;
import karolh95.chowdhury.service.ActiveModelService;
import karolh95.chowdhury.service.SchedulingService;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class ActiveModelController {

	private final SchedulingService schedulingService;
	private final ActiveModelService activeModelService;

	private final Model nagelSchreckenberg;

	@GetMapping("model/nagel_schreckenber")
	public void changeActiveModel() {

		schedulingService.cancelScheduledTask();
		activeModelService.changeModel(nagelSchreckenberg);
	}
}
