package net.g3infotech.culinaria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import net.g3infotech.culinaria.entitie.Step;
import net.g3infotech.culinaria.fragment.StepFragment;
import net.g3infotech.culinaria.listener.OnStepClickListener;
import net.g3infotech.culinaria.utils.Constants;

public class DetailsRecipeActivity extends AppCompatActivity implements OnStepClickListener{

    boolean mTwoPanes;
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        manager = getSupportFragmentManager();

        if(findViewById(R.id.two_pane_linear) != null){
            mTwoPanes = true;
            if(savedInstanceState == null) {
                manager.beginTransaction()
                        .add(R.id.detail_container, new StepFragment())
                        .commit();
            }
        }else {
            mTwoPanes = false;
        }
    }

    @Override
    public void onStepSelected(Step step) {
        if(!mTwoPanes){
            Intent intent = new Intent(this, StepsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(Constants.SEND_STEPS, getIntent().getParcelableArrayListExtra(Constants.SEND_RECIPE)) ;
            bundle.putInt(Constants.SEND_POSITION, 0);
            intent.putExtras(bundle);
            this.startActivity(intent);
        }else{
            StepFragment fragment = new StepFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Constants.SEND_STEP, step);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_container, fragment)
                    .commit();
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
