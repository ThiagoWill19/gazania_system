 package com.will.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.will.entities.Demonstrativo;
import com.will.entities.Gasto;
import com.will.entities.ProdutoVendido;
import com.will.entities.Servico;
import com.will.entities.ServicoAdicional;
import com.will.entities.dtos.FiltrarServicoDto;
import com.will.entities.enums.ServicoContratado;
import com.will.entities.enums.StatusDoServico;
import com.will.exceptions.ExceptionError;
import com.will.exceptions.UnauthorizedException;
import com.will.repositories.DadosFixosRepository;
import com.will.repositories.DemonstrativoRepository;

@Service
public class DemonstrativoService {

	@Autowired
	private DemonstrativoRepository demonstrativoRepository;
	
	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private GastoService gastoService;
	
	@Autowired
	private DadosFixosRepository dadosFixosRepository;
	
	public Demonstrativo montarDemosntrativo(String periodo) {
		
		Demonstrativo d = new Demonstrativo();
		List<Gasto> gastos = gastoService.findByPeriodo(periodo);
		List<Servico> servicos = servicoService.filtrarServicos(new FiltrarServicoDto(periodo));
		
		d.setProlabore(dadosFixosRepository.findById(1).get().getProlabore());
		d.setRgps(dadosFixosRepository.findById(1).get().getRGPS());
		
		d.setTotalGastos(d.getTotalGastos() + d.getProlabore() + d.getRgps());
		
		d.setPeriodo(periodo);
		
		for(Gasto g : gastos) {
			
			switch(g.getCategoria()) {
				case ABASTECIMENTO_CARRO : d.setAbastecimentoCarro(d.getAbastecimentoCarro() + g.getValor());
				d.setTotalGastos(d.getTotalGastos() + g.getValor());
				break;
				case ABASTECIMENTO_EQUIPAMENTO : d.setAbastecimentoEquipamento(d.getAbastecimentoEquipamento() + g.getValor());
				d.setTotalGastos(d.getTotalGastos() + g.getValor());
				break;
				case ADIANTAMENTO_PRESTADOR_DE_SERVICO : d.setPagamentoPrestadorServico(d.getPagamentoPrestadorServico() + g.getValor());
				d.setTotalGastos(d.getTotalGastos() + g.getValor());
				break;
				case  COMPRA_AUTOMOVEL : d.setCompraAutomovel(d.getCompraAutomovel() + g.getValor());
				d.setTotalGastos(d.getTotalGastos() + g.getValor());
				break;
				case COMPRA_DE_EQUIPAMENTO : d.setCompraEquipamento(d.getCompraEquipamento() + g.getValor());
				d.setTotalGastos(d.getTotalGastos() + g.getValor());
				break;
				case COMPRA_DE_PRODUTO : d.setCompraProduto(d.getCompraProduto() + g.getValor());
				d.setTotalGastos(d.getTotalGastos() + g.getValor());
				break;
				case MANUTENCAO_CARRO : d.setManutencaCarro(d.getManutencaCarro() + g.getValor());
				d.setTotalGastos(d.getTotalGastos() + g.getValor());
				break;
				case MANUTENCAO_EQUIPAMENTO : d.setManutencaoEquipameto(d.getManutencaoEquipameto() + g.getValor());
				d.setTotalGastos(d.getTotalGastos() + g.getValor());
				break;
				case PAGAMENTO_PRESTADOR_DE_SERVICO : d.setPagamentoPrestadorServico(d.getPagamentoPrestadorServico() + g.getValor());
				d.setTotalGastos(d.getTotalGastos() + g.getValor());
				break;
				case RETIRADA : d.setRetirada(d.getRetirada() + g.getValor());
				d.setTotalGastos(d.getTotalGastos() + g.getValor());
				break;
			}
			
		}
		
		for(Servico s : servicos) {
			
			
			
			if(s.getStatusDoServico() == StatusDoServico.EM_ABERTO) {
				d.setStatusDosServicos(true);
			}
			
			if(s.getContrato().getServicoContratado() != ServicoContratado.PISCINA) {
				d.setServicosContrarado(d.getServicosContrarado() +(s.getContrato().getValorDaVisita() * s.getVisitasFeitas().size()));
			}else {
				d.setServicosContrarado(d.getServicosContrarado() + s.getContrato().getValorDaVisita());
			}
			d.setServicosContrarado(d.getServicosContrarado() +(s.getContrato().getValorDaVisitaPiscina()));
			
			for(ServicoAdicional sa : s.getServicosAdicionais()) {
				d.setServicosAdicionais(d.getServicosAdicionais() + sa.getValor());
			}
			for(ProdutoVendido p : s.getProdutosVendidos()) {
				d.setVendas(d.getVendas() + p.getValor());
			}
			
		}
		
		d.setTotalReceita(d.getVendas() + d.getServicosAdicionais() + d.getServicosContrarado());
		d.setLucro(d.getTotalReceita() - d.getTotalGastos());
		
		return d;
		
	}
	
	
	public void save(Demonstrativo demonstrativo) throws ExceptionError {
		
		if(demonstrativo.isStatusDosServicos() == false) {
			demonstrativo.setSalvo(true);
			demonstrativoRepository.save(demonstrativo);
		}else throw new UnauthorizedException("Este demonstrativo nao pode ser salvo se ainda possuir serviços em aberto!");
		
		
	}
	
	public Demonstrativo getByPeriodo(String periodo) {
		
		Demonstrativo demonstrativo =  demonstrativoRepository.findByPeriodo(periodo);
		
		if(demonstrativo == null) {
			return montarDemosntrativo(periodo);
		}else return demonstrativo;
		
	}
	
	
	public void gerarArquivo(Demonstrativo demonstrativo) {
		
		String content = "DRE período: " + demonstrativo.getPeriodo() + "\n\n" +
						 "GASTOS ------------------------------\n" +
						 "Pro-labore: " + String.format("%.2f", demonstrativo.getProlabore())  +
						 "\nRGPS: " + String.format("%.2f",demonstrativo.getRgps() )  +
						 "\nPagamento Prestador: " +  String.format("%.2f", demonstrativo.getPagamentoPrestadorServico()) + 
						 "\nAbastecimento Automóvel: " + String.format("%.2f",demonstrativo.getAbastecimentoCarro()) +
						 "\nAbastecimento Maquinário: " + String.format("%.2f",demonstrativo.getAbastecimentoEquipamento()) +
						 "\nCompra de Produtos: " + String.format("%.2f",demonstrativo.getCompraProduto()) +
						 "\nCompra de Equipamento: " + String.format("%.2f",demonstrativo.getCompraEquipamento()) +
						 "\nCompra Automóvel: " + String.format("%.2f",demonstrativo.getCompraAutomovel()) +
						 "\nManutenção Automóvel: " + String.format("%.2f",demonstrativo.getManutencaCarro()) +
						 "\nManutenção Equipamento: " + String.format("%.2f",demonstrativo.getManutencaoEquipameto()) +
						 "\nRetirada: " + String.format("%.2f",demonstrativo.getRetirada()) +
						 "\nTOTAL GASTOS: " + demonstrativo.getGastoToString() +
						 "\n\n" +
						 "\nRECEITAS ------------------------------\n" +
						 "Total em serviços contratados: " + String.format("%.2f",demonstrativo.getServicosContrarado()) +
						 "\nTotal Serviços Adicionais: " + String.format("%.2f",demonstrativo.getServicosAdicionais()) +
						 "\nTotal Vendas: " + String.format("%.2f",demonstrativo.getVendas()) +
						 "\nTOTAL RECEITAS : " + demonstrativo.getReceitaToString() +
						 "\n\nLUCRO: " + demonstrativo.getLucroToString();
		
		
		
		try {
		    Document document = new Document();
		    PdfWriter.getInstance(document, new FileOutputStream("C://demonstrativos//"+demonstrativo.getPeriodo()+".pdf"));
		    document.open();
		   
		    Paragraph paragraph =  new Paragraph(content);
		    document.add(paragraph);
		    document.close();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (DocumentException e) {
		    e.printStackTrace();

		}
	
	
	}
	
	
}
