package model.dao;

import util.CustomList;

public interface CRUD_Interface<T> {

    // 추가기능 추상메소드
    boolean post( T dto );

    // 전체조회 추상메소드
    CustomList<T> getAll();

    // 단일조회 추상메소드
    T getOne( int No );

    // 수정기능 추상메소드
    boolean update( T dto );

    // 삭제기능 추상메소드
    boolean delete( T Dto );

} // interface end
