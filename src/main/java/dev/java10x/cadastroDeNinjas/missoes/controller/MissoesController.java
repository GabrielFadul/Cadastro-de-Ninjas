package dev.java10x.cadastroDeNinjas.missoes.controller;

import dev.java10x.cadastroDeNinjas.missoes.dtos.MissoesDTO;
import dev.java10x.cadastroDeNinjas.missoes.service.MissoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @PostMapping("/criar") // Metodo HTTP para "postar" algo no banco de dados
    @Operation(summary = "Cria uma nova missao", description = "A rota cria uma nova missao para o banco de dados") // Annotation do Swagger para dar um resumo e descricao
    @ApiResponses({ // Documenta Possiveis respostas
            @ApiResponse(responseCode = "201", description = "Ninja Criado Com sucesso"), // Documenta resposta especifica do 201
            @ApiResponse(responseCode = "400", description = "Erro ao criar um novo Missoes") // Documenta resposta especifica do 201
    })
    public ResponseEntity<String> criar(@RequestBody MissoesDTO missoesDTO){ // RequestBody  serializa para o DTO
        MissoesDTO missoesDTO1 = missoesService.criar(missoesDTO); // Crio um DTO e uso o Service Para fazer logica para converter DTO -> Model e criar
        return ResponseEntity.status(HttpStatus.CREATED).body("Missao Criada"); // Resposta em String da API se tudo ocorrer
    }

    // Mostrar todas as Missoes (READ)
    @GetMapping("/listar")
    @Operation(summary = "Retorna todas as missoes", description = "A rota retorna uma LISTA de missoes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Lista nao encontrada")
    })
    public ResponseEntity<List<MissoesDTO>> mostrarTodasAsMissoes(){ // Espero que a lista retorne na resposta
        List<MissoesDTO> lista = missoesService.listar(); // chama o service e guarda o retorno em uma lista
        return ResponseEntity.ok(lista); // usa a lista como retorno
    }


    // Mostrar Missao por ID (CREATE)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Retorna uma missao por ID", description = "A rota retorna uma missao quando usuario envia um ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Missao encontrada"),
            @ApiResponse(responseCode = "404", description = "Missao nao encontrada")

    })
    public ResponseEntity<MissoesDTO> buscarPorId(@Parameter(description = "Parametro esperado um ID de missao") @PathVariable Long id){
        MissoesDTO missoesDTO = missoesService.buscarPorId(id);
        return ResponseEntity.ok(missoesDTO);
    }

    // Alterar dados dos Missoes (UPDATE)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "atualiza uma missao", description = "Essa rota manda dados para atualizar uma missao no banco de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Missao atualizada"),
            @ApiResponse(responseCode = "404", description = "Missao nao encontrada")
    })
    public ResponseEntity<String> alterarMissoesPorId(@PathVariable Long id, @RequestBody MissoesDTO missoesDTO){
        MissoesDTO missoesDTO1 = missoesService.update(id, missoesDTO); // Faco a operacao
        if(missoesDTO1 != null){ // se existir responde ok
            return ResponseEntity.ok("Dados atualizado");
        } else { // Se vier NULL retorna 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missao nao encontrada");
        }
    }



    // Deletar Misseso (DELETE)
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta uma missao por ID", description = "Uma rota que deleta uma missao no banco de dados usando o ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Delecao bem sucedida"),
            @ApiResponse(responseCode = "404", description = "Delecao mal sucedida")
    })
    public ResponseEntity<Void> deletarMissoesPorId(@PathVariable Long id){
        if(missoesService.buscarPorId(id) != null){
            missoesService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




}
