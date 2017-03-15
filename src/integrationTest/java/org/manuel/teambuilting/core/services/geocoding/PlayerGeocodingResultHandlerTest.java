package org.manuel.teambuilting.core.services.geocoding;

import static org.hamcrest.MatcherAssert.assertThat;

import com.google.maps.model.GeocodingResult;

import javax.inject.Inject;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.manuel.teambuilting.core.repositories.PlayerGeocodingRepository;
import org.manuel.teambuilting.core.services.GeocodingExamples;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author manuel.doncel.martos
 * @since 15-3-2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PlayerGeocodingResultHandlerTest {

	@Inject
	private PlayerGeocodingRepository playerGeocodingRepository;

	@Test
	public void savePlayerGeocoding() {
		final PlayerGeocodingResultHandler handler = new PlayerGeocodingResultHandler("Ubeda, Jaen, Spain", "playerId", playerGeocodingRepository);
		final GeocodingResult[] results = GeocodingExamples.ubeda();
		handler.onResult(results);
		assertThat(playerGeocodingRepository.findAll(), Matchers.hasSize(1));
	}
}
