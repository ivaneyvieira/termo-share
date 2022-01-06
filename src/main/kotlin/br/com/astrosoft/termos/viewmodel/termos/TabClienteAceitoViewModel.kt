package br.com.astrosoft.termos.viewmodel.termos

import br.com.astrosoft.framework.viewmodel.ITabView
import br.com.astrosoft.termos.model.beans.Cliente
import br.com.astrosoft.termos.model.beans.ETipoCliente
import br.com.astrosoft.termos.model.beans.FiltroCliente

class TabClienteAceitoViewModel(val viewModel: TermoViewModel) {
  private val subView
    get() = viewModel.view.tabClienteAceitoViewModel

  fun updateGrid() {
    val query = subView.query()
    val lista = Cliente.findClientes(FiltroCliente(query, ETipoCliente.ACEITE))
    subView.updateClientes(lista)
  }

  fun naoAceito(cliente: Cliente) {
    subView.showQuestao("O termo de consentimento será cancelado") {
      cliente.desfaz()
      updateGrid()
    }
  }
}

interface ITabClienteAceitoViewModel : ITabView {
  fun query(): String
  fun updateClientes(listaCliente: List<Cliente>)
  fun showQuestao(msg: String, exec: () -> Unit)
}
