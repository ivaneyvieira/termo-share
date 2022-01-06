package br.com.astrosoft.framework.model

import br.com.astrosoft.framework.util.toLocalDate
import br.com.astrosoft.framework.util.toLocalDateTime
import org.sql2o.converters.Converter
import org.sql2o.converters.ConverterException
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class LocalDateTimeConverter : Converter<LocalDateTime?> {
  @Throws(ConverterException::class)
  override fun convert(value: Any?): LocalDateTime? {
    return when (value) {
      is Date      -> value.toLocalDateTime()
      is Timestamp -> value.toLocalDateTime()
      else         -> null
    }
  }

  override fun toDatabaseParam(value: LocalDateTime?): Any? {
    value ?: return null
    return Date.from(value.atZone(ZoneId.systemDefault()).toInstant())
  }
}