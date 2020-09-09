package karolh95.chowdhury.service;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ModelService implements Runnable {

	private final DataCollectingService dataCollecting;
	private final ActiveModelService activeModel;

	@Override
	public void run() {

		activeModel.updateModel();
		dataCollecting.sendData();
	}
}
