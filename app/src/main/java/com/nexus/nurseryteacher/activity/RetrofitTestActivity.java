package com.nexus.nurseryteacher.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.Retrofit.Comment;
import com.nexus.nurseryteacher.Retrofit.JsonPlaceHolderApi;
import com.nexus.nurseryteacher.Retrofit.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTestActivity extends AppCompatActivity {

    private TextView get_result;
    private Button getPosts_btn, getPostById_btn, getComments_btn;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getPosts_btn = findViewById(R.id.getPosts_btn);
        getPosts_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPosts();
            }
        });
        getPostById_btn = findViewById(R.id.getPostsByID_btn);
        getPostById_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPostsByID();
            }
        });
        getComments_btn = findViewById(R.id.getComments_btn);
        getComments_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getComments();
            }
        });

        get_result = findViewById(R.id.get_result);



    }

    private void getPosts(){

        get_result.setText("");
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    get_result.setText(response.code());
                    return;
                }

                List<Post> posts = response.body();
                for(Post post: posts){
                    String content="";
                    content += "ID: "+post.getId()+"\n";
                    content += "userId: "+post.getUserId()+"\n";
                    content += "title: "+post.getTitle()+ "\n";
                    content += "Text: "+post.getText()+"\n\n";

                    get_result.append(content);
                }

            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                get_result.setText(t.getMessage());
            }
        });

    }

    private void getPostsByID(){

        get_result.setText("");
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(new Integer[]{2,4,6}, "id", "desc");

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    get_result.setText(response.code());
                    return;
                }

                List<Post> posts = response.body();
                for(Post post: posts){
                    String content="";
                    content += "ID: "+post.getId()+"\n";
                    content += "userId: "+post.getUserId()+"\n";
                    content += "title: "+post.getTitle()+ "\n";
                    content += "Text: "+post.getText()+"\n\n";

                    get_result.append(content);
                }

            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                get_result.setText(t.getMessage());
            }
        });

    }

    private void getComments(){
        get_result.setText("");

        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(4);

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if(!response.isSuccessful()){
                    get_result.setText(response.code());
                    return;
                }

                List<Comment> comments = response.body();
                for(Comment comment: comments){
                    String content="";
                    content += "ID: "+comment.getId()+"\n";
                    content += "userId: "+comment.getPostId()+"\n";
                    content += "name: "+comment.getName()+ "\n";
                    content += "Email: "+comment.getEmail()+"\n";
                    content += "Body: "+comment.getText()+ "\n\n";

                    get_result.append(content);
                }

            }
            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                get_result.setText(t.getMessage());
            }
        });

    }

}
