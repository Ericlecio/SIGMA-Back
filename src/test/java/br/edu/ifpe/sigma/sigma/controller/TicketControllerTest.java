package br.edu.ifpe.sigma.sigma.controller;

import br.edu.ifpe.sigma.sigma.dto.TicketRequest;
import br.edu.ifpe.sigma.sigma.dto.TicketResponse;
import br.edu.ifpe.sigma.sigma.entity.Priority;
import br.edu.ifpe.sigma.sigma.entity.ProblemType;
import br.edu.ifpe.sigma.sigma.entity.Status;
import br.edu.ifpe.sigma.sigma.repository.UserRepository;
import br.edu.ifpe.sigma.sigma.service.TicketService;
import br.edu.ifpe.sigma.sigma.service.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateTicket() throws Exception {
        // Arrange
        UUID ticketId = UUID.randomUUID();
        TicketRequest request = new TicketRequest();
        request.setDescription("Test ticket");
        request.setStatus(Status.OPEN);
        request.setPriority(Priority.HIGH);
        request.setProblemType(ProblemType.SOFTWARE);
        request.setEnvironment(UUID.randomUUID());
        request.setComponent(UUID.randomUUID());
        request.setCreatedBy(UUID.randomUUID());

        TicketResponse response = TicketResponse.builder()
                .id(ticketId)
                .description("Test ticket")
                .status(Status.OPEN)
                .build();

        when(ticketService.create(any(TicketRequest.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/v1/tickets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ticketId.toString()))
                .andExpect(jsonPath("$.description").value("Test ticket"))
                .andExpect(jsonPath("$.status").value("OPEN"));
    }

    @Test
    @WithMockUser
    void testFindAllTickets() throws Exception {
        // Arrange
        TicketResponse ticket1 = TicketResponse.builder()
                .id(UUID.randomUUID())
                .description("Ticket 1")
                .status(Status.OPEN)
                .build();
        TicketResponse ticket2 = TicketResponse.builder()
                .id(UUID.randomUUID())
                .description("Ticket 2")
                .status(Status.IN_PROGRESS)
                .build();

        when(ticketService.findAll()).thenReturn(List.of(ticket1, ticket2));

        // Act & Assert
        mockMvc.perform(get("/api/v1/tickets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].description").value("Ticket 1"))
                .andExpect(jsonPath("$[1].description").value("Ticket 2"));
    }

    @Test
    @WithMockUser
    void testFindTicketById() throws Exception {
        // Arrange
        UUID ticketId = UUID.randomUUID();
        TicketResponse response = TicketResponse.builder()
                .id(ticketId)
                .description("Test ticket")
                .status(Status.OPEN)
                .build();

        when(ticketService.findById(ticketId)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(get("/api/v1/tickets/{id}", ticketId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ticketId.toString()))
                .andExpect(jsonPath("$.description").value("Test ticket"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateTicketStatus() throws Exception {
        // Arrange
        UUID ticketId = UUID.randomUUID();
        TicketRequest request = new TicketRequest();
        request.setDescription("Updated ticket");
        request.setStatus(Status.IN_PROGRESS);
        request.setPriority(Priority.MEDIUM);
        request.setProblemType(ProblemType.SOFTWARE);
        request.setAssignedTo(UUID.randomUUID());

        TicketResponse response = TicketResponse.builder()
                .id(ticketId)
                .description("Updated ticket")
                .status(Status.IN_PROGRESS)
                .build();

        when(ticketService.update(eq(ticketId), any(TicketRequest.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(put("/api/v1/tickets/{id}", ticketId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
                .andExpect(jsonPath("$.description").value("Updated ticket"));
    }
}
