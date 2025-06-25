package com.orchidservice.config;

import com.orchidservice.entity.*;
import com.orchidservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class DataInit implements CommandLineRunner {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CategoriesRepostory categoriesRepostory;
    @Autowired
    private OrchidsRepository orchidRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Override
    public void run(String... args) throws Exception {

        Roles adminRole = Roles.builder().roleName("Admin").build();
        Roles userRole = Roles.builder().roleName("User").build();
        roleRepository.saveAll(List.of(adminRole, userRole));

        // 2. Tạo Accounts
        Accounts admin = Accounts.builder()
                .accountName("Thoại Admin")
                .email("admin@example.com")
                .password(passwordEncoder.encode("123456")) // nên mã hoá thật khi deploy
                .role(adminRole)
                .build();

        Accounts user = Accounts.builder()
                .accountName("Nguyễn User")
                .email("user@example.com")
                .password(passwordEncoder.encode("123456"))
                .role(userRole)
                .build();

        accountRepository.saveAll(List.of(admin, user));

        // 3. Tạo Category
        Categories cat1 = Categories.builder().categoryName("Lan rừng").build();
        Categories cat2 = Categories.builder().categoryName("Lan cấy mô").build();
        categoriesRepostory.saveAll(List.of(cat1, cat2));

        // 4. Tạo Orchid
        Orchids orchid1 = Orchids.builder()
                .id(UUID.randomUUID().toString())
                .orchidName("Phi điệp tím")
                .orchidDescription("Loại lan quý từ Tây Bắc")
                .orchidUrl("https://example.com/phi-diep.jpg")
                .price(150000)
                .isNatural(true)
                .categories(cat1)
                .build();

        Orchids orchid2 = Orchids.builder()
                .id(UUID.randomUUID().toString())
                .orchidName("Lan hồ điệp")
                .orchidDescription("Lan đẹp cho ngày Tết")
                .orchidUrl("https://example.com/ho-diep.jpg")
                .price(200000)
                .isNatural(false)
                .categories(cat2)
                .build();

        orchidRepository.saveAll(List.of(orchid1, orchid2));

        // 5. Tạo Order
        Orders order = Orders.builder()
                .accounts(user)
                .orderDate(LocalDate.now())
                .orderStatus("Pending")
                .totalAmount(orchid1.getPrice() * 2).build();

        orderRepository.save(order);

        // 6. Tạo OrderDetail
        OrderDetails detail = OrderDetails.builder()
                .orders(order)
                .orchids(orchid1)
                .quantity(2)
                .price(orchid1.getPrice())
                .build();

        orderDetailsRepository.save(detail);

        System.out.println("✅ Seeded dữ liệu mẫu hoàn tất.");
    }
}

