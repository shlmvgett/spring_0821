package com.ots.springbooks.service;

import com.ots.springbooks.domain.Genre;
import com.ots.springbooks.repositories.interfaces.GenreRepository;
import com.ots.springbooks.service.interfaces.GenreService;
import com.ots.springbooks.service.interfaces.IOService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Log4j2
@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {

  private final IOService ioService;
  private final GenreRepository genreRepository;

  @Override
  public void getAllGenres() {
    List<Genre> genres = genreRepository.getAll();
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
    Genre genre = genreRepository.getById(genreId);
    if (genre != null) {
      ioService.print(genre.toString());
    } else {
      ioService.print("genre wasn't found");
    }
  }

  @Override
  public void insertGenre() {
    ioService.print("Type genre name:");
    String genreName = ioService.read();
    genreRepository.insert(new Genre(genreName));
    ioService.print("genre was added");
  }

  @Override
  public void updateGenre() {
    ioService.print("Type genre id:");
    long genreId = Long.parseLong(ioService.read());
    Genre genre = genreRepository.getById(genreId);
    if (genre == null) {
      ioService.print("genre with Id:" + genreId + " wasn't found");
    } else {
      ioService.print("Type genre name:");
      String genreName = ioService.read();
      genreRepository.update(new Genre(genre.getId(), genreName));
      ioService.print("genre was updated");
    }
  }

  @Override
  public void deleteGenre() {
    ioService.print("Type genre id:");
    long genreId = Long.parseLong(ioService.read());
    Genre genre = genreRepository.getById(genreId);
    if (null != genre) {
      genreRepository.delete(genre);
      ioService.print("genre was deleted");
    } else {
      ioService.print("genre wasn't found");
    }
  }
}
