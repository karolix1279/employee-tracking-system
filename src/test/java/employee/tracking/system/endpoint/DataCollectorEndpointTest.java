package employee.tracking.system.endpoint;

import employee.tracking.system.expositor.TrackedDataExpositor;
import employee.tracking.system.metrics.store.GaugeMeterStore;
import employee.tracking.system.model.dto.request.TrackedInformationDto;
import employee.tracking.system.service.TrackedDataMetricExposition;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin1")
class DataCollectorEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PrometheusMeterRegistry prometheusMeterRegistry;

    @Autowired
    private TrackedDataMetricExposition trackedDataMetricExposition;

    @Test
    public void shouldReturnStatsOk() throws Exception {
        mockMvc.perform(post("/api/collect")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"computerName\": \"DESKTOP-B4K6TGR\",\n" +
                        "  \"countClickedButtonsKeyboard\": 0,\n" +
                        "  \"countClickedButtonsMouse\": 0,\n" +
                        "  \"cpuUsage\": 3.3,\n" +
                        "  \"memoryUsage\": 59.8,\n" +
                        "  \"ip\": \"10.230.105.136\",\n" +
                        "  \"currentUser\": \"Wojtek\",\n" +
                        "  \"screenChanges\": 1,\n" +
                        "  \"connectedDevicesChanges\": 0\n" +
                        "}"))
                .andExpect(status().isOk());
    }

}