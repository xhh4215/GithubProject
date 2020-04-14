package com.xiaohei.common.ext
sealed class BooleanExt<out T>
object OtherWise: BooleanExt<Nothing>()
class WithData<T>(val data:T): BooleanExt<T>()
inline fun<T> Boolean.yes(block:()->T)=
      when{
          this-> WithData(block())
          else-> OtherWise
          }

inline fun<T> Boolean.no(block: () -> T)=
    when{
         this -> OtherWise
         else -> WithData(block())
}

inline fun<T> BooleanExt<T>.otherwise(block: () -> T):T=
    when(this){
        is OtherWise ->block()
        is WithData -> this.data
    }