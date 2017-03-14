package org.manuel.teambuilting.core.services.query;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author manuel.doncel.martos
 * @since 14-3-2017
 */
@Service
public class GoogleMapsApiService {

	private final GeoApiContext geoApiContext;

	@Inject
	public GoogleMapsApiService(final GeoApiContext geoApiContext) {
		this.geoApiContext = geoApiContext;
	}

	public Optional<GeocodingResult[]> get(final String address) {
		return Optional.ofNullable(GeocodingApi.newRequest(geoApiContext).address(address).awaitIgnoreError());
	}

	public void getInformationFrom(final GeocodingResult[] results) {
		Assert.notNull(results);
		Assert.isTrue(results.length > 0);
		final Map<AddressComponentType, String> map = new HashMap<>(results[0].addressComponents.length);
		for (final AddressComponent addressComponent : results[0].addressComponents) {
			map.put(addressComponent.types[0], addressComponent.longName);
		}
		final LatLng location = results[0].geometry.location;
		final String placeId = results[0].placeId;
	}
}
