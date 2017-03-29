package org.manuel.teambuilting.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TeamBuiltingUsersApplication {

	public static void main(final String[] args) {
		SpringApplication.run(TeamBuiltingUsersApplication.class, args);
	}

}
