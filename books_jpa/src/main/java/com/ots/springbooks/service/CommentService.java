package com.ots.springbooks.service;

public interface CommentService {

  void getAll();

  void getById();

  void getCommentsByBookId();

  void insert();

  void update();

  void delete();
}
