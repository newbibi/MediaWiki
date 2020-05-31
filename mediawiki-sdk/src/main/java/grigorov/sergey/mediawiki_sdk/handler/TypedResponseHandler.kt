package grigorov.sergey.mediawiki_sdk.handler

interface TypedResponseHandler <T> {

    fun onHandle(item: T)
    fun onError(throwable: Throwable)
    fun onComplete()

}