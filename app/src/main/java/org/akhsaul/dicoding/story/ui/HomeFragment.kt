package org.akhsaul.dicoding.story.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.coroutines.launch
import org.akhsaul.dicoding.story.ui.databinding.FragmentHomeBinding
import java.time.Instant
import kotlin.random.Random

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init(getString(R.string.lorem_ipsum))

        //exitTransition = Hold()
        reenterTransition = MaterialElevationScale(/* growing= */ true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MyListAdapter(::onItemClicked)
        binding.rvStory.adapter = adapter
        adapter.submitList(viewModel.storyList)
    }

    private fun onItemClicked(story: Story, sharedView: View, transitionName: String) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                story,
                transitionName
            ),
            FragmentNavigatorExtras(sharedView to transitionName)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

class HomeViewModel : ViewModel() {
    val storyList = mutableListOf<Story>()
    fun init(description: String) {
        viewModelScope.launch {
            repeat(10) {
                val id = "d_story_${generateRandomString()}"
                Log.d("Experimental", "storyList: $id")
                storyList.add(
                    Story(
                        id = id,
                        name = "Dicoding Story Name",
                        description = description,
                        createdAt = Instant.now(),
                        lat = 10.0,
                        lon = 10.0,
                        photoUrl = "none"
                    )
                )
            }
        }
    }

    fun generateRandomString(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z')
        val randomLength = Random.nextInt(5, 11)

        return (1..randomLength)
            .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
            .joinToString("")
    }
}