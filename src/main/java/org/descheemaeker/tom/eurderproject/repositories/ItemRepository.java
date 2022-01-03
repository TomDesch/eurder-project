package org.descheemaeker.tom.eurderproject.repositories;

import org.descheemaeker.tom.eurderproject.domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class ItemRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void addItem(Item item) {
        entityManager.persist(item);
    }

    public List<Item> getAllItems() {
        return entityManager.createQuery("select i from Item i", Item.class).getResultList();
    }

//    public Item getItem(String itemId) {
//        return items.get(itemId);
//    }


}
