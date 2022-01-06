package br.com.astrosoft.termos.model.beans

import br.com.astrosoft.termos.model.saci
import java.time.LocalDateTime

class Cliente(
  val codigo: Int,
  val nome: String,
  val cpf: String,
  val email: String,
  var flagEntregaTroca: Boolean,
  var flagUsoAsistencia: Boolean,
  var flagHorarioDias: Boolean,
  var flagPromocoesOferta: Boolean,
  var flagPesquisaSatisfacao: Boolean,
  var flagCadastro: Boolean,
  var flagCancelado: Boolean,
  val dataHoraAceite: LocalDateTime?,
  val dataHoraCancelamento: LocalDateTime?,
             ) {
  val dataAceite
    get() = dataHoraAceite?.toLocalDate()
  val horaAceite
    get() = dataHoraAceite?.toLocalTime()
  val dataCancelamento
    get() = dataHoraCancelamento?.toLocalDate()
  val horaCancelamento
    get() = dataHoraCancelamento?.toLocalTime()

  fun flagAceito() = flagCadastro

  fun token(): String {
    return "https://www.engecopi.com.br/"
  }

  fun save() {
    saci.saveCliente(this)
  }

  fun desfaz() {
    saci.desfazCliente(this)
  }

  companion object {
    fun findClientes(filtro: FiltroCliente) = saci.findClientes(filtro)
  }
}

enum class ETipoCliente(val codigo: String) {
  BASE("B"), TERMO("T"), ACEITE("A"), CANCELADO("C")
}

data class FiltroCliente(val query: String, val tipo: ETipoCliente)