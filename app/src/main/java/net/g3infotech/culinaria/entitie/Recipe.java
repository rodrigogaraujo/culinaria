package net.g3infotech.culinaria.entitie;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by g3infotech on 04/02/18.
 */

public class Recipe implements Parcelable{

    String name;
    List<Ingredient> ingredients;
    List<Step> steps;
    String servings;
    String image;

    public Recipe() {
    }

    public Recipe(Parcel in) {
        name = in.readString();
        servings = in.readString();
        image = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(servings);
        dest.writeString(image);
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public String getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", servings='" + servings + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
