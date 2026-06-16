package br.com.selecao.locadora.mapper;

import br.com.selecao.locadora.dto.request.LoteRequest;
import br.com.selecao.locadora.dto.response.LoteResponse;
import br.com.selecao.locadora.entity.Lote;
import org.springframework.stereotype.Component;

@Component
public class LoteMapper {

    public Lote toEntity(LoteRequest request) {
        Lote lote = new Lote();
        lote.setNumeroLote(request.getNumeroLote());
        lote.setDescricao(request.getDescricao());
        lote.setQuantidade(request.getQuantidade());
        lote.setValorInicial(request.getValorInicial());
        return lote;
    }

    public LoteResponse toResponse(Lote lote) {
        if (lote == null) return null;
        return new LoteResponse(
                lote.getId(),
                lote.getNumeroLote(),
                lote.getDescricao(),
                lote.getQuantidade(),
                lote.getValorInicial(),
                lote.getUnidade() != null ? lote.getUnidade().getId() : null,
                lote.getLeilao() != null ? lote.getLeilao().getId() : null,
                lote.getCreatedAt(),
                lote.getUpdatedAt()
                );
    }
}
