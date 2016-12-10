package br.com.marketedelivery.IDAO;


import br.com.marketedelivery.classesBasicas.Pagamento;

public interface IPagamentoDAO extends IDAOGenerico<Pagamento>
{
	public Pagamento buscarPorStatus(Pagamento pagamento);
}
