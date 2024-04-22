package com.potenday.anotherme.service;

import com.potenday.anotherme.dto.MBTIDetailsDto;
import com.potenday.anotherme.dto.MBTIDto;
import com.potenday.anotherme.repository.MBTIRepository;
import org.springframework.stereotype.Service;

@Service
public class MBTIService {
    private final MBTIRepository mbtiRepository;

    public MBTIService(MBTIRepository mbtiRepository) {
        this.mbtiRepository = mbtiRepository;
    }

    public MBTIDetailsDto calculateMBTI(MBTIDto mbtiDto) {
        String mbtiType = "";
        int eScore = mbtiDto.getMbtiScores().get("E");
        int iScore = mbtiDto.getMbtiScores().get("I");
        int sScore = mbtiDto.getMbtiScores().get("S");
        int nScore = mbtiDto.getMbtiScores().get("N");
        int tScore = mbtiDto.getMbtiScores().get("T");
        int fScore = mbtiDto.getMbtiScores().get("F");
        int pScore = mbtiDto.getMbtiScores().get("P");
        int jScore = mbtiDto.getMbtiScores().get("J");

        if (eScore > iScore) {
            mbtiType += "E";
        } else {
            mbtiType += "I";
        }

        // S 또는 N 결정
        if (sScore > nScore) {
            mbtiType += "S";
        } else {
            mbtiType += "N";
        }

        // T 또는 F 결정
        if (tScore > fScore) {
            mbtiType += "T";
        } else {
            mbtiType += "F";
        }

        // P 또는 J 결정
        if (pScore > jScore) {
            mbtiType += "P";
        } else {
            mbtiType += "J";
        }

        String[] details = getMBTIDescription(mbtiType);

        return new MBTIDetailsDto(mbtiType, details[0], details[1], details[2]);

    }

    public String getSystemMessageByMBTI(String mbtiType){
        String systeMessage = mbtiRepository.findByType(mbtiType).getSystemMessage();
        return systeMessage;
    }

    public String[] getMBTIDescription(String mbtiType){

        switch (mbtiType) {
            case "ENFJ" :
                return new String[]{"정의로운 싯다르타","함께 더 좋은 세상을 만들어볼까?\n" +
                        "넌 할 수 있어!\n" +
                        "더불어 살아가자!","imageName"};
            case "ENFP" :
                return new String[]{"위대한 개츠비","너는 머리가 있고, 신발이 있어. 너는 네가 원하는 방향으로 스스로를 이끌 수 있어\n" +
                        "우리 함께 춤추자!\n" +
                        "열정! 열정! 열정!\n" +
                        "사람 좋아!!!", "imageName"};
            case "ENTJ" :
                return new String[]{"열정의 아틀라스","나는 무엇이든 할 수 있어!\n" +
                        "관점을 바꿔보자!\n" +
                        "감정은 잠시 넣어둬!", "imageName"};
            case "ENTP" :
                return new String[]{"인생 역행자","놀면서 자유를 얻을 준비가 되었나?\n" +
                        "반대를 말하는걸 두려워하지 않아\n" +
                        "이유가 궁금해\n" +
                        "내 능력은 최고야!\n" +
                        "새로운 것 좋아!\n" +
                        "아이디어 뿜뿜!", "imageName"};
            case "ESFJ" :
                return new String[]{"모든게 가능한 파서블러","매 순간 내 꿈과 조응하는 선택을 한다\n" +
                        "함께하는 건 중요해!\n" +
                        "성실하게 행동해야지!\n" +
                        "가족과 친구는 우선순위야!", "imageName"};
            case "ESFP" :
                return new String[]{"순간을 사랑하는 내비게이션","즉흥적이고 사람이 원동력이야.\n" +
                        "주목받을 준비 되었나?!\n" +
                        "어디든 잘 적응하지!\n" +
                        "인생을 즐겨보자!", "imageName"};
            case "ESTJ" :
                return new String[]{"말 많은 큰 자아","변명을 증오하고 원칙이 중요해\n" +
                        "결정권은 내가 가진다!\n" +
                        "비합리성은 넣어둬!\n" +
                        "계획적으로 행동하자!"};
            case "ESTP" :
                return new String[]{"말랑말랑한 반항아","개방적이고 아이디어가 많아\n" +
                        "강한 사회성을 가졌지!\n" +
                        "유연성도 가졌다구!\n" +
                        "디테일도 챙겨볼까?", "imageName"};
            case "INFJ" :
                return new String[]{"뛰어난 직관의 통찰자","조심스러운 영감러\n" +
                        "너를 더 챙겨봐.\n" +
                        "포기하지 말자", "imageName"};
            case "INFP" :
                return new String[]{"절대 깨지지 않는 유리멘탈","약해보이지만 강한 사람\n" +
                        "내 도움이 필요해?\n" +
                        "네 감정도 중요해!\n" +
                        "섬세하게 행동할게!", "imageName"};
            case "INTJ" :
                return new String[]{"독창적 아이디어뱅크","스스로를 돌아보자!\n" +
                        "객관적인 정보가 맞아?\n" +
                        "소수로 충분해!", "imageName"};
            case "INTP" :
                return new String[]{"자유분방한 논리갑","논리적으로 접근하자!\n" +
                        "유연하게 생각하자!\n" +
                        "내가 선택한 소중한 내 사람!", "imageName"};
            case "ISFJ" :
                return new String[]{"세심한 배려킹","따뜻한 세상을 만들어보자!\n" +
                        "혼자있는 시간도 챙겨야지!\n" +
                        "나는 언제나 열려있어!", "imageName"};
            case "ISFP" :
                return new String[]{"유연함의 힘","유연함으로 쓰는 새로운 성장 공식\n" +
                        "평화로운게 최고야!\n" +
                        "현재를 살자!\n" +
                        "조금 더 섬세해져볼까?", "imageName"};
            case "ISTJ" :
                return new String[]{"내 안에 잠든 거인","운명에서 배우다\n" +
                        "질서 좋아! 계획 좋아!\n" +
                        "규칙과 절차 좋아!\n" +
                        "논리적으로 생각하자!", "imageName"};
            case "ISTP" :
                return new String[]{"현실적인 문제해결자","독립적이고 적응력이 뛰어나!\n" +
                        "자기 주도적이고 즉흥적인 방식으로 상호 작용할거야!\n" +
                        "세세한 부분도 중요해! ", "imageName"};
            default:
                return null;
        }
    }

    public String getQuestion(int no){
        switch (no) {
            case 1:
                return "지나가는 길에 어떤 사람이 울고있다. 이 때 당신의 반응은?";
            case 2:
                return "중요한 일을 맡게된 당신, 이 때 당신의 반응은?";
            case 3:
                return "새롭게 맡은 프로젝트, 당신이 하고싶던 주제이다. 리더를 자처하겠는가?";
            case 4:
                return "휴일이 생긴 당신, 업무를 위해 공부를 한다. 첫번째로 한일은?";
            case 5:
                return "친구에게 차사고가 생겼다. 이때 당신의 반응은?";
            case 6:
                return "당신 앞에 문이있다. 이 문을 열면 뭐가 나올까?";
            case 7:
                return "한번도 안해본 일을 할 기회가 생겼다. 당신의 반응은?";
            case 8:
                return "한 해의 마지막, 어떻게 보낼까?";
            case 9:
                return "할 일이 많은데 새로운 업무가 계속해서 생기는 상황, 당신의 선택은?";
            case 10:
                return "휴일을 즐기는 나만의 방법은?";
            case 11:
                return "소개팅에 나갔는데 상대방이 말이 없다. 어떻게 할까?";
            case 12:
                return "좋아하는 사람의 옆자리를 다른 사람에게 빼앗겼다. 이 때 나는?";
            case 13:
                return "삶이란 무엇일까?";
            case 14:
                return "전쟁이 난다면 어떻게 해야할까?";
            case 15:
                return "나에게 긍정적 허무주의란?";
            case 16:
                return "좀비로 변한 애인이 문 밖에 있다면?";
            case 17:
                return "친구와 약속이 잡혀있는데 친구가 코로나에 걸렸다. 이때 나의 반응은?";
            case 18:
                return "오해가 생겼을 때 내가 더 포커스를 맞추는 쪽은?";
            default:
                return "Not question (1~18)";
        }
    }

    public String[] getAnswer(int no){
        switch (no) {
            case 1:
                return new String[]{"도와주고 싶지만 나서기가 어렵다#I", "길에서 왜저러는지 이해 안간다#?", "나도 모르게 눈물이 난다#F"};
            case 2:
                return new String[]{"주변 사람들의 의견을 묻는다#F", "아이디어를 발휘해 새로운 방식으로 진행한다#N", "준비? 일이 닥칠 때까지 여유를 갖자#P"};
            case 3:
                return new String[]{"절대 그럴 일 없다#I", "내가 아니면 그 누구도 리더할 인물은 없다#E", "원하는 결과를 내도록 상황 주도한다#J"};
            case 4:
                return new String[]{"기존에 정리해왔던 부족한 부분들을 본다#J", "내가 어떤 점이 부족한지 정확히 모르는 상태다#P", "다른 사람들은 어떻게 학습하는지 물어본다#F"};
            case 5:
                return new String[]{"어쩌다 그랬어? 몸은 괜찮아?#?", "보험은 불렀어? 어느쪽 과실이래#T", "많이 무서웠겠다.. 얼마나 놀랬어ㅠㅠ#F"};
            case 6:
                return new String[]{"화장실#S", "밖으로 향하는 문#S", "다른 나라로 이동!#N"};
            case 7:
                return new String[]{"못먹어도 고!#E", "재지않고 재밌어 보이면 해본다.#P", "수익성을 따져보고 움직인다#J"};
            case 8:
                return new String[]{"올해를 되돌아보며 새해 계획을 세운다#J", "내년은 내년의 내가 알아서 행복하게 살거야~#P", "친구들과 연말파티를 한다#E"};
            case 9:
                return new String[]{"눈을 감았다 뜨면 업무가 끝나있는 상상을 한다#N", "하나씩 차근차근 계획을 세워서 끝낸다#J", "미래의 나에게 미룬다#P"};
            case 10:
                return new String[]{"오전부터 오후까지 스케줄대로 휴식한다#J", "혼자 밖에 나가서 여유를 즐기고 온다#I", "혼잔 싫어! 무조건 함께 놀아야지#E"};
            case 11:
                return new String[]{"상대가 먼저 이야기할 때까지 기다린다#I", "내가 먼저 이야기를 건다#E", "어떻게든 되겠지~ 상황이 흘러가는대로 둔다#P"};
            case 12:
                return new String[]{"물 좀 갖다주실래요? 중상모략한다#?", "자리 좀 바꿔주실래요? 요청한다#E", "다음 기회를 노린다#I"};
            case 13:
                return new String[]{"삶은 계란이다#S", "피곤하게 그런 생각을 왜 해?#S", "내 인생의 의미란 무엇일까?#N"};
            case 14:
                return new String[]{"전쟁나면 다 죽는거여...#I", "일단 근처 방공호를 찾아보자!#N", "전쟁이고 뭐고 내일 출근하기 싫다#?"};
            case 15:
                return new String[]{"긍정인데 허무인게 어디있어?#?", "그런 생각을 왜하지#S", "어떤 의미일까 생각해본다#N"};
            case 16:
                return new String[]{"그래도 나를 알아볼지도 몰라! 문을 열어준다#F", "사랑했지만.. 어쩔수 없지. 제거한다#T", "있을 수 없는 일이다#S"};
            case 17:
                return new String[]{"어떡해 ㅠㅠ 너무 아프겠다 ㅜㅜ#?", "자가격리 안해도 되니까 만나도 되려나?#T", "만났다가 옮으면 어떡해 약속을 미룬다#T"};
            case 18:
                return new String[]{"오해가 사실인가 아닌가#T", "내 기분이 나빴는가 안 나빴는가#F", "문제가 생겼다면 일단 해결부터 하자#T"};
            default:
                return null;
        }

    }
}
