package com.example.recipeapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.recipeapplication.details.RecipeDetailsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExploreActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var recipeViewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)

        recyclerView = findViewById(R.id.recyclerView)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RecipeAdapter { selectedRecipe ->
            // Handle click on a recipe
            val intent = Intent(this, RecipeDetailsActivity::class.java)
            intent.putExtra("recipe", selectedRecipe)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        // Observe changes in the recipe list
        recipeViewModel.allRecipes.observe(this, { recipes ->
            adapter.submitList(recipes)
        })

        // Handle Bottom Navigation clicks
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_explore -> {
                    // Do nothing or add custom logic
                    true
                }
                R.id.menu_categories -> {
                    // Handle navigation to Recipe Categories
                    // For example, startActivity(Intent(this, RecipeCategoriesActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Populate the database with sample recipes (do this only once, preferably in the app's setup)
        lifecycleScope.launch(Dispatchers.IO) {
            populateDatabase()
        }
    }

    private suspend fun populateDatabase() {
        // Sample recipe data (replace with your actual data)
        val recipes = listOf(
            Recipe(0, "Recipe 1", "Lunch", 4, "Intermediate", listOf("Ingredient 1", "Ingredient 2"), "Steps for Recipe 1"),
            Recipe(0, "Recipe 2", "Dinner", 2, "Beginner", listOf("Ingredient A", "Ingredient B"), "Steps for Recipe 2"),
            // Add more recipes
        )

        recipes.forEach { recipeViewModel.insert(it) }
    }
}
