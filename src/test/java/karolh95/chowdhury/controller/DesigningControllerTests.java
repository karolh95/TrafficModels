package karolh95.chowdhury.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import karolh95.chowdhury.model.descriptor.ModelDescriptor;
import karolh95.chowdhury.model.descriptor.RoadDescriptor;
import karolh95.chowdhury.model.descriptor.VehicleDescriptor;
import karolh95.chowdhury.service.DesigningService;

@DisplayName("DesigningController tests")
public class DesigningControllerTests {

	static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Mock
	DesigningService service;

	@InjectMocks
	DesigningController controller;

	MockMvc mockMvc;

	@BeforeEach
	void init() {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Nested
	@DisplayName("saveModelDescriptor() tests")
	class SaveModelDescriptorTests {

		private static final String URL = "/model/descriptor";

		@Test
		@DisplayName("Test with null modelDescriptor")
		void saveNullModelDescriptor() {

			String content = ToJson.convert(null);

			perform(URL, content, status().isBadRequest());
		}

		@ParameterizedTest
		@DisplayName("Test with uninitialized modelDescriptor")
		@ArgumentsSource(UninitializedModelDescriptorArgumentsProvider.class)
		void saveUninitializedModelDescriptor(RoadDescriptor roadDescriptor,
				List<VehicleDescriptor> vehicleDescriptors) {

			ModelDescriptor descriptor = new ModelDescriptor();
			descriptor.setRoadDescriptor(roadDescriptor);
			descriptor.setVehicleDescriptors(vehicleDescriptors);

			String content = ToJson.convert(descriptor);

			perform(URL, content, status().isBadRequest());
		}

		@Test
		@DisplayName("Test with valid modelDescriptor")
		void saveValidModelDescriptor() {

			List<VehicleDescriptor> vehicleDescriptors = Arrays.asList(new VehicleDescriptor(1, 5));

			ModelDescriptor descriptor = new ModelDescriptor();
			descriptor.setRoadDescriptor(roadDescriptor(1, 1));
			descriptor.setVehicleDescriptors(vehicleDescriptors);

			String content = ToJson.convert(descriptor);

			perform(URL, content, status().isOk());
		}

		void perform(String url, String content, ResultMatcher matcher) {

			MockHttpServletRequestBuilder builder = post(url).contentType(APPLICATION_JSON_UTF8).content(content);

			try {

				mockMvc.perform(builder).andExpect(matcher);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}

	static class ToJson {

		private static ObjectWriter writer;

		static {

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

			writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
		}

		public static String convert(ModelDescriptor descriptor) {

			String json = null;

			try {
				json = writer.writeValueAsString(descriptor);
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}

			return json;
		}
	}

	static class UninitializedModelDescriptorArgumentsProvider implements ArgumentsProvider {

		@Override
		public Stream<Arguments> provideArguments(ExtensionContext context) {

			List<Arguments> args = new ArrayList<>();

			args.addAll(combinationOf(null));
			args.addAll(combinationOf(roadDescriptor(0, 1)));
			args.addAll(combinationOf(roadDescriptor(1, 0)));
			args.addAll(combinationOfVehicleDescriptor(1, 0));
			args.addAll(combinationOfVehicleDescriptor(0, 1));

			return args.stream();
		}
	}

	static List<Arguments> combinationOf(RoadDescriptor descriptor) {

		List<VehicleDescriptor> vehicleDescriptors = Arrays.asList(new VehicleDescriptor(1, 5));

		return Arrays.asList(Arguments.of(descriptor, null),
				Arguments.of(descriptor, Collections.emptyList(), Arguments.of(descriptor, vehicleDescriptors)));
	}

	static List<Arguments> combinationOfVehicleDescriptor(int maxVelocity, int vehiclesNumber) {

		List<VehicleDescriptor> vehicleDescriptors = Arrays.asList(new VehicleDescriptor(maxVelocity, vehiclesNumber));

		return Arrays.asList(Arguments.of(null, vehicleDescriptors),
				Arguments.of(new RoadDescriptor(), vehicleDescriptors),
				Arguments.of(roadDescriptor(1, 0), vehicleDescriptors),
				Arguments.of(roadDescriptor(0, 1), vehicleDescriptors));
	}

	static RoadDescriptor roadDescriptor(int lanesNumber, int lanesLength) {

		RoadDescriptor descriptor = new RoadDescriptor();

		descriptor.setLanesNumber(lanesNumber);
		descriptor.setLanesLength(lanesLength);

		return descriptor;
	}
}
