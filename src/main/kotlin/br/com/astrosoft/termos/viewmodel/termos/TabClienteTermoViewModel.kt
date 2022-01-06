package br.com.astrosoft.termos.viewmodel.termos

import br.com.astrosoft.framework.viewmodel.ITabView
import br.com.astrosoft.termos.model.beans.Cliente
import br.com.astrosoft.termos.model.beans.ETipoCliente
import br.com.astrosoft.termos.model.beans.FiltroCliente

class TabClienteTermoViewModel(val viewModel: TermoViewModel) {
  private val subView
    get() = viewModel.view.tabClienteTermoViewModel

  fun updateGrid() {
    val query = subView.query()
    val lista = Cliente.findClientes(FiltroCliente(query, ETipoCliente.TERMO))
    subView.updateClientes(lista)
  }

  fun aceito(cliente: Cliente) {
    cliente.flagCadastro = true
    cliente.save()
    updateGrid()
  }
}

interface ITabClienteTermoViewModel : ITabView {
  fun query(): String
  fun updateClientes(listaCliente: List<Cliente>)
}
