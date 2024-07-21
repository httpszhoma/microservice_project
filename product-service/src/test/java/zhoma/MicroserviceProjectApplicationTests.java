package zhoma;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import zhoma.dto.ProductRequest;
import zhoma.repositories.ProductRepository;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class MicroserviceProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private ProductRepository repository;


	@Container
   static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest");


	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.postgres.uri",postgreSQLContainer::getJdbcUrl);
	}
	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest request = getProduct();
		String requestJson = mapper.writeValueAsString(request);
		mockMvc.perform(MockMvcRequestBuilders

				.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson))
				.andExpect(status().isCreated());

        Assertions.assertEquals(1, repository.findAll().size());


	}

	private ProductRequest getProduct() {
		return ProductRequest.builder()
				.name("IPhone 15 ")
				.description("Smart Phone")
				.price(BigDecimal.valueOf(1100))
				.build();
	}


}
