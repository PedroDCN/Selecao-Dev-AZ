package br.com.selecao.locadora.business;

import br.com.selecao.locadora.dto.request.LeilaoRequest;
import br.com.selecao.locadora.dto.response.LeilaoListResponse;
import br.com.selecao.locadora.dto.response.LeilaoResponse;
import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.entity.Leilao;
import br.com.selecao.locadora.exception.EmpresaNaoEncontradaException;
import br.com.selecao.locadora.exception.LeilaoInvalidoException;
import br.com.selecao.locadora.exception.LeilaoNaoEncontradoException;
import br.com.selecao.locadora.mapper.LeilaoMapper;
import br.com.selecao.locadora.repository.EmpresaRepository;
import br.com.selecao.locadora.repository.LeilaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeilaoBO {

    @Autowired
    private LeilaoRepository leilaoRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private LeilaoMapper leilaoMapper;

    public List<Leilao> buscarTodos() {
        return leilaoRepository.findAll();
    };

    public List<LeilaoListResponse> buscarTodosParaListagem() {
        return leilaoRepository.findAll().stream().map(this::toListResponse).collect(Collectors.toList());
    }

    private LeilaoListResponse toListResponse(Leilao leilao) {
        BigDecimal valorTotal = leilao.getLotes()
                                .stream()
                .map(lote -> lote.getValorInicial().multiply(lote.getQuantidade()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new LeilaoListResponse(
                leilao.getId(),
                leilao.getVendedor().getRazaoSocial(),
                leilao.getInicioPrevisto(),
                valorTotal
        );
    }

    public LeilaoResponse buscarPorId(Long id) {
        Leilao leilao = leilaoRepository.findById(id).orElseThrow(() -> new LeilaoNaoEncontradoException(String.format("Leilao com id %s não encontrado", id)));
        return leilaoMapper.toResponse(leilao);
    }

    @Transactional
    public LeilaoResponse salvarLeilao(LeilaoRequest request) {
        if (request.getVendedor() == null) {
            throw new LeilaoInvalidoException("Campo vendedor não pode ser vazio");
        }
        if (request.getDescricao() == null || request.getDescricao().isBlank()) {
            throw new LeilaoInvalidoException("Campo descrição não pode ser vazio");
        }
        if (request.getInicioPrevisto() == null) {
            throw new LeilaoInvalidoException("Campo início previsto não pode ser vazio");
        }

        if (request.getDescricao().length() > 60) {
            throw new LeilaoInvalidoException(("Tamanho de descricao excedido. Máximo de 60 caracteres"));
        }

        // não pode salvar um leilão que aconteceu antes de hoje
        if (request.getInicioPrevisto().isBefore(LocalDateTime.now())) {
            throw new LeilaoInvalidoException("Leilão já se iniciou");
        }

        Empresa vendedor = empresaRepository.findById(request.getVendedor())
                .orElseThrow(() -> new EmpresaNaoEncontradaException(String.format("Empresa com id %s não encontrado", request.getVendedor())));

        Leilao leilao = leilaoMapper.toEntity(request);
        leilao.setVendedor(vendedor);

        Leilao leilaoSalvo = leilaoRepository.save(leilao);
        return leilaoMapper.toResponse(leilaoSalvo);
    }

    @Transactional
    public LeilaoResponse atualizarLeilao(Long id, LeilaoRequest request) {
        Leilao leilao = leilaoRepository.findById(id).orElseThrow(
                () -> new LeilaoNaoEncontradoException(String.format("Leilao com id %s nao encontrado", id))
        );

        if (request.getVendedor() == null) {
            throw new LeilaoInvalidoException("Campo vendedor não pode ser vazio");
        }
        if (request.getDescricao() == null || request.getDescricao().isBlank()) {
            throw new LeilaoInvalidoException("Campo descrição não pode ser vazio");
        }
        if (request.getInicioPrevisto() == null) {
            throw new LeilaoInvalidoException("Campo início previsto não pode ser vazio");
        }

        if (request.getDescricao().length() > 60) {
            throw new LeilaoInvalidoException(("Tamanho de descricao excedido. Máximo de 60 caracteres"));
        }

        // não pode salvar um leilão que aconteceu antes de hoje
        if (request.getInicioPrevisto().isBefore(LocalDateTime.now())) {
            throw new LeilaoInvalidoException("Leilão já se iniciou");
        }

        Empresa vendedor = empresaRepository.findById(request.getVendedor())
                .orElseThrow(() -> new EmpresaNaoEncontradaException(String.format("Empresa com id %s não encontrado", request.getVendedor())));


        leilao.setVendedor(vendedor);
        leilao.setDescricao(request.getDescricao());
        leilao.setInicioPrevisto(request.getInicioPrevisto());

        Leilao leilaoSalvo = leilaoRepository.save(leilao);
        return leilaoMapper.toResponse(leilaoSalvo);
    }

    public void deletarLeilao(Long id) {
        leilaoRepository.deleteById(id);
    }
}
