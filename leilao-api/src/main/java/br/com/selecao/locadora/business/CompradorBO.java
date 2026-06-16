package br.com.selecao.locadora.business;

import br.com.selecao.locadora.dto.request.CompradorRequest;
import br.com.selecao.locadora.dto.response.CompradorResponse;
import br.com.selecao.locadora.entity.Comprador;
import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.entity.Leilao;
import br.com.selecao.locadora.entity.pk.CompradorPK;
import br.com.selecao.locadora.exception.CompradorInvalidoException;
import br.com.selecao.locadora.exception.CompradorNaoEncontradoException;
import br.com.selecao.locadora.exception.EmpresaNaoEncontradaException;
import br.com.selecao.locadora.exception.LeilaoNaoEncontradoException;
import br.com.selecao.locadora.mapper.CompradorMapper;
import br.com.selecao.locadora.repository.CompradorRepository;
import br.com.selecao.locadora.repository.EmpresaRepository;
import br.com.selecao.locadora.repository.LeilaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompradorBO {

    @Autowired
    private CompradorRepository compradorRepository;
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private LeilaoRepository leilaoRepository;
    @Autowired
    private CompradorMapper compradorMapper;

    public List<Comprador> buscarTodos() {
        return compradorRepository.findAll();
    }

    public CompradorResponse buscarPorId(Long empresaId, Long leilaoId) {
        Comprador comprador = compradorRepository.findById(new CompradorPK(empresaId, leilaoId)).orElseThrow(
                () -> new CompradorNaoEncontradoException("Comprador nao encontrado")
        );
        return compradorMapper.toResponse(comprador);
    }

    @Transactional
    public CompradorResponse salvarComprador(CompradorRequest request) {
        if (request.getEmpresa() == null) {
            throw new CompradorInvalidoException("Campo empresa não pode ser vazio");
        }

        if (request.getLeilao() == null) {
            throw new CompradorInvalidoException("Campo leilão não pode ser vazio");
        }

        Empresa empresa = empresaRepository.findById(request.getEmpresa()).orElseThrow(
                () -> new EmpresaNaoEncontradaException(String.format("Empresa com id %s não encontrada", request.getEmpresa()))
        );

        Leilao leilao = leilaoRepository.findById(request.getLeilao()).orElseThrow(
                () -> new LeilaoNaoEncontradoException(String.format("Leilão com id %s não encontrado", request.getLeilao()))
        );

        Comprador comprador = compradorMapper.toEntity(request);
        comprador.setEmpresa(empresa);
        comprador.setLeilao(leilao);

        Comprador compradorSalvo = compradorRepository.save(comprador);

        return compradorMapper.toResponse(compradorSalvo);
    }

    @Transactional
    public CompradorResponse atualizarComprador(Long idEmpresa, Long idComprador, CompradorRequest request) {
        if (request.getEmpresa() == null) {
            throw new CompradorInvalidoException("Campo empresa não pode ser vazio");
        }

        if (request.getLeilao() == null) {
            throw new CompradorInvalidoException("Campo leilão não pode ser vazio");
        }

        Comprador comprador = compradorRepository.findById(new CompradorPK(idEmpresa, idComprador)).orElseThrow(
                () -> new CompradorNaoEncontradoException("Comprador nao encontrado")
        );

        Empresa empresa = empresaRepository.findById(request.getEmpresa()).orElseThrow(
                () -> new EmpresaNaoEncontradaException(String.format("Empresa com id %s não encontrada", request.getEmpresa()))
        );

        Leilao leilao = leilaoRepository.findById(request.getLeilao()).orElseThrow(
                () -> new LeilaoNaoEncontradoException(String.format("Leilão com id %s não encontrado", request.getLeilao()))
        );

        comprador.setEmpresa(empresa);
        comprador.setLeilao(leilao);

        Comprador compradorSalvo = compradorRepository.save(comprador);

        return compradorMapper.toResponse(compradorSalvo);

    }

    public void deletarComprador(Long empresaId, Long leilaoId) {
        compradorRepository.deleteById(new CompradorPK(empresaId, leilaoId));
    }
}
