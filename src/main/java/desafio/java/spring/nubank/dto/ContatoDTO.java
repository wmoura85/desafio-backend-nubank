package desafio.java.spring.nubank.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContatoDTO {

    private String tel;
    private String email;
    private Long clienteId;
}
