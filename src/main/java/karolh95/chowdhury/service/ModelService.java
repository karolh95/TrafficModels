package karolh95.chowdhury.service;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ModelService implements Runnable {

	private final DataCollectingService dataCollecting;
	private final ModelFactoryService modelFactoryService;

	@Override
	public void run() {

		modelFactoryService.getModel().update();
		dataCollecting.sendData();
	}
}
