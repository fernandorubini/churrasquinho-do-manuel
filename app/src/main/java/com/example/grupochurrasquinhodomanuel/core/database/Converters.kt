package com.example.grupochurrasquinhodomanuel.core.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.math.BigDecimal
import com.example.grupochurrasquinhodomanuel.features.order.model.OrderItem
import com.example.grupochurrasquinhodomanuel.features.order.model.OrderStatus

/**
 * Converters globais do Room.
 * - Registre no AppDatabase com @TypeConverters(Converters::class)
 * - NÃO chame .addTypeConverter(Converters()) no Room.databaseBuilder.
 */
class Converters {

    private val gson = Gson()

    // --- OrderStatus <-> String ---
    @TypeConverter
    fun stringToOrderStatus(value: String?): OrderStatus? =
        value?.let { OrderStatus.valueOf(it) }

    @TypeConverter
    fun orderStatusToString(status: OrderStatus?): String? = status?.name

    // --- BigDecimal <-> String (TEXT) ---
    @TypeConverter
    fun stringToBigDecimal(value: String?): BigDecimal? =
        value?.let { BigDecimal(it) }

    @TypeConverter
    fun bigDecimalToString(value: BigDecimal?): String? =
        value?.toPlainString()

    // --- List<OrderItem> <-> JSON (TEXT) ---
    @TypeConverter
    fun jsonToOrderItemList(json: String?): List<OrderItem> {
        if (json.isNullOrBlank()) return emptyList()
        val listType = object : TypeToken<List<OrderItem>>() {}.type
        return gson.fromJson(json, listType)
    }

    @TypeConverter
    fun orderItemListToJson(list: List<OrderItem>?): String =
        gson.toJson(list ?: emptyList<OrderItem>())
}
