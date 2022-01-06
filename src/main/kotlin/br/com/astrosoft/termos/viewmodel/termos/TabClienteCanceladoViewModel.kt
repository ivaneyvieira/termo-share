package br.com.astrosoft.termos.viewmodel.termos

import br.com.astrosoft.framework.viewmodel.ITabView
import br.com.astrosoft.termos.model.beans.Cliente
import br.com.astrosoft.termos.model.beans.ETipoCliente
import br.com.astrosoft.termos.model.beans.FiltroCliente
import br.com.astrosoft.termos.model.saci

class TabClienteCanceladoViewModel(val viewModel: TermoViewModel) {
  private val subView
    get() = viewModel.view.tabClienteCanceladoViewModel

  fun updateGrid() {
    val query = subView.query()
    val lista = Cliente.findClientes(FiltroCliente(query, ETipoCliente.CANCELADO))
    subView.updateClientes(lista)
  }

  fun desfazerCancelamento(cliente: Cliente) {
    subView.showQuestao("O termo de consentimento será desfeito") {
      saci.excluiTermo(cliente)
      updateGrid()
    }
  }
}

interface ITabClienteCanceladoViewModel : ITabView {
  fun query(): String
  fun updateClientes(listaCliente: List<Cliente>)
  fun showQuestao(msg: String, exec: () -> Unit)
}
