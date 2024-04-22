package com.potenday.anotherme.service;

import com.potenday.anotherme.dto.ChatRecommendDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ChatService {

    public ChatRecommendDto generateMessages() {
        int n = 23;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        // 리스트를 섞고, 앞에서 2개의 숫자 선택
        Collections.shuffle(numbers);
        List<Integer> selectedNumbers = numbers.subList(0, 2);

        String m1 = getMessage(selectedNumbers.get(0));
        String m2 = getMessage(selectedNumbers.get(1));

        return ChatRecommendDto.builder().recommend1(m1).recommend2(m2).build();
    }


    public String getMessage(int i){
        switch(i){
            case 1:
                return "최근에 헤어진 전 애인한테 너가 아직 미련이 남았다면 어떻게 해야할 것 같아?";
            case 2:
                return "중요한 작업을 하고 지친 상태로 귀가하는 길에 친한 친구가 잠깐 만나자고 했어. 뭐라고 하면서 거절할거야?";
            case 3:
                return "과거에 실수한 것들이 계속 생각나. 어떻게 하면 해결할 수 있을까?";
            case 4:
                return "기분이 별로야. 기분전환 방법을 알려줘";
            case 5:
                return "마음에 드는 사람이 있는데 내일 먼저 데이트 신청할까 하거든. 뭐라고 물어봐야 좋을까?";
            case 6:
                return "내일은 소개팅날이야. 뭘 먼저 물어보는게 좋을까? 대화 주제를 추천해줘!";
            case 7:
                return "친구가 원하는 답을 정해놓고 나한테 물어볼 때 너무 답답해. 어떻게 말하면 좋을까?";
            case 8:
                return "매일 늦는 친구, 오늘은 한마디 꼭 해야겠어. 뭐라고 하지?";
            case 9:
                return "상사한테 혼났어. 내일 어떻게 상사를 대하는 게 좋을까?";
            case 10:
                return "더 다양한 사람을 만날 수 있는 새로운 취미를 갖고 싶어. 어떤 게 좋을까?";
            case 11:
                return "썸 상대방이 답장이 너무 느린데 나한테 관심이 없는걸까?";
            case 12:
                return "안 친한 친구 결혼식에 초대받았어. 축하는 해줘야 하는데, 어떻게 하면 좋을까? ";
            case 13:
                return "내일 애인의 생일인데 어제 싸웠어. 어떻게 화해하면서 축하해 줄 수 있을까?";
            case 14:
                return "운동하러 가기 싫은데 그래도 가야될 때, 어떻게 동기부여를 할 수 있을까?";
            case 15:
                return "인생이 너무 지루해. 어떻게 에너지를 충전할까?";
            case 16:
                return "좀 더 멋진 내가 되고 싶어. 어떻게 해야할까?";
            case 17:
                return "최고의 휴가 계획을 제안해줘!";
            case 18:
                return "친구가 애인이랑 헤어져서 우울해해. 어떻게 위로해줄까?";
            case 19:
                return "친구랑 싸웠는데 이대로 연을 끝내고 싶지 않아. 어떻게 해야할까?";
            case 20:
                return "친해지고 싶은 사람이 생겼어. 뭐라고 말을 걸어야 친구가 될 수 있을까?";
            case 21:
                return "피곤해서 집에 일찍 들어가고 싶은데 갑자기 잡힌 회식. 어떻게 빠져 나갈 수 있을까??";
            case 22:
                return "할 일이 너무 많아 번아웃이 올 것 같아. 어떻게 해야 하는 걸까?";
            case 23:
                return "이번 휴일에 약속이 없어. 뭐하면서 보내면 좋을지 추천해 줘";
            default:
                return "잘못된 값";
        }
    }
}
