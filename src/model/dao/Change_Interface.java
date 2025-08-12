package model.dao;

public interface Change_Interface<T> {

    // 번호 -> 이름 추상메소드
    String ChangeToName( int No );

    // 이름 -> 번호 추상메소드
    int ChangeToNo( String name );

} // interface end
