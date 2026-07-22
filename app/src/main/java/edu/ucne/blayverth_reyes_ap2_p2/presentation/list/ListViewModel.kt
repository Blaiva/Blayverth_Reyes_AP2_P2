package edu.ucne.blayverth_reyes_ap2_p2.presentation.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.blayverth_reyes_ap2_p2.domain.repository.Repository
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
}