package es.gresybano.gresybano.feature.blockchain.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import es.gresybano.gresybano.common.extensions.toFormatTwoDecimal
import es.gresybano.gresybano.common.view.BaseFragment
import es.gresybano.gresybano.common.view.toast
import es.gresybano.gresybano.domain.entities.blockchain.CryptoBo
import es.gresybano.gresybano.domain.entities.response.getStringError
import es.gresybano.gresybano.feature.blockchain.R
import es.gresybano.gresybano.feature.blockchain.databinding.FragmentHomeBinding
import es.gresybano.gresybano.feature.blockchain.ui.viewmodel.HomeFragmentViewModel

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    companion object {
        private const val BTC_USD = "BTC-USD"
    }

    override val viewModel by viewModels<HomeFragmentViewModel>()

    override fun getBindingCast() = binding as? FragmentHomeBinding

    override fun getInflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewReady(savedInstanceState: Bundle?) {
        getPriceBtc()
        configureSwipeRefresh()
    }

    private fun configureSwipeRefresh() {
        getBindingCast()?.fragmentHomeSwipeRefresh?.setOnRefreshListener {
            viewModel.getDataCryptoListeners(
                cryptoPair = BTC_USD,
                successful = { it?.let { crypto -> getBindingCast()?.setDataCrypto(crypto) } },
                error = { toast(it.getStringError()) },
                loading = { getBindingCast()?.fragmentHomeSwipeRefresh?.isRefreshing = it }
            )
        }
    }

    private fun getPriceBtc() {
        viewModel.getDataCrypto(BTC_USD).observe(viewLifecycleOwner) { result ->
            result?.let { crypto ->
                getBindingCast()?.setDataCrypto(crypto)
            }
        }
    }

    private fun FragmentHomeBinding.setDataCrypto(cryptoBo: CryptoBo) {
        setNameCrypto(cryptoBo.name)
        setRemotePrice(cryptoBo.priceOnline, R.string.icon_dolar)
        setLocalPrice(cryptoBo.priceOffline, R.string.icon_dolar)
    }

    private fun FragmentHomeBinding.setLocalPrice(price: Double?, @StringRes idIconMoney: Int) {
        fragmentHomeLabelLocalPriceEdit.text =
            getString(idIconMoney, price?.toFormatTwoDecimal())
    }

    private fun FragmentHomeBinding.setRemotePrice(price: Double?, @StringRes idIconMoney: Int) {
        fragmentHomeLabelOnlinePriceEdit.text =
            getString(idIconMoney, price?.toFormatTwoDecimal())
    }

    private fun FragmentHomeBinding.setNameCrypto(name: String) {
        fragmentHomeLabelNameCrypto.text = name
    }

}