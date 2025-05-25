package org.akhsaul.dicoding.story.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import org.akhsaul.dicoding.story.ui.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setStory(args.shareData)
        sharedElementEnterTransition = MaterialContainerTransform(requireContext(), true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.transitionName = args.transitionName

        with(binding) {
            val story = viewModel.getStory()
            ivDetailPhoto.setImageResource(R.drawable.photo_test)
            tvDetailName.text = story.name
            tvDetailDescription.text = story.description
            tvDate.text = story.createdAt.toString()
            tvLocation.text = "Unknown city, Jawa Barat"
        }
    }
}

class DetailViewModel : ViewModel() {
    private var story: Story? = null

    fun setStory(story: Story) {
        this.story = story
    }

    fun getStory() = requireNotNull(story)
}