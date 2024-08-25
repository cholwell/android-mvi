package cholwell.dev.android_mvi

import androidx.lifecycle.ViewModel

/**
 * A [Output] is the outcome of the [ViewModel] handling an [Input]
 *
 * [Output]'s are sent from the [ViewModel] to the [Reducer] in order to update the [State]
 * In the special case of [Effect] the [Effect] is presented directly to the view
 */
interface Output