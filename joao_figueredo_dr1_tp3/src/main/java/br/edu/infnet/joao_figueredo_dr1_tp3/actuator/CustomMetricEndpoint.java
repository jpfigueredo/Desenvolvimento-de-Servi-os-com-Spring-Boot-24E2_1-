package br.edu.infnet.joao_figueredo_dr1_tp3.actuator;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
@Endpoint(id = "customMetrics")
public class CustomMetricEndpoint {

    private final MeterRegistry meterRegistry;

    @Autowired
    public CustomMetricEndpoint(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @ReadOperation
    public Map<String, Object> customMetrics() {
        Map<String, Object> metrics = new HashMap<>();

        // Example metric: Response times
        Timer timer = meterRegistry.find("http.server.requests").timer();
//        if (timer != null) {
//            metrics.put("response_time_avg", timer.mean());
//        }

        // Example metric: Error rates
        Timer errorTimer = meterRegistry.find("http.server.requests")
                .tag("status", "500").timer();
        if (errorTimer != null) {
            metrics.put("error_rate", errorTimer.count());
        }

        // Example metric: Request count
        if (timer != null) {
            metrics.put("request_count", timer.count());
        }

        // Example metric: Memory usage
        metrics.put("memory_used", Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        // Example metric: CPU usage
        metrics.put("cpu_usage", getProcessCpuLoad());

        // Example metric: Active user sessions (this is a placeholder, implement accordingly)
        metrics.put("active_sessions", getActiveSessions());

        return metrics;
    }

    private double getProcessCpuLoad() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        if (osBean instanceof com.sun.management.OperatingSystemMXBean) {
            com.sun.management.OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) osBean;
            return os.getProcessCpuLoad() * 100;
        }
        return -1;
    }

    private int getActiveSessions() {
        // Placeholder for active session count
        // Implement this method to return the actual number of active sessions
        // For example, if using Spring Session, you might retrieve this information from the session repository
        return 42; // Replace with actual session count retrieval code
    }
}
