package net.g3infotech.culinaria.provider.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.g3infotech.culinaria.entitie.Ingredient;
import net.g3infotech.culinaria.utils.Constants;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by g3infotech on 26/02/18.
 */

public class CookingPreferences {

    private Gson mGson;
    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private SharedPreferences.Editor mEditor;

    public CookingPreferences(Context context){
        mGson = new Gson();
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(Constants.SEND_INGREDIENTS, mContext.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public void addListIngredients(List<Ingredient> ingredients){
        String json = mGson.toJson(ingredients);
        mEditor.putString(Constants.SEND_INGREDIENTS, json);
        mEditor.commit();
    }

    public List<Ingredient> getListIngredients(){
        String str = mSharedPreferences.getString(Constants.SEND_INGREDIENTS, "");
        Type type = new TypeToken<List<Ingredient>>(){}.getType();
        List<Ingredient> ingredients = mGson.fromJson(str, type);
        return ingredients;
    }

    public void clearPreferences(){
        mEditor.clear();
        mEditor.commit();
    }

}
