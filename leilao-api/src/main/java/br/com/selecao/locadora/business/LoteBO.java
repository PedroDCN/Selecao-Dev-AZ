package br.com.selecao.locadora.business;

import br.com.selecao.locadora.dto.request.LoteRequest;
import br.com.selecao.locadora.dto.response.LoteResponse;
import br.com.selecao.locadora.entity.Leilao;
import br.com.selecao.locadora.entity.Lote;
import br.com.selecao.locadora.entity.Unidade;
import br.com.selecao.locadora.exception.LeilaoNaoEncontradoException;
import br.com.selecao.locadora.exception.LoteInvalidoException;
import br.com.selecao.locadora.exception.LoteNaoEncontradoException;
import br.com.selecao.locadora.exception.UnidadeNaoEncontradaException;
import br.com.selecao.locadora.mapper.LoteMapper;
import br.com.selecao.locadora.repository.LeilaoRepository;
import br.com.selecao.locadora.repository.LoteRepository;
import br.com.selecao.locadora.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoteBO {

    @Autowired
    private LoteRepository loteRepository;
    @Autowired
    private LeilaoRepository leilaoRepository;
    @Autowired
    private UnidadeRepository unidadeRepository;
    @Autowired
    private LoteMapper loteMapper;

    public List<Lote> buscarTodos() {
        return loteRepository.findAll();
    }

    public LoteResponse buscarPorId(Long id) {
        Lote lote = loteRepository.findById(id).orElseThrow(() -> new LoteNaoEncontradoException(String.format("Lote com id %s não encontrado", id)));
        return loteMapper.toResponse(lote);
    }

    @Transactional
    public LoteResponse salvarLote(LoteRequest request) {
        if (request.getDescricao() == null || request.getDescricao().isBlank()) {
            throw new LoteInvalidoException("Campo descrição não pode ser vazio");
        }

        if (request.getDescricao().length() > 60) {
            throw new LoteInvalidoException("Tamanho de descricao excedido. Máximo de 60 caracteres");
        }

        if (request.getQuantidade() == null) {
            throw new LoteInvalidoException("Campo quantidade não pode ser vazio");
        }

        if (request.getQuantidade().signum() < 0) {
            throw new LoteInvalidoException("Campo quantidade não pode ser negativo");
        }

        if (request.getQuantidade().scale() > 2) {
            throw new LoteInvalidoException("Campo quantidade com escala inválida");
        }

        if (request.getQuantidade().compareTo(new BigDecimal("99999999.99")) > 0) {
            throw new LoteInvalidoException("Campo quantidade não pode ser maior que 99999999.99");
        }

        if (request.getUnidade() == null) {
            throw new LoteInvalidoException("Campo unidade não pode ser vazio");
        }

        if (request.getLeilao() == null) {
            throw new LoteInvalidoException("Campo leilão não pode ser vazio");
        }

        if (request.getNumeroLote() < 0) {
            throw new LoteInvalidoException("Campo número lote não pode ser negativo");
        }

        if (request.getValorInicial().signum() < 0) {
            throw new LoteInvalidoException("Campo valorInicial não pode ser negativo");
        }

        if (request.getValorInicial().scale() > 2) {
            throw new LoteInvalidoException("Campo valorInicial com escala inválida");
        }

        if (request.getValorInicial().compareTo(new BigDecimal("99999999.99")) > 0) {
            throw new LoteInvalidoException("Campo valorInicial não pode ser maior que 99999999.99");
        }

        Leilao leilao = leilaoRepository.findById(request.getLeilao())
                .orElseThrow(() -> new LeilaoNaoEncontradoException(String.format("Leilão com id %s não encontrado", request.getLeilao())));

        Unidade unidade = unidadeRepository.findById(request.getUnidade())
                .orElseThrow(() -> new UnidadeNaoEncontradaException(String.format("Unidade com id %s não encontrado", request.getUnidade())));

        Lote lote = loteMapper.toEntity(request);
        lote.setLeilao(leilao);
        lote.setUnidade(unidade);

        Lote loteSalvo = loteRepository.save(lote);

        return loteMapper.toResponse(loteSalvo);
    }

    @Transactional
    public LoteResponse atualizarLote(Long id, LoteRequest request) {
        Lote lote = loteRepository.findById(id).orElseThrow(
                () -> new LoteNaoEncontradoException(String.format("Lote com id %s não encontrado", id)));

        if (request.getDescricao() == null || request.getDescricao().isBlank()) {
            throw new LoteInvalidoException("Campo descrição não pode ser vazio");
        }

        if (request.getDescricao().length() > 60) {
            throw new LoteInvalidoException("Tamanho de descricao excedido. Máximo de 60 caracteres");
        }

        if (request.getQuantidade() == null) {
            throw new LoteInvalidoException("Campo quantidade não pode ser vazio");
        }

        if (request.getUnidade() == null) {
            throw new LoteInvalidoException("Campo unidade não pode ser vazio");
        }

        if (request.getQuantidade().signum() < 0) {
            throw new LoteInvalidoException("Campo quantidade não pode ser negativo");
        }

        if (request.getQuantidade().scale() > 2) {
            throw new LoteInvalidoException("Campo quantidade com escala inválida");
        }

        if (request.getQuantidade().compareTo(new BigDecimal("99999999.99")) > 0) {
            throw new LoteInvalidoException("Campo quantidade não pode ser maior que 99999999.99");
        }

        if (request.getLeilao() == null) {
            throw new LoteInvalidoException("Campo leilão não pode ser vazio");
        }

        if (request.getNumeroLote() < 0) {
            throw new LoteInvalidoException("Campo número lote não pode ser negativo");
        }

        if (request.getValorInicial().signum() < 0) {
            throw new LoteInvalidoException("Campo valorInicial não pode ser negativo");
        }

        if (request.getValorInicial().scale() > 2) {
            throw new LoteInvalidoException("Campo valorInicial com escala inválida");
        }

        if (request.getValorInicial().compareTo(new BigDecimal("99999999.99")) > 0) {
            throw new LoteInvalidoException("Campo valorInicial não pode ser maior que 99999999.99");
        }


        Leilao leilao = leilaoRepository.findById(request.getLeilao())
                .orElseThrow(() -> new LoteInvalidoException(String.format("Leilão com id %s não encontrado", request.getLeilao())));

        Unidade unidade = unidadeRepository.findById(request.getUnidade())
                .orElseThrow(() -> new UnidadeNaoEncontradaException(String.format("Unidade com id %s não encontrado", request.getUnidade())));

        lote.setNumeroLote(request.getNumeroLote());
        lote.setDescricao(request.getDescricao());
        lote.setQuantidade(request.getQuantidade());
        lote.setValorInicial(request.getValorInicial());
        lote.setLeilao(leilao);
        lote.setUnidade(unidade);

        Lote loteSalvo = loteRepository.save(lote);
        return loteMapper.toResponse(loteSalvo);
    }

    public void deletarLote(Long id) {
        loteRepository.deleteById(id);
    }
}
