package dev.myrold.lotty;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
    info = @Info(
        title = "lotty",
        version = "0.0"
    )
)
public class LottyServiceApp {

    public static void main(String[] args) {
        Micronaut.run(LottyServiceApp.class, args);
    }
}