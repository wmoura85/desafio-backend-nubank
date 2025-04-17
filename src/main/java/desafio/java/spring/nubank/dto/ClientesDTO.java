package desafio.java.spring.nubank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientesDTO {

    private String nome;
    private List<ContatoDTO> contatos;
}
