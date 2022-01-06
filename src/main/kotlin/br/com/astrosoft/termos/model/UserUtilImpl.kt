package br.com.astrosoft.termos.model

import br.com.astrosoft.framework.model.IUser
import br.com.astrosoft.framework.model.IUserUtil
import br.com.astrosoft.termos.model.saci

class UserUtilImpl : IUserUtil {
  override fun findUser(username: String): IUser? = saci.findUser(username)
}