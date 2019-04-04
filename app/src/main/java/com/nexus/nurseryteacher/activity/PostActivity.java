package com.nexus.nurseryteacher.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nexus.nurseryteacher.R;
import com.nexus.nurseryteacher.Retrofit.JsonPlaceHolderApi;
import com.nexus.nurseryteacher.Retrofit.Post;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {

    private EditText userId_editText, title_editText, text_editText, postId_editText;
    private TextView result, result2;
    private Button sendPost_btn, updatePostPut_btn, updatePostPatch_btn;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private String userId, title,text, postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        userId_editText = findViewById(R.id.userID_value);
        title_editText = findViewById(R.id.title_value);
        text_editText = findViewById(R.id.body_value);
        postId_editText = findViewById(R.id.postId_value);
        sendPost_btn = findViewById(R.id.sendPost_btn);
        updatePostPut_btn = findViewById(R.id.putPost_btn);
        updatePostPatch_btn = findViewById(R.id.patchPost_btn);
        result = findViewById(R.id.result);
        result2 = findViewById(R.id.result2);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        sendPost_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = userId_editText.getText().toString();
                title = title_editText.getText().toString();
                text = text_editText.getText().toString();

                createPost();
            }
        });

        updatePostPut_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postId = postId_editText.getText().toString();
                userId = userId_editText.getText().toString();
                title = title_editText.getText().toString();
                text = text_editText.getText().toString();

                updatePost_Put();
            }
        });

        updatePostPatch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postId = postId_editText.getText().toString();
                userId = userId_editText.getText().toString();
                title = title_editText.getText().toString();
                text = text_editText.getText().toString();

                updatePost_Patch();
            }
        });
    }

    private void createPost(){

        Map<String, String> fields = new HashMap<>();
        fields.put("userId", userId);
        fields.put("title", title);

        Call<Post> call = jsonPlaceHolderApi.createPost(fields);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    result.setText("Code:" + response.code());
                    return;
                }

                Post postResponse = response.body();
                String content ="";
                content += "Code: "+ response.code()+"\n";
                content += "UserId: "+ postResponse.getUserId()+"\n";
                content += "Title: " + postResponse.getTitle()+"\n";
                content += "Text: "+postResponse.getText()+"\n\n";

                result.append(content);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                result.setText(t.getMessage());
            }
        });
    }

    private void updatePost_Put(){
        result.setText("");
        Post post = new Post(Integer.valueOf(userId), title, text);
        Call<Post> call = jsonPlaceHolderApi.putPost(Integer.valueOf(postId), post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful()){
                    result.setText("Put Result\n"+response.code());
                    return;
                }

                Post postresponse = response.body();

                String content ="";
                content += "Code: "+ response.code()+"\n";
                content += "UserId: "+ postresponse.getUserId()+"\n";
                content += "Title: " + postresponse.getTitle()+"\n";
                content += "Text: "+postresponse.getText()+"\n\n";
                result.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                result.setText("Put Result\n");
                result.append(t.getMessage());
            }
        });

    }

    private void updatePost_Patch(){
        result2.setText("");
        Post post = new Post(Integer.valueOf(userId), null, text);
        Call<Post> call = jsonPlaceHolderApi.patchPost(Integer.valueOf(postId), post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(!response.isSuccessful()){
                    result2.setText("Put Result\n"+response.code());
                    return;
                }

                Post postresponse = response.body();

                String content ="";
                content += "Code: "+ response.code()+"\n";
                content += "UserId: "+ postresponse.getUserId()+"\n";
                content += "Title: " + postresponse.getTitle()+"\n";
                content += "Text: "+postresponse.getText()+"\n\n";
                result2.append(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                result2.setText("Put Result\n");
                result2.append(t.getMessage());
            }
        });

    }

}
