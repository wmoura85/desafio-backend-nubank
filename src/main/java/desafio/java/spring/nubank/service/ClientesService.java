package desafio.java.spring.nubank.service;

import desafio.java.spring.nubank.dto.ClientesDTO;
import desafio.java.spring.nubank.dto.ClientesResponseDTO;
import desafio.java.spring.nubank.dto.ContatoResponseDTO;
import desafio.java.spring.nubank.model.Clientes;
import desafio.java.spring.nubank.model.Contato;
import desafio.java.spring.nubank.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    public Clientes salvarCliente(ClientesDTO dto) {
        Clientes clientes = new Clientes();
        clientes.setNome(dto.getNome());

        if (dto.getContatos() != null && dto.getContatos().size() > 0) {
            List<Contato> contatos = dto.getContatos().stream().map(c ->{
                Contato contato = new Contato();
                contato.setTel(c.getTel());
                contato.setEmail(c.getEmail());
                contato.setClientes(clientes);
                return contato;
            } ).collect(Collectors.toList());
            clientes.setContatos(contatos);
        }
        return clientesRepository.save(clientes);
    }
    public List<ClientesResponseDTO> listarTodos(){
        return clientesRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ContatoResponseDTO> çistarContatosPorID(Long clienteId) {
        Clientes cliente = clientesRepository.findById(clienteId)
                           .orElseThrow() -> new RuntimeException("Cliente não encontrado!");
        return cliente.getContatos().stream().map(c -> {
         ContatoResponseDTO dto = new ContatoResponseDTO();
         dto.setId(c.getId());
         dto.setTel(c.getTel());
         dto.setEmail(c.getEmail());
         return dto;
        }).collect(Collectors.toList());
    }

    public List<ClientesResponseDTO> toDTO(Clientes cliente) {
        ClientesResponseDTO dto = new ClientesResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());

        List<ContatoResponseDTO> contatos = cliente.getContatos().stream().map(c -> {
            ContatoResponseDTO contatoDTO = new ContatoResponseDTO();
            contatoDTO.setId(c.getId());
            contatoDTO.setTel(c.getTel());
            contatoDTO.setEmail(c.getEmail());
            return contatoDTO;
        }).collect(Collectors.toList());
        dto.setContatos(contatos);

        return dto;
    }
}
