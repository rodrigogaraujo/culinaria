package net.g3infotech.culinaria;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.g3infotech.culinaria.entitie.Step;
import net.g3infotech.culinaria.fragment.RecipeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction()
                    .add(R.id.container, new RecipeFragment())
                    .commit();
    }

}
