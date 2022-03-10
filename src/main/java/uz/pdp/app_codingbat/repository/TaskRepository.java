package uz.pdp.app_codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app_codingbat.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    boolean existsByName(String name);
}
