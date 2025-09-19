package com.example.demo.config;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initData(BrandRepository brands, CategoryRepository categories, GuitarRepository guitars,
                               CustomerRepository customers, SupplierRepository suppliers,
                               AppUserRepository users, PasswordEncoder encoder) {
        return args -> {
            if (brands.count() == 0) {
                Brand fender = new Brand(); fender.setName("Fender"); fender.setCountry("USA"); brands.save(fender);
                Brand gibson = new Brand(); gibson.setName("Gibson"); gibson.setCountry("USA"); brands.save(gibson);

                Category electric = new Category(); electric.setName("Electric"); electric.setDescription("Electric guitars"); categories.save(electric);
                Category acoustic = new Category(); acoustic.setName("Acoustic"); acoustic.setDescription("Acoustic guitars"); categories.save(acoustic);

                Guitar strat = new Guitar(); strat.setModel("Stratocaster"); strat.setBrand(fender); strat.setCategory(electric); strat.setPrice(1200.0); strat.setStock(5); guitars.save(strat);
                Guitar lesPaul = new Guitar(); lesPaul.setModel("Les Paul"); lesPaul.setBrand(gibson); lesPaul.setCategory(electric); lesPaul.setPrice(2500.0); lesPaul.setStock(3); guitars.save(lesPaul);

                Customer john = new Customer(); john.setFullName("John Doe"); john.setEmail("john@example.com"); customers.save(john);

                Supplier sup = new Supplier(); sup.setName("Main Supplier"); sup.setContactInfo("supplier@example.com");
                sup.getGuitars().add(strat); sup.getGuitars().add(lesPaul);
                suppliers.save(sup);
            }

            if (users.count() == 0) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPasswordHash(encoder.encode("admin123"));
                admin.getRoles().add("ADMIN");
                users.save(admin);

                AppUser manager = new AppUser();
                manager.setUsername("manager");
                manager.setEmail("manager@example.com");
                manager.setPasswordHash(encoder.encode("manager123"));
                manager.getRoles().add("MANAGER");
                users.save(manager);

                AppUser customer = new AppUser();
                customer.setUsername("customer");
                customer.setEmail("customer@example.com");
                customer.setPasswordHash(encoder.encode("customer123"));
                customer.getRoles().add("CUSTOMER");
                users.save(customer);
            }
        };
    }
}


