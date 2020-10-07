package karolh95.chowdhury.model;

import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonValue;

import karolh95.chowdhury.model.component.Road;
import karolh95.chowdhury.model.factory.ASEPModelFactory;
import karolh95.chowdhury.model.factory.ModelFactory;
import karolh95.chowdhury.model.factory.NagelSchreckenbergModelFactory;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ModelType {

	NAGEL_SCHRECKENBERG(NagelSchreckenbergModelFactory.MODEL_NAME, NagelSchreckenbergModelFactory::new),
	ASEP(ASEPModelFactory.MODEL_NAME, ASEPModelFactory::new);

	@Getter
	@JsonValue
	private final String name;

	private final Function<Road, ModelFactory> factory;

	public static ModelType of(String type) {

		if (type == null) {
			return null;
		}

		for (ModelType modelType : ModelType.values()) {

			if (type.equals(modelType.getName())) {
				return modelType;
			}
		}

		throw new IllegalArgumentException("ModelType: unknown value: " + type);
	}

	public ModelFactory getFactory(Road road) {

		return factory.apply(road);
	}
}
