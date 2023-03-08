package module.common.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class QRCode(
    @SerializedName("barcode")
    var barcode: String? = ""
) : Parcelable