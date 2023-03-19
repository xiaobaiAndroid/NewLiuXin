package module.camera

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 *@author: baizf
 *@date: 2023/2/21
 */
class VideoRecordView : View {

    constructor(
        context: Context,
        attributeSet: AttributeSet?,
        defaultStyle: Int,
        defaultRes: Int
    ) : super(context, attributeSet, defaultStyle, defaultRes) {

    }

    constructor(context: Context, attributeSet: AttributeSet?, defaultStyle: Int) : this(
        context,
        attributeSet,
        defaultStyle,
        0
    )

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0, 0)
    constructor(context: Context) : this(context, null, 0, 0)


    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        canvas ?: return
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = resources.getDimension(R.dimen.dp_4)
        paint.color = resources.getColor(R.color.cl_ffffff)

        var radius = width / 2.0f - paint.strokeWidth
        canvas.save()
        canvas.drawCircle(width / 2.0f, height / 2.0f, radius, paint)
        canvas.restore()


        radius = width / 2.0f - resources.getDimension(R.dimen.dp_6) * 2
        paint.style = Paint.Style.FILL
        paint.alpha = 100
        canvas.save()
        canvas.drawCircle(width / 2.0f, height / 2.0f, radius, paint)
        canvas.restore()

    }


}