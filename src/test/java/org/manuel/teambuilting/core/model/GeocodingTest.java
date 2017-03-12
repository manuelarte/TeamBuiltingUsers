package org.manuel.teambuilting.core.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.manuel.teambuilting.core.model.Geocoding.AddressComponent;
import org.springframework.core.io.ClassPathResource;

/**
 * @author manuel.doncel.martos
 * @since 12-3-2017
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class GeocodingTest {

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testFormat() throws IOException {
		final ClassPathResource resource = new ClassPathResource("/geocoding/ubeda-jaen-spain.json");
		final String jsonInString = new String(Files.readAllBytes(Paths.get(resource.getURI())), Charset.forName("ISO-8859-15"));
		final Geocoding geocoding = mapper.readValue(jsonInString, Geocoding.class);
		assertEquals(5, geocoding.getResults().get(0).getAddress_components().size());
		assertThat(geocoding.getResults().get(0).getAddress_components(), hasTheseAddress("Úbeda", "Jaén", "Andalusia", "Spain", "23400"));
	}

	private BaseMatcher<List<AddressComponent>> hasTheseAddress(final String... longNames) {
		return new TypeSafeMatcher<List<AddressComponent>>() {

			@Override
			public void describeTo(final Description description) {

			}

			@Override
			protected boolean matchesSafely(final List<AddressComponent> item) {
				for (int i = 0; i < longNames.length; i++) {
					if (!longNames[i].equals(item.get(i).getLong_name())) {
						return false;
					}
				}
				return true;
			}

		};
	}
}
