package com.nag.repository;

import com.nag.model.Book;
import com.nag.util.NumberGenerator;
import com.nag.util.TextUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by nagendra on 1/12/18.
 */
@Transactional(Transactional.TxType.SUPPORTS)  //
public class BookRepository {

    // inject the persistence unit
    @PersistenceContext(unitName = "persistenceUnit")
    private EntityManager em;

    @Inject
    private TextUtil textUtil;

    @Inject
    private NumberGenerator numberGenerator;

    @Transactional(Transactional.TxType.SUPPORTS)
    public Book find(@NotNull Long id){
        return em.find(Book.class,id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Book create(@NotNull Book book){
        book.setTitle(textUtil.sanitize(book.getTitle()));
        book.setIsbn(numberGenerator.generateNumber());
        em.persist(book);
        return book;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(@NotNull Long id){
        em.remove(em.getReference(Book.class, id));
    }
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Book> findAll(){
        TypedQuery<Book> query = em.createQuery("SELECT b from Book b order by b.title DESC ",Book.class);
        return query.getResultList();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Long countAll(){
        TypedQuery<Long> query = em.createQuery("select count(b) from Book b", Long.class);
        return query.getSingleResult();
    }
}
