package br.com.joel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.joel.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{

}
