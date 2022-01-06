package br.com.astrosoft.termos.viewmodel.termos

import br.com.astrosoft.framework.viewmodel.ITabView
import br.com.astrosoft.termos.model.beans.Cliente
import br.com.astrosoft.termos.model.beans.ETipoCliente
import br.com.astrosoft.termos.model.beans.FiltroCliente

class TabClienteBaseViewModel(val viewModel: TermoViewModel) {
  private val subView
    get() = viewModel.view.tabClienteBaseViewModel

  fun updateGrid() {
    val query = subView.query()
    val lista = Cliente.findClientes(FiltroCliente(query, ETipoCliente.BASE))
    subView.updateClientes(lista)
  }

  fun naoAceito(cliente: Cliente) {
    cliente.flagHorarioDias = false
    cliente.flagEntregaTroca = false
    cliente.flagPesquisaSatisfacao = false
    cliente.flagPromocoesOferta = false
    cliente.flagUsoAsistencia = false
    cliente.flagCadastro = false
    cliente.save()
    updateGrid()
  }

  fun aceito(cliente: Cliente) {
    cliente.flagCadastro = true
    cliente.save()
    updateGrid()
  }
}

interface ITabClienteBaseViewModel : ITabView {
  fun query(): String
  fun updateClientes(listaCliente: List<Cliente>)
}
