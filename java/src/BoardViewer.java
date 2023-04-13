

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BoardViewer {
    Scanner scanner = new Scanner(System.in);
    List<Review> boardList = new ArrayList<>(); // 게시글 객체를 저장할 List


    public void getBoardList() {
        System.out.println(         "<<후기 게시판>>"       );
        System.out.println("번호          제목            작성자          작성일 ");
        System.out.println("=================================================================");
        if (boardList.isEmpty()) { // 게시글 객체들을 담은 리스트에 아무것도 없는 경우
            System.out.println("                       게시글 없음");
        }else {
            for(Review rv : boardList) { // 글 번호를 1번부터 시작하기 위해 인덱스 +1
                //제목, 작성자 글자가 5자 넘어가면 3항연산자 사용 나머지 부분 ...으로 표시
                System.out.printf("%d |%15s | %10s | %13s\n", boardList.indexOf(rv) + 1,
                        rv.getTitle().length() > 5 ? rv.getTitle().substring(0, 5) + "..." : rv.getTitle(),
                        rv.getWriter().length() > 5 ? rv.getWriter().substring(0, 5) + "..." : rv.getWriter(),
                        rv.getRegistDate());
//                System.out.printf("%d |%15s | %10s | %13s\n" ,boardList.indexOf(rv) + 1, rv.getTitle(),
//                        rv.getWriter(), rv.getRegistDate());
            }
        }
        System.out.println("=================================================================");
        System.out.println("1. 새글작성 2. 상세보기, 3. 삭제, 4. 종료");
    }

    public void boardInsert() {// 개시글 새로쓰기
        Review rv = new Review(); //게시글 객체 생성

        System.out.println("글제목(취소 : quit):"); //제목 입력
        String title = scanner.nextLine();
        if (title.equals("quit")) {
            System.out.println("작성이 취소되었습니다.");
            return;
        }

        System.out.println("작성자(취소 : quit):"); //작성자 입력
        String writer = scanner.nextLine();
        if (writer.equals("quit")) {
            System.out.println("작성이 취소되었습니다.");
            return;
        }

        System.out.println("글내용(취소 : quit):"); // 내용 입력
        String detail = scanner.nextLine();
        if (detail.equals("quit")) {
            System.out.println("작성이 취소되었습니다.");
            return;
        }

        rv.setNum(boardList.indexOf(rv)); // 인덱스를 객체 번호에 저장
        rv.setTitle(title); // 글 제목 저장
        rv.setWriter(writer); // 글 작성자 저장
        rv.setDetail(detail); // 글 내용 저장

        // 현재 날짜 저장
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String registDate = dateFormat.format(new Date());
        rv.setRegistDate(registDate);

        boardList.add(rv); // List에 현재 작성한 게시글 저장
        System.out.println("글이 추가되었습니다.\n");
    }

    public void boardDetail(int select) { // 게시글 상세보기
        if (boardList.isEmpty()) { // boardList가 비어 있다면
            System.out.println("게시글이 존재하지 않습니다.");
            return;
        }
        Review rv = new Review(); // 게시글 객체 생성
        rv = boardList.get(select - 1); // 인덱스 = 글번호 -1. 해당 인덱스의 객체를 가져옴
        System.out.println("No. " + select); // 글 번호가 1부터 시작해서 + 1
        System.out.println("제목 : "+ rv.getTitle());
        System.out.println("작성자 : " + rv.getWriter());
        System.out.println("------------------------------------------------------------------------");
        System.out.println(rv.getDetail());
        System.out.println("------------------------------------------------------------------------");

        if (rv.getReply() != null)
            System.out.println(rv.getReply());

        System.out.println("(1. 댓글 등록 2. 댓글 수정, 3. 댓글 삭제, 4. 목록) :");
        select = Integer.parseInt(scanner.nextLine());
        if (select == 1) { // 현재 글 댓글 작성
            boardAddreply(rv);
        } else if (select == 2) { // 현재 글 댓글 수정
            boardUpdate(boardList.indexOf(rv) + 1);
        } else if (select == 3) { // 현재 글 댓글 삭제
            boardDelete(boardList.indexOf(rv) + 1);
        } else if (select == 4) { // 목록으로 이동
            return;
        }
    }

    public void boardAddreply(Review rv) {
        System.out.println("작성해주세요:");
        String comment = scanner.nextLine();
        rv.setReply(comment);
        System.out.println("추가 완료했습니다.");
    }

    public void boardUpdate(int select) { // 게시글 수정
        if (boardList.isEmpty()) { // BoardList가 비어있는 경우
            System.out.println("게시글이 없습니다.");
            return;
        }

        Review rv = new Review(); // 게시글 객체 생성
        rv = boardList.get(select - 1); // 인덱스 = 글번호 - 1. 해당 인덱스의 객체를 가져옴

        System.out.println("글제목(취소 : quit):"); // 제목 수정
        String title = scanner.nextLine();
        if (title.equals("quit")) {
            System.out.println("수정이 취소되었습니다.");
            return;
        }

        System.out.println("작성자(취소 : quit):"); // 작성자 수정
        String writer = scanner.nextLine();
        if (writer.equals("quit")) {
            System.out.println("수정이 취소되었습니다.");
            return;
        }

        System.out.println("글내용(취소 : quit): "); // 내용 수정
        String detail = scanner.nextLine();
        if (detail.equals("quit")) {
            System.out.println("수정이 취소되었습니다.");
            return;
        }
        // 수정 취소를 하지 않았을 경우 입력한 값을 저장
        rv.setTitle(title);
        rv.setWriter(writer);
        rv.setDetail(detail);

        // 등록한 현재 날짜 저장
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd(수정됨)");
        String registDate = dateFormat.format(new Date());
        rv.setRegistDate(registDate); // 등록 날짜를 수정 날짜로 변경

        boardList.set(boardList.indexOf(rv), rv); // 해당 객체의 인덱스 위치에 rv를 저장
        System.out.println("글이 수정되었습니다.\n");
    }

    public void boardDelete(int select) { // 게시글 삭제
        if(boardList.isEmpty()) {
            System.out.println("게시글이 없습니다.");
            return;
        }

        Review rv = new Review();
        rv = boardList.get(select - 1); // 선택한 번호의 객체
        boardList.remove(boardList.indexOf(rv)); // 해당 인덱스에 있는 객체 삭제
        System.out.println(select + "번 글이 삭제되었습니다.");
    }

    public void txtRead() throws Exception { // 입력 스트림 (메모장 파일 읽기)
        Reader reader = new FileReader("D/Temp/BoardDB.txt"); // 해당 경로를 참조하는 입력 스트림 객체 생성
        while (true) {
            int data = reader.read(); // 데이터를 한 문자씩 읽어옴, 숫자로 저장됨
            if (data == -1) // 데이터가 없는 경우
                break;
            System.out.print((char) data); // 받아온 데이터를 문자로 반환하여 출력
        }
        reader.close(); // 입력 스트림 닫기
        System.out.println("데이터 로드 완료");
    }

    public void txtWrite() throws Exception { // 출력 스트림 (메모장 파일 쓰기)
        Writer writer = new FileWriter("D/Temp/BoardDB.txt"); // 해당 경로를 참조하는 출력 스트림 객체 생성
        String [] data = new String[10000]; // 내보낼 문자열을 저장할 변수,
        for (int i = 0; i < boardList.size(); i++) { // boardList의 객체를 하나씩 불러 data에 해당 값들을 저장
            Review rv = boardList.get(i);
            data[i] = boardList.indexOf(rv) + "|\t" + rv.getTitle() + "|\t" + rv.getDetail() + "|\t" + rv.getWriter()
                    + "|\t" + rv.getRegistDate() + "|\t\t";
            writer.write(data[i]); // 받아온 데이터를 출력
        }

        writer.flush(); // 버퍼에 남은 데이터를 파일로 출력(write) 후 버퍼를 비움
        // 버퍼가 꽉 차기 전에 프로그램이 종료되면 버퍼에 남아있는 내용이
        // 파일로 출력이 되지 않기 떄문에 사용 ( 사용자가 원할 때 사용하면 됨)
        writer.close(); // 출력 스트림 닫기
        System.out.println("현재 데이터 저장완료");
    }

}
