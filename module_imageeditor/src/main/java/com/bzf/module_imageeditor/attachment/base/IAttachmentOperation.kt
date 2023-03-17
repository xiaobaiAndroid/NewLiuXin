package com.bzf.module_imageeditor.attachment.base

/**
 *@author: baizf
 *@date: 2023/3/16
 */
interface IAttachmentOperation<T: IAttachmentDraw> {

    fun add(drawable: T)
    fun delete()
    fun move(currentX: Float, currentY: Float, lastX: Float, lastY: Float)
    fun scaleAndRotate(drawable: T, currentX: Float, currentY: Float, offsetX: Float, offsetY: Float)

    /*
    * @describe: 选中状态
    * @date: 2023/3/16
    */
     fun isSelected(): Boolean

     fun isTouchAtAttachment(x: Float, y: Float)

     fun cancelSelected()

}

enum class AttachmentOperationType{
    NONE,MOVE,DELETE,RESIZE
}