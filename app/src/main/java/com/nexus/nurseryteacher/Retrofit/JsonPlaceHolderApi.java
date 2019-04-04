package com.nexus.nurseryteacher.Retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") Integer[] userId, @Query("_sort") String sort, @Query("_order") String order);

    @GET("post/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postID);

    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(@FieldMap Map<String, String> fileds);

    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int postId, @Body Post post);

    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int postId, @Body Post post);

}
