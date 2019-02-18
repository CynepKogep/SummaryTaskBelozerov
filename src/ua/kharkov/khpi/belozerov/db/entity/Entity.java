package ua.kharkov.khpi.belozerov.db.entity;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 * (Базовый бстрактный класс (основание) всех сущностей(объектов), имеющих индификационное поле.)
 * @author D.Kolesnikov
 * 
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 8466257860808346236L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
