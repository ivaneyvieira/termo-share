package br.com.astrosoft.termos.model.beans

import br.com.astrosoft.framework.model.IUser
import br.com.astrosoft.termos.model.saci
import kotlin.math.pow
import kotlin.reflect.KProperty

class UserSaci : IUser {
  var no: Int = 0
  var name: String = ""
  override var login: String = ""
  override var senha: String = ""
  var bitAcesso: Int = 0
  var storeno: Int = 0
  var impressora: String? = ""
  override var ativo by DelegateAuthorized(0)
  var termos by DelegateAuthorized(1)
  override val admin
    get() = login == "ADM"
  var clienteBase by DelegateAuthorized(1)
  var clienteTermo by DelegateAuthorized(2)
  var clienteAceito by DelegateAuthorized(3)
  var clienteCancelado by DelegateAuthorized(4)

  companion object {
    fun findAll(): List<UserSaci> {
      return saci.findAllUser().filter { it.ativo }
    }

    fun updateUser(user: UserSaci) {
      saci.updateUser(user)
    }

    fun findUser(login: String?): UserSaci? {
      return saci.findUser(login)
    }
  }
}

class DelegateAuthorized(numBit: Int) {
  private val bit = 2.toDouble().pow(numBit).toInt()

  operator fun getValue(thisRef: UserSaci?, property: KProperty<*>): Boolean {
    thisRef ?: return false
    return (thisRef.bitAcesso and bit) != 0 || thisRef.admin
  }

  operator fun setValue(thisRef: UserSaci?, property: KProperty<*>, value: Boolean?) {
    thisRef ?: return
    val v = value ?: false
    thisRef.bitAcesso = when {
      v    -> thisRef.bitAcesso or bit
      else -> thisRef.bitAcesso and bit.inv()
    }
  }
}


