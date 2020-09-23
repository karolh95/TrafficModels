package karolh95.chowdhury.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
	public ResponseEntity<ModelDescriptor> saveModelDescriptor(@Valid @RequestBody ModelDescriptor modelDescriptor,
			BindingResult result) {

		if (result.hasErrors()) {

			return ResponseEntity.badRequest().build();
		} else {

			service.saveModelDescriptor(modelDescriptor);
			return ResponseEntity.ok(modelDescriptor);
		}
	}
}
