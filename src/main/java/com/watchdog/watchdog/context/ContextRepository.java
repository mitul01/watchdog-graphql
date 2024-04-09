package com.watchdog.watchdog.context;

import com.watchdog.watchdog.model.Context;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContextRepository extends JpaRepository<Context, String>, JpaSpecificationExecutor<Context> {
}
