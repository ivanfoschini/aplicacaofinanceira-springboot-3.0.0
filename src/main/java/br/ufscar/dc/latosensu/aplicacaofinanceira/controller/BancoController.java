package br.ufscar.dc.latosensu.aplicacaofinanceira.controller;

import br.ufscar.dc.latosensu.aplicacaofinanceira.exception.NotEmptyCollectionException;
import br.ufscar.dc.latosensu.aplicacaofinanceira.exception.NotFoundException;
import br.ufscar.dc.latosensu.aplicacaofinanceira.exception.NotUniqueException;
import br.ufscar.dc.latosensu.aplicacaofinanceira.model.Banco;
import br.ufscar.dc.latosensu.aplicacaofinanceira.service.BancoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/banco")
@Tag(name = "Banco")
@ApiResponse(responseCode = "401", description = "Usuário não autorizado a acessar a aplicação",
        content = @Content(mediaType = "application/json"))
@ApiResponse(responseCode = "403", description = "Papel não autorizado a acessar o recurso",
        content = @Content(mediaType = "application/json"))
public class BancoController {

    @Autowired
    private BancoService bancoService;

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Exclui um banco",
            parameters = { @Parameter(name = "token", description = "Token JWT de acesso obtido no login",
                    in = ParameterIn.HEADER, schema = @Schema(implementation = String.class), required = true)})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Banco excluído com sucesso"),
            @ApiResponse(responseCode = "422", description = "Não foi possível processar a requisição",
                    content = @Content(mediaType = "application/json"))
    })
    public void delete(@PathVariable("id") long id) throws NotEmptyCollectionException, NotFoundException {
        bancoService.delete(id);
    }

    @GetMapping("/list")
    @Operation(summary = "Retorna uma listagem de bancos, ordenados pelo nome do banco",
            parameters = { @Parameter(name = "token", description = "Token JWT de acesso obtido no login",
                    in = ParameterIn.HEADER, schema = @Schema(implementation = String.class), required = true)})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem dos bancos encontrados",
                    content = { @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "[{ \"id\": 1, \"numero\": 10, \"cnpj\": \"00000000000191\", \"nome\": \"Banco do Brasil\" }, { \"id\": 2, \"numero\": 20, \"cnpj\": \"00360305000104\", \"nome\": \"Caixa Econômica Federal\" }]"),
                            schema = @Schema(implementation = Banco.class)) })
    })
    public List<Banco> findAll() {
        return bancoService.findAll();
    }

    @GetMapping("/show/{id}")
    @Operation(summary = "Retorna um único banco, a partir do identificador do banco",
            parameters = { @Parameter(name = "token", description = "Token JWT de acesso obtido no login",
                    in = ParameterIn.HEADER, schema = @Schema(implementation = String.class), required = true),
                            @Parameter(name = "id", description = "Identificador do banco",
                    in = ParameterIn.PATH, required = true)})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informações do banco encontrado",
                    content = { @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"id\": 1, \"numero\": 10, \"cnpj\": \"00000000000191\", \"nome\": \"Banco do Brasil\" }"),
                            schema = @Schema(implementation = Banco.class)) }),
            @ApiResponse(responseCode = "404", description = "Banco não encontrado",
                    content = @Content(mediaType = "application/json"))
    })
    public Banco findById(@PathVariable("id") long id) throws NotFoundException {
        return bancoService.findById(id);
    }

    @Operation(summary = "Insere um novo banco",
            parameters = { @Parameter(name = "token", description = "Token JWT de acesso obtido no login",
                    in = ParameterIn.HEADER, schema = @Schema(implementation = String.class), required = true)})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = { @Content(mediaType = "application/json",
                                                        examples = @ExampleObject(value = "{ \"numero\": 10, \"cnpj\": \"00000000000191\", \"nome\": \"Banco do Brasil\" }"),
                                                        schema = @Schema(implementation = Banco.class)) })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Banco inserido com sucesso",
                    content = { @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"id\": 1, \"numero\": 10, \"cnpj\": \"00000000000191\", \"nome\": \"Banco do Brasil\" }"),
                            schema = @Schema(implementation = Banco.class)) }),
            @ApiResponse(responseCode = "422", description = "Não foi possível processar a requisição",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Banco save(@RequestBody @Valid Banco banco) throws NotUniqueException {
        return bancoService.save(banco);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Altera os dados de um banco",
            parameters = { @Parameter(name = "token", description = "Token JWT de acesso obtido no login",
                    in = ParameterIn.HEADER, schema = @Schema(implementation = String.class), required = true)})
    @io.swagger.v3.oas.annotations.parameters.RequestBody(content = { @Content(mediaType = "application/json",
            examples = @ExampleObject(value = "{ \"numero\": 10, \"cnpj\": \"00000000000191\", \"nome\": \"Banco do Brasil\" }"),
            schema = @Schema(implementation = Banco.class)) })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Banco alterado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"id\": 1, \"numero\": 10, \"cnpj\": \"00000000000191\", \"nome\": \"Banco do Brasil\" }"),
                            schema = @Schema(implementation = Banco.class)) }),
            @ApiResponse(responseCode = "404", description = "Banco não encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "422", description = "Não foi possível processar a requisição",
                    content = @Content(mediaType = "application/json"))
    })
    public Banco update(@PathVariable("id") long id, @RequestBody @Valid Banco banco) throws NotFoundException, NotUniqueException {
        return bancoService.update(id, banco);
    }
}
