package karolh95.chowdhury.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import karolh95.chowdhury.service.DesigningService;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class DesigningController {

	private final DesigningService service;

	@PostMapping("model/descriptor")
	public ModelDescriptor addVehicles(@RequestBody ModelDescriptor modelDescriptor) {

		return service.save(modelDescriptor);
	}
}
