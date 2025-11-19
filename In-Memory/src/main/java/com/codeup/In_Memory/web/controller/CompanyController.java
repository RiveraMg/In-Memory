/*package com.codeup.In_Memory.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
@Tag(name = "Companies", description = "API endpoints para la gestión de empresas")
public class CompanyController {

    // Ejemplo de un método de ejemplo
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    @Operation(summary = "Obtener todas las empresas", description = "Retorna una lista de todas las empresas")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Crear una nueva empresa", description = "Crea una nueva empresa")
    public Company createCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una empresa por ID", description = "Retorna una empresa específica por su ID")
    public Company getCompanyById(@PathVariable Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada con ID: " + id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una empresa por ID", description = "Actualiza los datos de una empresa existente por su ID")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada con ID: " + id));
        company.setName(companyDetails.getName());
        // Actualizar otros campos
        return companyRepository.save(company);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una empresa por ID", description = "Elimina una empresa por su ID")
    public void deleteCompany(@PathVariable Long id) {
        companyRepository.deleteById(id);
    }
}


package com.codeup.In_Memory.web.controller;

import com.codeup.In_Memory.service.CatalogService;
import com.codeup.In_Memory.web.dto.EventRequestDTO;
import com.codeup.In_Memory.web.dto.EventResponseDTO;
import com.codeup.In_Memory.web.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final CatalogService catalogService;

    public EventController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    // 🔹 Crear un nuevo evento
    @PostMapping
    public ResponseEntity<ApiResponse<EventResponseDTO>> createEvent(@RequestBody EventRequestDTO request) {
        EventResponseDTO event = catalogService.createEvent(
                request.name(),
                request.hoster(),
                request.description(),
                request.date(),
                request.status(),
                request.idVenue()
        );

        return ResponseEntity.ok(ApiResponse.withMeta(
                event,
                new ApiResponse.Meta("🎉 Evento añadido exitosamente", "trace-001", "1.0.0", null)
        ));
    }

    // 🔹 Obtener todos los eventos
    @GetMapping
    public ResponseEntity<ApiResponse<List<EventResponseDTO>>> getAllEvents() {
        List<EventResponseDTO> events = catalogService.getAllEvents();
        return ResponseEntity.ok(ApiResponse.ok(events));
    }

    // 🔹 Obtener un evento por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventResponseDTO>> getEventById(@PathVariable Long id) {
        EventResponseDTO event = catalogService.getEventById(id);
        if (event == null) {
            return ResponseEntity.status(404).body(ApiResponse.error("Evento no encontrado", "trace-404"));
        }
        return ResponseEntity.ok(ApiResponse.ok(event));
    }

    // 🔹 Actualizar un evento
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EventResponseDTO>> updateEvent(
            @PathVariable Long id,
            @RequestBody EventRequestDTO request
    ) {
        EventResponseDTO updatedEvent = catalogService.updateEvent(
                id,
                request.name(),
                request.hoster(),
                request.description(),
                request.date(),
                request.status(),
                request.idVenue()
        );

        return ResponseEntity.ok(ApiResponse.withMeta(
                updatedEvent,
                new ApiResponse.Meta("✅ Evento actualizado exitosamente", "trace-002", "1.0.0", null)
        ));
    }

    // 🔹 Eliminar un evento
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEvent(@PathVariable Long id) {
        catalogService.deleteEvent(id);
        return ResponseEntity.ok(ApiResponse.withMeta(
                null,
                new ApiResponse.Meta("🗑️ Evento eliminado exitosamente", "trace-003", "1.0.0", null)
        ));
    }
}
*/