package org.akhsaul.dicoding.story.ui

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import org.akhsaul.dicoding.story.ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _navController: NavController? = null
    private val navController: NavController get() = _navController!!
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        with(binding) {
            _navController = fragmentContainerView.findNavController()
        }
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration(setOf(R.id.homeFragment))
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun getActionBarHeightPx(context: Context): Int {
        val tv = TypedValue()
        if (context.theme.resolveAttribute(
                android.R.attr.actionBarSize,
                tv,
                true
            )
        ) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.resources.displayMetrics)
        }
        return 0
    }
}