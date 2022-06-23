package com.meloeclaranhan.prefeitura.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.meloeclaranhan.prefeitura.modelo.Cidade;
import com.meloeclaranhan.prefeitura.modelo.Prefeito;
import com.meloeclaranhan.prefeitura.repository.PrefeitoRepository;
import com.meloeclaranhan.prefeitura.repository.CidadeRepository;

@Controller
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private PrefeitoRepository prefeitoRepository;

	@RequestMapping("/")
	public String listarCidades(Model model) {
		List<Cidade> listaCidades = cidadeRepository.findAll();
		model.addAttribute("cidades", listaCidades);

		return "tabela-cidade";
	}


	// MANIPULAR CIDADES

	@GetMapping("/novaCidade")
	public String adicionarCidade(Model model) {
		model.addAttribute("cidade", new Cidade());

		return "formulario-cidade";
	}

	@PostMapping("/salvarCidade")
	public String salvarCidade(@Valid Cidade cidade, BindingResult result) {
		if (result.hasErrors()) {
			return "formulario-cidade";
		}

		cidadeRepository.save(cidade);
		return "redirect:/";
	}


	// MANIPULAR PREFEITOS

	@GetMapping("/novoPrefeito/{idCidade}")
	public String adicionarPrefeito(@PathVariable("idCidade") long idCidade,
			Model model) {

		Optional<Cidade> optionalCidade = cidadeRepository.findById(idCidade);
		Cidade cidade = optionalCidade.get();

		Prefeito prefeito = new Prefeito();
		prefeito.setCidade(cidade);

		model.addAttribute("prefeito", prefeito);
		return "formulario-prefeito";
	}

	@PostMapping("/salvarPrefeito/{idCidade}")
	public String salvarPrefeito(@PathVariable("idCidade") long idCidade,
			@Valid Prefeito prefeito, BindingResult result) {

		Optional<Cidade> optionalCidade = cidadeRepository.findById(idCidade);
		Cidade cidade = optionalCidade.get();
		prefeito.setCidade(cidade);

		if (result.hasErrors()) {
			return "formulario-prefeito";
		}

		prefeitoRepository.save(prefeito);
		return "redirect:/";
	}
}