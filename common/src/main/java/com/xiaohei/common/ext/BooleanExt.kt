package com.xiaohei.common.ext

/***
 * 对Boolean类型编写扩展函数
 */
//out 标识了的范型表示这个传入的参数不会被消费。
//in  标识了的范型是只能被消费不能被生产
sealed class BooleanExt<out T>
object OtherWise : BooleanExt<Nothing>()
// val data：T 此处是获取T的范型T 所以BooleanExt的范型是协变的
class WithData<T>(val data: T) : BooleanExt<T>()

/***
 * Boolean函数返回yes的时候执行
 * 如果返回yes 执行参数   block的这个函数
 */
inline fun <T> Boolean.yes(block: () -> T) =
    when {
        this -> WithData(block())
        else -> OtherWise
    }

/***
 * Boolean函数返回no的时候执行
 * 如果返回no 执行参数   block的这个函数
 */
inline fun <T> Boolean.no(block: () -> T) =
    when {
        this -> OtherWise
        else -> WithData(block())
    }
/***
 * Boolean函数返回其他情况的时候执行
 *  执行参数   block的这个函数
 */
inline fun <T> BooleanExt<T>.otherwise(block: () -> T): T =
    when (this) {
        //如果当前的返回值OtherWise
        is OtherWise -> block()
        //如果当前的返回值是WithData
        is WithData -> this.data
    }