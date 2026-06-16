package br.com.selecao.locadora.mapper;

import br.com.selecao.locadora.dto.request.EmpresaRequest;
import br.com.selecao.locadora.dto.response.EmpresaResponse;
import br.com.selecao.locadora.entity.Empresa;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {
    public Empresa toEntity(EmpresaRequest request) {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(request.getRazaoSocial());
        empresa.setCnpj(request.getCnpj());
        empresa.setLogradouro(request.getLogradouro());
        empresa.setMunicipio(request.getMunicipio());
        empresa.setNumero(request.getNumero());
        empresa.setComplemento(request.getComplemento());
        empresa.setBairro(request.getBairro());
        empresa.setEmail(request.getEmail());
        empresa.setSite(request.getSite());
        empresa.setUsuario(request.getUsuario());
        empresa.setSenha(request.getSenha());
        return empresa;
    }

    public EmpresaResponse toResponse(Empresa empresa) {
        if (empresa == null) return null;
        return new EmpresaResponse(
                empresa.getId(),
                empresa.getRazaoSocial(),
                empresa.getCnpj(),
                empresa.getLogradouro(),
                empresa.getMunicipio(),
                empresa.getNumero(),
                empresa.getComplemento(),
                empresa.getBairro(),
                empresa.getEmail(),
                empresa.getSite(),
                empresa.getUsuario(),
                empresa.getSenha(),
                empresa.getCreatedAt(),
                empresa.getUpdatedAt()
        );
    }
}
