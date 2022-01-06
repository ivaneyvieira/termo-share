package br.com.astrosoft.termos.viewmodel.termos

import br.com.astrosoft.framework.viewmodel.ITabView
import br.com.astrosoft.framework.viewmodel.IView
import br.com.astrosoft.framework.viewmodel.ViewModel

class TermoViewModel(view: ITermoView) : ViewModel<ITermoView>(view) {
  val tabClieteBaseViewModel = TabClienteBaseViewModel(this)
  val tabClieteViewModel = TabClienteTermoViewModel(this)
  val tabClieteAceitoViewModel = TabClienteAceitoViewModel(this)
  val tabClieteCanceladoViewModel = TabClienteCanceladoViewModel(this)
  override fun listTab(): List<ITabView> =
          listOf(view.tabClienteBaseViewModel, view.tabClienteTermoViewModel, view.tabClienteAceitoViewModel, view
            .tabClienteCanceladoViewModel)
}

interface ITermoView : IView {
  val tabClienteBaseViewModel: ITabClienteBaseViewModel
  val tabClienteTermoViewModel: ITabClienteTermoViewModel
  val tabClienteAceitoViewModel: ITabClienteAceitoViewModel
  val tabClienteCanceladoViewModel: ITabClienteCanceladoViewModel
}

