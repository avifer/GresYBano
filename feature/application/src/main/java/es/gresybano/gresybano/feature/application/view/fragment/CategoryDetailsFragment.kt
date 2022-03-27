package es.gresybano.gresybano.feature.application.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.extensions.loadUrl
import es.gresybano.gresybano.common.util.EMPTY_STRING
import es.gresybano.gresybano.common.util.ZERO
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.feature.application.R
import es.gresybano.gresybano.feature.application.databinding.FragmentCategoryDetailsBinding
import es.gresybano.gresybano.feature.application.view.adapter.HeightPublicationAdapter
import es.gresybano.gresybano.feature.application.viewmodel.CategoryDetailsViewModel

@AndroidEntryPoint
class CategoryDetailsFragment : BaseFragment() {

    companion object {
        private const val KEY_ID_CATEGORY = "idCategory"
        private const val KEY_NAME_CATEGORY = "nameCategory"
        private const val KEY_PRIMARY_URL_CATEGORY = "primaryUrlCategory"
    }

    private val adapterListPublications =
        HeightPublicationAdapter(
            listenerClickElement = { /*TODO*/ },
            centerGravity = true,
            favoriteEnable = true
        )

    override val viewModel by viewModels<CategoryDetailsViewModel>()

    override fun getBindingCast() = binding as? FragmentCategoryDetailsBinding

    override fun onViewReady(savedInstanceState: Bundle?) {
        showToolbarGoBack(arguments?.getString(KEY_NAME_CATEGORY) ?: EMPTY_STRING)
        initHeader()
        initList()
        setDataInList()
    }

    private fun setDataInList() {
        arguments?.getLong(KEY_ID_CATEGORY)?.let { idCategory ->
            viewModel.getPublicationsOfCategory(idCategory = idCategory)
                .observe(viewLifecycleOwner) {
                    adapterListPublications.submitList(it)
                    getBindingCast()?.loadAmountPublications(it?.size ?: ZERO)
                }
        }
    }

    private fun initList() {
        getBindingCast()?.fragmentCategoryDetailsListListPublications?.let {
            with(it) {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = adapterListPublications
            }
        }
    }

    private fun initHeader() {
        getBindingCast()?.loadImageCategory(
            arguments?.getString(KEY_PRIMARY_URL_CATEGORY) ?: EMPTY_STRING
        )
    }

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCategoryDetailsBinding.inflate(inflater, container, false)

    private fun FragmentCategoryDetailsBinding.loadImageCategory(urlImage: String) {
        fragmentCategoryDetailsImgCategory.loadUrl(urlImage)
    }

    private fun FragmentCategoryDetailsBinding.loadAmountPublications(amount: Int) {
        fragmentCategoryDetailsLabelAmountPublications.text =
            requireActivity().getString(R.string.publications_with_number, amount)
    }

}