package br.com.selecao.locadora.business;

import br.com.selecao.locadora.dto.request.EmpresaRequest;
import br.com.selecao.locadora.dto.response.EmpresaResponse;
import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.exception.EmpresaInvalidaException;
import br.com.selecao.locadora.exception.EmpresaNaoEncontradaException;
import br.com.selecao.locadora.mapper.EmpresaMapper;
import br.com.selecao.locadora.repository.EmpresaRepository;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class EmpresaBO {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private EmpresaMapper empresaMapper;

    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$"
    );

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

        if (request.getCnpj().length() > 32) {
            throw new EmpresaInvalidaException("Tamanho de cnpj excedido. Máximo de 32 caracteres");
        }

        if (request.getRazaoSocial() == null || request.getRazaoSocial().isBlank()) {
            throw new EmpresaInvalidaException("Campo razão social não pode ser vazio");
        }

        if (request.getRazaoSocial().length() > 64) {
            throw new EmpresaInvalidaException("Tamanho de razaoSocial excedido. Máximo de 64 caracteres");
        }

        if (request.getLogradouro().length() > 64) {
            throw new EmpresaInvalidaException("Tamanho de logradouro excedido. Máximo de 64 caracteres");
        }

        if (request.getMunicipio().length() > 64) {
            throw new EmpresaInvalidaException("Tamanho de municipio excedido. Máximo de 64 caracteres");
        }

        if (request.getComplemento().length() > 64) {
            throw new EmpresaInvalidaException("Tamanho de complemento excedido. Máximo de 64 caracteres");
        }

        if (request.getBairro().length() > 64) {
            throw new EmpresaInvalidaException("Tamanho de bairro excedido. Máximo de 64 caracteres");
        }

        if (request.getTelefone().length() > 32) {
            throw new EmpresaInvalidaException("Tamanho de telefone excedido. Máximo de 32 caracteres");
        }

        if (!isValidTelefone(request.getTelefone())) {
            throw new EmpresaInvalidaException("Formato de telefone inválido");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new EmpresaInvalidaException("Campo email não pode ser vazio");
        }

        if (request.getEmail().length() > 254) {
            throw new EmpresaInvalidaException("Tamanho de email excedido. Máximo de 254 caracteres");
        }

        if (!isValidEmail(request.getEmail())) {
            throw new EmpresaInvalidaException("Formato de email inválido");
        }

        if (request.getSite().length() > 254) {
            throw new EmpresaInvalidaException("Tamanho de site excedido. Máximo de 254 caracteres");
        }

        if (!isvalidUrl(request.getSite())) {
            throw new EmpresaInvalidaException("Formato de site inválido");
        }

        if (request.getUsuario() == null || request.getUsuario().isBlank()) {
            throw new EmpresaInvalidaException("Campo usuário não pode ser vazio");
        }

        if (request.getUsuario().length() > 20) {
            throw new EmpresaInvalidaException("Tamanho de usuario excedido. Máximo de 20 caracteres");
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

        if (request.getCnpj().length() > 32) {
            throw new EmpresaInvalidaException("Tamanho de cnpj excedido. Máximo de 32 caracteres");
        }

        if (request.getRazaoSocial() == null || request.getRazaoSocial().isBlank()) {
            throw new EmpresaInvalidaException("Campo razão social não pode ser vazio");
        }

        if (request.getRazaoSocial().length() > 64) {
            throw new EmpresaInvalidaException("Tamanho de razaoSocial excedido. Máximo de 64 caracteres");
        }

        if (request.getLogradouro().length() > 64) {
            throw new EmpresaInvalidaException("Tamanho de logradouro excedido. Máximo de 64 caracteres");
        }

        if (request.getMunicipio().length() > 64) {
            throw new EmpresaInvalidaException("Tamanho de municipio excedido. Máximo de 64 caracteres");
        }

        if (request.getComplemento().length() > 64) {
            throw new EmpresaInvalidaException("Tamanho de complemento excedido. Máximo de 64 caracteres");
        }

        if (request.getBairro().length() > 64) {
            throw new EmpresaInvalidaException("Tamanho de bairro excedido. Máximo de 64 caracteres");
        }

        if (request.getTelefone().length() > 32) {
            throw new EmpresaInvalidaException("Tamanho de telefone excedido. Máximo de 32 caracteres");
        }

        if (!isValidTelefone(request.getTelefone())) {
            throw new EmpresaInvalidaException("Formato de telefone inválido");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new EmpresaInvalidaException("Campo email não pode ser vazio");
        }

        if (request.getEmail().length() > 254) {
            throw new EmpresaInvalidaException("Tamanho de email excedido. Máximo de 254 caracteres");
        }

        if (!isValidEmail(request.getEmail())) {
            throw new EmpresaInvalidaException("Formato de email inválido");
        }

        if (request.getSite().length() > 254) {
            throw new EmpresaInvalidaException("Tamanho de site excedido. Máximo de 254 caracteres");
        }

        if (!isvalidUrl(request.getSite())) {
            throw new EmpresaInvalidaException("Formato de site inválido");
        }

        if (request.getUsuario() == null || request.getUsuario().isBlank()) {
            throw new EmpresaInvalidaException("Campo usuário não pode ser vazio");
        }

        if (request.getUsuario().length() > 20) {
            throw new EmpresaInvalidaException("Tamanho de usuario excedido. Máximo de 20 caracteres");
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
        empresaSalvar.setTelefone(request.getTelefone());
        empresaSalvar.setEmail(request.getEmail());
        empresaSalvar.setSite(request.getSite());
        empresaSalvar.setUsuario(request.getUsuario());
        empresaSalvar.setSenha(request.getSenha());
        return empresaSalvar;
    }

    public void deletarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

    private static boolean isValidEmail(String email) {
        if (email == null) return false;
        return EMAIL_PATTERN.matcher(email).matches();

    }

    private static boolean isvalidUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException ex) {
            return false;
        }
    }

    private static boolean isValidTelefone(String telefone) {
        try {
            PhoneNumber phoneNumber = phoneUtil.parse(telefone, "BR");
            return phoneUtil.isValidNumber(phoneNumber);
        } catch (NumberParseException ex) {
            return false;
        }
    }
}
