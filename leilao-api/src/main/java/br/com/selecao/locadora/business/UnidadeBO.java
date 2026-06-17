package br.com.selecao.locadora.business;

import br.com.selecao.locadora.dto.request.UnidadeRequest;
import br.com.selecao.locadora.dto.response.UnidadeResponse;
import br.com.selecao.locadora.entity.Unidade;
import br.com.selecao.locadora.exception.UnidadeInvalidaException;
import br.com.selecao.locadora.exception.UnidadeNaoEncontradaException;
import br.com.selecao.locadora.mapper.UnidadeMapper;
import br.com.selecao.locadora.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeBO {

    @Autowired
    private UnidadeRepository unidadeRepository;
    @Autowired
    private UnidadeMapper unidadeMapper;

    public List<Unidade> buscarTodos(){
        return unidadeRepository.findAll();
    }

    public UnidadeResponse buscarPorId(Long id) {
        Optional<Unidade> unidade = unidadeRepository.findById(id);

        if (unidade.isEmpty()) {
            throw new UnidadeNaoEncontradaException((String.format("Unidade com id %s não encontra", id)));
        }

        return unidadeMapper.toResponse(unidade.get());
    }

    @Transactional
    public UnidadeResponse salvarUnidade(UnidadeRequest request) {
        if (request.getNome() == null || request.getNome().isBlank()) {
            throw new UnidadeInvalidaException("Campo nome não pode ser vazio");
        }

        if (request.getNome().length() > 128) {
            throw new UnidadeInvalidaException("Tamanho de nome excedido. Máximo 128 caracteres");
        }

        Unidade unidadeSalva = unidadeRepository.save(unidadeMapper.toEntity(request));
        return unidadeMapper.toResponse(unidadeSalva);
    }

    @Transactional
    public UnidadeResponse atualizarUnidade(Long id, UnidadeRequest request) {
        Optional<Unidade> unidade = unidadeRepository.findById(id);

        if (unidade.isEmpty()) {
            throw new UnidadeNaoEncontradaException((String.format("Unidade com id %s não encontra", id)));
        }

        if (request.getNome() == null || request.getNome().isBlank()) {
            throw new UnidadeInvalidaException("Campo nome não pode ser vazio");
        }

        if (request.getNome().length() > 128) {
            throw new UnidadeInvalidaException("Tamanho de nome excedido. Máximo 128 caracteres");
        }

        Unidade unidadeAtualizar = unidade.get();
        unidadeAtualizar.setNome(request.getNome());

        Unidade unidadeSalva = unidadeRepository.save(unidadeAtualizar);
        return unidadeMapper.toResponse(unidadeSalva);
    }

    public void deletarUnidade(Long id) {
        unidadeRepository.deleteById(id);
    }
}
