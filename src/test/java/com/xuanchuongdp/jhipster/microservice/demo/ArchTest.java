package com.xuanchuongdp.jhipster.microservice.demo;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.xuanchuongdp.jhipster.microservice.demo");

        noClasses()
            .that()
            .resideInAnyPackage("com.xuanchuongdp.jhipster.microservice.demo.service..")
            .or()
            .resideInAnyPackage("com.xuanchuongdp.jhipster.microservice.demo.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.xuanchuongdp.jhipster.microservice.demo.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
