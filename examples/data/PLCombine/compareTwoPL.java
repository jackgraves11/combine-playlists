package data.PLCombine;

import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.*;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.ArrayList;

import static data.PLCombine.Main.*;

public class compareTwoPL {

  public ArrayList<Track> getCombinedTracks(String pLIDOne, String pLIDTwo) {
    ArrayList<Track> firstPlaylist = getPlayListTracks(pLIDOne);
    ArrayList<Track> secondPlaylist = getPlayListTracks(pLIDTwo);
    ArrayList<Track> combinedPlaylist = new ArrayList<>();
    for (Track t : firstPlaylist) {
      if (t == null) {
        continue;
      } else {
        for (Track t2 : secondPlaylist) {
          if (t2 == null) {
            continue;
          } else {
            if (t.equals(t2)) {
              combinedPlaylist.add(t);
              break;
            }
          }
        }
      }
    }
    return combinedPlaylist;
  }

  private GetPlaylistsItemsRequest initializePLItemsRequest(String PLID, int offset) {
    GetPlaylistsItemsRequest pLItemsRequest = spotifyApi.getPlaylistsItems(PLID)
      .offset(offset)
      .build();
    return pLItemsRequest;
  }

  private ArrayList<Track> getPlayListTracks(String pLID) {
    int offset = 0;
    int plLength = 0;
    ArrayList<Track> allTracks = new ArrayList<>();
    try {
      while (offset == 0 || offset < plLength) {
        GetPlaylistsItemsRequest pLRequest = initializePLItemsRequest(pLID, offset);
        Paging<PlaylistTrack> playlistPager = pLRequest.execute();
        PlaylistTrack[] pLTracks = playlistPager.getItems();
        for (PlaylistTrack pLTrack : pLTracks) {
          allTracks.add((Track) pLTrack.getTrack()); //Check Cast
        }
        plLength = playlistPager.getTotal();
        offset = offset + 100;
      }
    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return allTracks;
  }

  public void printTracks(ArrayList<Track> trackList) {
    for (Track t : trackList) {
      System.out.println(t.getName());
    }
  }
}
