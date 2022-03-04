package com.example.com.spring.reactive;

import com.example.com.spring.reactive.Controller.StudentController;
import com.example.com.spring.reactive.Dto.StudentDto;
import com.example.com.spring.reactive.Service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


		import org.junit.runner.RunWith;
		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
		import org.springframework.boot.test.context.SpringBootTest;
		import org.springframework.boot.test.mock.mockito.MockBean;
		import org.springframework.test.context.junit4.SpringRunner;
		import org.springframework.test.web.reactive.server.WebTestClient;
		import reactor.core.publisher.Flux;
		import reactor.core.publisher.Mono;
		import reactor.test.StepVerifier;

		import static org.mockito.ArgumentMatchers.any;
		import static org.mockito.BDDMockito.given;
		import  static org.mockito.Mockito.when;
@SpringBootTest
@RunWith(SpringRunner.class)
@WebFluxTest(StudentController.class)
class ApplicationTests {
	@Autowired
	private WebTestClient webTestClient;
	@MockBean
	private StudentService service;


		public void addProductTest () {
			Mono<StudentDto> studentDtoMono = Mono.just(new StudentDto("102", "neha", "Rai", 25));
			when(service.saveStudent(studentDtoMono)).thenReturn(studentDtoMono);

			webTestClient.post().uri("/students")
					.body(Mono.just(studentDtoMono), StudentDto.class)
					.exchange()
					.expectStatus().isOk();//200

		}


		@Test
		public void getProductsTest () {
			Flux<StudentDto> studentDtoFlux = Flux.just(new StudentDto("102", "neha", "Rai", 25),
					new StudentDto("10", "Roli",
							"Rai", 50));

			when(service.getStudents()).thenReturn(studentDtoFlux);

			Flux<StudentDto> responseBody = webTestClient.get().uri("/students")
					.exchange()
					.expectStatus().isOk()
					.returnResult(StudentDto.class)
					.getResponseBody();

			StepVerifier.create(responseBody)
					.expectSubscription()
					.expectNext(new StudentDto("102", "neha", "Rai", 25))
					.expectNext(new StudentDto("10", "Roli", "Rai", 50))
					.verifyComplete();

		}


		@Test
		public void getStudentTest () {
			Mono<StudentDto> studentDtoMono = Mono.just(new StudentDto("102", "neha", "Rai", 25));
			when(service.getStudent(any())).thenReturn(studentDtoMono);

			Flux<StudentDto> responseBody = webTestClient.get().uri("/students/102")
					.exchange()
					.expectStatus().isOk()
					.returnResult(StudentDto.class)
					.getResponseBody();

			StepVerifier.create(responseBody)
					.expectSubscription()
					.expectNextMatches(p -> p.getFirstName().equals("neha"))
					.verifyComplete();
		}


		@Test
		public void updateProductTest () {
			Mono<StudentDto> studentDtoMono = Mono.just(new StudentDto("102", "neha", "Rai", 25));
			when(service.updateStudent(studentDtoMono, "102")).thenReturn(studentDtoMono);

			webTestClient.put().uri("/products/update/102")
					.body(Mono.just(studentDtoMono), StudentDto.class)
					.exchange()
					.expectStatus().isOk();//200
		}

		@Test
		public void deleteProductTest () {
			given(service.deleteStudent(any())).willReturn(Mono.empty());
			webTestClient.delete().uri("/students/delete/102")
					.exchange()
					.expectStatus().isOk();//200
		}
	}

