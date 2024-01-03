package com.example.recipeapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.recipeapplication.R

class RecipeDetailsActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        val recipe = intent.getParcelableExtra<Recipe>("recipe")

        // Display recipe details in your UI components
        findViewById<TextView>(R.id.titleTextView).text = recipe?.title
        findViewById<TextView>(R.id.typeTextView).text = recipe?.type
        findViewById<TextView>(R.id.servingsTextView).text = recipe?.servings.toString()
        findViewById<TextView>(R.id.difficultyTextView).text = recipe?.difficulty
        // Add more UI components for other details
    }

    class Recipe : Parcelable {

    }
}
