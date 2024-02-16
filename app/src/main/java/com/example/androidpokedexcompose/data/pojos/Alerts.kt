package com.example.androidpokedexcompose.data.pojos

enum class TypesAlerts{ WIFI_STATUS, } // PROGRESS, ERROR
data class CustomAlerts(
    val isVisible: Boolean,
    val type: TypesAlerts = TypesAlerts.WIFI_STATUS,
    val alertData: AlertData =  AlertData(
        "","","","", { }, { },
    )
)

data class AlertData(
    val title: String,
    val message: String,
    val confirmButtonText: String,
    val dismissButtonText: String,
    val onDismiss: () -> Unit,
    val callback: () -> Unit,
)