package app.estat.web.contoller.service;

import app.estat.web.Application;
import app.estat.web.contoller.Utils;
import app.estat.web.model.entity.Entity;
import app.estat.web.service.EntityService;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes =  Application.class)
public abstract class AbstractEntityServiceImplTest<E extends Entity> {

    private Class<E> clazz;

    private EntityService<E> entityService;

    public void setClazz(Class<E> clazz) {
        this.clazz = clazz;
    }

    public void setEntityService(EntityService<E> entityService) {
        this.entityService = entityService;
    }

    @Test
    public void testSaveEntity() throws IllegalAccessException, ParseException {
        List<E> entities = new ArrayList<>();
        entities.add(getSimpleEntity());
        entities.add(getEmptyEntity());

        for (E entity : entities) {
            E savedEntity = entityService.save(entity);
            E readedEntity = entityService.get(savedEntity.getId());

            assertTrue(Utils.assertPropertiesEquals(savedEntity, readedEntity, clazz));
        }
    }

    @Test
    public void testGetEntity() throws IllegalAccessException, ParseException {
        E savedEntity = entityService.save(getSimpleEntity());
        assertTrue(savedEntity.getId() != null);

        E readedEntity = entityService.get(savedEntity.getId());
        assertTrue(Utils.assertPropertiesEquals(savedEntity, readedEntity, clazz));
    }

    @Test(expected = RuntimeException.class)
    public void testGetEntityWithNoSuchEntityException() {
        entityService.get(1L);
    }

    @Test(expected = RuntimeException.class)
    public void testGetEntityWithNullIdException() {
        entityService.get(null);
    }

    @Test
    public void testGetAllEntities() throws ParseException, IllegalAccessException {
        List<E> entities = new ArrayList<>();
        for (int i = 0; i <= 18; i += 2) {
            entities.add(i, entityService.save(getSimpleEntity()));
            entities.add(i + 1, entityService.save(getEmptyEntity()));
        }

        List<E> readedEntities = entityService.getAll();

        assertEquals(20, readedEntities.size());

        for (int i = 0; i <= 18; i += 2) {
            Utils.assertPropertiesEquals(entities.get(i), readedEntities.get(i), clazz);
            Utils.assertPropertiesEquals(entities.get(i + 1), readedEntities.get(i + 1), clazz);
        }
    }

    @Test
    public void testUpdateEntity() throws ParseException, IllegalAccessException {
        List<E> entities = new ArrayList<>();
        entities.add(0, getSimpleEntity());
        entities.add(1, getEmptyEntity());

        for (int i = 0; i < 2; i++) {
            E savedEntity = entityService.save(entities.get(i));
            E readedEntity = entityService.get(savedEntity.getId());

            assertTrue(Utils.assertPropertiesEquals(savedEntity, readedEntity, clazz));

            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType().equals(String.class)) {
                    field.setAccessible(true);
                    field.set(entities.get(i), "SIMPLE_VALUE");
                }
            }

            E updatedEntity = entityService.update(savedEntity.getId(), entities.get(i));
            readedEntity = entityService.get(savedEntity.getId());

            assertTrue(Utils.assertPropertiesEquals(updatedEntity, readedEntity, clazz));

            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType().equals(String.class)) {
                    field.setAccessible(true);
                    assertEquals("SIMPLE_VALUE", field.get(updatedEntity));
                    assertEquals("SIMPLE_VALUE", field.get(readedEntity));
                }
            }
        }
    }

    @Test
    public void testDeleteEntity() throws ParseException {
        E savedEntity = entityService.save(getSimpleEntity());

        assertEquals(1, entityService.getAll().size());

        entityService.delete(savedEntity.getId());

        assertEquals(0, entityService.getAll().size());
    }

    @Test(expected = RuntimeException.class)
    public void testDeleteEntityWithNullIdException() {
        entityService.delete(null);
    }

    @Test
    public void testDeleteAllEntities() {
        for (int i = 0; i < 5; i++) {
            entityService.save(getEmptyEntity());
        }

        assertEquals(5, entityService.getAll().size());

        entityService.deleteAll();

        assertEquals(0, entityService.getAll().size());
    }

    @After
    public void tearDown() {
        entityService.getRepository().deleteAll();
    }

    protected abstract E getSimpleEntity() throws ParseException;

    protected abstract E getEmptyEntity();

}
