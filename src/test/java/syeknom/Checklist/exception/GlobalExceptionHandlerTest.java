package syeknom.Checklist.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import jakarta.persistence.EntityNotFoundException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void handleEntityNotFoundException_ShouldReturnNotFound() {
        EntityNotFoundException exception = new EntityNotFoundException("Categoria não encontrada");
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleNotFound(exception);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(404, response.getStatusCode().value());
        assertTrue(response.getBody().containsKey("error"));
        assertEquals("Categoria não encontrada", response.getBody().get("error"));
    }

    @Test
    void handleDataIntegrityViolationException_ShouldReturnConflict() {
        DataIntegrityViolationException exception = new DataIntegrityViolationException("Erro de chave duplicada");
        
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleConflict(exception);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(409, response.getStatusCode().value());
        assertTrue(response.getBody().containsKey("error"));
        assertTrue(response.getBody().get("error").contains("Violação de integridade"));
    }

    @Test
    void handleGenericException_ShouldReturnInternalError() {
        Exception exception = new Exception("Erro inesperado");
        
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleGeneric(exception);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, response.getStatusCode().value());
        assertTrue(response.getBody().containsKey("error"));
        assertTrue(response.getBody().get("error").contains("Erro interno"));
    }
}