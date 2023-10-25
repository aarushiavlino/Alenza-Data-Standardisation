package utils

import java.text.SimpleDateFormat
import java.util.Date

object DateTimeUtils {

   def millisToTime(millis: Long): String = {
    val date = new Date(millis)
    val formatter = new SimpleDateFormat("HH:mm:ss:SSS")
    formatter.format(date)
  }


   def millisToDate(millis: Long): String = {
    val date = new Date(millis)
    val formatter = new SimpleDateFormat("dd-MM-yyyy")
    formatter.format(date)
  }
}
