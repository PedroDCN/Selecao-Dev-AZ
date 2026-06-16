package br.com.selecao.locadora.business;

import br.com.selecao.locadora.dto.request.EmpresaRequest;
import br.com.selecao.locadora.dto.response.EmpresaResponse;
import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.entity.Unidade;
import br.com.selecao.locadora.exception.EmpresaInvalidaException;
import br.com.selecao.locadora.exception.EmpresaNaoEncontradaException;
import br.com.selecao.locadora.mapper.EmpresaMapper;
import br.com.selecao.locadora.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaBO {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private EmpresaMapper empresaMapper;

    public List<Empresa> buscarTodos() {
        return empresaRepository.findAll();
    }

    public EmpresaResponse buscarPorId(Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);

        if (empresa.isEmpty()) {
            throw new EmpresaNaoEncontradaException((String.format("Empresa com id %s não encontra", id)));
        }

        return empresaMapper.toResponse(empresa.get());
    }

    public EmpresaResponse buscarPorCnpj(String cnpj) {
        Optional<Empresa> empresa = empresaRepository.findByCnpj(cnpj);

        if (empresa.isEmpty()) {
            throw new EmpresaNaoEncontradaException(String.format("Empresa com cnpj %s não encontra", cnpj));
        }

        return empresaMapper.toResponse(empresa.get());
    }

    public EmpresaResponse buscarPorUsuario(String usuario) {
        Optional<Empresa> empresa = empresaRepository.findByUsuario(usuario);

        if (empresa.isEmpty()) {
            throw new EmpresaNaoEncontradaException(String.format("Empresa com usuário %s não encontrada", usuario));
        }

        return empresaMapper.toResponse(empresa.get());
    }

    @Transactional
    public EmpresaResponse salvarEmpresa(EmpresaRequest request) {
        // validar campos do request se estão vazios
        if (request.getCnpj() == null || request.getCnpj().isBlank()) {
            throw new EmpresaInvalidaException("Campo cnpj não pode ser vazio");
        }

        if (request.getRazaoSocial() == null || request.getRazaoSocial().isBlank()) {
            throw new EmpresaInvalidaException("Campo razão social não pode ser vazio");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new EmpresaInvalidaException("Campo email não pode ser vazio");
        }

        if (request.getUsuario() == null || request.getUsuario().isBlank()) {
            throw new EmpresaInvalidaException("Campo usuário não pode ser vazio");
        }

        // validar se cnpj ou usuario já existem (campos unique)
        if (empresaRepository.findByCnpj(request.getCnpj()).isPresent()) {
            throw new EmpresaInvalidaException(String.format("Empresa com cnpj %s já existente", request.getCnpj()));
        }

        if (empresaRepository.findByUsuario(request.getUsuario()).isPresent()) {
            throw new EmpresaInvalidaException(String.format("Empresa com usuário %s já existente", request.getUsuario()));
        }

        Empresa empresaSalva = empresaRepository.save(empresaMapper.toEntity(request));
        return empresaMapper.toResponse(empresaSalva);
    }

    @Transactional
    public EmpresaResponse atualizarEmpresa(Long id, EmpresaRequest request) {
        Optional<Empresa> empresa = empresaRepository.findById(id);

        if (empresa.isEmpty()) {
            throw new EmpresaNaoEncontradaException((String.format("Empresa com id %s não encontra", id)));
        }

        // validar campos do request se estão vazios
        if (request.getCnpj() == null || request.getCnpj().isBlank()) {
            throw new EmpresaInvalidaException("Campo cnpj não pode ser vazio");
        }

        if (request.getRazaoSocial() == null || request.getRazaoSocial().isBlank()) {
            throw new EmpresaInvalidaException("Campo razão social não pode ser vazio");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new EmpresaInvalidaException("Campo email não pode ser vazio");
        }

        if (request.getUsuario() == null || request.getUsuario().isBlank()) {
            throw new EmpresaInvalidaException("Campo usuário não pode ser vazio");
        }

        // validar se cnpj ou usuario já existem (campos unique)
        if (!empresa.get().getCnpj().equals(request.getCnpj()) && empresaRepository.findByCnpj(request.getCnpj()).isPresent()) {
            throw new EmpresaInvalidaException(String.format("Empresa com cnpj %s já existente", request.getCnpj()));
        }

        if (!empresa.get().getUsuario().equals(request.getUsuario()) && empresaRepository.findByUsuario(request.getUsuario()).isPresent()) {
            throw new EmpresaInvalidaException(String.format("Empresa com usuário %s já existente", request.getUsuario()));
        }

        Empresa empresaSalvar = getEmpresaSalvar(request, empresa.get());

        Empresa empresaSalva =  empresaRepository.save(empresaSalvar);
        return empresaMapper.toResponse(empresaSalva);
    }

    private Empresa getEmpresaSalvar(EmpresaRequest request, Empresa empresaSalvar) {
        empresaSalvar.setRazaoSocial(request.getRazaoSocial());
        empresaSalvar.setCnpj(request.getCnpj());
        empresaSalvar.setLogradouro(request.getLogradouro());
        empresaSalvar.setMunicipio(request.getMunicipio());
        empresaSalvar.setNumero(request.getNumero());
        empresaSalvar.setComplemento(request.getComplemento());
        empresaSalvar.setBairro(request.getBairro());
        empresaSalvar.setEmail(request.getEmail());
        empresaSalvar.setSite(request.getSite());
        empresaSalvar.setUsuario(request.getUsuario());
        empresaSalvar.setSenha(request.getSenha());
        return empresaSalvar;
    }

    public void deletarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }
}
