package karolh95.chowdhury.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import karolh95.chowdhury.model.ModelType;

@Component
public class ModelTypeConverter implements Converter<String, ModelType> {

	@Override
	public ModelType convert(String source) {

		return ModelType.of(source);
	}
}
