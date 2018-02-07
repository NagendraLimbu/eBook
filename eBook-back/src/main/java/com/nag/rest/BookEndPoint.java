package com.nag.rest;

import com.nag.model.Book;
import com.nag.repository.BookRepository;

import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/books")
public class BookEndPoint {

    @Inject
    private BookRepository bookRepository;

//    to delegate   shortcut is Alt+Shift+E

    @GET
    @Path("/{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") @Min(1) Long id) {
        Book b = bookRepository.find(id);
        if(b == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(b).build();
    }

   /* @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book book, @Context UriInfo uriInfo) {

        book = bookRepository.create(book);
        URI createdURI = uriInfo.getBaseUriBuilder().path(book.getId().toString()).build();
        return Response.created(createdURI).build();
    }
*/
    @DELETE
    @Path("/{id : \\d+}")
    public Response deleteBook(@PathParam("id") @Min(1) Long id) {
        bookRepository.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findBooks() {
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()){
            return Response.noContent().build();
        }
        return Response.ok(books).build();
    }

    @GET
    @Path("/count")
    public Response countBooks() {
        Long noOfBooks = bookRepository.countAll();
        if(noOfBooks == 0)
            return Response.noContent().build();

        return Response.ok(noOfBooks).build();
    }
}
