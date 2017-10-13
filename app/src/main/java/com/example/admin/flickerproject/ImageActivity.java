package com.example.admin.flickerproject;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.flickerproject.model.Item;

public class ImageActivity extends AppCompatActivity {
    private static final String IMAGE_FRAG_TAG = "imageFragTag";
    ImageView ivImage;
    TextView tvTitle, tvLink, tvDataTaken, tvDescription, tvPublished, tvAuthor, tvAuthorId, tvTags;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ivImage = findViewById(R.id.ivImage);
        tvTitle = findViewById(R.id.tvTitle);
        tvLink = findViewById(R.id.tvLink);
        tvDataTaken = findViewById(R.id.tvDataTaken);
        tvDescription = findViewById(R.id.tvDescription);
        tvPublished = findViewById(R.id.tvPublished);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvAuthorId = findViewById(R.id.tvAuthorId);
        tvTags = findViewById(R.id.tvTags);


        item = (Item) getIntent().getSerializableExtra("start");
        Glide.with(this).load(item.getMedia().getM()).into(ivImage);
        tvTitle.setText(item.getTitle());
        tvLink.setText(item.getLink());
        tvDataTaken.setText(item.getDateTaken());
        tvDescription.setText(item.getDescription());
        tvPublished.setText(item.getPublished());
        tvAuthor.setText(item.getAuthor());
        tvAuthorId.setText(item.getAuthorId());
        tvTags.setText(item.getTags());

    }

}
