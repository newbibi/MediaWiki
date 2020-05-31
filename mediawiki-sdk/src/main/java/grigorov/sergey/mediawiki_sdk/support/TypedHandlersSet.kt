package grigorov.sergey.mediawiki_sdk.support

import grigorov.sergey.mediawiki_sdk.handler.TypedResponseHandler
import java.lang.reflect.Type

class TypedHandlersSet {

    private val handlers: HashMap<Type, TypedResponseHandler<*>> = HashMap()

    fun <T> addHandler(forType: Class<T>, handler: TypedResponseHandler<T>) { handlers[forType] = handler }

    fun <T> removeHandler(forType: Class<T>) { handlers.remove(forType) }

    @Suppress("UNCHECKED_CAST")
    fun <T> getHandler(forType: Class<T>): TypedResponseHandler<T>? = handlers[forType] as TypedResponseHandler<T>

    fun getAll(): List<TypedResponseHandler<*>> {
        val r = ArrayList<TypedResponseHandler<*>>()
        for (entry : Map.Entry<Type, TypedResponseHandler<*>> in handlers) {
            r.add(entry.value)
        }
        return r
    }

    fun getTypes(): List<Type> {
        val r = ArrayList<Type>()
        r.addAll(handlers.keys)
        return r
    }

}