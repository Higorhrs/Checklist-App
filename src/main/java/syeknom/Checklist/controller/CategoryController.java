package syeknom.Checklist.controller;

import syeknom.Checklist.model.Category;
import syeknom.Checklist.service.CategoryService;
import syeknom.Checklist.dto.CategoryCreateDTO;
import syeknom.Checklist.dto.CategoryResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Controlador de Categorias", description = "Endpoints para gerenciamento de categorias de tarefas")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Cria uma nova categoria",
            description = "Cria uma categoria com nome e descrição informados no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryCreateDTO dto) {

        Category newCategory = new Category(dto.getName(), dto.getDescription());
        Category savedCategory = categoryService.save(newCategory);
        CategoryResponseDTO responseDTO = new CategoryResponseDTO(savedCategory);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Lista todas as categorias",
            description = "Retorna uma lista de todas as categorias cadastradas no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {

        List<Category> categories = categoryService.findAll();

        List<CategoryResponseDTO> responseList = categories.stream()
                .map(CategoryResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Busca uma categoria pelo ID",
            description = "Retorna os dados de uma categoria existente, informando o ID no caminho da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {

        return categoryService.findById(id)
                .map(CategoryResponseDTO::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // -------------------------------------------------------------------------
    @Operation(summary = "Exclui uma categoria pelo ID",
            description = "Remove uma categoria existente com base no ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {

        if (categoryService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
        
    }
}
