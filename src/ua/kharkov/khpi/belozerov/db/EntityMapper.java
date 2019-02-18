package ua.kharkov.khpi.belozerov.db;

import java.sql.ResultSet;

/**
 * Defines general contract for mapping database result set rows to application entities.
 * Implementations are not supposed to move cursor of the resultSet via next() method,
 * but only extract information from the row in current cursor position.
 * 
 * Определяет общий контракт для сопоставления строк набора результатов базы данных с 
 * объектами приложения. 
 * Предполагается, что реализация не должна перемещать курсор из набора результатов через
 * метод next(), но только извлекать информацию из строки в текущую позицию курсора.
 * 
 */
public interface EntityMapper<T> {
    T mapRow(ResultSet rs);
}
