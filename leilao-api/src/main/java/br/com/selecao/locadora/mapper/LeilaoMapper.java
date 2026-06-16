package br.com.selecao.locadora.mapper;

import br.com.selecao.locadora.dto.request.LeilaoRequest;
import br.com.selecao.locadora.dto.response.LeilaoResponse;
import br.com.selecao.locadora.entity.Leilao;
import org.springframework.stereotype.Component;

@Component
public class LeilaoMapper {

    public Leilao toEntity(LeilaoRequest request) {
        Leilao leilao = new Leilao();
        leilao.setCodigo(request.getCodigo());
        leilao.setDescricao(request.getDescricao());
        leilao.setVendedor(request.getVendedor());
        leilao.setInicioPrevisto(request.getInicioPrevisto());
        return leilao;
    }

    public LeilaoResponse toResponse(Leilao leilao) {
        if (leilao == null) return null;
        return new LeilaoResponse(
                leilao.getId(),
                leilao.getCodigo(),
                leilao.getDescricao(),
                leilao.getVendedor(),
                leilao.getInicioPrevisto(),
                leilao.getCreatedAt(),
                leilao.getUpdatedAt()
        );
    }
}
