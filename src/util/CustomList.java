package util;

public class CustomList<E> {
    // 해당 메소드 내에서만 사용할 변수들
    private static final int default_size = 10;     // List의 최초 기본 사이즈
    private E[] list;                               // 최초 배열
    private int size;                               // 현재 저장된 데이터 개수

    /**
     * 생성자
     * @author Jeonghoon
     */
    public CustomList(){
        this.list = (E[]) new Object[default_size]; // 기본 사이즈로 배열을 생성하고
        // new E[]로 직접 생성이 불가하여 Object로 배열 생성 후, 형변환을 진행해야한다.
        this.size = 0;                              // 최초니깐 저장된 데이터 개수 0개
    } // func end

    /**
     * 현재 List의 데이터 개수를 반환하는 메소드
     * @author Jeonghoon
     * @return int
     */
    public int size(){
        return this.size;
    } // func end

    /**
     * 배열을 출력하면 주소값이 반환되므로, 배열을 확인하기 위한 String 변환 메소드
     * @author Jeonghoon
     * @return String
     */
    public String toString(){
        StringBuilder toString = new StringBuilder();
        toString.append("[");                       // 대괄호 열기
        for (int i = 0; i< size; i++){              // list를 순회하면서
            toString.append(list[i]);               // 각각의 데이터를 추가
            if (i < size - 1){                      // 마지막 데이터가 아니면
                toString.append(", ");              // 가독성을 위한 ', ' 추가
            } // if end
        } // for end
        toString.append("]");                       // 대괄호 닫기
        return toString.toString();                 // 최종적으로 toString 반환
    } // func end

    /**
     * [data]를 매개변수로 받아서 리스트에 데이터를 추가하고
     * 저장된 데이터 개수를 1 증가시킨다.
     * @author Jeonghoon
     * @param data 추가될 데이터
     */
    public void add(E data){
        if (size == list.length){                   // 현재 저장된 개수와 배열의 크기가 같아지면
            list = plusSize();                      // 배열의 크기를 증가시켜, 기존 배열에 저장한다.
        } // if end
        list[size] = data;                          // 입력받은 데이터를 list에 추가한다.
        size++;                                     // size를 1 증가시킨다.
    } // func end

    /**
     * [index, data]를 받아 해당하는 데이터를 지정한 인덱스 앞에 추가한다.
     * 위 add(E data)와 오버로딩 관계
     * @author Jeonghoon
     * @param index
     * @param data
     */
    public void add(int index, E data){
        checkIndex(index);                          // index의 유효성 검사를 진행한다.
        if (size == list.length){                   // 현재 저장된 개수와 배열의 크기가 같아지면
            list = plusSize();                      // 배열의 크기를 증가시켜, 기존 배열에 저장한다.
        } // if end
        size++;                                     // size를 1 증가시키고
        E[] new_list = (E[]) new Object[size];      // 새로운 배열 선언
        for (int i = 0; i < size; i++){             // 모든 배열을 순회하면서
            if (i < index){                         // i가 index보다 작으면
                new_list[i] = list[i];              // 그대로 복사하고
            } else if (i > index){                  // i가 index보다 크면
                new_list[i] = list[i - 1];          // i-1을 복사하고
            } else {                                // i가 index와 같으면
                new_list[i] = data;                 // 입력받은 값을 저장한다.
            } // if end
        } // for end
        list = new_list;                            // list에 추가된 배열 저장
    } // func end

    /**
     * 최초 기본 사이즈보다 많은 데이터가 입력되었을 때
     * 더 큰 배열을 만들고, 기존 배열을 추가한다.
     * 해당 클래스 내에서만 사용할 메소드이므로 private로 선언한다.
     * @author Jeonghoon
     */
    private E[] plusSize(){
        int new_size = list.length + 10;            // 기존 배열 크기 + 10
        E[] new_list = (E[]) new Object[new_size];  // 새로운 배열 선언
        for (int i = 0; i< size; i++){              // System.arraycopy로 대체 가능
            // System.arraycopy(원본배열, 원본시작위치, 새로운배열, 새로운시작위치, 복사할개수)
            new_list[i] = list[i];                  // 기존 배열을 새로운 배열에 추가한다.
        } // for end
        return new_list;                            // 새롭게 생성된 배열을 반환한다.
    } // func end

    /**
     * [index]를 받아서 해당하는 데이터를 반환한다.
     * @author Jeonghoon
     * @param index
     * @return E 해당 리스트의 타입
     */
    public E get(int index){
        checkIndex(index);                          // index의 유효성 검사를 진행한다.
        return list[index];                         // 해당하는 리스트의 인덱스 값을 반환한다.
    } // func end

    /**
     * @author Jeonghoon
     * @param index
     */
    public void remove(int index){
        checkIndex(index);                          // index의 유효성 검사를 진행한다.
        E[] new_list = (E[]) new Object[size];      // 새로운 배열 선언
        for (int i = 0; i < size; i++){             // 모든 배열을 순회하면서
            if (i < index){                         // i가 index보다 작으면
                new_list[i] = list[i];              // 그대로 복사하고
            } else if (i > index){                  // i가 index보다 크면
                new_list[i -1] = list[i];           // 우측에 있는 데이터를 하나씩 땡기기
            } // if end                             // index의 데이터는 복사 X
        } // for end
        size--;                                     // 데이터를 삭제했으므로, size 1 감소
        list = new_list;                            // list에 삭제된 배열 저장
    } // func end

    /**
     * [index, data]를 받아 해당하는 인덱스 값을 data로 수정한다.
     * @author Jeonghoon
     * @param index, data
     */
    public void set(int index, E data){
        checkIndex(index);                          // index의 유효성 검사를 진행한다.
        list[index] = data;                         // index의 값을 data로 변경한다.
    } // func end

    /**
     * 해당 리스트의 모든 값을 삭제한다.
     * @author Jeonghoon
     */
    public void clear(){
        list = (E[]) new Object[default_size];      // 새로운 배열을 만들어 저장한다.
        size = 0;                                   // 데이터가 모두 삭제되었으므로, size = 0
    } // func end

    /**
     * 해당 리스트가 비어있는지 확인하여 boolean으로 반환한다.
     * @author Jeonghoon
     * @return boolean
     */
    public boolean isEmpty(){
        return size == 0;                           // size == 0과 동일한 구조
    } // func end

    /**
     * [data]를 받아 해당하는 값이 리스트에 있는지 확인하여 boolean으로 반환한다.
     * @author Jeonghoon
     * @param data
     * @return boolean
     */
    public boolean contains(E data){
        for (E e : list){                           // 배열을 모두 순회하면서
            if(e.equals(data)) return true;         // 값이 같은게 있다면, true 반환
        } // for end
        return false;                               // 모두 순회하고도 없으면, false 반환
    } // func end

    /**
     * [data]를 받아 해당하는 값의 인덱스를 반환한다. 값이 없으면 -1을 반환한다.
     * @author Jeonghoon
     * @param data
     * @return int
     */
    public int indexOf(E data){
        for (int i = 0; i < size; i++){             // 배열을 모두 순회하면서
            if(list[i].equals(data)) return i;      // 값이 같다면, 해당하는 인덱스 반환
        } // for end
        return -1;                                  // 모두 순회하고도 없으면, -1 반환
    } // func end

    /**
     * [index]를 받아 해당하는 인덱스의 범위 유효성을 검사한다.
     * 해당 클래스 내에서만 사용할 메소드이므로 private로 선언한다.
     * @author Jeonghoon
     * @param index
     */
    private void checkIndex(int index){
        // 입력받은 인덱스의 범위 유효성 검사 : throw로 예외 실행
        if (index < 0 || index >= size){            // 0보다 작거나 데이터 개수보다 크면
            // 왜 size 이상임? index는 0부터 시작이기에 데이터가 10개여도 index는 9개까지 존재하므로
            // size가 10이고 index가 10이면, 배열 범위를 벗어나게 됨
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size + ", Index가 범위를 벗어났습니다.");
        } // if end
    } // func end
} // class end