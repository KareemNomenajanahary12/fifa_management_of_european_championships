package org.hei.school.fifa_management_if_european_championships.dao.operation;

import java.util.List;
import java.util.UUID;

public interface CrudOperations<T> {
    List<T> getAll(int page, int size);
    T findById(UUID id);
    List<T> saveAll(List<T> entities);
}
