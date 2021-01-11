package data.PLCombine;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.IPlaylistItem;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

import static data.Jack.firstExample.jefferyID;
import static data.PLCombine.Main.*;

public class compareTwoPL {

  private static final GetPlaylistRequest getPlaylistRequestOne = spotifyApi.getPlaylist(jackID)
//          .fields("description")
//          .market(CountryCode.SE)
//          .additionalTypes("track,episode")
    .build();

  private static final GetPlaylistRequest getPlaylistRequestTwo = spotifyApi.getPlaylist(grayID)
    .build();


  public static void getPlaylist_Sync() {
    try {
      final Playlist rapCaviar = getPlaylistRequestOne.execute();
      final Playlist mostNecessary = getPlaylistRequestTwo.execute();

      Paging<PlaylistTrack> rapCaviarPager = rapCaviar.getTracks();
      PlaylistTrack[] rapCaviarPLTracks = rapCaviarPager.getItems();
      IPlaylistItem[] RCTracks = new IPlaylistItem[rapCaviarPLTracks.length];
      for (int i = 0; i < RCTracks.length; i += 1) {
        RCTracks[i] = rapCaviarPLTracks[i].getTrack();
      }

      System.out.println(rapCaviarPLTracks.length);

      Paging<PlaylistTrack> mostNecessaryPager = mostNecessary.getTracks();
      PlaylistTrack[] mostNecessaryPLTracks = mostNecessaryPager.getItems();
      IPlaylistItem[] MNTracks = new IPlaylistItem[mostNecessaryPLTracks.length];
      for (int i = 0; i < MNTracks.length; i += 1) {
        MNTracks[i] = mostNecessaryPLTracks[i].getTrack();
      }

      System.out.println(MNTracks.length);

      for (IPlaylistItem t : RCTracks) {
        if (t == null) {
          continue;
        } else{
          for (IPlaylistItem t2 : MNTracks) {
            if (t2 == null) {
              continue;
            } else {
              if (t.getName().equals(t2.getName())) {
                System.out.println(t.getName());
                break;
              }
            }
          }
        }
      }

    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  private static final GetAlbumRequest getAlbumRequest = spotifyApi.getAlbum(jefferyID)
//          .market(CountryCode.SE)
    .build();

  public static void getAlbum_Sync() {
    try {
      final Album album = getAlbumRequest.execute();

      System.out.println("Name: " + album.getName());
      Paging<TrackSimplified> tracks = album.getTracks();
      TrackSimplified[] realTracks = tracks.getItems();
      for (TrackSimplified t : realTracks) {
        System.out.println(t.getName());
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
