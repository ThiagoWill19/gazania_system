package com.will.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.will.entities.ProdutoVendido;
import com.will.entities.Servico;
import com.will.entities.ServicoAdicional;
import com.will.entities.Visita;
import com.will.entities.VisitaPiscina;
import com.will.entities.dtos.FiltrarServicoDto;
import com.will.entities.dtos.NovoServicoDto;
import com.will.entities.dtos.ServicoUpdateDto;
import com.will.entities.enums.ServicoContratado;
import com.will.entities.enums.StatusDoPagamento;
import com.will.entities.enums.StatusDoServico;
import com.will.exceptions.ExceptionError;
import com.will.exceptions.NotFoundException;
import com.will.repositories.ClienteRepository;
import com.will.repositories.ProdutoVendidoRepository;
import com.will.repositories.ServicoAdicionalRepository;
import com.will.repositories.ServicoRepository;
import com.will.repositories.VisitaPiscinaRepository;
import com.will.repositories.VisitaRepository;

@Service
public class ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ProdutoVendidoRepository produtoVendidoRepository;
	@Autowired
	private ServicoAdicionalRepository servicoAdicionalRepository;
	@Autowired
	private VisitaRepository visitaRepository;
	@Autowired
	private VisitaPiscinaRepository visitaPiscinaRepository;
	@Autowired
	private MailService mailService;

	public Servico save(NovoServicoDto novoServicoDto) throws ExceptionError {

		Servico servico = new Servico();

		if (clienteRepository.existsById(novoServicoDto.getClienteId())) {

			servico.setCliente(clienteRepository.findById(novoServicoDto.getClienteId()).get());

			servico.setPeriodo(dateFormat(novoServicoDto.getPeriodo()));

			servico.setContrato(novoServicoDto.getContrato());
			servico.getContrato().setServico(servico);
			servico.setStatusDoServico(StatusDoServico.EM_ABERTO);
			servico.setStatusDoPagamento(StatusDoPagamento.PENDENTE);
			return servicoRepository.save(servico);

		} else
			throw new NotFoundException("Não existe cliente cadastrado com ID: " + novoServicoDto.getClienteId());

	}

	public Servico update(int id, ServicoUpdateDto servicoUpdateDto) throws ExceptionError {

		if (servicoRepository.existsById(id)) {
			Servico servico = servicoRepository.findById(id).get();

			if (servicoUpdateDto.getContrato() != null) {
				servicoUpdateDto.getContrato().setId(servico.getContrato().getId());
				servico.setContrato(servicoUpdateDto.getContrato());
				servico.getContrato().setServico(servico);
			}

			if (servicoUpdateDto.getStatusDoPagamento() != null) {
				servico.setStatusDoPagamento(servicoUpdateDto.getStatusDoPagamento());
			}

			if (servicoUpdateDto.getStatusDoServico() != null) {
				servico.setStatusDoServico(servicoUpdateDto.getStatusDoServico());
			}

			return servicoRepository.save(servico);

		} else
			throw new NotFoundException("Não há serviço no cadastrado com ID: " + id);
	}

	public List<Servico> findAll() {
		return servicoRepository.findAll();
	}

	public Servico findById(int id) throws ExceptionError {

		if (servicoRepository.existsById(id)) {

			return servicoRepository.findById(id).get();

		} else
			throw new NotFoundException("Não existe nenhum servico no sistema com ID: " + id);

	}

	public List<Servico> filtrarServicos(FiltrarServicoDto filtrar) {

		List<Servico> servicos;
		List<Servico> servicosFiltrados = new ArrayList<>();

		if (filtrar.getPeriodo() != "") {

			servicos = servicoRepository.findByPeriodoContaining(filtrar.getPeriodo());

		} else {
			servicos = servicoRepository.findAll();
		}

		if (!filtrar.getStatusDoServico().equals("TODOS")) {

			switch (filtrar.getStatusDoServico()) {
			case "EM_ABERTO":
				for (Servico s : servicos) {
					if (s.getStatusDoServico() == StatusDoServico.EM_ABERTO) {
						servicosFiltrados.add(s);
					}
				}
				break;

			case "FINALIZADO":
				for (Servico s : servicos) {
					if (s.getStatusDoServico() == StatusDoServico.FINALIZADO) {
						servicosFiltrados.add(s);
					}
				}
				break;
			}

			return servicosFiltrados;

		} else
			return servicos;

	}

	public String dateFormat(String htmlDate) {

		String[] vetorData = htmlDate.split("-");

		String dataFormatada = vetorData[1] + "-" + vetorData[0];

		return dataFormatada;
	}

	public boolean deleteById(int id) throws ExceptionError {
		if (servicoRepository.existsById(id)) {
			servicoRepository.deleteById(id);
			return true;
		} else
			throw new NotFoundException("Nada encontrado com ID: " + id);
	}

	public Servico addProduto(int servicoId, ProdutoVendido produtoVendido) throws ExceptionError {
		if (servicoRepository.existsById(servicoId)) {
			Servico servico = servicoRepository.findById(servicoId).get();		
			
			servico.getProdutosVendidos().add(produtoVendido);
			produtoVendido.setServico(servico);
			
			List<ProdutoVendido> vendidos = servico.getProdutosVendidos();
			Collections.sort(vendidos);
			
			List<ProdutoVendido> listaNova = new ArrayList<>();
			
			for(ProdutoVendido p : vendidos) {
				int index = listaNova.indexOf(p);
				if(index == -1) {
					listaNova.add(p);
				} else {
					if(listaNova.get(index).getUnidadeDeMedida().equals(p.getUnidadeDeMedida())) {
						ProdutoVendido produtoExistente = listaNova.get(index);
						produtoExistente.setQuantidade(produtoExistente.getQuantidade() + p.getQuantidade());
						produtoExistente.setValor(produtoExistente.getValor() + p.getValor());
					}else {
						listaNova.add(p);
					}
				}
	
				
			}
			
			servico.setProdutosVendidos(listaNova);
			
			return servicoRepository.save(servico);
		} else
			throw new NotFoundException("Não há serviço cadastrado com ID: " + servicoId);
	}

	public Servico addServicoAdc(int servicoId, ServicoAdicional servicoAdicional) throws ExceptionError {
		if (servicoRepository.existsById(servicoId)) {
			Servico servico = servicoRepository.findById(servicoId).get();
			servico.getServicosAdicionais().add(servicoAdicional);
			servicoAdicional.setServico(servico);
			return servicoRepository.save(servico);
		} else
			throw new NotFoundException("Não há serviço cadastrado com ID: " + servicoId);
	}

	public Servico addVisita(int servicoId, Visita visita) throws ExceptionError {
		if (servicoRepository.existsById(servicoId)) {
			Servico servico = servicoRepository.findById(servicoId).get();
			visita.setServico(servico);
			servico.getVisitasFeitas().add(visita);
			return servicoRepository.save(servico);
		} else
			throw new NotFoundException("Não há serviço cadastrado com ID: " + servicoId);
	}
	
	public Servico addVisitaPiscina(int servicoId, VisitaPiscina visita) throws ExceptionError {
		if (servicoRepository.existsById(servicoId)) {
			Servico servico = servicoRepository.findById(servicoId).get();
			visita.setServico(servico);
			servico.getVisitasFeitasPiscina().add(visita);
			return servicoRepository.save(servico);
		} else
			throw new NotFoundException("Não há serviço cadastrado com ID: " + servicoId);
	}

	public void removerProduto(int idProduto) {
		if (produtoVendidoRepository.existsById(idProduto)) {
			produtoVendidoRepository.deleteById(idProduto);
		}
	}

	public void removerServicoAdc(int idServico) {
		if (servicoAdicionalRepository.existsById(idServico)) {
			servicoAdicionalRepository.deleteById(idServico);
		}
	}

	public void removerVisita(int idVisita) {
		if (visitaRepository.existsById(idVisita)) {
			visitaRepository.deleteById(idVisita);
		}
	}
	
	public void removerVisitaPiscina(int idVisita) {
		if (visitaPiscinaRepository.existsById(idVisita)) {
			visitaPiscinaRepository.deleteById(idVisita);
		}
	}

	public String gerarFechamento(int id) {

		Servico servico = servicoRepository.findById(id).get();
		String fechamento = "\n\n";
		fechamento += "Fechamento do período: " + servico.getPeriodo();
		
		fechamento += "\n\nCliente: " + servico.getCliente().getNome();
		fechamento +="\nEndereço: " + servico.getCliente().getEndereco().getComplemento();
		
		fechamento += "\n\n--CONTRATO------------------------------------------------------------------------\n";
		fechamento += "Serviço Contratado: " + servico.getContrato().getServicoContratado().getName();
		if(servico.getContrato().getServicoContratado() == ServicoContratado.JARDINAGEM ||
				servico.getContrato().getServicoContratado() == ServicoContratado.JARDINAGEM_E_PISCINA) {
			
			fechamento += "\nFrequência para manutenção de Jardim: " + servico.getContrato().getEscala();
			fechamento += "\nValor da visita para manutenção de jardim: " + servico.getContrato().getValorVisitaToString();
			
		}else {
			fechamento += "\nValor da manutenção de Piscina: " + servico.getContrato().getValorVisitaToString();
		}
		
		if(servico.getContrato().getServicoContratado() == ServicoContratado.JARDINAGEM_E_PISCINA) {
			
			fechamento += "\nValor da manutenção Piscina: " + servico.getContrato().getValorVisitaPiscinaToString();
		}
		fechamento += "\n-----------------------------------------------------------------------------------------\n";
		
		
		if (!servico.getVisitasFeitas().isEmpty() && servico.getContrato().getServicoContratado() != ServicoContratado.PISCINA ) {

			double total = 0;
			fechamento += "\n\nDATA DAS MANUTENÇÕES DE JARDIM REALIZADAS\n";
			for (Visita v : servico.getVisitasFeitas()) {
				total += v.getServico().getContrato().getValorDaVisita();
				fechamento += v.getData() + " - Valor: " + servico.getContrato().getValorVisitaToString() + "\n";
			}
			fechamento += "-----------------------------------------------------------------\n";
			fechamento += "Valor total para manutenção de Jardim: R$ " + String.format("%.2f", total) + "\n\n";

		}
		

		if (!servico.getProdutosVendidos().isEmpty()) {
			double total = 0;
			fechamento += "\nPRODUTOS VENDIDOS\n";
		
			
			for (ProdutoVendido p : servico.getProdutosVendidos()){
				
				total += p.getValor();
				fechamento += p.getProduto() + " - " + p.formatarDouble() + " " + p.getUnidadeDeMedida().getName() + " - "
						+ p.getValorToString() + "\n---------------------------------------------\n";
			}
			fechamento += "Subtotal em produtos: R$ " + String.format("%.2f", total) + "\n\n\n";
			
		}

		if (!servico.getServicosAdicionais().isEmpty()) {
			double total = 0;
			fechamento += "SERVIÇOS ADICIONAIS\n";
			for (ServicoAdicional s : servico.getServicosAdicionais()) {
				total += s.getValor();
				fechamento += s.getDescricao() + " - " + s.getValorToString()
						+ "\n---------------------------------------------\n";
			}
			
			fechamento += "Subtotal em serviços: R$ " + String.format("%.2f", total) + "\n\n";
		}

		fechamento += "\nVALOR TOTAL: " + servico.getValorToString();

		servico.setStatusDoServico(StatusDoServico.FINALIZADO);
		servico.setFechamentoEnviado(true);
		servicoRepository.save(servico);

		return fechamento;
	}

	public void enviarFechamentoPorEmail(int idCliente) {
		Servico servico = servicoRepository.findById(idCliente).get();
		String fechamento = gerarFechamento(idCliente);
		mailService.send(servico.getCliente().getEmail(), fechamento);
	}

	public void gerarPdfDoFechamento(int idCliente) {
		
		Servico servico = servicoRepository.findById(idCliente).get();
		
		String fechamento = gerarFechamento(idCliente);
		String nomeEmpresa = "Empresa Manutenção de jardins e piscinas";
		String endereco = "Rua, 0000 - Bairro  - Cidade UF - CEP:00000000";
		String contato = "Telefones: (00) 000000000";
		
		Font f1 = new Font(FontFamily.TIMES_ROMAN,20,Font.BOLD,BaseColor.BLACK);
		Font f2 = new Font(FontFamily.TIMES_ROMAN,12,Font.NORMAL ,BaseColor.BLACK);
		Font f3 = new Font(FontFamily.TIMES_ROMAN,10,Font.NORMAL ,BaseColor.BLACK);
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("C://fechamentos//" + servico.getCliente().getNome()
					+ "_" + servico.getPeriodo() + "_" + servico.getContrato().getServicoContratado().getName() + ".pdf"));
			document.open();
		
			Paragraph title = new Paragraph(nomeEmpresa, f1);
			Paragraph subTitle1 = new Paragraph(endereco, f3);
			Paragraph subTitle2 = new Paragraph(contato, f3);
			
			title.setAlignment(Paragraph.ALIGN_CENTER);
			subTitle1.setAlignment(Paragraph.ALIGN_CENTER);
			subTitle2.setAlignment(Paragraph.ALIGN_CENTER);
			
 			Paragraph paragraph = new Paragraph(fechamento,f2);
 			document.add(title);
 			document.add(subTitle1);
			document.add(subTitle2);
			document.add(paragraph);
			
			
			document.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();

		}
	}

	public List<Servico> procurarServicoAbertoPorCliente(int idCliente) {

		List<Servico> lista = new ArrayList<>();

		for (Servico s : findAll()) {
			if (s.getCliente().getId() == idCliente) {
				if (s.getStatusDoServico() == StatusDoServico.EM_ABERTO) {
					lista.add(s);
				}
			}
		}

		return lista;

	}

}
