package yahav.itunessearch;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ItunesSearchService {
    @GET("term&media=music&entity=song")
    Single<ItunesSearchFeed> getSong(
            @Query("term") String term
    );
    @GET("?term&media=music&entity=album")
    Single<AlbumSearchFeed> getAlbum(
            @Query("term") String term
    );
}
