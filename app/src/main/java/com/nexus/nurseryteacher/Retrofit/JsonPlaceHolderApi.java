package com.nexus.nurseryteacher.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") Integer[] userId, @Query("_sort") String sort, @Query("_order") String order);

    @GET("post/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postID);
}
