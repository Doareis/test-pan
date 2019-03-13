package com.test.backend.testpan.controller;

import static org.apache.logging.log4j.LogManager.getLogger;

import com.test.backend.testpan.dto.EnderecoDTO;
import com.test.backend.testpan.model.Cliente;
import com.test.backend.testpan.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints para busca de dados de clientes
 *
 * @author doareis@gmail.com
 * @since Março 2019
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private static final Logger LOGGER = getLogger(ClienteController.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping(value = "/busca/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCliente(@PathVariable("cpf") String cpf){
        LOGGER.debug("Buscando cliente com CFP {}", cpf);

        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        if(clienteOptional.isPresent()) {
            return ResponseEntity.ok(clienteOptional.get());
        } else {
            LOGGER.warn("Cliente com CPF {} não encontrado", cpf);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/busca", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getClientes(){
        LOGGER.debug("Buscando lista de clientes");
        return clienteRepository.findAll();
    }

    @PostMapping(value = "altera/endereco/{idCliente}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity alteraEnderecoCliente(@PathVariable("idCliente") long idCliente,
                                                @RequestBody EnderecoDTO enderecoDTO) {

        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        if (!clienteOptional.isPresent()) {
            LOGGER.warn("Cliente com ID {} não encontrado", idCliente);
            return ResponseEntity.notFound().build();
        }

        Cliente cliente = clienteOptional.get();
        LOGGER.debug("Alterando endereço do cliente {}", cliente.getNome());

        cliente.setEndereco(enderecoDTO.getLogradouro());
        cliente.setEstado(enderecoDTO.getUf());
        cliente.setMunicipio(enderecoDTO.getLocalidade());

        clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }
}
