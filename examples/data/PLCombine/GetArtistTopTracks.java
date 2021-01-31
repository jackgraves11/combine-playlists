package data.PLCombine;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class GetArtistTopTracks {

  public Track[] getTopTracks(ArtistSimplified artist) {
    GetArtistsTopTracksRequest getArtistsTopTracksRequest = Main.spotifyApi
      .getArtistsTopTracks(artist.getId(), CountryCode.US)
      .build();
    try {
      return getArtistsTopTracksRequest.execute();
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return null;
  }
}
