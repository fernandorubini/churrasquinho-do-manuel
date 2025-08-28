package com.example.grupochurrasquinhodomanuel.features.welcome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.features.welcome.WelcomePreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val preferences: WelcomePreferences
) : ViewModel() {

    private val _isFirstLaunch = MutableStateFlow(true)
    val isFirstLaunch: StateFlow<Boolean> = _isFirstLaunch

    private var hasChecked = false

    fun checkFirstLaunch() {
        if (hasChecked) return
        hasChecked = true
        viewModelScope.launch {
            preferences.isFirstLaunch.collect { firstLaunch ->
                _isFirstLaunch.value = firstLaunch
            }
        }
    }

    fun disableFirstLaunch() {
        viewModelScope.launch {
            preferences.setNotFirstLaunch()
        }
    }
}
