package com.netmed.usermodule.config;

import io.jaegertracing.internal.JaegerTracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * Configuration class for Jaeger Open Tracing Tool
 *
 * @author Nataraj m
 * @created 24/03/21
 */
@Configuration
public class JaegerConfig {

    @Bean
    public JaegerTracer getTracer() {
        io.jaegertracing.Configuration.SamplerConfiguration samplerConfig = io.jaegertracing.Configuration.SamplerConfiguration.fromEnv().withType("const").withParam(1);
        io.jaegertracing.Configuration.ReporterConfiguration reporterConfig = io.jaegertracing.Configuration.ReporterConfiguration.fromEnv().withLogSpans(true);
        io.jaegertracing.Configuration config = new io.jaegertracing.Configuration("jaeger netmed-user-module").withSampler(samplerConfig).withReporter(reporterConfig);
        return config.getTracer();
    }
}
