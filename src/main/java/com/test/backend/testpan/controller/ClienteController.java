package com.test.backend.testpan.controller;

import com.test.backend.testpan.dto.EnderecoDTO;
import com.test.backend.testpan.model.Cliente;
import com.test.backend.testpan.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Endpoints para busca de dados de clientes
 *
 * @author doareis@gmail.com
 * @since Março 2019
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping(value = "/busca/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getCliente(@PathVariable("cpf") String cpf){
        Optional<Cliente> clienteOptional = clienteRepository.findByCpf(cpf);
        return clienteOptional.isPresent()
            ? ResponseEntity.ok(clienteOptional.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/busca", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }

    @PostMapping(value = "altera/endereco/{idCliente}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity alteraEnderecoCliente(@PathVariable("idCliente") long idCliente,
                                                @RequestBody EnderecoDTO enderecoDTO) {

        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        if (!clienteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Cliente cliente = clienteOptional.get();
        cliente.setEndereco(enderecoDTO.getLogradouro());
        cliente.setEstado(enderecoDTO.getUf());
        cliente.setMunicipio(enderecoDTO.getLocalidade());

        clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }
}
