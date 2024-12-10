package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardReopositoryTests {

    @Autowired
    // 아무 메소드가 없지만, 기본 탑재된 쿼리 메소드 이용해서, crud  해보기.
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        // 더미 데이터, 앞에서, 병렬 처리 기능 사용하기.
        // stream 클래스 이용하기.
        // 1 ~ 99번까지 생성해요.
        IntStream.range(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("샘플 제목 : " + i)
                    .content("샘플 내용 : " + i)
                    .writer("샘플 작성자 : lsy "+i)
                    .build();
            // crud, insert , save-> 1차 임시 테이블 저장 -> 실제 테이블 반영
            // save ->
            // 예시 sql,    insert
            //    into
            //        board
            //        (content, mod_date, reg_date, title, writer)
            //    values
            //        (?, ?, ?, ?, ?)
            Board result = boardRepository.save(board);
            log.info("추가된 bno 번호 : " + result);
        });
    }

    @Test
    public void testSelectOne() {
        Long bno = 99L;
        //Optional , 있으면, 해당 인스턴스 가져오기, 없으면, null 입니다.
        Optional<Board> result = boardRepository.findById(bno);
        // result 있으면, Board 타입으로 받고, 없으면, 예외 발생시킴.
        Board board= result.orElseThrow();
        log.info("하나 조회 : " + board);

    }

    @Test
    public void testUpdate() {
        Long bno = 99L;
        // 수정 할 데이터가 해당 테이블에 있는지 조회 부터 하기.
        Optional<Board> result = boardRepository.findById(bno);
        // result 있으면, Board 타입으로 받고, 없으면, 예외 발생시킴.
        // board, 엔티티 클래스 인스턴스가, 하나의 데이터베이스의 내용임.
        Board board= result.orElseThrow();
        board.changeTitleConent("변경 제목 ","변경 내용");
        // 실제 디비 테이블 반영.
        // 순서 -> 1차 영속성 컨텍스트(임시 테이블) 적용 -> 실제 테이블 반영.
        // save -> 해당 실제 테이블 없다면, -> insert
        // save -> 해당 실제 테이블 있다면, -> update

        boardRepository.save(board);

    }

    @Test
    public void testDelete() {
        Long bno = 99L;
        boardRepository.deleteById(bno);
    }


}
