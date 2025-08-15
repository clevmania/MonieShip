package com.example.monieship.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

/**
 * @author by Lawrence on 8/8/25.
 * for MonieShip
 */

class ShipmentViewModel : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _allShipments = MutableStateFlow(
        listOf(
            PackageInfo("Macbook pro M2", "#NE43857340857904", "Paris", "Morocco"),
            PackageInfo("Summer linen jacket", "#NEJ20089934122231", "Barcelona", "Paris"),
            PackageInfo("Tapered-fit jeans AW", "#NEJ35870264978659", "Colombia", "Paris"),
            PackageInfo("Slim fit jeans AW", "#NEJ35870264978659", "Bogota", "Dhaka"),
            PackageInfo("Office setup desk", "#NEJ23481570754963", "France", "Germany")
        )
    )

    val filteredShipments = searchQuery
        .combine(_allShipments) { query, shipments ->
            if (query.isBlank()) {
                shipments
            } else {
                shipments.filter {
                    it.title.contains(query, ignoreCase = true) ||
                            it.trackingNumber.contains(query, ignoreCase = true) ||
                            it.origin.contains(query, ignoreCase = true) ||
                            it.destination.contains(query, ignoreCase = true)
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _allShipments.value
        )

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }
}

data class PackageInfo(
    val title: String,
    val trackingNumber: String,
    val origin: String,
    val destination: String
)