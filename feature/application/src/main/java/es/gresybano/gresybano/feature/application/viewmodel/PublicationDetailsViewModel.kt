package es.gresybano.gresybano.feature.application.viewmodel

import es.gresybano.gresybano.common.util.DEFAULT_ID_LONG
import es.gresybano.gresybano.common.viewmodel.BaseViewModel

class PublicationDetailsViewModel : BaseViewModel(){

    var idPublication: Long = DEFAULT_ID_LONG
    var listImages: List<String> = listOf()

}