package com.ots.springbooks.config;

import com.ots.springbooks.config.processors.AuthorProcessor;
import com.ots.springbooks.config.processors.BookProcessor;
import com.ots.springbooks.config.processors.GenreProcessor;
import com.ots.springbooks.config.readers.AuthorReader;
import com.ots.springbooks.config.readers.BookReader;
import com.ots.springbooks.config.readers.GenreReader;
import com.ots.springbooks.models.jpa.AuthorJpa;
import com.ots.springbooks.models.jpa.BookJpa;
import com.ots.springbooks.models.jpa.GenreJpa;
import com.ots.springbooks.models.mongo.AuthorMongo;
import com.ots.springbooks.models.mongo.BookMongo;
import com.ots.springbooks.models.mongo.GenreMongo;
import com.ots.springbooks.repositories.mongo.AuthorMongoRepository;
import com.ots.springbooks.repositories.mongo.BookMongoRepository;
import com.ots.springbooks.repositories.mongo.GenreMongoRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

  private static final int CHUNK_SIZE = 5;
  public static final String JOB_NAME = "migration_job";

  @Autowired private JobBuilderFactory jobBuilderFactory;
  @Autowired private StepBuilderFactory stepBuilderFactory;

  @Autowired private AuthorMongoRepository authorMongoRepository;
  @Autowired private GenreMongoRepository genreMongoRepository;
  @Autowired private BookMongoRepository bookMongoRepository;

  @Autowired private GenreReader genreReader;
  @Autowired private AuthorReader authorReader;
  @Autowired private BookReader bookReader;

  @Autowired private AuthorProcessor authorProcessor;
  @Autowired private GenreProcessor genreProcessor;
  @Autowired private BookProcessor bookProcessor;

  @Bean
  public RepositoryItemWriter<AuthorMongo> writerAuthor() {
    return new RepositoryItemWriterBuilder<AuthorMongo>().repository(authorMongoRepository).build();
  }

  @Bean
  public RepositoryItemWriter<GenreMongo> writerGenre() {
    return new RepositoryItemWriterBuilder<GenreMongo>().repository(genreMongoRepository).build();
  }

  @Bean
  public RepositoryItemWriter<BookMongo> writerBook() {
    return new RepositoryItemWriterBuilder<BookMongo>().repository(bookMongoRepository).build();
  }

  @Bean
  public Step authorMigrationStep(RepositoryItemWriter<AuthorMongo> writer) {
    return stepBuilderFactory
        .get("author_migration_step")
        .<AuthorJpa, AuthorMongo>chunk(CHUNK_SIZE)
        .reader(authorReader)
        .processor(authorProcessor)
        .writer(writer)
        .build();
  }

  @Bean
  public Step genreMigrationStep(RepositoryItemWriter<GenreMongo> writer) {
    return stepBuilderFactory
        .get("genre_migration_step")
        .<GenreJpa, GenreMongo>chunk(CHUNK_SIZE)
        .reader(genreReader)
        .processor(genreProcessor)
        .writer(writer)
        .build();
  }

  @Bean
  public Step bookMigrationStep(RepositoryItemWriter<BookMongo> writer) {
    return stepBuilderFactory
        .get("book_migration_step")
        .<BookJpa, BookMongo>chunk(CHUNK_SIZE)
        .reader(bookReader)
        .processor(bookProcessor)
        .writer(writer)
        .build();
  }

  @Bean
  public Job migrationUserJob(
      Step authorMigrationStep, Step genreMigrationStep, Step bookMigrationStep) {
    return jobBuilderFactory
        .get(JOB_NAME)
        .incrementer(new RunIdIncrementer())
        .flow(authorMigrationStep)
        .next(genreMigrationStep)
        .next(bookMigrationStep)
        .end()
        .build();
  }
}
