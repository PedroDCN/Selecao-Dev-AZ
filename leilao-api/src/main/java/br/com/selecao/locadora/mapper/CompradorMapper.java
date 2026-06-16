package br.com.selecao.locadora.mapper;

import br.com.selecao.locadora.dto.request.CompradorRequest;
import br.com.selecao.locadora.dto.response.CompradorResponse;
import br.com.selecao.locadora.entity.Comprador;
import br.com.selecao.locadora.entity.pk.CompradorPK;
import org.springframework.stereotype.Component;

@Component
public class CompradorMapper {

    public Comprador toEntity(CompradorRequest request) {
        Comprador comprador = new Comprador();
        comprador.setId(new CompradorPK(request.getEmpresa(), request.getLeilao()));
        return comprador;
    }

    public CompradorResponse toResponse(Comprador comprador) {
        if (comprador == null) return null;
        return new CompradorResponse(
                comprador.getId().getEmpresa(),
                comprador.getId().getLeilao()
        );
    }
}
