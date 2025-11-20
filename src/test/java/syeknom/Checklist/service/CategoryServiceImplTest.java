package syeknom.Checklist.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import syeknom.Checklist.model.Category;
import syeknom.Checklist.repository.CategoryRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {
	
	@Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category("Trabalho", "Tarefas do trabalho");
        category.setId(1L);
    }
    
    
    @Test
    void save_DeveRetornarCategoriaSalva() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category result = categoryService.save(category);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Trabalho", result.getName());
        verify(categoryRepository, times(1)).save(category);
    }
     

	@Test
	void findAll_DeveRetornarTodasAsCategorias() {
	    List<Category> categories = Arrays.asList(category);
	    when(categoryRepository.findAll()).thenReturn(categories);
	
	    List<Category> result = categoryService.findAll();
	
	    assertNotNull(result);
	    assertEquals(1, result.size());
	    assertEquals("Trabalho", result.get(0).getName());
	    verify(categoryRepository, times(1)).findAll();
	}
	
	@Test
	void findById_QuandoCategoriaExiste_DeveRetornarCategoria() {
	    when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
	
	    Optional<Category> result = categoryService.findById(1L);
	
	    assertTrue(result.isPresent());
	    assertEquals(1L, result.get().getId());
	    verify(categoryRepository, times(1)).findById(1L);
	}
	
	@Test
	void findById_QuandoCategoriaNaoExiste_DeveRetornarVazio() {
	    when(categoryRepository.findById(99L)).thenReturn(Optional.empty());
	
	    Optional<Category> result = categoryService.findById(99L);
	
	    assertFalse(result.isPresent());
	    verify(categoryRepository, times(1)).findById(99L);
	}
	
	@Test
	void deleteById_DeveChamarRepositoryDelete() {
	    doNothing().when(categoryRepository).deleteById(1L);
	
	    categoryService.deleteById(1L);
	
	    verify(categoryRepository, times(1)).deleteById(1L);
	}

}
