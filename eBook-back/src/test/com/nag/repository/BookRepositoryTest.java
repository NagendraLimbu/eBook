package com.nag.repository;

import com.nag.model.Book;
import com.nag.model.Language;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by nagendra on 1/13/18.
 */
@RunWith(Arquillian.class)
public class BookRepositoryTest {
    @Test
    public void delete() throws Exception {

    }

    @Inject
    private BookRepository bookRepository;

    @Test
    @InSequence(1)
    public void create() throws Exception {
        Book b = new Book("title", "dexc", 40.0f, "isbn", new Date(), 10, "iamgeurl", Language.ENGLISH);
                b = bookRepository.create(b);

        assertNotNull(b);
    }

    @Deployment
    public static Archive<?> createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookRepository.class)
                .addClass(Language.class)
                .addClass(Book.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("/META-INF/persistence.xml", "persistence.xml");
    }

}