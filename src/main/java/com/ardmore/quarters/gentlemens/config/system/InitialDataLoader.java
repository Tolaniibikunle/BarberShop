package com.ardmore.quarters.gentlemens.config.system;

import com.ardmore.quarters.gentlemens.entity.Role;
import com.ardmore.quarters.gentlemens.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

  private boolean alreadySetup = false;

  @Autowired
  private RoleRepository roleRepository;

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {

    if (alreadySetup)
      return;

    createRoleIfNotFound(SystemConsts.BARBER_ROLE);
    createRoleIfNotFound(SystemConsts.CUSTOMER_ROLE);

    alreadySetup = true;
  }

  @Transactional
  void createRoleIfNotFound(String name) {
    Role role = roleRepository.findByName(name);
    if (role == null) {
      role = new Role(name);
      roleRepository.save(role);
    }
  }


}
