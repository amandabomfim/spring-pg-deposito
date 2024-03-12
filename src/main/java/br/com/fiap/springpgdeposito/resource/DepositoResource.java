package br.com.fiap.springpgdeposito.resource;


import br.com.fiap.springpgdeposito.entity.Deposito;
import br.com.fiap.springpgdeposito.repository.DepositoRepository;
import br.com.fiap.springpgdeposito.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
@RestController
@RequestMapping(value = "/deposito")
public class DepositoResource {
    @Autowired
    private DepositoRepository repo;

    @Autowired
    private EnderecoRepository repoEndereco;

    @GetMapping
    public ResponseEntity<List<Deposito>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Deposito> findById(@PathVariable Long id) {


        var deposito = repo.findById(id);


        if (deposito.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(deposito.get());

    }

    @Transactional
    @PostMapping
    public ResponseEntity<Deposito> save(@RequestBody Deposito deposito) {


        if (Objects.nonNull(deposito.getEndereco().getId())) {


            var endereco = repoEndereco.findById(deposito.getEndereco().getId());


            if (endereco.isEmpty()) return ResponseEntity.badRequest().build();

            deposito.setEndereco(endereco.get());
        }

        Deposito save = repo.save(deposito);


        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();

        return ResponseEntity.created(uri).body(save);
    }

}

