package com.example.secondworkpractice;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener, ResultFragment.SecondFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MainFragment())
                    .commit();
        }
    }

    @Override
    public void onBackButtonClick() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onButtonClick(Bundle data) {
        ResultFragment secondFragment = new ResultFragment();
        secondFragment.setArguments(data);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, secondFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_home) {
            // Нажата кнопка "Главный экран"
            MainFragment mainFragment = new MainFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, mainFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        } else if (id == R.id.about_action) {
            new AlertDialog.Builder(this)
                    .setTitle("О приложении")
                    .setMessage("Это приложение для перевода массы")
                    .setPositiveButton("OK", null)
                    .show();
            return true;
        } else if (id == R.id.author_action) {
            new AlertDialog.Builder(this)
                    .setTitle("Об авторе")
                    .setMessage("Автор: Руденко Алексей Дмитриевич")
                    .setPositiveButton("OK", null)
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
