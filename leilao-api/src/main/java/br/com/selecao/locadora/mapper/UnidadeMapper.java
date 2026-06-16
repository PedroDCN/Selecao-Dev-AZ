package br.com.selecao.locadora.mapper;

import br.com.selecao.locadora.dto.request.UnidadeRequest;
import br.com.selecao.locadora.dto.response.UnidadeResponse;
import br.com.selecao.locadora.entity.Unidade;
import org.springframework.stereotype.Component;

@Component
public class UnidadeMapper {
    public Unidade toEntity(UnidadeRequest request) {
        Unidade unidade = new Unidade();
        unidade.setNome(request.getNome());
        return unidade;
    }

    public UnidadeResponse toResponse(Unidade unidade) {
        if (unidade == null) return null;
        return new UnidadeResponse(
                unidade.getId(),
                unidade.getNome(),
                unidade.getCreatedAt(),
                unidade.getUpdatedAt()
        );
    }
}
