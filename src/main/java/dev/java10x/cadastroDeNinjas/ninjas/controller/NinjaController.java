package dev.java10x.cadastroDeNinjas.ninjas.controller;

import dev.java10x.cadastroDeNinjas.ninjas.dtos.NinjaDTO;
import dev.java10x.cadastroDeNinjas.ninjas.model.NinjaModel;
import dev.java10x.cadastroDeNinjas.ninjas.service.NinjaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class NinjaController {
        private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas(){
        return "Essa eh a minha primeira mensagem";
    }

    // Adicionar Ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "A rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = { //Documenta todas as possiveis respostas
            @ApiResponse(responseCode = "201", description = "Ninja Criado com sucesso"), // resposta especifica do 201
            @ApiResponse(responseCode = "400", description = "Erro na criacao do ninja") // Se for errado pode dar 400
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninjaDTO){
        NinjaDTO ninjaDTO1 = ninjaService.criarNinja(ninjaDTO);
        // Cria uma responsa cmo HTTP 201 Create e body da responsa sera uma String com alguns dados
        return ResponseEntity.status(HttpStatus.CREATED).body("Ninja Criado com sucesso: " + ninjaDTO1.getNome() + " ID: " + ninjaDTO1.getId());

    }

    // Mostrar todos os Ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas = ninjaService.listarNinjas(); // retorna o metodo do service que lista
        return ResponseEntity.ok(ninjas);
    }


    // Mostrar Ninja por ID (CREATE)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por ID", description = "A rota lista um ninja por o seu id ")
    @ApiResponses(value = { //Documenta todas as possiveis respostas
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"), // resposta especifica do 201
            @ApiResponse(responseCode = "404", description = "Erro ninja nao encontrado") // Se for errado pode dar 400
    })
    public ResponseEntity<?> mostrarTodosOsNinjasPorId(@PathVariable Long id){
            NinjaDTO ninjaDTO = ninjaService.listarNinjaPorId(id); // pega entidade com o ID
            if(ninjaDTO != null){ // se o ninja do ID nao foi null
                return ResponseEntity.ok(ninjaDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com esse ID " + id + " Nao existe no nosso registro");
            }

    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Altera ninja por id", description = "A rota altera o ninja por id")
    @ApiResponses(value = { //Documenta todas as possiveis respostas
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"), // resposta especifica do 201
            @ApiResponse(responseCode = "400", description = "Erro ao alterar o ninja") // Se for errado pode dar 400
    })
    public ResponseEntity<String> alterarNinjasPorId(@Parameter(description = "Usuario manda o id no caminho da requisicao") @PathVariable Long id, //Pode ser usado o swagger tbm no parametro, documentando o que vai chegar
                                                     @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Usuário manda os dados do ninja a ser atualizado", required = true) @RequestBody NinjaDTO ninjaAtualizado){ // essa classe documenta o  @RequestBody
        NinjaDTO ninjaDTO = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if(ninjaDTO != null){
            return ResponseEntity.ok("Alteracao feita com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alteracao interrompida, id nao encontrado");
        }
    }
    // Deletar Ninja (DELETE) - Sempre void
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjasPorId(@PathVariable Long id){
        if(ninjaService.listarNinjaPorId(id) != null){ // Primeiro verifica se o ID existe
            ninjaService.deletar(id); // Deleta a entidade do ID
            return ResponseEntity.ok("Ninja " + id + " deletado com sucesso"); // retorna OK
        } else { // Se nao existir, da um 404 NOT FOUND
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" O ninja com ID "+ id + " Nao encontrado");
        }
    }
}
