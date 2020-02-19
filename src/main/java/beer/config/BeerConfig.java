package beer.config;

import beer.BeerPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackageClasses = BeerPackage.class)
@EntityScan(basePackageClasses = BeerPackage.class)
public class BeerConfig {
}
