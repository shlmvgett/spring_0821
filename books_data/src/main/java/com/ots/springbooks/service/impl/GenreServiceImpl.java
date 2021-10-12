package com.ots.springbooks.service.impl;

import com.ots.springbooks.models.Genre;
import com.ots.springbooks.repositories.GenreRepository;
import com.ots.springbooks.service.GenreService;
import com.ots.springbooks.service.IOService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Log4j2
@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final IOService ioService;
  private final GenreRepository genreRepository;

  @Override
  public void getAllGenres() {
    List<Genre> genres = genreRepository.findAll();
    if (!CollectionUtils.isEmpty(genres)) {
      genres.forEach(genre -> ioService.print(genre.toString()));
    } else {
      ioService.print("genres table is empty.");
    }
  }

  @Override
  public void getGenreById() {
    ioService.print("Type genre id:");
    long genreId = Long.parseLong(ioService.read());
    Optional<Genre> genre = genreRepository.findById(genreId);
    if (genre.isPresent()) {
      ioService.print(genre.toString());
    } else {
      ioService.print("genre wasn't found");
    }
  }

  @Override
  @Transactional
  public void insertGenre() {
    ioService.print("Type genre name:");
    String genreName = ioService.read();
    genreRepository.save(new Genre(genreName));
    ioService.print("genre was added");
  }

  @Override
  @Transactional
  public void updateGenre() {
    ioService.print("Type genre id:");
    long genreId = Long.parseLong(ioService.read());
    Optional<Genre> genre = genreRepository.findById(genreId);
    if (genre.isEmpty()) {
      ioService.print("genre with Id:" + genreId + " wasn't found");
    } else {
      ioService.print("Type genre name:");
      String genreName = ioService.read();
      genre.get().setTitle(genreName);
      genreRepository.save(genre.get());
      ioService.print("genre was updated");
    }
  }

  @Override
  @Transactional
  public void deleteGenre() {
    ioService.print("Type genre id:");
    long genreId = Long.parseLong(ioService.read());
    Optional<Genre> genre = genreRepository.findById(genreId);
    if (genre.isPresent()) {
      genreRepository.deleteById(genre.get().getId());
      ioService.print("genre was deleted");
    } else {
      ioService.print("genre wasn't found");
    }
  }
}
