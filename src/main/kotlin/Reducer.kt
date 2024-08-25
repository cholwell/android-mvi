package cholwell.dev.android_mvi

/**
 * A reducer is a pure function that takes a [Output] and a [State] and returns a new [State]
 */
interface Reducer<O: Output, S: State> {

    operator fun invoke(output: O, state: S): S
}