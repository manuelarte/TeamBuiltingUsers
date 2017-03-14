package org.manuel.teambuilting.core.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.google.maps.model.AddressComponent;

import java.io.IOException;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * @author manuel.doncel.martos
 * @since 12-3-2017
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class GeocodingTest {

	@Test
	public void testFormat() throws IOException {
		//final ClassPathResource resource = new ClassPathResource("/geocoding/ubeda-jaen-spain.json");
		//final String jsonInString = new String(Files.readAllBytes(Paths.get(resource.getURI())), Charset.forName("ISO-8859-15"));
		final Geocoding geocoding = new Geocoding(GeocodingExamples.ubeda(), "OK");
		assertEquals(5, geocoding.getResults()[0].addressComponents.length);
		assertThat(geocoding.getResults()[0].addressComponents, hasTheseAddress("Úbeda", "Jaén", "Andalusia", "Spain", "23400"));
	}

	private BaseMatcher<AddressComponent[]> hasTheseAddress(final String... longNames) {
		return new TypeSafeMatcher<AddressComponent[]>() {

			@Override
			public void describeTo(final Description description) {

			}

			@Override
			protected boolean matchesSafely(final AddressComponent[] item) {
				for (int i = 0; i < longNames.length; i++) {
					if (!longNames[i].equals(item[i].longName)) {
						return false;
					}
				}
				return true;
			}

		};
	}


	private BeanDeserializerModifier customDeserializers() {
		return new BeanDeserializerModifier() {
			@Override
			public JsonDeserializer<Enum> modifyEnumDeserializer(DeserializationConfig config,
				final JavaType type,
				BeanDescription beanDesc,
				final JsonDeserializer<?> deserializer) {
				return new JsonDeserializer<Enum>() {
					@Override
					public Enum deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
						Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();
						return Enum.valueOf(rawClass, jp.getValueAsString().toUpperCase());
					}
				};
			}
		};
	}

}
