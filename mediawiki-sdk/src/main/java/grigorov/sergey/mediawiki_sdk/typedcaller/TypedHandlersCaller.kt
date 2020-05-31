package grigorov.sergey.mediawiki_sdk.typedcaller

import android.util.Log
import com.google.gson.Gson
import grigorov.sergey.mediawiki_sdk.exception.ParserIncompatibleTypeException
import grigorov.sergey.mediawiki_sdk.support.TypedHandlersSet
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class TypedHandlersCaller {

    @Inject lateinit var gson: Gson

    private lateinit var handlersSet: TypedHandlersSet
    private lateinit var json: String

    fun setJson(value: String) { json = value }

    fun setJson(value: Response) { json = gson.toJson(value.body) }

    fun setJson(value: ResponseBody) { json = gson.toJson(value) }

    fun setHandlersSet(set: TypedHandlersSet) { handlersSet = set }

    private fun <T> call(forType: Class<T>) {
        val handler = handlersSet.getHandler(forType)
        try {
            val item = gson.fromJson(json, forType)
            handler?.onHandle(item)
        } catch (throwable: Throwable) {
            Log.e(this::javaClass.name, throwable.message.toString())
            throwable.printStackTrace()
            throw ParserIncompatibleTypeException(throwable.message.toString())
        }
    }

    fun call() {
        for (type in handlersSet.getTypes()) {
            try {
                call(type::class.java)
            } catch (throwable: Throwable) {
                continue
            }
        }
    }

}