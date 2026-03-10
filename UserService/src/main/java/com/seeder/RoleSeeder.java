package com.seeder;

import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.entity.Role;
import com.enums.RoleEnum;
import com.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(1)
@Component
@RequiredArgsConstructor
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent>{

	//@Autowired  = 1️ Do NOT Use @Autowired With @RequiredArgsConstructor
	//This is wrong usage. 
	//@RequiredArgsConstructor already creates constructor injection.
	private final RoleRepository rr;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		log.debug("Roles Added: "+ event);
		log.info("Role seeding process started");
		loadRoles();
		
	}
	
	private void loadRoles() {

		//RoleEnum[] roleEnums = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN };
		
		Map<RoleEnum, String> roleDescription = Map.of(
		        RoleEnum.USER, "Default User Role",
		        RoleEnum.ADMIN, "Administrator Role",
		        RoleEnum.SUPER_ADMIN, "Super Administrator Role"
		);

		
		//Java 11 Style
		for (RoleEnum roleEnum : RoleEnum.values()) {

            if (rr.findByRolename(roleEnum).isEmpty()) {

                Role role = new Role();
                role.setRolename(roleEnum);
                role.setDescription(roleDescription.get(roleEnum));

                rr.save(role);

                log.info("Role created: {}", roleEnum);
            }
            
//    		// Java 8–style Map creation
//    		Map<RoleEnum, String> roleDescription = new HashMap<>();
//    		roleDescription.put(RoleEnum.USER, "Default User Role");
//    		roleDescription.put(RoleEnum.ADMIN, "Administrator Role");
//    		roleDescription.put(RoleEnum.SUPER_ADMIN, "Super Administrator Role");
		
//		for (RoleEnum roleEnum : roleEnums) {
//			boolean roleExists  = !rr.findByRolename(roleEnum).isPresent();
//			
//
//			if (roleExists) {
//				Role role = new Role();
//				role.setRolename(roleEnum);
//				role.setDescription(roleDescription.get(roleEnum));
//				rr.save(role);
//				log.info("Role with name: {} created", role.getRolename());
//			}
		}
	}

}
