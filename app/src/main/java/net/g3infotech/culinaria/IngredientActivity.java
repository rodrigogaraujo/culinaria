package net.g3infotech.culinaria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import net.g3infotech.culinaria.adapter.IngredientAdapter;
import net.g3infotech.culinaria.entitie.Ingredient;
import net.g3infotech.culinaria.utils.Constants;

import java.util.List;

public class IngredientActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = findViewById(R.id.rv_ingredient);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        if(getIntent().hasExtra(Constants.SEND_INGREDIENTS)) {
            List<Ingredient> ingredients = getIntent().getExtras().getParcelableArrayList(Constants.SEND_INGREDIENTS);
            IngredientAdapter adapter = new IngredientAdapter(ingredients, this);
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
