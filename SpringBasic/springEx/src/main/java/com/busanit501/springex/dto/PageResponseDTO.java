package com.busanit501.springex.dto;

import java.util.List;

// <E> 제너릭으로, Element, 해당 타입을 유연하게 설정.
// PageResponseDTO 응답을 하는 건, 페이징 처리
// 페이징 처리 도메인(예시, Todo, Member, Reply, Product 등.)
public class PageResponseDTO<E> {
    // 서버 -> 웹 , 페이징 준비물 전달.
    // 준비물
    // 1) 보여줄 todo 게시물 10개
    // 2) 전체 갯수 ,
    // 3) 현재 페이지 번호 page, PageRequestDTO
    // 4) 시작 페이지 번호(start) , 끝나는 페이지 번호(end)
    // 스프링 부트 -> Pagination 등을 이용해서 조금더 편하게 구현할 예정.
    private int page;
    private int size; // 페이징당 보여줄 갯수
    private int total;

    private int start;
    private int end;

    // 이전페이지 존재여부
    private boolean prev;
    // 다음페이지 존재여부
    private boolean next;

    // 웹에 전달할 페이징 처리된 게시글 10개, 임의로
    private List<E> dtoList;



}
