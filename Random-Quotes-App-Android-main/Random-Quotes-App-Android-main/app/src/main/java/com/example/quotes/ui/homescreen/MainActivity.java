package com.example.quotes.ui.homescreen;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.quotes.data.RandomQuotes;
import com.example.quotes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private QuotesViewModel quotesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        quotesViewModel = new ViewModelProvider(this).get(QuotesViewModel.class);

        quotesViewModel._randomQuotes.observe(this, new Observer<RandomQuotes>() {
            @Override
            public void onChanged(RandomQuotes randomQuotes) {
                binding.quotesId.setText(String.valueOf(randomQuotes.getId()));
                binding.quotesText.setText(randomQuotes.getQuote());
                binding.quotesAuthor.setText(randomQuotes.getAuthor());
            }
        });

        binding.qBtn.setOnClickListener(view -> {
            binding.moreBtn.setVisibility(View.VISIBLE);
            quotesViewModel.set_randomQuotes();
        });

        String quotesTV = binding.quotesText.getText().toString();
        binding.copyBtn.setOnClickListener(view -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Copy",quotesTV);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this, "Copied!", Toast.LENGTH_SHORT).show();
        });
        binding.shareBtn.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, quotesTV);
            startActivity(Intent.createChooser(intent, "Shared Quotes Via"));
        });
    }
}