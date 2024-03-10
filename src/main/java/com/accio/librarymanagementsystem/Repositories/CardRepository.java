package com.accio.librarymanagementsystem.Repositories;

import com.accio.librarymanagementsystem.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<LibraryCard, Integer> { //it will save to the LibraryCard table (entity -> LibraryCard and, datatype of PK -> Integer)


}
