package syeknom.Checklist.controller;

import syeknom.Checklist.model.Category;
import syeknom.Checklist.service.CategoryService;
import syeknom.Checklist.dto.CategoryCreateDTO; // Para a entrada (POST)
import syeknom.Checklist.dto.CategoryResponseDTO; // Para a saída (GET e POST)

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors; // Para mapear listas de DTOs

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Controlador de Categorias", description = "Endpoints para gerenciamento de categorias de tarefas")
public class CategoryController {

    private final CategoryService categoryService;

    // Use injeção via construtor, é a melhor prática no Spring
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // -------------------------------------------------------------------------

    // POST /api/categories - CRIA UMA NOVA CATEGORIA
    // O retorno agora é um DTO de Resposta (sem a lista de Tasks)
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryCreateDTO dto) {

        // 1. Converte DTO de Criação (entrada) para a Entity
        // Presume que Category.java tem o construtor: public Category(String name, String description)
        Category newCategory = new Category(dto.getName(), dto.getDescription());

        // 2. Salva a Entity no banco de dados
        Category savedCategory = categoryService.save(newCategory);

        // 3. Converte a Entity salva para o DTO de Resposta (saída)
        CategoryResponseDTO responseDTO = new CategoryResponseDTO(savedCategory);

        // Retorna 201 Created com o DTO
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // -------------------------------------------------------------------------

    // GET /api/categories - LISTA TODAS AS CATEGORIAS
    // O retorno agora é uma lista de DTOs de Resposta
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {

        // Busca todas as Entities
        List<Category> categories = categoryService.findAll();

        // Mapeia (converte) a lista de Entities para uma lista de DTOs
        List<CategoryResponseDTO> responseList = categories.stream()
                .map(CategoryResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    // -------------------------------------------------------------------------

    // GET /api/categories/{id} - BUSCA UMA CATEGORIA PELO ID
    // O retorno agora é um DTO de Resposta
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {

        return categoryService.findById(id)
                // Se encontrar, mapeia para o DTO de Resposta e retorna 200 OK
                .map(CategoryResponseDTO::new)
                .map(ResponseEntity::ok)
                // Se não encontrar, retorna 404 Not Found
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // -------------------------------------------------------------------------

    // DELETE /api/categories/{id} - EXCLUI UMA CATEGORIA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {

        if (categoryService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteById(id);

        // Retorna 204 No Content
        return ResponseEntity.noContent().build();
    }
}