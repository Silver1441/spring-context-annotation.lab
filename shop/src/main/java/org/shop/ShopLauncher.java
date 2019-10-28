package org.shop;

import org.shop.api.ProposalService;
import org.shop.configuration.ApplicationConfiguration;
import org.shop.data.Product;
import org.shop.repository.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The ShopLauncher class.
 */
public class ShopLauncher {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        ProposalRepository proposalRepository = context.getBean(ProposalRepository.class);
        UserRepository userRepository = context.getBean(UserRepository.class);

        ProposalService proposalService = context.getBean(ProposalService.class);
        proposalService.activateProposal(2L);

        for (Product product : productRepository.getProducts()) {
            System.out.println(product);
        }

        for (long i = 1; i < 5; i++) {
            System.out.println(proposalRepository.getProposal(i));
        }

        System.out.println(userRepository.getUserById(1L));
        System.out.println(userRepository.getUserById(2L));
    }
}
