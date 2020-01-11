package employee.tracking.system.config;

import employee.tracking.system.metrics.store.GaugeMeterStore;
import employee.tracking.system.metrics.store.MeterStore;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.config.MeterFilterReply;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class MicrometerConfiguration {

    @Bean
    @Order(-1)
    MeterRegistry prometheusMeterRegistry() {
        return new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
    }

    @Bean
    MeterStore gaugeMeterStore(MeterRegistry meterRegistry) {
        return new GaugeMeterStore(meterRegistry);
    }

    @Bean
    public MeterFilter meterFilter() {
        return new MeterFilter() {
            @Override
            public MeterFilterReply accept(Meter.Id id) {
                if(id.getName().startsWith("tomcat.")) {
                    return MeterFilterReply.DENY;
                }
                if(id.getName().startsWith("jvm.")) {
                    return MeterFilterReply.DENY;
                }
                if(id.getName().startsWith("process.")) {
                    return MeterFilterReply.DENY;
                }
                if(id.getName().startsWith("system.")) {
                    return MeterFilterReply.DENY;
                }
                return MeterFilterReply.NEUTRAL;
            }
        };
    }
}
