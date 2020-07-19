package com.example.bookingapp;

import Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import vo.Flightdetail;
import vo.User;
import vo.UserCollection;

import java.util.ArrayList;
import java.util.Arrays;

public class BookingappApplication implements CommandLineRunner
 {



	@Autowired
	UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(BookingappApplication.class, args);

	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	@Override
	public void run(String... params) throws Exception {
		User admin = new User();
		admin.setId(1);
		admin.setPassword("admin");
		admin.setEmail("admin@example.com");
		admin.setRoles(new ArrayList<model.Role>(Arrays.asList(model.Role.ROLE_ADMIN)));

		userService.register(admin);

		User user = new User();
		user.setId(2);
		user.setPassword("user");
		user.setEmail("user@example.com");
		user.setRoles(new ArrayList<model.Role>(Arrays.asList(model.Role.ROLE_USER)));

		userService.register(user);

		Flightdetail flights=new Flightdetail();
		flights.setId(1);
        flights.setSeats(5);
        flights.setOrigin("Madurai");
        flights.setDestination("Chennai");
        flights.setDeparture_time("12:00 noon sunday");
        flights.setArrival_time("2:00 sunday");

		UserCollection.flight.add(flights);

	}


}
